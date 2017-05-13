package gen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XXXLargeGem {
	static final String type = ".txt";
	public static void main(String[] args) throws IOException {
		int numInts = 1000000;
		File dir = new File("src/data/Large/unsorted");
		dir.mkdirs();
		for(int i = 0; i < 30; i++)
		{
			System.out.println(i);
			File file = new File(dir, i+type);
			file.createNewFile();
			FileWriter fw = new FileWriter(file);
			for(int j = 0; j < numInts; j++)
			{
				int rand = (int) (Math.random()*10000);
				fw.write(rand+System.getProperty("line.separator"));
				fw.flush();
				System.out.println(rand);
			}
			fw.close();
		}
	}
}
