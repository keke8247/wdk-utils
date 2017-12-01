package com.wdk.util.leetcode;

import org.junit.Test;

public class SolutionPivotIndex {
	public int pivotIndex(int[] nums) {
		if(nums.length == 0){
			return -1;
		}
		
		int left = nums[0];
		int right = 0;
		
		for(int i=0;i<nums.length;i++){
			right += nums[i];
		}
		if(left == right){
			return 0;
		}
		
		for(int j=1;j<nums.length;j++){
			left += nums[j];
			
			right -= nums[j-1];
			
			if(left == right){
				System.out.println("pivot index is:"+j);
				return j;
			}
		}
		System.out.println("this arrays have no pivot index");
		return -1;
    }
	
	@Test
	public void testMethod(){
		int [] nums = {1, 7, 3, 4, 6, 5, 6, 2, 2};
//		int [] nums = {-1,-1,0,1,1,0};
		System.out.println(pivotIndex(nums));
	}
}
