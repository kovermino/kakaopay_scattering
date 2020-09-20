package com.jongwoo.kakao.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoneyDistributerTest {
	
	MoneyDistributer md;

	@BeforeEach
	void setUp() throws Exception {
		md = new MoneyDistributer();
	}

	@Test
	void when5000dividedInto5Parts() {
		int totalAmount = 5000;
		int distributeCount = 5;
		int[] dist = md.distributeMoney(totalAmount, distributeCount);
		int sum = Arrays.stream(dist).sum();
		int len = dist.length;
		
		assertEquals(totalAmount, sum);
		assertEquals(distributeCount, len);
	}

}
