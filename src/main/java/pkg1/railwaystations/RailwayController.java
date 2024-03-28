package pkg1.railwaystations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RailwayController {
	@Autowired
	RailwayRepo rr;
	
	@PostMapping("/stations/loadFile/{fname}")
	public List<String> loadStationsFromFile(@PathVariable String fname) throws Exception{
		File f1=new File(fname);
		Scanner sc1=new Scanner(f1);
		List<RailwayEntity>list1=new ArrayList<>();
		List<String>list2=new ArrayList<>();
		while(sc1.hasNext()) {
			String[] arr1=sc1.nextLine().split(",");
			list2.add(arr1[1]);
			list1.add(new RailwayEntity(arr1[0], arr1[1]));
		}
		rr.saveAll(list1);
		return list2;
	}
	
	@PostMapping("/stations/add")
	public String addStation(@RequestBody RailwayEntity re) {
		rr.save(re);
		return "added Successfully";
	}
	
	@GetMapping("/stations/get")
	public List<String> getAllStations() {
		return rr.getAllStations();
	}
	
	@GetMapping("/stations/get/id/{id}")
	public List<String> getStationById(@PathVariable int id){
		return rr.findLocationById(id);
	}
	
	@GetMapping("/stations/get/code/{code}")
	public List<String> getStationByCode(@PathVariable String code){
		return rr.findLocationByCode(code);
	}
	
}
