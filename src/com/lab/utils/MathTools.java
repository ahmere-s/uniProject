package com.lab.utils;

public class MathTools {
	
	static int primeCheckCount = 0;
	public boolean isPrime(int num){
		primeCheckCount++;
		
		if(num <= 1){return false;}
		else if (num <= 3){return true;}
		
		if (num % 2 == 0 || num % 3 == 0){return false;}
		
		for(int i = 1; i*i <= num; i += 6){
			if (num % i == 0 || num % (i + 2) == 0){return false;}
		}
		return true;
	}
	
	
}
