package com.jongwoo.kakao.util;

import java.util.Random;

public class MoneyDistributer {
	
	/**
	 * @param totalAmount
	 * @param distributeCount
	 * @return 
	 */
	public int[] distributeMoney(final int totalAmount, final int distributeCount){
		if (totalAmount == 0) {
			throw new IllegalArgumentException("Total amount cannot be 0.");
		}
		if (distributeCount == 0) {
			throw new IllegalArgumentException("Distribute count cannot be 0.");
		}
		if (distributeCount > totalAmount) {
			throw new IllegalArgumentException("Distribute count cannot exceed total amount.");
		}
		int[] nums = new int[distributeCount];
		int total = totalAmount;
		Random rand = new Random();
		for (int i = 0; i < nums.length-1; i++) {
		    nums[i] = rand.nextInt(total);
		    total -= nums[i];
		}
		nums[nums.length-1] = total;
		return nums;
	}

}
