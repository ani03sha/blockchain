/**
 * 
 */
package org.anirudh.redquark.blockchain.repository;

import org.anirudh.redquark.blockchain.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author anirshar
 *
 */
public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
