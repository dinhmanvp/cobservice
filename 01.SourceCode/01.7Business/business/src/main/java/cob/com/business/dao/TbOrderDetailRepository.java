package cob.com.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cob.com.business.entities.TbOrderDetail;

public interface TbOrderDetailRepository extends JpaRepository<TbOrderDetail, Integer> {
}