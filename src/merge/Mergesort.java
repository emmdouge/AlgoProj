package merge;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import read.AlgFileReader;
import read.AlgFileWriter;
import runner.TestAlgorithms;
public class Mergesort {

	public static void run(String type, String size, boolean writeData) throws IOException {
		long[] Stimes = new long[30];
		long[] Rtimes = new long[30];
		boolean sorted = false;
		boolean sortedR = false;
		String sortType = type;
		String sortSize = size;
		for(int i = 0; i < 30; i++)
		{
			//read small data set
			int[] array = AlgFileReader.read("src/data/"+sortSize+"/unsorted/"+i+".txt");
			
			//get time before mergesort starts
			long start = System.nanoTime();
			
			//sort the array using mergesort
			mergesort(array);
			
			//calculate time it took to sort the array (average case)
			long elapsedTime = System.nanoTime() - start;
			
			//store time it took to sort the array
			Stimes[i] = elapsedTime;
			System.out.println("s: "+elapsedTime);
			
			//writes sorted array to a file for the array to check if sorted properly
			if(writeData)
			AlgFileWriter.write(array, "src/"+sortType+"/"+sortSize+"/sorted", i+".txt");
			
			sorted = TestAlgorithms.checksort(array);
			
			//gets time before reverse sorting begins
			start = System.nanoTime();
			
			//sorts the array in descending order 
			reverse(array);
			
			//calculate time it took to sort the array (worst case)
			elapsedTime = System.nanoTime() - start;
			System.out.println("r: "+elapsedTime);
			
			sortedR = TestAlgorithms.checkReverseSort(array);
			
			//stores time it took to reverse sort the array
			Rtimes[i] = elapsedTime;
			
			if(sorted && sortedR)	
			{
				System.out.println("Sorted and Reverse Sorted correctly");
			}
			
			//writes sorted array to a file to check if sorted properly
			if(writeData)
			AlgFileWriter.write(array, "src/"+sortType+"/"+sortSize+"/Rsorted", i+".txt");
			System.out.println(i);
		}
		
		//writes times it took to sort and reversesort
		if(writeData)
		{
			AlgFileWriter.writeTimes(Stimes, "src/"+sortType+"/"+sortSize, "sortedTimes"+sortType+sortSize+".txt");
			AlgFileWriter.writeTimes(Rtimes, "src/"+sortType+"/"+sortSize, "RsortedTimes"+sortType+sortSize+".txt");
		}
	}

	/**
	 * does the same thing as mergesort but uses swapR instead of swap
	 * and the conditionals are flipped
	 * @param array split array
	 */
	static void reverse(int[] array)
	{
		if(array.length <= 2)
		{
			swapR(array);
			//System.out.println(Arrays.toString(array));
			return;
		}
		else
		{
			int[] left;
			int[] right;
			left = new int[array.length/2];
			if(array.length%2==0)
			{
				right = new int[array.length/2];
			}
			else
			{
				right = new int[(array.length/2)+1];
			}
			initLR(array, left, right, array.length/2);

			reverse(left);
			reverse(right);
			//System.out.println("sorted l"+Arrays.toString( left));
			//System.out.println("sorted r"+Arrays.toString(right));

			for(int i = 0, j=0, k=0; i < array.length; i++)
			{
				//System.out.println(i+"array b4 "+Arrays.toString(array));
				if(j == left.length && k != right.length)
				{
					array[i] = right[k];
					k++;
				}
				else if(k == right.length && j != left.length)
				{
					array[i] = left[j];
					j++;		
				}
				else if(left[j] > right[k])
				{
					//System.out.println("left < right");
					//System.out.println("left:"+left[j]+" right: "+right[k]);
					array[i] = left[j];
					j++;
				}
				else if(left[j] <= right[k])
				{
					//System.out.println("left > right");
					//System.out.println("left:"+left[j]+" right: "+right[k]);
					array[i] = right[k];
					k++;
				}
				//System.out.println(i+"array now "+Arrays.toString(array)+"\n");
				//System.out.println(j+"/"+left.length+" "+k+"/"+right.length+" "+i+"/"+array.length);
			}
		}
	}
	
	/**
	 * recursive function that ends when the split array size is less that or equal to 2
	 * @param array new array
	 */
	static void mergesort(int[] array)
	{
		if(array.length <= 2)
		{
			swap(array);
			//System.out.println(Arrays.toString(array));
			return;
		}
		else
		{
			int[] left;
			int[] right;
			left = new int[array.length/2];
			if(array.length%2==0)
			{
				right = new int[array.length/2];
			}
			else
			{
				right = new int[(array.length/2)+1];
			}
			initLR(array, left, right, array.length/2);

			mergesort(left);
			mergesort(right);
			//System.out.println("sorted l"+Arrays.toString( left));
			//System.out.println("sorted r"+Arrays.toString(right));

			//sorts the left and right parts of array passed in
			for(int leftIndex = 0, arrayIndex=0, rightIndex=0; leftIndex < array.length; leftIndex++)
			{
				//if theres one element left on the right side
				if(arrayIndex == left.length && rightIndex != right.length)
				{
					array[leftIndex] = right[rightIndex];
					rightIndex++;
				}
				//if theres one element left on the left side
				else if(rightIndex == right.length && arrayIndex != left.length)
				{
					array[leftIndex] = left[arrayIndex];
					arrayIndex++;		
				}
				//if the left element is less than the right element
				else if(left[arrayIndex] <= right[rightIndex])
				{
					//System.out.println("left < right");
					//System.out.println("left:"+left[j]+" right: "+right[k]);
					array[leftIndex] = left[arrayIndex];
					arrayIndex++;
				}
				//if the left element is greater than the right element
				else if(left[arrayIndex] > right[rightIndex])
				{
					//System.out.println("left > right");
					//System.out.println("left:"+left[j]+" right: "+right[k]);
					array[leftIndex] = right[rightIndex];
					rightIndex++;
				}
				//System.out.println(i+"array now "+Arrays.toString(array)+"\n");
				//System.out.println(j+"/"+left.length+" "+k+"/"+right.length+" "+i+"/"+array.length);
			}
		}
	}
	
	/**
	 * initialize left and right subarrays based on its size
	 * @param array original array
	 * @param left new array that will be initialized to represent the left half
	 * @param right new array that will be initialized to represent the left half
	 * @param half determines where the left half ends and where the right half begins
	 */
	static void initLR(int[] array, int[] left, int[] right, int half) {
		for(int i = 0; i < half; i++)
		{
			left[i] = array[i];
		}
		for(int i = half, j = 0; i < array.length; i++, j++)
		{
			right[j] = array[i];
		}
	}

	/**
	 * swap them if the first element is greater than the second element
	 * @param array the split array
	 */
	static void swap(int[] array)
	{
		if(array.length > 1 && array[0] > array[1])
		{
			int temp = array[0];
			array[0] = array[1];
			array[1] = temp;
		}
	}
	
	/**
	 * swap them if the first element is less than the second element
	 * @param array the split array
	 */
	static void swapR(int[] array)
	{
		if(array.length > 1 && array[0] < array[1])
		{
			int temp = array[0];
			array[0] = array[1];
			array[1] = temp;
		}
	}
}
