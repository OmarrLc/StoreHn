package hn.test.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hn.test.store.entity.CustomerOrderEntity;

public interface OrderRepository extends JpaRepository<CustomerOrderEntity, Long>{
	
	@Modifying
	@Query("UPDATE CustomerOrderEntity co SET co.statusRegister= :statusRegister WHERE co.customerOrderId = :customerOrderId")
	Integer updateById(@Param ("customerOrderId") Long customerOrderId,
			@Param ("statusRegister") String statusRegister);
	
	@Query("SELECT co FROM CustomerOrderEntity co WHERE co.customerOrderId = :customerOrderId AND co.statusRegister ='ACTIVE'")
	Optional<CustomerOrderEntity> findByCustomerIdAndStatus(@Param ("customerOrderId") Long customerOrderId);

}
