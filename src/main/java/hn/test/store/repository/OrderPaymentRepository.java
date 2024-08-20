package hn.test.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.test.store.entity.OrderPaymentEntity;

public interface OrderPaymentRepository extends JpaRepository<OrderPaymentEntity, Long>{

}
