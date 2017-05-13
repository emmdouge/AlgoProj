package runner;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import heap.Heapsort;
import merge.Mergesort;
import quick.Quicksort;
import read.AlgFileReader;

public class TestAlgorithms {
	
	/**
	 * Tests all algorithms on small data sets
	 * Does not write data
	 * @throws IOException
	 */
	@Test
	public void testAllAlgorithmsSmallSize() throws IOException {
		testMergesortSmall();
		testQuicksortSmall();
		testHeapsortSmall();
	}
	
	/**
	 * Tests all algorithms on small data sets
	 * Does write data
	 * @throws IOException
	 */
	@Test
	public void testAllAlgorithmsSmallSizeWriteData() throws IOException {
		testMergesortSmallWriteData();
		testQuicksortSmallWriteData();
		testHeapsortSmallWriteData();
	}
	
	/**
	 * Tests mergesort on small data set
	 * Does not write data
	 * @throws IOException
	 */
	@Test
	public void testMergesortSmall() throws IOException {
		String type = "Merge";
		String size = "Small";
		Mergesort.run(type, size, false);
	}
	
	/**
	 * Tests mergesort on medium data set
	 * Does not write data
	 * @throws IOException
	 */
	@Test
	public void testMergesortMedium() throws IOException {
		String type = "Merge";
		String size = "Medium";
		Mergesort.run(type, size, false);
		checkSortThruFiles(type, size);
	}
	
	/**
	 * Tests mergesort on large data set
	 * Does not write data
	 * @throws IOException
	 */
	@Test
	public void testMergesortLarge() throws IOException {
		String type = "Merge";
		String size = "Large";
		Mergesort.run(type, size, false);
		checkSortThruFiles(type, size);
	}
	
	/**
	 * Tests mergesort on small data set
	 * Does write data
	 * @throws IOException
	 */
	@Test
	public void testMergesortSmallWriteData() throws IOException {
		String type = "Merge";
		String size = "Small";
		Mergesort.run(type, size, true);
		checkSortThruFiles(type, size);
	}
	
	/**
	 * Tests mergesort on medium data set
	 * Does write data
	 * @throws IOException
	 */
	@Test
	public void testMergesortMediumWriteData() throws IOException {
		String type = "Merge";
		String size = "Medium";
		Mergesort.run(type, size, true);
		checkSortThruFiles(type, size);
	}
	
	/**
	 * Tests mergesort on large data set
	 * Does write data
	 * @throws IOException
	 */
	@Test
	public void testMergesortLargeWriteData() throws IOException {
		String type = "Merge";
		String size = "Large";
		Mergesort.run(type, size, true);
		checkSortThruFiles(type, size);
	}
	
	/**
	 * Tests quicksort on small data set
	 * Does not write data
	 * @throws IOException
	 */
	@Test
	public void testQuicksortSmall() throws IOException {
		String type = "Quick";
		String size = "Small";
		Quicksort.run(type, size, false);
	}
	
	/**
	 * Tests quicksort on medium data set
	 * Does not write data
	 * @throws IOException
	 */
	@Test
	public void testQuicksortMedium() throws IOException {
		String type = "Quick";
		String size = "Medium";
		Quicksort.run(type, size, false);
	}
	
	/**
	 * Tests quicksort on large data set
	 * Does not write data
	 * @throws IOException
	 */
	@Test
	public void testQuicksortLarge() throws IOException {
		String type = "Quick";
		String size = "Large";
		Quicksort.run(type, size, false);
	}
	
	/**
	 * Tests quicksort on small data set
	 * Does write data
	 * @throws IOException
	 */
	@Test
	public void testQuicksortSmallWriteData() throws IOException {
		String type = "Quick";
		String size = "Small";
		Quicksort.run(type, size, true);
		checkSortThruFiles(type, size);
	}
	
	/**
	 * Tests quicksort on medium data set
	 * Does write data
	 * @throws IOException
	 */
	@Test
	public void testQuicksortMediumWriteData() throws IOException {
		String type = "Quick";
		String size = "Medium";
		Quicksort.run(type, size, true);
		checkSortThruFiles(type, size);
	}
	
	/**
	 * Tests quicksort on large data set
	 * Does write data
	 * @throws IOException
	 */
	@Test
	public void testQuicksortLargeWriteData() throws IOException {
		String type = "Quick";
		String size = "Large";
		Quicksort.run(type, size, true);
		checkSortThruFiles(type, size);
	}
	
	/**
	 * Tests heapsort on small data set
	 * Does not write data
	 * @throws IOException
	 */
	@Test
	public void testHeapsortSmall() throws IOException {
		String type = "Heap";
		String size = "Small";
		Heapsort.run(type, size, false);
		checkSortThruFiles(type, size);
	}
	
	/**
	 * Tests heapsort on medium data set
	 * Does not write data
	 * @throws IOException
	 */
	@Test
	public void testHeapsortMedium() throws IOException {
		String type = "Heap";
		String size = "Medium";
		Heapsort.run(type, size, false);
		checkSortThruFiles(type, size);
	}
	
	/**
	 * Tests heapsort on large data set
	 * Does not write data
	 * @throws IOException
	 */
	@Test
	public void testHeapsortLarge() throws IOException {
		String type = "Heap";
		String size = "Large";
		Heapsort.run(type, size, false);
		checkSortThruFiles(type, size);
	}
	
	/**
	 * Tests heapsort on small data set
	 * Does write data
	 * @throws IOException
	 */
	@Test
	public void testHeapsortSmallWriteData() throws IOException {
		String type = "Heap";
		String size = "Small";
		Heapsort.run(type, size, true);
		checkSortThruFiles(type, size);
	}
	
	/**
	 * Tests heapsort on medium data set
	 * Does write data
	 * @throws IOException
	 */
	@Test
	public void testHeapsortMediumWriteData() throws IOException {
		String type = "Heap";
		String size = "Medium";
		Heapsort.run(type, size, true);
		checkSortThruFiles(type, size);
	}
	
	/**
	 * Tests heapsort on large data set
	 * Does write data
	 * @throws IOException
	 */
	@Test
	public void testHeapsortLargeWriteData() throws IOException {
		String type = "Heap";
		String size = "Large";
		Heapsort.run(type, size, true);
		checkSortThruFiles(type, size);
	}

	static void checkSortThruFiles(String type, String size) throws IOException
	{
		for(int i = 0; i < 30; i++)
		{
			int[] array = AlgFileReader.read("src/"+type+"/"+size+"/sorted/"+i+".txt");
			int correct = 0;
			for(int j = 0; j < array.length-1; j++)
			{
				if(array[j] <= array[j+1])
				{
					correct++;
				}
				else
				{
					fail();
				}
			}
		}
		for(int i = 0; i < 30; i++)
		{
			int[] array = AlgFileReader.read("src/"+type+"/"+size+"/Rsorted/"+i+".txt");
			int correct = 0;
			for(int j = 0; j < array.length-1; j++)
			{
				if(array[j] >= array[j+1])
				{
					correct++;
				}
				else
				{
					fail();
				}
			}
		}
	}
	
	public static boolean checksort(int[] array)
	{
		int correct = 0;
		for(int j = 0; j < array.length-1; j++)
		{
			if(array[j] <= array[j+1])
			{
				correct++;
			}
			else
			{
				return false;
			}
		}
		return true;
	}	
	public static boolean checkReverseSort(int[] array)
	{
		int correct = 0;
		for(int j = 0; j < array.length-1; j++)
		{
			if(array[j] >= array[j+1])
			{
				correct++;
			}
			else
			{
				return false;
			}
		}
		return true;
	}
}
