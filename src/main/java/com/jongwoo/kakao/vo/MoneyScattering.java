package com.jongwoo.kakao.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MONEY_SCATTERING")
public class MoneyScattering implements Serializable{
	
	private static final long serialVersionUID = 7355909043814360604L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="sct_seq") 
	private long sctSeq;

	@Column(name="token") 
	String token;
	
	@Column(name="owner") 
	String owner;
	
	@Column(name="room_id") 
	String roomId;
	
	@Column(name="total_amount") 
	int totalAmount;
	
	@Column(name="distribute_count") 
	int distributeCount;
	
	@Column(name="time") 
	Date time;

}
