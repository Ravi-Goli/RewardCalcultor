package com.rewards.app.service;

import com.rewards.app.entity.Customer;


public interface CustomerService {
	
	 public Customer findByCustomerId(Long customerId);

}
