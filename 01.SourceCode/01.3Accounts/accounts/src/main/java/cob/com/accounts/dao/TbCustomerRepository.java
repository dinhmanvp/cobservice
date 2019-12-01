package cob.com.accounts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.accounts.entities.TbCustomer;


public interface TbCustomerRepository extends JpaRepository<TbCustomer,Integer>{
	@Query("SELECT t FROM TbCustomer t WHERE t.sCustomerId = :customerId")
	public TbCustomer getCustomerByCustomerId(@Param("customerId") String customerId);
	
}