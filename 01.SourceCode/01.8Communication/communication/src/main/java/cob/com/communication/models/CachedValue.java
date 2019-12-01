package cob.com.communication.models;

import java.io.Serializable;

/**
 * Created by Joe on August, 16 2019 .
 */
@SuppressWarnings("all")
public interface CachedValue extends Serializable {
  public Object getKey();
}
