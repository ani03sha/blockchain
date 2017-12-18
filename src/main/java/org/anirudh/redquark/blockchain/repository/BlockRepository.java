/**
 * 
 */
package org.anirudh.redquark.blockchain.repository;

import org.anirudh.redquark.blockchain.model.Block;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Anirudh Sharma
 *
 */
public interface BlockRepository extends MongoRepository<Block, String> {

}
