package com.insurance.policy.service;

import com.insurance.policy.dao.PolicyRepository;
import com.insurance.policy.entity.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    @Autowired
    PolicyRepository policyRepository;

    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    public Policy getPolicyById(Long id) {
        return policyRepository.findById(id).orElse(null);
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public void deletePolicy(Long id) {
        policyRepository.deleteById(id);
    }

    public Policy updatePolicy(Long id, Policy policy) {
        Policy existingPolicy = policyRepository.findById(id).orElse(null);
        if(existingPolicy != null) {
            existingPolicy.setPolicyNumber(policy.getPolicyNumber());
            existingPolicy.setPolicyType(policy.getPolicyType());
            existingPolicy.setCoverageAmount(policy.getCoverageAmount());
            existingPolicy.setPremiumAmount(policy.getPremiumAmount());
            return policyRepository.save(existingPolicy);
        }
        return null;
    }

    public Policy patchPolicy(Long id, Policy policy) {
        Policy existingPolicy = policyRepository.findById(id).orElse(null);
        if(existingPolicy != null) {
            if(policy.getPolicyNumber() != null) {
                existingPolicy.setPolicyNumber(policy.getPolicyNumber());
            }
            if(policy.getPolicyType() != null) {
                existingPolicy.setPolicyType(policy.getPolicyType());
            }
            if(policy.getCoverageAmount() != null) {
                existingPolicy.setCoverageAmount(policy.getCoverageAmount());
            }
            if(policy.getPremiumAmount() != null) {
                existingPolicy.setPremiumAmount(policy.getPremiumAmount());
            }
            return policyRepository.save(existingPolicy);
        }
        return null;
    }
}
