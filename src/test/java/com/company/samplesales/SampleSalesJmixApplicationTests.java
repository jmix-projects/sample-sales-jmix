package com.company.samplesales;

import com.company.samplesales.entity.Customer;
import io.jmix.core.DataManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SampleSalesJmixApplicationTests {

	@Autowired
	private DataManager dataManager;

	@Test
	void contextLoad() {

		Customer customer = dataManager.create(Customer.class);
		customer.setName("Test customer");
		dataManager.save(customer);

		Customer loaded = dataManager.load(Customer.class)
				.id(customer.getId())
				.one();

		assert loaded != null;
		assert customer.getName().equals(loaded.getName());

		dataManager.remove(loaded);

	}

}
