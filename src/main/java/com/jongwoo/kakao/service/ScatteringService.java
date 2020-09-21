package com.jongwoo.kakao.service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jongwoo.kakao.entity.ScatteringInfoEntity;
import com.google.gson.Gson;
import com.jongwoo.kakao.entity.ScatteredMoneyEntity;
import com.jongwoo.kakao.repository.ScatteringInfoRepository;
import com.jongwoo.kakao.repository.ScatteredMoneyRepository;
import com.jongwoo.kakao.util.Constants;
import com.jongwoo.kakao.util.MoneyDistributer;
import com.jongwoo.kakao.util.TimeUtil;

@Component
@Service
public class ScatteringService {
	
	@Autowired
	ScatteringInfoRepository scatteringInfoRepo;
	
	@Autowired
	ScatteredMoneyRepository scatteredMoneyRepo;
	
	@Autowired
	TokenService tokenService;
	
	public Map<String, String> scattering(final String userId, 
							 			  final String roomId, 
							 			  final String totalAmount, 
							 			  final String distributeCount) {
		Map<String, String> responseEntity = new HashMap<>();
		try {
			final int ta = Integer.parseInt(totalAmount);
			final int da = Integer.parseInt(distributeCount);
			String token = tokenService.issueNewToken();
			Timestamp now = TimeUtil.getCurrentSeoulTime();
			ScatteringInfoEntity scatteringInfo = new ScatteringInfoEntity(token, userId, roomId, ta, da, now);
			scatteringInfoRepo.save(scatteringInfo);
			
			MoneyDistributer md = new MoneyDistributer();
			final int[] dist = md.distributeMoney(ta, da);
			List<ScatteredMoneyEntity> scatteredMoney = new ArrayList<>();;
			for (int distAmount : dist) {
				scatteredMoney.add(new ScatteredMoneyEntity(token, distAmount));
			}
			scatteredMoneyRepo.saveAll(scatteredMoney);
			
			responseEntity.put("token", token);
		} catch (NumberFormatException nfe) {
			responseEntity.put(Constants.ERROR_MSG, "Parameters must be integer value.");
		} catch (IllegalArgumentException iae) {
			responseEntity.put(Constants.ERROR_MSG, iae.toString());
		} catch (Exception e) {
			responseEntity.put(Constants.ERROR_MSG, e.toString());
		}
		return responseEntity;
	}
	
	public Map<String, String> receive(final String userId, final String roomId, final String token) {
		Map<String, String> responseEntity = new HashMap<>();
		try {
			List<ScatteringInfoEntity> scatteringInfoList = scatteringInfoRepo.findByToken(token);
			if (scatteringInfoList.size() == 0) {
				responseEntity.put(Constants.ERROR_MSG, "There is no scattering info for requested token.");
				return responseEntity;
			}
			
			ScatteringInfoEntity scatteringInfo = scatteringInfoList.get(0);
			if (scatteringInfo.getOwner().equals(userId)) {
				responseEntity.put(Constants.ERROR_MSG, "Owner cannot receive money.");
				return responseEntity;
			}
			
			if (scatteringInfo.getRoomId().equals(roomId)) {
				responseEntity.put(Constants.ERROR_MSG, "Requested token is not belong to this chatting room.");
				return responseEntity;
			}
			
			Timestamp now = TimeUtil.getCurrentSeoulTime();
			long minutesBetween = TimeUtil.getMinutesBetween(scatteringInfo.getTime(), now);
			if (minutesBetween >= 10) {
				responseEntity.put(Constants.ERROR_MSG, "Cannot receive this money because requested scattering is expired.");
				return responseEntity;
			}
			
			List<ScatteredMoneyEntity> scatteredMoneyList = scatteredMoneyRepo.findByToken(token);
			if (scatteredMoneyList.size() == 0) {
				responseEntity.put(Constants.ERROR_MSG, "There is no scattered money details.");
				return responseEntity;
			}
			
			Optional<ScatteredMoneyEntity> findResult = scatteredMoneyList.stream().filter(x -> x.getReceiver().equals(userId)).findFirst();
			if (findResult.isPresent()) {
				responseEntity.put(Constants.ERROR_MSG, userId + " already received from this.");
				return responseEntity;
			}
			
			int receivedAmount = 0;
			for (ScatteredMoneyEntity sme: scatteredMoneyList) {
				if (sme.getReceivedYN().equals("N")) {
					sme.setReceivedYN("Y");
					sme.setReceiver(userId);
					receivedAmount = sme.getAmount();
					scatteredMoneyRepo.save(sme);
					break;
				}
			}
			responseEntity.put("received_amount", receivedAmount + "");
		} catch (Exception e) {
			responseEntity.put(Constants.ERROR_MSG, e.toString());
		}
		return responseEntity;
	}
	
	public Map<String, String> getScatteringStatus(final String userId, final String roomId, final String token) {
		Map<String, String> responseEntity = new HashMap<>();
		try {
			List<ScatteringInfoEntity> scatteringInfoList = scatteringInfoRepo.findByToken(token);
			if (scatteringInfoList.size() == 0) {
				responseEntity.put(Constants.ERROR_MSG, "There is no scattering info for requested token.");
				return responseEntity;
			}
			
			ScatteringInfoEntity scatteringInfo = scatteringInfoList.get(0);
			if (!scatteringInfo.getOwner().equals(userId)) {
				responseEntity.put(Constants.ERROR_MSG, "This token is for other's scattering info.");
				return responseEntity;
			}
			
			Timestamp now = TimeUtil.getCurrentSeoulTime();
			long daysBetween = TimeUtil.getDaysBetween(scatteringInfo.getTime(), now);
			if (daysBetween >= 7) {
				responseEntity.put(Constants.ERROR_MSG, "Cannot read this info because requested scattering is expired.");
				return responseEntity;
			}
			
			List<ScatteredMoneyEntity> scatteredMoneyList = scatteredMoneyRepo.findByToken(token);
			if (scatteredMoneyList.size() == 0) {
				responseEntity.put(Constants.ERROR_MSG, "There is no scattered money details.");
				return responseEntity;
			}
			
			int totalReceived = scatteredMoneyList.stream().filter(x -> x.getReceivedYN().equals("Y")).mapToInt(x -> x.getAmount()).sum();
			responseEntity.put("scattered_time", scatteringInfo.getTime().toString());
			responseEntity.put("total_amount", scatteringInfo.getTotalAmount()+"");
			responseEntity.put("total_received", totalReceived+"");
			List<ScatteredMoneyEntity> receivedInfo = scatteredMoneyList.stream().filter(x -> x.getReceivedYN().equals("Y")).collect(Collectors.toList());
			List<String> received = new ArrayList<>();
			for (ScatteredMoneyEntity info : receivedInfo) {
				received.add("[" + info.getReceiver() + ", " + info.getAmount() + "]");
			}
			Gson gson = new Gson();
			String jsonInfo = gson.toJson(received);
			responseEntity.put("received_info", jsonInfo);
		} catch (Exception e) {
			responseEntity.put(Constants.ERROR_MSG, e.toString());
		}
		return responseEntity;
	}

}
