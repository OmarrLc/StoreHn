package hn.test.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hn.test.store.dto.base.ViewProductDto;
import hn.test.store.entity.OrderDetailEntity;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {

	@Query("SELECT new hn.test.store.dto.base.ViewProductDto(od.orderDetailId, od.productId, pr.productTitle, pr.productPrice, od.quantity, (od.quantity * pr.productPrice) AS TotalProduct)"
			+ " from OrderDetailEntity od" + " INNER JOIN ProductEntity pr ON pr.productId = od.productId\r\n"
			+ " WHERE od.customerOrderId = :customerOrderId AND od.statusRegister = 'ACTIVE'")
	List<ViewProductDto> getDetailOrder(@Param("customerOrderId") Long customerOrderId);

	@Query("SELECT od from OrderDetailEntity od WHERE od.customerOrderId = :customerOrderId AND od.productId = :productId AND od.statusRegister = 'ACTIVE'")
	Optional<OrderDetailEntity> findByProductOrder(@Param("productId") Long productId,
			@Param("customerOrderId") Long customerOrderId);

	@Modifying
	@Query("UPDATE OrderDetailEntity co SET co.quantity = 0L, co.statusRegister= 'INACTIVE' WHERE co.orderDetailId = :orderDetailId")
	Integer removeProductToOrderId(@Param("orderDetailId") Long orderDetailId);

	@Modifying
	@Query("UPDATE OrderDetailEntity co SET co.quantity = :quantity WHERE co.customerOrderId = :customerOrderId AND co.productId = :productId AND co.statusRegister = 'ACTIVE'")
	Integer updateQuantityProduct(@Param("quantity") Long quantity, @Param("customerOrderId") Long customerOrderId,
			@Param("productId") Long productId);
}
