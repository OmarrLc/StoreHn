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
@Table(name="CLIENT")
public class ClientEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7703741449505538993L;
	
	@Id
	@Column(name = "CLIENT_ID", nullable = false)
	private Long clienteId;
	
	@Column(name = "CLIENT_NAME", nullable = false)
	private String clienteName;
	
	@Column(name = "REGISTER_DATE", nullable = false)
	private String registerDate;
	
	@Column(name = "REGISTER_TIME", nullable = false)
	private String registerTime;
	
	@Column(name = "STATUS_REGISTER", nullable = false)
	private String statusRegister;

}
