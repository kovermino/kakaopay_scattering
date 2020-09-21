package com.jongwoo.kakao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jongwoo.kakao.entity.ScatteredMoneyEntity;

public interface ScatteredMoneyRepository extends CrudRepository<ScatteredMoneyEntity, Long> {

	public List<ScatteredMoneyEntity> findByToken(String token);
	
}
