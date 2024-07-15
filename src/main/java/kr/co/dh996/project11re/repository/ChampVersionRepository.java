package kr.co.dh996.project11re.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.dh996.project11re.entity.ChampVersion;

public interface ChampVersionRepository extends JpaRepository<ChampVersion, String> {

	//중복 체크 확인용
	boolean existsByChampVersion(String champVersion);
	
	@Query("SELECT c.champ_version FROM ChampVersion c")
	List<String> getAllVersions();
}
