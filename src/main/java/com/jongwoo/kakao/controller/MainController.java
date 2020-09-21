package com.jongwoo.kakao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jongwoo.kakao.service.ScatteringService;
import com.jongwoo.kakao.util.Constants;

@RestController
public class MainController {
	
	@Autowired
	ScatteringService scatteringService;
	
	@RequestMapping("/welcome")
	public String welcome(@RequestHeader(value="X-USER-ID") String userId) throws InterruptedException {
		return userId + " welcome";
	}
	
	/**
	 * @param userId
	 * @param roomId
	 * @param param
	 * @return token
	 */
	@ResponseBody
	@RequestMapping(value = "moneyScattering", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> scattering(@RequestHeader(value="X-USER-ID") String userId,
														  @RequestHeader(value="X-ROOM-ID") String roomId,
														  @RequestParam(value = "token", required = false) final String totalAmount,
														  @RequestParam(value = "token", required = false) final String distributeCount) 
	{
		Map<String, String> result = scatteringService.scattering(userId, roomId, totalAmount, distributeCount);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	

	/**
	 * @param userId
	 * @param roomId
	 * @param param
	 * @return receivingAmount
	 */
	@ResponseBody
	@RequestMapping(value = "scatteredMoney", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, String>> receive(@RequestHeader(value="X-USER-ID") String userId,
													   @RequestHeader(value="X-ROOM-ID") String roomId,
													   @RequestParam(value = "token", required = false) final String token) {
		Map<String, String> result = scatteringService.receive(userId, roomId, token);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	/**
	 * @param userId
	 * @param roomId
	 * @param param
	 * @return scattering info
	 */
	@RequestMapping(value = "/moneyScattering")
	public ResponseEntity<Map<String, String>> getScatteringStatus(@RequestHeader(value="X-USER-ID") String userId,
																   @RequestHeader(value="X-ROOM-ID") String roomId,
																   @RequestParam(value = "token", required = false) final String token)  throws InterruptedException
	{
		Map<String, String> scatteringInfo = scatteringService.getScatteringStatus(userId, roomId, token);
		return new ResponseEntity<>(scatteringInfo, HttpStatus.OK);
	}

}
