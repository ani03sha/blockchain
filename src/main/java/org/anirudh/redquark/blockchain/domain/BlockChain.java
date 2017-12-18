/**
 * 
 */
package org.anirudh.redquark.blockchain.domain;

import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.anirudh.redquark.blockchain.dto.BlockChainResponseDTO;
import org.anirudh.redquark.blockchain.hash.HashGenerator;
import org.anirudh.redquark.blockchain.model.Block;
import org.anirudh.redquark.blockchain.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.google.common.io.*;

/**
 * @author Anirudh Sharma
 *
 */

@Component
public class BlockChain implements Serializable {

	/**
	 * Generated serialVersionUID - required as the objects need to be serialized
	 */
	private static final long serialVersionUID = 1543474716745990767L;

	/**
	 * Hashing object
	 */
	private HashGenerator hashGenerator;

	/**
	 * List of current transactions
	 */
	private List<Transaction> currentTransactions;

	/**
	 * Instance of one block
	 */
	private List<Block> chain;

	/**
	 * Set of nodes - represents various devices around the globe connected to the
	 * internet
	 */
	private Set<URL> nodes;

	/**
	 * RestTemplate
	 */
	private RestTemplate restTemplate;

	/**
	 * 
	 * @param hashGenerator
	 * 
	 *            Initializing all the parameters
	 */
	@Autowired
	public BlockChain(HashGenerator hashGenerator) {
		this.currentTransactions = Collections.synchronizedList(new LinkedList<>());
		this.chain = Collections.synchronizedList(new LinkedList<>());
		this.nodes = Collections.synchronizedSet(new HashSet<>());
		this.hashGenerator = hashGenerator;
		this.restTemplate = new RestTemplate();

		createBlock(new Block(100, "1"));
	}

	/**
	 * @param block
	 *            Creation of a block
	 * @return block
	 */
	public Block createBlock(Block block) {
		block.setIndex(chain.size() + 1);
		block.setTimestamp(System.currentTimeMillis());
		block.setTransactions(currentTransactions);

		// check if the previous hash is null
		if (block.getPreviousHash() == null) {
			block.setPreviousHash(hashGenerator.hashBlock(block));
		}

		this.currentTransactions = Collections.synchronizedList(new LinkedList<>());

		// Adding block to the existing chain
		this.chain.add(block);
		return block;
	}

	/**
	 * 
	 * @return Block
	 * 
	 *         Getting the current block
	 */
	public Block getLastBlock() {
		return this.chain.get(this.chain.size() - 1);
	}

	/**
	 * 
	 * @param transaction
	 * @return Transaction index
	 */
	public long createTransaction(Transaction transaction) {
		this.currentTransactions.add(transaction);
		return this.chain.get(this.chain.size() - 1).getIndex() + 1;
	}

	/**
	 * 
	 * @param lastProof
	 * @return proof
	 * 
	 *         Performing proof of work
	 */
	public long proofOfWork(long lastProof) {
		long proof = 0;

		while (!validProof(lastProof, proof)) {
			proof++;
		}
		return proof;
	}

	/**
	 * 
	 * @param lastProof
	 * @param proof
	 * @return isProofValid
	 */
	public boolean validProof(long lastProof, long proof) {

		String guess = BaseEncoding.base64()
				.encode(String.format("{%d}{%d}", lastProof, proof).getBytes(Charset.forName("UTF8")));

		String guessHash = hashGenerator.hash(guess);

		return guessHash.substring(0, 4).equals("0000");
	}

	/**
	 * @return current chain or list of blocks
	 */
	public List<Block> getChain() {
		return Collections.unmodifiableList(this.chain);
	}

	/**
	 * @param url
	 * 
	 *            Registering a node
	 */
	public void registerNode(URL url) {
		this.nodes.add(url);
	}

	/**
	 * 
	 * @param chain
	 * @return isValidChain
	 * 
	 *         Checks if the current chain is valid or not
	 */
	public boolean validChain(List<Block> chain) {
		Block lastBlock = chain.get(0);
		Block block = null;
		int currentIndex = 1;

		while (currentIndex < chain.size()) {
			block = chain.get(currentIndex);

			if (!block.getPreviousHash().equals(hashGenerator.hashBlock(lastBlock))) {
				return false;
			}

			if (!validProof(lastBlock.getProof(), block.getProof())) {
				return false;
			}

			currentIndex++;
		}
		return true;
	}

	/**
	 * 
	 * @return isConflict
	 * 
	 *         Resolves conflicts between the nodes on the internet
	 */
	public boolean resolvedConficts() {
		Set<URL> neighbours = this.nodes;
		List<Block> newChain = null;

		int maxLength = chain.size();

		for (URL node : neighbours) {

			BlockChainResponseDTO dto = restTemplate.getForObject(node.toString() + "/chain",
					BlockChainResponseDTO.class);

			int length = dto.getLength();
			List<Block> chain = this.getChain();

			if (length > maxLength && validChain(chain)) {
				maxLength = length;
				newChain = chain;
			}
		}

		if (newChain != null) {
			this.chain = newChain;
			return true;
		}
		return false;
	}

	public Set<URL> getNodes() {
		return Collections.unmodifiableSet(this.nodes);
	}
}
