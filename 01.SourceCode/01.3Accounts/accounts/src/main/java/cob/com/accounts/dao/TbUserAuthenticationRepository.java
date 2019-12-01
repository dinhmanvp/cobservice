package cob.com.accounts.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cob.com.accounts.entities.TbUserAuthentication;

public interface TbUserAuthenticationRepository extends JpaRepository<TbUserAuthentication,Integer>{

}
