package hn.test.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.test.store.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>{

}
