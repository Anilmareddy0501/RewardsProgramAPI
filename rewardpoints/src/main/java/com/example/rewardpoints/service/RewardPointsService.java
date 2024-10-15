
package com.example.rewardpoints.service;

import com.example.rewardpoints.model.RewardPointsSummary;
import com.example.rewardpoints.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardPointsService {

    private static final Logger logger = LoggerFactory.getLogger(RewardPointsService.class);
    private List<Transaction> transactions = new ArrayList<>();


    @PostConstruct
    public void init() {
        logger.info("Initializing RewardPointsService with sample transactions.");
        transactions.add(new Transaction("Jack", LocalDate.of(2024, 1, 15), 120));
        transactions.add(new Transaction("Becky", LocalDate.of(2024, 2, 17), 75));
        transactions.add(new Transaction("Jack", LocalDate.of(2024, 2, 10), 150));
        transactions.add(new Transaction("Becky", LocalDate.of(2024, 3, 15), 95));
    }


    public void addTransaction(Transaction transaction) {
        logger.info("Adding transaction for customer: {}", transaction.getCustomerId());
        transactions.add(transaction);
    }


    public List<Transaction> getTransactionsWithinDateRange(String customerId, LocalDate startDate, LocalDate endDate) {
        logger.info("Fetching transactions for customer {} between {} and {}", customerId, startDate, endDate);
        return transactions.stream()
                .filter(t -> t.getCustomerId().equalsIgnoreCase(customerId))
                .filter(t -> (t.getDate().isAfter(startDate) || t.getDate().isEqual(startDate)) &&
                        (t.getDate().isBefore(endDate) || t.getDate().isEqual(endDate)))
                .collect(Collectors.toList());
    }

    public RewardPointsSummary calculateRewardsForDateRange(String customerId, LocalDate startDate, LocalDate endDate) {
        logger.info("Calculating rewards for customer {} between {} and {}", customerId, startDate, endDate);
        List<Transaction> filteredTransactions = getTransactionsWithinDateRange(customerId, startDate, endDate);
        int totalPoints = filteredTransactions.stream().mapToInt(Transaction::getPoints).sum();

        RewardPointsSummary summary = new RewardPointsSummary();
        summary.setCustomerId(customerId);
        summary.setTotalPoints(totalPoints);
        summary.setTransactions(filteredTransactions);

        return summary;
    }


    public List<RewardPointsSummary> calculateRewards() {
        logger.info("Calculating rewards for all customers.");

        // Group transactions by customer
        Map<String, List<Transaction>> transactionsByCustomer = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerId));

        List<RewardPointsSummary> summaries = new ArrayList<>();

        // Calculate rewards for each customer
        for (Map.Entry<String, List<Transaction>> entry : transactionsByCustomer.entrySet()) {
            String customerId = entry.getKey();
            List<Transaction> customerTransactions = entry.getValue();

            // Summing up all reward points for the customer
            int totalPoints = customerTransactions.stream()
                    .mapToInt(Transaction::getPoints)
                    .sum();

            // Create a summary for each customer
            RewardPointsSummary summary = new RewardPointsSummary();
            summary.setCustomerId(customerId);
            summary.setTotalPoints(totalPoints);
            summary.setTransactions(customerTransactions);

            // Add the summary to the list
            summaries.add(summary);
        }

        return summaries;
    }


    public List<Transaction> getTransactions() {
        logger.info("Fetching all transactions.");
        return transactions;
    }
}
