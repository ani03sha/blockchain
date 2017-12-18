/**
 * 
 */
package org.anirudh.redquark.blockchain.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Anirudh Sharma
 *
 */
public class Transaction implements Serializable {

	/**
	 * Generated serialVersionUID - required as the objects need to be serialized
	 */
	private static final long serialVersionUID = -5999453026871972159L;
	
	/**
	 * Sender of the transaction - the one who initiates it
	 */
	private String sender;
	
	/**
	 * Recipient of the transaction
	 */
	private String recipient;
	
	/**
	 * Amount that is sent
	 */
	private BigDecimal amount;
	
	public Transaction () {
		
	}

	/**
	 * @param sender
	 * @param recipient
	 * @param amount
	 */
	public Transaction(String sender, String recipient, BigDecimal amount) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.amount = amount;
	}

	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * @return the recipient
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * @param recipient the recipient to set
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
