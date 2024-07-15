package kr.co.dh996.project11re.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.dh996.project11re.dto.SimulDTO;
import kr.co.dh996.project11re.entity.SimulList;

public interface SimulListRepository extends JpaRepository<SimulList, String> {

	@Query("SELECT new kr.co.dh996.project11re.dto.SimulDTO(sl, sd, slog, spa, spd, spt) " +
	       "FROM SimulList sl " +
	       "LEFT JOIN SimulData sd ON sl.simul_sid = sd.simul_sid " +
	       "LEFT JOIN SimulLogs slog ON sl.simul_sid = slog.simul_sid " +
	       "LEFT JOIN SimulProcessA spa ON sl.simul_sid = spa.simul_sid " +
	       "LEFT JOIN SimulProcessD spd ON sl.simul_sid = spd.simul_sid " +
	       "LEFT JOIN SimulProcessT spt ON sl.simul_sid = spt.simul_sid " +
	       "WHERE sl.simul_sid = :sid")
	SimulDTO findSimulInfo(@Param("sid") String sid);
}
