package com.rewards.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rewards.app.entity.Customer;
import com.rewards.app.repo.CustomerRepo;

class CustomerServiceImplTest {

	CustomerServiceImpl customerServiceImpl;

	CustomerRepo customerRepo;

	@BeforeEach
	void setUp() throws Exception {
		customerRepo = mock(CustomerRepo.class);
		customerServiceImpl = new CustomerServiceImpl(customerRepo);

	}

	@Test
	void findByCustomerIdTest() {
		Customer cu = new Customer();
		cu.setCustomerId(123L);
		cu.setCustomerName("ABCD");
		when(customerRepo.findByCustomerId(anyLong())).thenReturn(cu);
		Customer c = customerServiceImpl.findByCustomerId(123L);

		assertEquals(cu.getCustomerId(), c.getCustomerId());
	}

}
