package com.rewards.app.service;

import com.rewards.app.model.Rewards;



public interface RewardsService {
    public Rewards getRewardsByCustomerId(Long customerId);
   
}

