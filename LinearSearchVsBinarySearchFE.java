/*
 * Reece Peters
 */

import java.util.Random;

public class LinearSearchVsBinarySearchFE {

	public static void main(String[] args) {
		System.out.println("Welcome to the search tester. We are going to see which algorithm performs the best out of 20 tests");
		
		
		int [] array = new int[1000];
		
		boolean found = false;
		
		Random r = new Random();
		
		//setup for all counters needed
		int testCount = 0;
		int binaryCount = 0;
		int linearCount = 0;
		
		int linearTotalCount = 0;
		int binaryTotalCount = 0;
		
		//for loop to populate array with random numbers
		for(int i = 0; i < 20; i++) {
			for (int a = 0; a < array.length; a++) {
				array[a] = r.nextInt(999);
			}
			//creates a sorted version of array
			int[] sortedArray = quickSort(array, 0, array.length-1);
			
			//assigns number to find within array
			int target = r.nextInt(999);
			
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Test " + testCount);
			System.out.println("-----------------------------------------------------------------");
			
			//linear Search
			linearCount = 0;
			
			for (int b = 0; b < sortedArray.length; b++) {
				linearCount++;
				if(sortedArray[b] == target) {
					found = true;
					break;
				}
			}
			
			System.out.println("Searching using linear search. Found? " + found);
			found = false;
			
			//binary search
			binaryCount = 0;
			//call binarySearchBool to find if number/target was found
			System.out.println("Searching using binary search. Found? " + binarySearchBool(sortedArray, target, 0, sortedArray.length-1));
			
			//Counters displayed
			System.out.println("Linear Checks: " + linearCount);
			//finds number of runs it took to find binary
			int binCount = binarySearchCount(sortedArray, target, 0, sortedArray.length-1, binaryCount);
			System.out.println("Binary Checks: " + binCount);
			
			//add to total counts for average
			linearTotalCount = linearTotalCount + linearCount;
			binaryTotalCount = binaryTotalCount + binCount;
			
			
			//counter for test # increase
			testCount++;
		}
		
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Results");
		System.out.println("-----------------------------------------------------------------");
		
		//calls average class
		System.out.println("On average linear search took: " + getAverage(linearTotalCount) + " Checks");
		System.out.println("On average binary search took: " + getAverage(binaryTotalCount) + " Checks");
		
	}
	
	//total count divided by number of tests
	public static int getAverage(int total) {
		return (total / 20);
	}
	
	//quick sort to sort array
	public static int[] quickSort(int[]array, int startIndex, int endIndex) {
		int part = partition(array, startIndex, endIndex);
		if(startIndex < part-1) {
			quickSort(array, startIndex, part-1);
		} 
		if(part < endIndex) {
			quickSort(array, part, endIndex);
		}
		
		return array;
		
	}
	
	//partition used to recursively split array until sorted
	public static int partition(int[] array, int startIndex, int endIndex) {
		int i = startIndex;
		int j = endIndex;
		int piv = array[(endIndex+startIndex)/2];
		
		while(i <= j) {
			while(array[i] < piv) {
				i++;
			}
			while(array[j] > piv) {
				j--;
			}
			
			if(i <= j) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
				j--;
			}
		}
		return i;
	}
	
	//boolean version to  return if number was found
	public static boolean binarySearchBool(int[] array, int target, int min, int max) {
		if(min>max) {
			return false;
		}
		int mid = ((max+min)/2);
		if(array[mid] == target) {
			return true;
		} else {
			if(array[mid] < target) {
				return binarySearchBool(array, target, mid+1, max);
			} else {
				return binarySearchBool(array, target, min, mid-1);
			}
		}
	}
	
	//class to get the number of times to find number
	public static int binarySearchCount(int[] array, int target, int min, int max, int binaryCount) {
		
		
		if(min>max) {
			return binaryCount;
		}
		int mid = ((max+min)/2);
		
		binaryCount++;
		
		if(array[mid] == target) {
			return binaryCount;
		} else {
			if(array[mid] < target) {
				return binarySearchCount(array, target, mid+1, max, binaryCount);
			} else {
				return binarySearchCount(array, target, min, mid-1, binaryCount);
			}
		}
	}
	
}
