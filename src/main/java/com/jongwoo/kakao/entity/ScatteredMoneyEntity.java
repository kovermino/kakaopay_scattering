package com.jongwoo.kakao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="SCATTERED_MONEY")
public class ScatteredMoneyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="scattered_money_id")
	String id;
	
	@Column(name="token") 
	String token;
	
	@Column(name="amount") 
	int amount;
	
	@Column(name="received_yn")
	String receivedYN;
	
	@Column(name="receiver")
	String receiver;

	public ScatteredMoneyEntity(String token, int amount) {
		super();
		this.token = token;
		this.amount = amount;
		this.receivedYN = "N";
		this.receiver = "";
	}
	
}
