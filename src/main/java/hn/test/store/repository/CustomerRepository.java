package hn.test.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hn.test.store.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>{
	
	@Query("SELECT co FROM CustomerEntity co WHERE co.customerName = :customerName AND co.statusRegister ='ACTIVE'")
	Optional<CustomerEntity> findByCustomerName(@Param ("customerName") String customerName);

}
