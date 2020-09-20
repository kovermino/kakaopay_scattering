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

@RestController
public class MainController {
	
	@Autowired
	ScatteringService mss;
	
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
		try {
			
		} catch (Exception e) {
			
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
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
		return new ResponseEntity<>(null, HttpStatus.OK);
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
		System.out.println(token);
		Map<String, String> scatteringInfo = new HashMap<>();
		scatteringInfo.put("time", "time");
		return new ResponseEntity<>(scatteringInfo, HttpStatus.OK);
	}

}
