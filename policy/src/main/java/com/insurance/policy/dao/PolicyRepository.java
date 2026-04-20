package com.insurance.policy.dao;

import com.insurance.policy.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

    List<Policy> findByPolicyTypeAndStatus(String type, String status);

    List<Policy> findByPolicyType(String type);

    List<Policy> findByStatus(String status);
}
