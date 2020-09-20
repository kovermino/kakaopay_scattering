package com.jongwoo.kakao.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jongwoo.kakao.repository.MoneyScatteringRepository;
import com.jongwoo.kakao.util.MoneyDistributer;

@Component
@Service
public class ScatteringService {
	
	@Autowired
	MoneyScatteringRepository msr;
	
	public Map<String, String> scattering(final String userId, 
							 final String roomId, 
							 final String totalAmount, 
							 final String distributeCount) {
		Map<String, String> responseEntity = new HashMap<>();
		try {
			final int ta = Integer.parseInt(totalAmount);
			final int da = Integer.parseInt(distributeCount);
			
			MoneyDistributer md = new MoneyDistributer();
			final int[] dist = md.distributeMoney(ta, da);
			responseEntity.put("token", "STF");
		} catch (NumberFormatException nfe) {
			responseEntity.put("error_msg", "Parameters must be integer value.");
		} catch (IllegalArgumentException iae) {
			responseEntity.put("error_msg", iae.toString());
		} catch (Exception e) {
			responseEntity.put("error_msg", e.toString());
		}
		return responseEntity;
	}
	
	public int receive(String userId, String roomId, String token) {
		return 0;
	}
	
	public String getScatteringStatus() {
		return "";
	}

}
