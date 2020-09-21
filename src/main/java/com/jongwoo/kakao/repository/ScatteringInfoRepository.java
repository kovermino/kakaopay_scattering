package com.jongwoo.kakao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jongwoo.kakao.entity.ScatteringInfoEntity;

public interface ScatteringInfoRepository extends CrudRepository<ScatteringInfoEntity, Long>{
	
	public List<ScatteringInfoEntity> findByToken(String token);

}
