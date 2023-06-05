package com.rewards.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewards.app.entity.Customer;
import com.rewards.app.repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	CustomerRepo customerRepo;

	CustomerServiceImpl(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}

	@Override
	public Customer findByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return customerRepo.findByCustomerId(customerId);
	}

}
