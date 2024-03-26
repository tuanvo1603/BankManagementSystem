package com.example.bank;

import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionServiceApplicationTests {

	private final String baseUrl = "http://localhost:8080/v1/transaction/api";


	@Test
	public void testGetAllTransaction() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/get-all-transaction", String.class);
		if (response.getStatusCode().is2xxSuccessful()) {
			return;
		} else {
			return; // You can handle the error according to your requirements
		}
	}

}
