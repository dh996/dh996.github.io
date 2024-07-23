package kr.co.dh996.project11re.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.dh996.project11re.dto.ChampDTO;
import kr.co.dh996.project11re.entity.ChampName;

public interface ChampNameRepository extends JpaRepository<ChampName, String> {
	
	@Query("SELECT new kr.co.dh996.project11re.dto.ChampDTO(c.champId, c.champName, " +
	           "(SELECT t.champTags FROM champName c JOIN c.champTags t WHERE c.champVersion = :version)) " +
	           "FROM champName c " +
	           "JOIN c.champTags t " +
	           "WHERE c.champVersion.champVersion = :version")
	List<ChampDTO> findByChampVersionWithTags(@Param("version") String version);
	   
    
	@Query("SELECT new kr.co.dh996.project11re.dto.ChampDTO(c.champId, c.champName, " +
	           "(SELECT t.champTags FROM champName c JOIN c.champTags t WHERE c.champId IN :ids)) " +
	           "FROM champName c " +
	           "JOIN c.champTags t " +
	           "WHERE c.champId IN :ids")
	List<ChampDTO> findByChampIdsWithTags(@Param("ids") List<String> ids);
}
