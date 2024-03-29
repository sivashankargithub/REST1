package pkg1.cricket;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CricCon2 {

	public static void main(String[] args) throws Exception {
		int len1=0;
		File f1=new File("cric_data.csv");
		Scanner sc1=new Scanner(f1);
		ArrayList<String>list1=new ArrayList<>();
		for(int i=0;i<11;i++) {
			sc1.nextLine();
		}
		
		while(sc1.hasNext()) {
			String[] arr1=sc1.nextLine().split(",");
			len1=arr1.length;
			//System.out.println(len1);
			if(len1==16) {
				list1.add(arr1[14]);
			}
			else {
				list1.add("");
			}
		}
//		System.out.println(list1);
	}
}