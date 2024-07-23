package kr.co.dh996.project11re.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.dh996.project11re.dto.SimulDTO;
import kr.co.dh996.project11re.entity.SimulList;

public interface SimulListRepository extends JpaRepository<SimulList, String> {

	@Query("SELECT new kr.co.dh996.project11re.dto.SimulDTO(sl, sd, slog, spa, spd, spt) " +
	       "FROM SimulList sl " +
	       "LEFT JOIN SimulData sd ON sl.simulSid = sd.simulSid " +
	       "LEFT JOIN SimulLogs slog ON sl.simulSid = slog.simulSid " +
	       "LEFT JOIN SimulProcessA spa ON sl.simulSid = spa.simulSid " +
	       "LEFT JOIN SimulProcessD spd ON sl.simulSid = spd.simulSid " +
	       "LEFT JOIN SimulProcessT spt ON sl.simulSid = spt.simulSid " +
	       "WHERE sl.simulSid = :sid")
	SimulDTO findSimulInfo(@Param("sid") String sid);
}
