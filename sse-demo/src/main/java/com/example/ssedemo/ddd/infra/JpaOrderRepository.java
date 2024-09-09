package com.example.ssedemo.ddd.infra;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface JpaOrderRepository extends JpaRepository<JpaOrder, Long>, JpaSpecificationExecutor<JpaOrder> {

    List<JpaOrder> findAll(Specification<JpaOrder> spec);
}