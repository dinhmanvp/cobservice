package cob.com.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tb_order_detail database table.
 * 
 */
@Entity
@Table(name="tb_order_detail")
public class TbOrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="n_id")
	private Integer nId;

	@Column(name="n_is_product")
	private Integer nIsProduct;

	@Column(name="n_qty")
	private BigDecimal nQty;

	@Column(name="n_subprice")
	private BigDecimal nSubprice;

	@Column(name="n_total")
	private BigDecimal nTotal;

	@Column(name="s_mydeal_id")
	private String sMydealId;

	@Column(name="s_order_detail_id")
	private String sOrderDetailId;

	@Column(name="s_order_number")
	private String sOrderNumber;

	@Column(name="s_product_id")
	private String sProductId;

	public TbOrderDetail() {
	}

	public Integer getNId() {
		return this.nId;
	}

	public void setNId(Integer nId) {
		this.nId = nId;
	}

	public Integer getNIsProduct() {
		return this.nIsProduct;
	}

	public void setNIsProduct(Integer nIsProduct) {
		this.nIsProduct = nIsProduct;
	}

	public BigDecimal getNQty() {
		return this.nQty;
	}

	public void setNQty(BigDecimal nQty) {
		this.nQty = nQty;
	}

	public BigDecimal getNSubprice() {
		return this.nSubprice;
	}

	public void setNSubprice(BigDecimal nSubprice) {
		this.nSubprice = nSubprice;
	}

	public BigDecimal getNTotal() {
		return this.nTotal;
	}

	public void setNTotal(BigDecimal nTotal) {
		this.nTotal = nTotal;
	}

	public String getSMydealId() {
		return this.sMydealId;
	}

	public void setSMydealId(String sMydealId) {
		this.sMydealId = sMydealId;
	}

	public String getSOrderDetailId() {
		return this.sOrderDetailId;
	}

	public void setSOrderDetailId(String sOrderDetailId) {
		this.sOrderDetailId = sOrderDetailId;
	}

	public String getSOrderNumber() {
		return this.sOrderNumber;
	}

	public void setSOrderNumber(String sOrderNumber) {
		this.sOrderNumber = sOrderNumber;
	}

	public String getSProductId() {
		return this.sProductId;
	}

	public void setSProductId(String sProductId) {
		this.sProductId = sProductId;
	}

}