package cob.com.accounts.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cob.com.accounts.entities.TbAcount;

public interface TbAcountRepository extends JpaRepository<TbAcount,Long>{

}
