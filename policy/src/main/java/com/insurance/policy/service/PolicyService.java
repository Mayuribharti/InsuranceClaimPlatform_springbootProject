package com.insurance.policy.service;

import com.insurance.policy.dao.PolicyRepository;
import com.insurance.policy.entity.Policy;
import com.insurance.policy.exception.customException.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    private static final Logger log = LoggerFactory.getLogger(PolicyService.class);

    @Autowired
    private PolicyRepository policyRepository;

    // CREATE
    public Policy createPolicy(Policy policy) {
        log.info("Creating new policy with number: {}", policy.getPolicyNumber());

        Policy savedPolicy = policyRepository.save(policy);

        log.debug("Policy created successfully with ID: {}", savedPolicy.getId());

        return savedPolicy;
    }

    // READ BY ID
    public Policy getPolicyById(Long id) {
        log.info("Fetching policy with ID: {}", id);

        return policyRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Policy not found with ID: {}", id);
                    return new ResourceNotFoundException("Policy not found with id: " + id);
                });
    }

    // READ ALL
    public List<Policy> getAllPolicies() {
        log.info("Fetching all policies");

        List<Policy> policies = policyRepository.findAll();

        log.debug("Total policies found: {}", policies.size());

        return policies;
    }

    // DELETE
    public void deletePolicy(Long id) {
        log.warn("Attempting to delete policy with ID: {}", id);

        if (!policyRepository.existsById(id)) {
            log.error("Delete failed. Policy not found with ID: {}", id);
            throw new ResourceNotFoundException("Policy not found with id: " + id);
        }

        policyRepository.deleteById(id);

        log.info("Policy deleted successfully with ID: {}", id);
    }

    // UPDATE (FULL)
    public Policy updatePolicy(Long id, Policy policy) {
        log.info("Updating policy with ID: {}", id);

        Policy existingPolicy = policyRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Update failed. Policy not found with ID: {}", id);
                    return new ResourceNotFoundException("Policy not found with id: " + id);
                });

        existingPolicy.setPolicyNumber(policy.getPolicyNumber());
        existingPolicy.setPolicyType(policy.getPolicyType());
        existingPolicy.setCoverageAmount(policy.getCoverageAmount());
        existingPolicy.setPremiumAmount(policy.getPremiumAmount());

        Policy updatedPolicy = policyRepository.save(existingPolicy);

        log.debug("Policy updated successfully with ID: {}", id);

        return updatedPolicy;
    }

    // PATCH (PARTIAL)
    public Policy patchPolicy(Long id, Policy policy) {
        log.info("Patching policy with ID: {}", id);

        Policy existingPolicy = policyRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Patch failed. Policy not found with ID: {}", id);
                    return new ResourceNotFoundException("Policy not found with id: " + id);
                });

        if (policy.getPolicyNumber() != null) {
            log.debug("Updating policyNumber");
            existingPolicy.setPolicyNumber(policy.getPolicyNumber());
        }
        if (policy.getPolicyType() != null) {
            log.debug("Updating policyType");
            existingPolicy.setPolicyType(policy.getPolicyType());
        }
        if (policy.getCoverageAmount() != null) {
            log.debug("Updating coverageAmount");
            existingPolicy.setCoverageAmount(policy.getCoverageAmount());
        }
        if (policy.getPremiumAmount() != null) {
            log.debug("Updating premiumAmount");
            existingPolicy.setPremiumAmount(policy.getPremiumAmount());
        }

        Policy patchedPolicy = policyRepository.save(existingPolicy);

        log.info("Policy patched successfully with ID: {}", id);

        return patchedPolicy;
    }

    // FILTER
    public List<Policy> filterPolicies(String type, String status) {
        log.info("Filtering policies with type: {} and status: {}", type, status);

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

        if (policies.isEmpty()) {
            log.warn("No policies found for given filter criteria");
            throw new ResourceNotFoundException("No policies found with given criteria");
        }

        log.debug("Filtered policies count: {}", policies.size());

        return policies;
    }
}