/**
 * 
 */
package org.anirudh.redquark.blockchain.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.anirudh.redquark.blockchain.domain.BlockChain;
import org.anirudh.redquark.blockchain.dto.BlockChainResponseDTO;
import org.anirudh.redquark.blockchain.model.Block;
import org.anirudh.redquark.blockchain.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anirudh Sharma
 *
 */

@RestController
public class BlockChainController {

	@Autowired
	private BlockChain blockChain;

	@Value("${blockchain.node.id}")
	private String blockChainNodeId;

	@RequestMapping(value = "/transaction", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, String> createTransaction(@RequestBody Transaction transaction){
		long index = blockChain.createTransaction(transaction);

		return Collections.singletonMap("message", String.format("Transaction will be added to the block {%d}", index));
	}

	@RequestMapping("/mine")
	public Map<String, Object> mine() {
		Block lastBlock = blockChain.getLastBlock();
		long lastProof = lastBlock.getProof();

		long proof = blockChain.proofOfWork(lastProof);

		blockChain.createTransaction(new Transaction("0", blockChainNodeId, BigDecimal.valueOf(1)));

		Block block = blockChain.createBlock(new Block(proof, null));

		Map<String, Object> response = new HashMap<>();

		response.put("message", "New Block Forged");
		response.put("index", block.getIndex());
		response.put("transactions", block.getTransactions());
		response.put("proof", block.getProof());
		response.put("previous_hash", block.getPreviousHash());

		return response;
	}

	@RequestMapping("chain")
	public BlockChainResponseDTO chain() {
		return new BlockChainResponseDTO(blockChain.getChain());
	}

}
