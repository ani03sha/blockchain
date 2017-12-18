/**
 * 
 */
package org.anirudh.redquark.blockchain.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Anirudh Sharma
 * 
 * Model class to represent one block of data
 *
 */
public class Block implements Serializable {

	/**
	 * Generated serialVersionUID - required as the objects need to be serialized
	 */
	private static final long serialVersionUID = -6215607856457466392L;

	/**
	 *  Index of the block
	 */
	private long index;
	
	/**
	 * Time stamp when the block is generated
	 */
	private long timeStamp;
	
	/**
	 * List of transactions as one block can contain multiple transactions
	 */
	private List<Transaction> transactions;
	
	/**
	 * Proof of the block
	 */
	private long proof;
	
	/**
	 * Hash of the block previous to current one
	 */
	private String previousHash;
	
	public Block(long proof, String previousHash) {
		this.proof = proof;
		this.previousHash = previousHash;
	}

	/**
	 * @return the index
	 */
	public long getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(long index) {
		this.index = index;
	}

	/**
	 * @return the timeStamp
	 */
	public long getTimestamp() {
		return timeStamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timeStamp = timestamp;
	}

	/**
	 * @return the transactions
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	/**
	 * @return the proof
	 */
	public long getProof() {
		return proof;
	}

	/**
	 * @param proof the proof to set
	 */
	public void setProof(long proof) {
		this.proof = proof;
	}

	/**
	 * @return the previousHash
	 */
	public String getPreviousHash() {
		return previousHash;
	}

	/**
	 * @param previousHash the previousHash to set
	 */
	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}
	
	
}
