package cob.com.communication.models;

import javax.persistence.Id;

/**
 * Created by Joe on August, 17 2019 .
 */
public abstract class BaseEntity implements CachedValue {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;

	@Override
	public Object getKey() {
		return this.id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
