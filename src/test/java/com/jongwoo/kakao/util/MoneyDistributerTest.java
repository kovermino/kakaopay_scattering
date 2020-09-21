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
	
	@Test
	void when0dividedInto5Parts() {
		int totalAmount = 0;
		int distributeCount = 5;
		assertThrows(IllegalArgumentException.class, () -> {
			int[] dist = md.distributeMoney(totalAmount, distributeCount);
	    });
	}
	
	@Test
	void when5000dividedInto0Parts() {
		int totalAmount = 5000;
		int distributeCount = 0;
		assertThrows(IllegalArgumentException.class, () -> {
			int[] dist = md.distributeMoney(totalAmount, distributeCount);
	    });
	}
	
	@Test
	void whenTotalAmountBiggerThanDistributeCount() {
		int totalAmount = 5000;
		int distributeCount = 10000;
		assertThrows(IllegalArgumentException.class, () -> {
			int[] dist = md.distributeMoney(totalAmount, distributeCount);
	    });
	}

}
