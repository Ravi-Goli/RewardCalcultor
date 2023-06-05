package com.rewards.app.repo;

import org.springframework.data.repository.CrudRepository;

import com.rewards.app.entity.Customer;

public interface CustomerRepo extends CrudRepository<Customer,Long> {
    public Customer findByCustomerId(Long customerId);
}