package cob.com.marketing.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cob.com.marketing.entities.TbAdvertise;

public interface TbAdvertiseRepository extends JpaRepository<TbAdvertise, Integer> {
}
