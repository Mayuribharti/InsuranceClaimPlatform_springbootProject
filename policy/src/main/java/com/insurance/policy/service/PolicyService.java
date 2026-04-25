package com.insurance.policy.service;

import com.insurance.policy.dao.PolicyRepository;
import com.insurance.policy.entity.Policy;
import com.insurance.policy.exception.customException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    // CREATE
    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    // READ BY ID
    public Policy getPolicyById(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Policy not found with id: " + id)
                );
    }

    // READ ALL
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    // DELETE
    public void deletePolicy(Long id) {
        if (!policyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Policy not found with id: " + id);
        }
        policyRepository.deleteById(id);
    }

    // UPDATE (FULL)
    public Policy updatePolicy(Long id, Policy policy) {

        Policy existingPolicy = policyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Policy not found with id: " + id)
                );

        existingPolicy.setPolicyNumber(policy.getPolicyNumber());
        existingPolicy.setPolicyType(policy.getPolicyType());
        existingPolicy.setCoverageAmount(policy.getCoverageAmount());
        existingPolicy.setPremiumAmount(policy.getPremiumAmount());

        return policyRepository.save(existingPolicy);
    }

    // PATCH (PARTIAL)
    public Policy patchPolicy(Long id, Policy policy) {

        Policy existingPolicy = policyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Policy not found with id: " + id)
                );

        if (policy.getPolicyNumber() != null) {
            existingPolicy.setPolicyNumber(policy.getPolicyNumber());
        }
        if (policy.getPolicyType() != null) {
            existingPolicy.setPolicyType(policy.getPolicyType());
        }
        if (policy.getCoverageAmount() != null) {
            existingPolicy.setCoverageAmount(policy.getCoverageAmount());
        }
        if (policy.getPremiumAmount() != null) {
            existingPolicy.setPremiumAmount(policy.getPremiumAmount());
        }

        return policyRepository.save(existingPolicy);
    }

    // FILTER
    public List<Policy> filterPolicies(String type, String status) {

        List<Policy> policies;

        if (type != null && status != null) {
            policies = policyRepository.findByPolicyTypeAndStatus(type, status);
        } else if (type != null) {
            policies = policyRepository.findByPolicyType(type);
        } else if (status != null) {
            policies = policyRepository.findByStatus(status);
        } else {
            policies = policyRepository.findAll();
        }

        // Optional: throw exception if no data found
        if (policies.isEmpty()) {
            throw new ResourceNotFoundException("No policies found with given criteria");
        }

        return policies;
    }
}