package pkg1.cricket;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CricDataController {
	@GetMapping("/cric/all/bestbatsman/{fname}")
	public static String findBestBatsman(@PathVariable String fname) throws IOException{
		ArrayList<String>players=new ArrayList<>();//11 players
		ArrayList<String>batsman=new ArrayList<>();//125 times batsman has faced the ball
		ArrayList<String>bowlers=new ArrayList<>();//125 times bowler bowled the ball
		ArrayList<String>wickets=new ArrayList<>();//125 times has the opportunity to get wickets
		ArrayList<Integer>scores=new ArrayList<>();// runs scored each of the 125 balls
		ArrayList<Integer>total=new ArrayList<>();
		ArrayList<Integer>total_wick=new ArrayList<>();
		String[] arr1;
		String name1="";
		String bestbatsman="";
		String date1="";
		int total1=0;
		int total2=0;
		int maxtotal=0;
		int len1=0;
		File f1=new File(fname);
		Scanner sc1=new Scanner(f1);
		for(int i=0;i<6;i++) {// Skipping the unwanted lines in the file
			sc1.nextLine();
		}
		arr1=sc1.nextLine().split(",");//Finding the date of the match
		date1=arr1[2];
		
		for(int i=0;i<14;i++) {// Skipping the unwanted lines in the file
			sc1.nextLine();
		}
		
		
		for(int i=0;i<22;i++) { //first 11 lines is having player information 
			arr1=sc1.nextLine().split(",");
			players.add(arr1[3]);
		}
		for(int i=0;i<28;i++) {// Skipping the unwanted lines in the file
			sc1.nextLine();
		}
		while(sc1.hasNext()) {//Reading remaining lines 
			arr1=sc1.nextLine().split(",");
			len1=arr1.length;
			batsman.add(arr1[4]);
			bowlers.add(arr1[6]);
			scores.add(Integer.parseInt(arr1[7]));
			if(len1==16) {
				wickets.add(arr1[14]);
			}
			else {
				wickets.add("");
			}
			
		}
		
		for(int j=0;j<players.size();j++) {
			total1=0;
			name1=players.get(j);
			for(int i=0;i<scores.size();i++) {
				if((batsman.get(i)).equals(name1)) {
					total1=total1+scores.get(i);
				}
			}
			total.add(total1);	
		}
		
		for(int i=0;i<players.size();i++) {
			if(total.get(i)>maxtotal) {
				maxtotal=total.get(i);
				bestbatsman=players.get(i);
			}
		}
		System.out.println("Best batsman is  "+bestbatsman+" : "+maxtotal);
		
		String msg1=date1+" Best batsman is  "+bestbatsman+" : "+maxtotal;
		
		for(int j=0;j<players.size();j++) {
			total2=0;
			for(int i=0;i<bowlers.size();i++) {
				if(players.get(j).equals(bowlers.get(i))) {
					if(wickets.get(i)=="") {
					}
					else {
						total2=total2+1;
						total_wick.add(total2);
					}
				}
			}
		}
		System.out.println(total_wick);
		
		
		
		return msg1;
	}
	
	@GetMapping("/cric/all/bestbatsmen/{fnames}")
	public String findBestBatsmen(@PathVariable String fnames) throws IOException{
		String msg2="";
		String s2="";
		File f2= new File(fnames);
		Scanner sc2=new Scanner(f2);
		s2=sc2.nextLine();
		msg2=msg2+findBestBatsman(s2)+"\n";
		s2=sc2.nextLine();
		msg2=msg2+findBestBatsman(s2)+"\n";
		
		
		sc2.close();
		return msg2;
	}
	
	public static void main(String[] args) throws Exception {
		findBestBatsman("1273727.csv");
	}
}