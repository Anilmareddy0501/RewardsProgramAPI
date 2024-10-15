package com.example.rewardpoints.controller;

import com.example.rewardpoints.exception.ResourceNotFoundException;
import com.example.rewardpoints.model.RewardPointsSummary;
import com.example.rewardpoints.service.RewardPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/rewards")
public class RewardPointsController {

    private final RewardPointsService rewardPointsService;

    // Constructor-based dependency injection
    public RewardPointsController(RewardPointsService rewardPointsService) {
        this.rewardPointsService = rewardPointsService;
    }

    @GetMapping
    public List<RewardPointsSummary> getAllRewardPoints() {
        return rewardPointsService.calculateRewards();
    }



    @GetMapping("/{customerId}")
    public RewardPointsSummary getRewardPointsByCustomer(@PathVariable String customerId) throws ResourceNotFoundException {
        return rewardPointsService.calculateRewards().stream()
                .filter(summary -> summary.getCustomerId().equalsIgnoreCase(customerId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + customerId + " not found"));
    }


    @GetMapping("/{customerId}/dateRange")
    public RewardPointsSummary getRewardPointsByCustomerAndDateRange(
            @PathVariable String customerId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return rewardPointsService.calculateRewardsForDateRange(customerId, startDate, endDate);
    }
}
