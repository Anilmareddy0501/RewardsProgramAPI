package com.example.rewardpoints;

import com.example.rewardpoints.controller.RewardPointsController;
import com.example.rewardpoints.model.RewardPointsSummary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("unused")
@SpringBootTest
public class RewardPointsControllerTest {

    @Autowired
    private RewardPointsController controller;

    @Test
    public void testGetAllRewardPoints() {
        List<RewardPointsSummary> summaryList = controller.getAllRewardPoints();
        assertEquals(2, summaryList.size());
    }
}
