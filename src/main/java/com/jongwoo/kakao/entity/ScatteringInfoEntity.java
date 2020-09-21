package com.jongwoo.kakao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="MONEY_SCATTERING")
public class ScatteringInfoEntity implements Serializable{
	
	private static final long serialVersionUID = 7355909043814360604L;
	
	@Id 
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
	Timestamp time;

	@Builder
	public ScatteringInfoEntity(String token, 
								 String owner, 
								 String roomId, 
								 int totalAmount,
								 int distributeCount, 
								 Timestamp time) {
		super();
		this.token = token;
		this.owner = owner;
		this.roomId = roomId;
		this.totalAmount = totalAmount;
		this.distributeCount = distributeCount;
		this.time = time;
	}

}
