/**
 * 
 */
package org.anirudh.redquark.blockchain.hash;

import java.nio.charset.StandardCharsets;

import org.anirudh.redquark.blockchain.model.Block;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;

/**
 * @author Anirudh Sharma
 *
 * This class generates the Hash for a block
 */

@Component
public class HashGenerator {

	/**
	 * Instance of ObjectMapper
	 */
	private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * @param block
	 * @return hash
	 * 
	 * This method extracts the hash from the Block
	 */
	public String hashBlock(Block block) {
		try {
			return hash(objectMapper.writeValueAsString(block));
		}catch(JsonProcessingException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param content
	 * @return hash
	 * 
	 * This method generates the Hash
	 */
	public String hash(String content) {
		String hash = Hashing
				      .sha256()
				      .hashString(content, StandardCharsets.UTF_8)
				      .toString();
		
		return hash;
	}
}
