package com.rewards.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.app.entity.Customer;
import com.rewards.app.model.Rewards;
import com.rewards.app.service.CustomerService;
import com.rewards.app.service.RewardsService;

@RestController
@RequestMapping("/customers")
public class RewardsController {
	
	public static final String ERROR_TEXT = "Invalid / Missing customer Id";

	RewardsService rewardsService;

	CustomerService customerService;

	public RewardsController(RewardsService rewardsService, CustomerService customerService) {
		this.rewardsService = rewardsService;
		this.customerService = customerService;

	}

	@GetMapping(value = "/{customerId}/rewards", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("customerId") Long customerId) {
		Customer customer = customerService.findByCustomerId(customerId);
		if (customer == null) {
			throw new RuntimeException(ERROR_TEXT);
		}
		Rewards customerRewards = rewardsService.getRewardsByCustomerId(customerId);
		return new ResponseEntity<>(customerRewards, HttpStatus.OK);
	}

}
