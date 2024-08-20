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
@Table(name="PRPDUCT")
public class ProductEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2256510534255748636L;

	@Id
	@Column(name = "PRODUCT_ID", nullable = false)
	private Long productId;
	
	@Column(name = "PRODUCT_TITLE", nullable = false)
	private String productTitle;
	
	@Column(name = "PRODUCT_PRICE", nullable = false)
	private String productPrice;
	
	@Column(name = "PRODUCT_CATEGORY", nullable = false)
	private String productCategory;
	
	@Column(name = "PRODUCT_DESCRIPTION", nullable = false)
	private String productDescription;
	
	@Column(name = "PRODUCT_IMAGE", nullable = false)
	private String productImage;
	
	@Column(name = "REGISTER_DATE", nullable = false)
	private String registerDate;
	
	@Column(name = "REGISTER_TIME", nullable = false)
	private String registerTime;
	
	@Column(name = "STATUS_REGISTER", nullable = false)
	private String statusRegister;
}
