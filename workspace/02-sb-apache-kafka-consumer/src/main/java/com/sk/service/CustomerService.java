package com.sk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sk.model.Customer;
import com.sk.model.CustomerNeo4JEntity;
import com.sk.repository.CustomerRepository;
import com.sk.util.KafkaConstants;

/**
 * This class is used to perform business operation
 * 
 * @author Lukman
 *
 */

@Service("customerService")
public class CustomerService {

	private final CustomerRepository repo;

	@Autowired
	public CustomerService(CustomerRepository repo) {
		this.repo = repo;
	}

	/**
	 * This method is used to consumer messages from kafka topic
	 * 
	 * @param c
	 * @return
	 */
	@KafkaListener(topics = KafkaConstants.TOPIC, groupId = KafkaConstants.GROUP_ID)
	public Customer listener(Customer c) {
		System.out.println("***Msg recieved from Kafka Topic ::" + c);

		String s = insertCustomer(c);
		System.out.println(s);

		System.out.println(s);
		return c;
	}

	public String insertCustomer(Customer customer) {
		CustomerNeo4JEntity neoEntity = new CustomerNeo4JEntity();
		neoEntity.setId(customer.getCustomerId());
		neoEntity.setCustomerName(customer.getCustomerName());
		neoEntity.setCustomerEmail(customer.getCustomerEmail());

		repo.save(neoEntity);
		return "Customer inserted successfully to Neo4J DB...!";
	}

}
