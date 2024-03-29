package pkg1.railwaystations;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface RailwayRepo extends JpaRepository<RailwayEntity , Integer>{
	@Query(value = "select station_name, station_code where id=:id",nativeQuery = true)
	List<String> findLocationById(@Param("id") int id);
	
	@Query(value = "select station_name, station_code where station_code=:code",nativeQuery = true)
	List<String> findLocationByCode(@Param("code") String code);

	@Query(value="select * from railwaystations",nativeQuery = true)
	List<String> getAllStations();

}
