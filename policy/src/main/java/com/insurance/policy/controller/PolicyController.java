package com.insurance.policy.controller;

import com.insurance.policy.entity.Policy;
import com.insurance.policy.service.PolicyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Policy Service";
    }

    @PostMapping("/create")
    @Operation(summary = "Create Policy",
               description = "Create a new insurance policy")
    public Policy createPolicy(@RequestBody Policy policy){
        return policyService.createPolicy(policy);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Policy by ID",
               description = "Fetch a policy using its ID")
    public Policy getPolicyById(@PathVariable Long id) {
        return policyService.getPolicyById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all policies",
               description = "Fetch all policies from database")
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete policy",
               description = "Delete a policy by ID")

    public void deletePolicy(@PathVariable Long id){
        policyService.deletePolicy(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update policy (full)",
               description = "Update entire policy details using PUT")
    public Policy updatePolicy(@PathVariable Long id, @RequestBody Policy policy){
        return policyService.updatePolicy(id,policy);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update policy (partial)",
               description = "Partially update policy fields using PATCH")
    public Policy patchPolicy(@PathVariable Long id, @RequestBody Policy policy) {
        return policyService.patchPolicy(id, policy);
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter policies",
               description = "Fetch policies by type and status")
    public List<Policy> filterPolicies(@RequestParam String type, @RequestParam String status) {
        return policyService.filterPolicies(type, status);
    }
}
