/**
 * 
 */
package org.anirudh.redquark.blockchain.dto;

import java.io.Serializable;
import java.util.List;

import org.anirudh.redquark.blockchain.model.Block;

/**
 * @author Anirudh Sharma
 *
 */
public class BlockChainResponseDTO implements Serializable{

	/**
	 * Generated serialVersionUID - required as the objects need to be serialized
	 */
	private static final long serialVersionUID = -2528338962927956104L;

	/**
	 * List of the blocks
	 */
	private List<Block> chain;
	
	/**
	 * Length of block
	 */
	private int length;
	
	/**
	 * Default constructor
	 */
	public BlockChainResponseDTO() {
		
	}
	
	/**
	 * Parameterized constructor
	 */
	public BlockChainResponseDTO(List<Block> chain) {
		this.chain = chain;
		this.length = chain.size();
	}

	/**
	 * @return the chain
	 */
	public List<Block> getChain() {
		return chain;
	}

	/**
	 * @param chain the chain to set
	 */
	public void setChain(List<Block> chain) {
		this.chain = chain;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	
}
