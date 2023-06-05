package com.rewards.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rewards.app.entity.Customer;
import com.rewards.app.model.Rewards;
import com.rewards.app.service.CustomerService;
import com.rewards.app.service.RewardsService;

@RunWith(SpringJUnit4ClassRunner.class)

class RewardsControllerTest {

	RewardsController controller;

	RewardsService rewardsService;

	CustomerService customerService;

	@BeforeEach
	void setUp() throws Exception {
		rewardsService = mock(RewardsService.class);
		customerService = mock(CustomerService.class);
		controller = new RewardsController(rewardsService, customerService);

	}

	@Test
	void getRewardsByCustomerIdTest() {
		Customer cu = new Customer();
		cu.setCustomerId(123L);
		cu.setCustomerName("ABCD");

		when(customerService.findByCustomerId(anyLong())).thenReturn(cu);

		Rewards re = new Rewards();
		re.setCustomerId(123L);
		re.setTotalRewards(1000);
		when(rewardsService.getRewardsByCustomerId(anyLong())).thenReturn(re);
		ResponseEntity<Rewards> responseEntity = controller.getRewardsByCustomerId(1L);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

	@Test
	void getRewardsByCustomerIdTestWithNullCustomerData() {	

		when(customerService.findByCustomerId(anyLong())).thenReturn(null);

		try{
			controller.getRewardsByCustomerId(1L);
		}catch(Exception e) {
			assertEquals(RewardsController.ERROR_TEXT, e.getMessage());
			
		}

	}

}
