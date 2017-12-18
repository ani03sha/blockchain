package org.anirudh.redquark.blockchain;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedquarkBlockchainApplication {

	public static void main(String[] args) {

		if (System.getProperty("blockchain.node.id") == null) {
			System.setProperty("blockchain.node.id", UUID.randomUUID().toString().replace("-", ""));
		}

		SpringApplication.run(RedquarkBlockchainApplication.class, args);
	}
}
