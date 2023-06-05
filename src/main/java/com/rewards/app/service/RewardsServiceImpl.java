package com.rewards.app.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewards.app.constants.Constants;
import com.rewards.app.entity.Transaction;
import com.rewards.app.model.Rewards;
import com.rewards.app.repo.TransactionRepo;

@Service
public class RewardsServiceImpl implements RewardsService {

	
	@Autowired
	TransactionRepo transactionRepository;

	public Rewards getRewardsByCustomerId(Long customerId) {

		Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(Constants.daysInMonth);
		Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2*Constants.daysInMonth);
		Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3*Constants.daysInMonth);

		List<Transaction> lastMonthTransactions = transactionRepository.findAllByCustomerIdAndTransactionDateBetween(
				customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
		List<Transaction> lastSecondMonthTransactions = transactionRepository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
		List<Transaction> lastThirdMonthTransactions = transactionRepository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
						lastSecondMonthTimestamp);

		Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
		Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
		Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

		Rewards customerRewards = new Rewards();
		customerRewards.setCustomerId(customerId);
		customerRewards.setLastMonthRewardPoints(lastMonthRewardPoints);
		customerRewards.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
		customerRewards.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
		customerRewards.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

		return customerRewards;

	}

	private Long getRewardsPerMonth(List<Transaction> transactions) {
		return transactions.stream().map(transaction -> calculateRewards(transaction))
				.collect(Collectors.summingLong(r -> r.longValue()));
	}

	private Long calculateRewards(Transaction t) {
		if (t.getTransactionAmount() > Constants.firstRewardCap && t.getTransactionAmount() <= Constants.secondRewardCap) {
			return Math.round(t.getTransactionAmount() - Constants.firstRewardCap);
		} else if (t.getTransactionAmount() > Constants.secondRewardCap) {
			return Math.round(t.getTransactionAmount() - Constants.secondRewardCap) * 2
					+ (Constants.secondRewardCap - Constants.firstRewardCap);
		} else
			return 0l;

	}

	public Timestamp getDateBasedOnOffSetDays(int days) {
		return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
	}

}
