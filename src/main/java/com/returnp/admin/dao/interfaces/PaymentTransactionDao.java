
package com.returnp.admin.dao.interfaces;

import com.returnp.admin.model.PaymentTransaction;

public interface PaymentTransactionDao {
		public int insertPaymentTransaction(PaymentTransaction trans);
	
	  int deleteByPrimaryKey(Integer paymentTransactionNo);

	    int insert(PaymentTransaction record);

	    int insertSelective(PaymentTransaction record);

	    PaymentTransaction selectByPrimaryKey(Integer paymentTransactionNo);

	    int updateByPrimaryKeySelective(PaymentTransaction record);

	    int updateByPrimaryKey(PaymentTransaction record);
}
