package hn.test.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.test.store.entity.CustomerOrderEntity;

public interface OrderRepository extends JpaRepository<CustomerOrderEntity, Long>{

}
