package pkg1.railwaystations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileNameCreate {

	public static void main(String[] args) throws IOException{
		
		File f1=new File("cric_info.txt");
		FileWriter fw1=new FileWriter("cric2.txt");
		Scanner sc1=new Scanner(f1);
		while(sc1.hasNext()) {
			String[] arr1=sc1.nextLine().split(" - ");
			fw1.write(arr1[4]);
			fw1.write(".txt\n");
		}
		fw1.close();
	}

}
