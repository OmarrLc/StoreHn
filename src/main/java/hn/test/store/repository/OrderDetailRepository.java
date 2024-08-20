package hn.test.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.test.store.entity.OrderDetailEntity;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long>{

}
