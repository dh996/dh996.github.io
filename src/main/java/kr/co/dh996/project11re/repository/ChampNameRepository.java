package kr.co.dh996.project11re.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.dh996.project11re.dto.ChampDTO;
import kr.co.dh996.project11re.entity.ChampName;

public interface ChampNameRepository extends JpaRepository<ChampName, String> {
	
	@Query("SELECT new kr.co.dh996.project11re.dto.ChampDTO(c.champ_id, c.champ_name, " +
	           "(SELECT t.champ_tags FROM champ_name c JOIN c.champ_tags t WHERE c.champ_version = :version)) " +
	           "FROM champ_name c " +
	           "JOIN c.champ_tags t " +
	           "WHERE c.champ_version.champ_version = :version")
	List<ChampDTO> findByChampVersionWithTags(@Param("version") String version);
	   
    
	@Query("SELECT new kr.co.dh996.project11re.dto.ChampDTO(c.champ_id, c.champ_name, " +
	           "(SELECT t.champ_tags FROM champ_name c JOIN c.champ_tags t WHERE c.champ_id IN :ids)) " +
	           "FROM champ_name c " +
	           "JOIN c.champ_tags t " +
	           "WHERE c.champId IN :ids")
	List<ChampDTO> findByChampIdsWithTags(@Param("ids") List<String> ids);
}
