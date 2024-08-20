package hn.test.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="ORDER_PAYMENT")
public class OrderPaymentEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4482240679188018597L;
	
	@Id
	@Column(name = "ORDER_PAYMENT_ID", nullable = false)
	private Long orderPaymentId;
	
	@Column(name = "CUSTOMER_ORDER_ID", nullable = false)
	private Long customerOrderEntityId;

	@Column(name = "REGISTER_DATE", nullable = false)
	private String registerDate;
	
	@Column(name = "REGISTER_TIME", nullable = false)
	private String registerTime;
	
	@Column(name = "STATUS_REGISTER", nullable = false)
	private String statusRegister;

}
