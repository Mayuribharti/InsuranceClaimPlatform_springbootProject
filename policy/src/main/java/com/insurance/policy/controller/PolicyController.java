package com.insurance.policy.controller;

import com.insurance.policy.entity.Policy;
import com.insurance.policy.service.PolicyService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    private static final Logger log = LoggerFactory.getLogger(PolicyController.class);

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Policy Service";
    }

    @PostMapping("/create")
    @Operation(summary = "Create Policy",
               description = "Create a new insurance policy")
    public Policy createPolicy(@RequestBody Policy policy){
        log.info("Policy created successfully: {}", policy);
        return policyService.createPolicy(policy);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Policy by ID",
               description = "Fetch a policy using its ID")
    public Policy getPolicyById(@PathVariable Long id) {
        log.warn("Fetching policy with ID: {}", id);
        return policyService.getPolicyById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all policies",
               description = "Fetch all policies from database")
    public List<Policy> getAllPolicies() {
        log.info("Fetching all policies");
        List<Policy> policies = policyService.getAllPolicies();
        log.info("Total policies fetched: {}", policies.size());
        return policies;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete policy",
               description = "Delete a policy by ID")

    public void deletePolicy(@PathVariable Long id){
        log.warn("Deleting policy with ID: {}", id);
        policyService.deletePolicy(id);
        log.info("Policy deleted successfully with ID: {}", id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update policy (full)",
               description = "Update entire policy details using PUT")
    public Policy updatePolicy(@PathVariable Long id, @RequestBody Policy policy){
        log.info("Updating policy with ID: {} and data: {}", id, policy);
        Policy updated = policyService.updatePolicy(id, policy);
        log.info("Policy updated successfully: {}", updated);
        return updated;
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update policy (partial)",
               description = "Partially update policy fields using PATCH")
    public Policy patchPolicy(@PathVariable Long id, @RequestBody Policy policy) {
        log.info("Partially updating policy ID: {} with data: {}", id, policy);
        Policy updated = policyService.patchPolicy(id, policy);
        log.info("Policy partially updated: {}", updated);
        return updated;
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter policies",
               description = "Fetch policies by type and status")
    public List<Policy> filterPolicies(@RequestParam String type, @RequestParam String status) {
        log.info("Filtering policies with type: {} and status: {}", type, status);
        List<Policy> policies = policyService.filterPolicies(type, status);
        log.info("Filtered policies count: {}", policies.size());
        return policies;    }
}
