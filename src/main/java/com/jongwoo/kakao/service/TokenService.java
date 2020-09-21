package com.jongwoo.kakao.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jongwoo.kakao.entity.ScatteringInfoEntity;
import com.jongwoo.kakao.repository.ScatteringInfoRepository;

@Component
@Service
public class TokenService {
	
	@Autowired
	ScatteringInfoRepository moneyScatteringRepo;
	
	public String issueNewToken() {
		String token = makeRandomToken();
		List<ScatteringInfoEntity> scatteringInfo = moneyScatteringRepo.findByToken(token);
		while (scatteringInfo.size() == 0) {
			token = makeRandomToken();
			scatteringInfo = moneyScatteringRepo.findByToken(token);
		}
		return token;
	}
	
	public String makeRandomToken() {
		Random r = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<3;i++){
            char c = (char)(r.nextInt(26) + 'A');
            builder.append(c);
        }
        return builder.toString();
	}

}
