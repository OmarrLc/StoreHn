package hn.test.store.dao;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class SequenceDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	public Long getNextInternalTransactionSequenceValue() {
        String sql = "SELECT NEXT VALUE FOR INTERNAL_TRANSACTION_SEQ FROM dual";
        BigInteger sequenceValue = (BigInteger) entityManager.createNativeQuery(sql).getSingleResult();
        return sequenceValue.longValue(); 
    }
	
	public Long getNextCustomerOrderSequenceValue() {
        String sql = "SELECT NEXT VALUE FOR CUSTOMER_ORDER_SEQ FROM dual";
        BigInteger sequenceValue = (BigInteger) entityManager.createNativeQuery(sql).getSingleResult();
        return sequenceValue.longValue(); 
    }
	
	public Long getNextOrderDetailSequenceValue() {
		String sql = "SELECT NEXT VALUE FOR ORDER_DETAIL_SEQ FROM dual";
		BigInteger sequenceValue = (BigInteger) entityManager.createNativeQuery(sql).getSingleResult();
		return sequenceValue.longValue(); 
	}
	
	public Long getNextCustomerSequenceValue() {
		String sql = "SELECT NEXT VALUE FOR CUSTOMER_SEQ FROM dual";
		BigInteger sequenceValue = (BigInteger) entityManager.createNativeQuery(sql).getSingleResult();
		return sequenceValue.longValue(); 
	}
	
	public Long getNextOrderPaymentSequenceValue() {
		String sql = "SELECT NEXT VALUE FOR ORDER_PAYMENT_SEQ FROM dual";
		BigInteger sequenceValue = (BigInteger) entityManager.createNativeQuery(sql).getSingleResult();
		return sequenceValue.longValue(); 
	}
}
