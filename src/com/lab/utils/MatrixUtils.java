package com.lab.utils;

public class MatrixUtils {
	String version = "1.0";
	
	public void printMatrix(int[][] matrix){
		for (int[] row : matrix){
			for (int col : row){
				System.out.print(formatNumber(col));
			}
			System.out.println();
		}
	}
	private String formatNumber(int num){
		String conv = Integer.toString(num);
		
		while(conv.length() < 10){conv += " ";}
		
		return conv;
	}
}
