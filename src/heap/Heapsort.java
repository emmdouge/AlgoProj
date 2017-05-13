package heap;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import read.AlgFileReader;
import read.AlgFileWriter;
import runner.TestAlgorithms;

public class Heapsort {
	
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
			
			heapify(array, array.length);
			heapsort(array, array.length);
			
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
			
			heapifyR(array, array.length);
			reverse(array, array.length);
			
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
	 * makes sure the each parent node is the lowest of its subtree
	 * @param array
	 * @param length
	 */
	public static void heapifyR(int array[], int length) 
	{
		//gets each node that is a parent starting from the last parent
		for (int i = length/2; i >= 0; i--)
		{
			minheap(array, i, -1, array.length-1);
		}
	}

	/**
	 * swaps the nodes until the parent node is actually the min in relation to the children
	 * return when the previous parentIndex(minNodeIndex) and the current parentIndex(minNodeIndex)
	 * are the same
	 * @param array length of the array
	 * @param parentIndex current parentIndex
	 * @param prevParentIndex previous parentIndex
	 */
	public static void minheap(int array[], int parentIndex, int prevParentIndex, int sortedIndex) 
	{
		//no changes are made to subtree
		if(parentIndex == prevParentIndex)
		{
			return;
		}
		else
		{	
			int minNodeIndex = parentIndex;
			int leftChildIndex = parentIndex*2;
			int rightChildIndex = (parentIndex*2)+1;
			
			int minVal = 0;
			if(leftChildIndex <= sortedIndex)
			{
				minVal = Math.min(array[parentIndex],array[leftChildIndex]);
				if(minVal == array[leftChildIndex])
				{
					minNodeIndex = leftChildIndex;
				}
			}
			if(rightChildIndex < array.length && rightChildIndex <= sortedIndex)
			{
				minVal = Math.min(array[minNodeIndex], Math.min(array[leftChildIndex], array[rightChildIndex]));
				if(minVal == array[rightChildIndex])
				{
					minNodeIndex = rightChildIndex;
				}
			}
			
			//if parent is not the smallest, swap it with the smallest
			if (minNodeIndex != parentIndex) 
			{
				int temp = array[parentIndex];
				array[parentIndex] = array[minNodeIndex];
				array[minNodeIndex] = temp;
				minheap(array, minNodeIndex, parentIndex, sortedIndex);
			}
		}
	}
	
	/**
	 * sorts the array in descending order
	 * @param array
	 * @param length
	 */
	public static void reverse(int array[], int length) 
	{
		int sortedIndex = array.length-1;
		while(sortedIndex > 0) 
		{
			//send the the smallest element to the back of the array
			int temp = array[0];
			array[0] = array[sortedIndex];
			array[sortedIndex] = temp;
			
			//remove the last node for consideration as it is in the correct position
			sortedIndex--;
			
			//makes sure the smallest node is at the top
			minheap(array, 0, -1, sortedIndex);
		}
	}
	
	/**
	 * makes sure the parent node is the largest in relation to its children
	 * @param array the original array
	 * @param length length of the array
	 */
	public static void heapify(int array[], int length) 
	{
		//each node that is a parent starting from the last parent
		for (int i = length/2; i >= 0; i--)
		{
			maxheap(array, i, -1, array.length-1);
		}
	}

	/**
	 * swaps the parent node and children until the parent node is the highest of the three 
	 * returns when the maxNodeIndex and the parent index are the same
	 * @param array the original array
	 * @param parentIndex index of the parent of the subtree
	 * @param prevParentIndex index of the previosu parent of the subtree
	 */
	public static void maxheap(int array[], int parentIndex, int prevParentIndex, int sortedIndex) 
	{
		//no changes are made to subtree
		if(parentIndex == prevParentIndex)
		{
			return;
		}
		else
		{	
			int maxNodeIndex = parentIndex;
			int leftChildIndex = parentIndex*2;
			int rightChildIndex = (parentIndex*2)+1;
			
			int maxVal = 0;
			if(leftChildIndex <= sortedIndex)
			{
				maxVal = Math.max(array[parentIndex],array[leftChildIndex]);
				if(maxVal == array[leftChildIndex])
				{
					maxNodeIndex = leftChildIndex;
				}
			}
			if(rightChildIndex < array.length && rightChildIndex <= sortedIndex)
			{
				maxVal = Math.max(array[maxNodeIndex], Math.max(array[leftChildIndex], array[rightChildIndex]));
				if(maxVal == array[rightChildIndex])
				{
					maxNodeIndex = rightChildIndex;
				}
			}
			//if parent is not the biggest, swap it with the biggest
			if (maxNodeIndex != parentIndex) 
			{
				int temp = array[parentIndex];
				array[parentIndex] = array[maxNodeIndex];
				array[maxNodeIndex] = temp;
				maxheap(array, maxNodeIndex, parentIndex, sortedIndex);
			}
		}
	}

	/**
	 * 
	 * @param array
	 * @param length
	 */
	public static void heapsort(int array[], int length) 
	{
		int sortedIndex = array.length-1;
		while(sortedIndex > 0) 
		{
			//sends biggest element to the back and last element to the front
			int temp = array[0];
			array[0] = array[sortedIndex];
			array[sortedIndex] = temp;
			
			//remove last node from consideration as it is in the correct position
			sortedIndex--;
			
			//makes sure the  biggest one is on top
			maxheap(array, 0, -1, sortedIndex);
		}
	}

}
