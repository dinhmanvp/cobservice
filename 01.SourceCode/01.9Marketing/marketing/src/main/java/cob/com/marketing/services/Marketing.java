package cob.com.marketing.services;

import java.util.List;

import cob.com.marketing.entities.TbAdvertise;

public interface Marketing {
	List<TbAdvertise> getAdvertises(Integer isHome);
	String getImage(String tablename, String id);
}
