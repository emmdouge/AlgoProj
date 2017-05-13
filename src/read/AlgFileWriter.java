package read;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AlgFileWriter {
	static final String type = ".txt";
	public static void write(int[] array, String filepath, String filename) throws IOException {
		int numInts = array.length;
		File dir = new File(filepath);
		dir.mkdirs();
		File file = new File(dir, filename);
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		for(int j = 0; j < numInts; j++)
		{
			fw.write(array[j]+System.getProperty("line.separator"));
			fw.flush();
			//System.out.println(j);
		}
		fw.close();
	}

	public static void writeTimes(long[] array, String filepath, String filename) throws IOException {
		int numInts = array.length;
		File dir = new File(filepath);
		dir.mkdirs();
		File file = new File(dir, filename);
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		for(int j = 0; j < numInts; j++)
		{
			fw.write(array[j]+System.getProperty("line.separator"));
			fw.flush();
			//System.out.println(j);
		}
		fw.close();
	}
}
