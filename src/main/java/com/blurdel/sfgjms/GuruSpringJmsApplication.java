package com.blurdel.sfgjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuruSpringJmsApplication {

	public static void main(String[] args) throws Exception {

		// Setup server - straight from docs
//		ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
//				.setPersistenceEnabled(false)
//				.setJournalDirectory("target/data/journal")
//				.setSecurityEnabled(false)
//				.addAcceptorConfiguration("invm", "vm://0"));
//
//		server.start();

		SpringApplication.run(GuruSpringJmsApplication.class, args);
	}

}
