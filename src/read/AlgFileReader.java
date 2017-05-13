package read;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AlgFileReader {
	public static int[] read(String filename) throws IOException
	{
		int size = 100;
		if(filename.contains("test"))
		{
			FileReader fr =  new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);

			int i = 0;
			while ((br.readLine()) != null) {
				i++;
			}
			br.close();
			size = i;
		}	
		if(filename.contains("Small"))
		{
			size = 10000;
		}
		if(filename.contains("Medium"))
		{
			size = 100000;
		}
		if(filename.contains("Large"))
		{
			size = 1000000;
		}
		
		int[] array = new int[size];
		
		FileReader fr =  new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);

		String currentLine;

		int i = 0;
		while ((currentLine = br.readLine()) != null) {
			array[i] = Integer.parseInt(currentLine);
			i++;
		}
		br.close();
		return array;
	}
}
