package quick;

import java.io.IOException;
import java.util.Arrays;

import read.AlgFileReader;
import read.AlgFileWriter;
import runner.TestAlgorithms;

public class Quicksort {
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
			
			partition(array, 0, array.length);
			
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
			
			reverse(array, 0, array.length);

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
		if(writeData)
		{
			AlgFileWriter.writeTimes(Stimes, "src/"+sortType+"/"+sortSize, "sortedTimes"+sortType+sortSize+".txt");
			AlgFileWriter.writeTimes(Rtimes, "src/"+sortType+"/"+sortSize, "RsortedTimes"+sortType+sortSize+".txt");
		}
	}


	/**
	 * 
	 * @param array the original array
	 * @param start where the array starts
	 * @param end where the array ends
	 */
	static void partition(int[] array, int start, int end) 
	{
		int length = end - start;

//		System.out.println(s);
		System.out.println("Enter array from "+start+" to "+(end-1));
		
		//init values
		int first = array[start];
		int medIndex = -1;
		int mid = array[start+(length/2)];
		int last = array[end-1];
		int median = -1;
		
		//choose the median
		if(Math.min(first, last) <= mid && mid <= Math.max(first, last))
		{
			median = mid;
			medIndex = start+(length/2);
			System.out.print("is mid ");
		}
		else if(Math.min(mid, last) <= first && first <= Math.max(mid, last))
		{
			median = first;
			medIndex = start;
			System.out.print("is first ");
		}
		else if(Math.min(mid, first) <= last && last <= Math.max(mid, first))
		{
			median = last;
			medIndex = end-1;
			System.out.print("is last ");
		}
		System.out.println("first: "+first+" mid: "+mid+" last: "+last+" med("+medIndex +"):"+median);

		//swap median to front of array
		int tempP = array[medIndex];
		array[medIndex] = array[start];
		array[start] = tempP;
		medIndex = first;
		
		int keep = 0;
		int prevKeep = 0;
		int front = start;
		int back = start;
		
		//puts first median in correct place
		while(front < end)
		{
			if(array[back] == median && array[front] > median)
			{
				prevKeep = keep;
				keep = front;
			}
			if(array[front] <= median && front > start)
			{	
				if(array[front] <= median && array[back] == median)
				{
					int temp = array[back];
					array[back] = array[front];
					array[front] = temp;
//					System.out.println("a swapped "+array[back]+" with "+array[front]+" from "+start+" to "+end);
//					System.out.println(Arrays.toString(array));
					back++;					
					if(array[back] == median && prevKeep != keep)
					{
						back = keep;
					}
				}
				if(array[front] <= median && array[back] != median)
				{
					int temp = array[back];
					array[back] = array[front];
					array[front] = temp;
//					System.out.println("b swapped "+array[back]+" with "+array[front]+" from "+start+" to "+end);
//					System.out.println(Arrays.toString(array));
					if(array[front] < median)
					back++;					
				}
				front++;
			}
			else
			{
				front++;
			}
		}

		//choose second median value
		int end2 = back;
		last = array[back];
		mid = array[start+(back-start)/2];
		median = mid;
		medIndex = start+(back-start)/2;

		//swap it to the front
		tempP = array[medIndex];
		array[medIndex] = array[start];
		array[start] = tempP;
		medIndex = start;

		//put median value in correct position
		int keep2 = 0;
		int prevKeep2 = 0;
		int front2 = 0;
		int back2 = start;
		System.out.println("2Enter array from "+start+" to "+end2+"med:["+medIndex+"]:"+median);
		while(front2 < end2)
		{
//			System.out.println("back: "+back2+" front: "+front2);
			if(array[back2] == median && array[front2] > median)
			{
				prevKeep2 = keep2;
				keep2 = front2;
//					System.out.println("k "+keep2);
			}
			if(array[front2] <= median && front2 > start)
			{	
				if(array[front2] <= median && array[back2] == median)
				{
					int temp = array[back2];
					array[back2] = array[front2];
					array[front2] = temp;
//					System.out.println("a swapped "+array[back2]+" with "+array[front2]+" from "+start+" to "+end2);
//					System.out.println(Arrays.toString(array));
					back2++;					
					if(array[back2] == median && prevKeep2 != keep2)
					{
						back2 = keep2;
					}
				}
				if(array[front2] <= median && array[back2] != median)
				{
					int temp = array[back2];
					array[back2] = array[front2];
					array[front2] = temp;
//					System.out.println("b swapped "+array[back2]+" with "+array[front2]+" from "+start+" to "+end2);
//					System.out.println(Arrays.toString(array));
					if(array[front2] < median)
					back2++;					
				}
				front2++;
			}
			else
			{
				front2++;
			}
		}
//		System.out.println("after: "+Arrays.toString(array)+" k:"+keep+" b:"+back+" f:"+front+" begin:");
//		System.out.println("after: "+Arrays.toString(array)+" k2:"+keep2+" b2:"+back2+" f2:"+front2+" begin:"+"\n");

		System.out.println("Entering right partition");
		quicksort(array, back, front);

		System.out.println("Entering middle partition");
		quicksort(array, back2, back);

		System.out.println("Entering left partition");
		quicksort(array, 0, back2);
		System.out.println("F: "+0+"~"+(back2)+" M: "+(back2)+"~"+(back)+" L: "+(back)+"~"+(front)+"\n");
	
	}
	
	/**
	 * perform quicksort on portion of array given
	 * @param array the original array
	 * @param start where the array will begin
	 * @param end where it will stop
	 */
	static void quicksort(int[] array, int start, int end) 
	{
		int length = end - start;
		if(length < 2)
		{
			return;
		}
		else
		{
			//choose the start as the median
			int medIndex = start;
			int med = array[start];

			//put median in correct place
			int keep = 0;
			int prevKeep = 0;
			int front = start;
			int back = start;
			while(front < end)
			{
//				System.out.println("back: "+back+" front: "+front);
				if(array[back] == med && array[front] > med)
				{
					prevKeep = keep;
					keep = front;
//					System.out.println("k "+keep);
				}
				if(array[front] <= med && front > start)
				{	
					if(array[front] <= med && array[back] == med)
					{
						int temp = array[back];
						array[back] = array[front];
						array[front] = temp;
//						System.out.println("a swapped "+array[back]+" with "+array[front]+" from "+start+" to "+end+" med: "+med);
//						System.out.println(Arrays.toString(array));
						back++;					
						if(array[back] == med && prevKeep != keep)
						{
							back = keep;
						}
					}
					if(array[front] <= med && array[back] != med)
					{
						int temp = array[back];
						array[back] = array[front];
						array[front] = temp;
//						System.out.println("b swapped "+array[back]+" with "+array[front]+" from "+start+" to "+end+" med: "+med);
//						System.out.println(Arrays.toString(array));
						if(array[front] < med)
						back++;					
					}
					front++;
				}
				else
				{
					front++;
				}
			}

//			System.out.println("after: "+Arrays.toString(array)+" e:"+end+" b:"+back+" f:"+front+" s:"+start);

			quicksort(array, start, back);
			quicksort(array, back+1, front);
		}		
	}
	
	/**
	 * reverse sort the array 
	 * @param array
	 * @param start
	 * @param end
	 */
	static void reverse(int[] array, int start, int end) 
	{
		int length = end - start;
		if(length < 2)
		{
			return;
		}
		else
		{
			int medIndex = start;
			int med = array[start];

			int keep = 0;
			int prevKeep = 0;
			int front = start;
			int back = start;
			while(front < end)
			{
				if(array[back] == med && array[front] > med)
				{
					prevKeep = keep;
					keep = front;
//					System.out.println("k "+keep);
				}
				if(array[front] >= med && front > start)
				{	
					if(array[front] >= med && array[back] == med)
					{
						int temp = array[back];
						array[back] = array[front];
						array[front] = temp;
						back++;					
						if(array[back] == med && prevKeep != keep)
						{
							back = keep;
						}
					}
					if(array[front] >= med && array[back] != med)
					{
						int temp = array[back];
						array[back] = array[front];
						array[front] = temp;
						if(array[front] <= med)
						back++;					
					}
					front++;
				}
				else
				{
					front++;
				}
			}
			reverse(array, 0, back);
			reverse(array, back+1, front);
		}		
	}
}
