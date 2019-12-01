package cob.com.finance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cob.com.finance.entities.TbTransaction;

public interface TbTransactionRepository extends JpaRepository<TbTransaction, Integer>{
	@Query("select t from TbTransaction t where t.sTransactionId = :sTransactionId")
	public TbTransaction getByTransactionId(@Param("sTransactionId")String id);
}
