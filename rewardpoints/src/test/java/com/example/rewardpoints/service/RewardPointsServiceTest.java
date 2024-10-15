package com.example.rewardpoints.service;

import com.example.rewardpoints.model.RewardPointsSummary;
import com.example.rewardpoints.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RewardPointsServiceTest {

    private RewardPointsService rewardPointsService;

    @BeforeEach
    public void setUp() {
        rewardPointsService = new RewardPointsService();
        rewardPointsService.init(); // Initialize the service with sample data
    }

    @Test
    public void testCalculateRewardsForDateRange() {
        String customerId = "Jack";
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        RewardPointsSummary summary = rewardPointsService.calculateRewardsForDateRange(customerId, startDate, endDate);
        assertEquals(customerId, summary.getCustomerId());
        assertTrue(summary.getTotalPoints() > 0);
    }

    @Test

    public void testAddTransaction() {
        // Arrange: Create a new transaction object
        Transaction transaction = new Transaction("Alice", LocalDate.of(2024, 5, 20), 150);

        // Act: Add the transaction to the service
        rewardPointsService.addTransaction(transaction);

        // Assert: Verify that the transaction was added
        List<Transaction> transactions = rewardPointsService.getTransactions();
        assertTrue(transactions.contains(transaction));
    }
}

