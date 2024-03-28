package pkg1.emi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmiController {
	@GetMapping("/emi/test1")
	public double getEmi() {
		int p=1000;
		int t=2;
		double r=8.0;
		double si=p*t*r/100.0;
		double amt=p+si;
		double emi=amt/(t*12);
		return emi;
	}
	
	@PostMapping("/emi/calc1")
	public double calcEmi(@RequestBody EmiEntity ee) {
		double si1=ee.getP()*ee.getT()*ee.getR()/100.0;
		double amt1=ee.getP()+si1;
		double emi1=amt1/(ee.getT()*12);
		return emi1;
	}
	
	@GetMapping("/emi/file/{fname}")
	public String calcEmiFile(@PathVariable String fname) throws IOException{
		ArrayList<String>names=new ArrayList<>();
		ArrayList<String>cars=new ArrayList<>();
		ArrayList<Integer>principal=new ArrayList<>();
		ArrayList<Integer>time=new ArrayList<>();
		ArrayList<Double>rates=new ArrayList<>();
		ArrayList<Double>simpleinterest=new ArrayList<>();
		ArrayList<Double>amount=new ArrayList<>();
		ArrayList<Double>emi=new ArrayList<>();
		File f1=new File(fname);
		System.out.println(fname);
		String[] arr1=fname.split("[.]",0);
		//System.out.println(arr1[0]);
		//System.out.println(arr1[1]);
		String fname2=arr1[0]+"_out."+arr1[1];
		//String fname2="temp.txt";
		FileWriter fw1=new FileWriter(fname2);
		Scanner sc1=new Scanner(f1);
		String[] arr2=sc1.nextLine().split(",");
		names.add(arr2[0]);
		cars.add(arr2[1]);
		principal.add(Integer.parseInt(arr2[2]));
		time.add(Integer.parseInt(arr2[3]));
		rates.add(Double.parseDouble(arr2[4]));
		simpleinterest.add(principal.get(0)*time.get(0)*rates.get(0)/100.0);
		amount.add(principal.get(0)+simpleinterest.get(0));
		emi.add(amount.get(0)/(time.get(0)*12));
		fw1.write("Siva");
		fw1.close();
		return "Output File created";
	}
	
}
