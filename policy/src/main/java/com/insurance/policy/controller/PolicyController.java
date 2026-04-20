package com.insurance.policy.controller;

import com.insurance.policy.entity.Policy;
import com.insurance.policy.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Policy createPolicy(@RequestBody Policy policy){
        return policyService.createPolicy(policy);
    }

    @GetMapping("/{id}")
    public Policy getPolicyById(@PathVariable Long id) {
        return policyService.getPolicyById(id);
    }

    @GetMapping("/all")
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id){
        policyService.deletePolicy(id);
    }

    @PutMapping("/{id}")
    public Policy updatePolicy(@PathVariable Long id, @RequestBody Policy policy){
        return policyService.updatePolicy(id,policy);
    }

    @PatchMapping("/{id}")
    public Policy patchPolicy(@PathVariable Long id, @RequestBody Policy policy) {
        return policyService.patchPolicy(id, policy);
    }
}
