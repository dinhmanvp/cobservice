package cob.com.cobgateway.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_channel database table.
 * 
 */
@Entity
@Table(name="tb_channel", schema="mdl_gateway")
@NamedQuery(name="TbChannel.findAll", query="SELECT t FROM TbChannel t")
public class TbChannel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="s_channel_id")
	private String channelId;

	@Column(name="n_time_out")
	private Integer timeOut;

	public TbChannel() {
	}

	public String getChannelId() {
		return this.channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Integer getTimeOut() {
		return this.timeOut;
	}

	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}

}