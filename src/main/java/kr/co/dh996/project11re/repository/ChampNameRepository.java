package kr.co.dh996.project11re.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.dh996.project11re.dto.ChampDTO;
import kr.co.dh996.project11re.entity.ChampName;

public interface ChampNameRepository extends JpaRepository<ChampName, String> {

    @Query("SELECT new kr.co.dh996.project11re.dto.ChampDTO(c.champId, c.champName, " +
           "(SELECT t.champTags FROM ChampTags t WHERE t.champVersion = c.champVersion AND t.champId = c)) " +
           "FROM ChampName c " +
           "WHERE c.champVersion.champVersion = :version")
    List<ChampDTO> findByChampVersionWithTags(@Param("version") String version);

    @Query("SELECT new kr.co.dh996.project11re.dto.ChampDTO(c.champId, c.champName, " +
           "(SELECT t.champTags FROM ChampTags t WHERE t.champId = c)) " +
           "FROM ChampName c " +
           "WHERE c.champId IN :ids")
    List<ChampDTO> findByChampIdsWithTags(@Param("ids") List<String> ids);
}

/* 복합적인 쿼리작성에 대한 지식이 아직 없어서 gpt의 도움을 다소 받았음 이하 설명을 추가함

첫 번째 메소드 - 입력받은 버전값에 해당하는 모든 데이터를 dto로 포장해 빼내는 것을 요구


SELECT new kr.co.dh996.project11re.dto.ChampDTO(...):

SELECT: JPQL 쿼리의 시작을 알리며, 데이터를 선택(검색)하는 데 사용됩니다.
new kr.co.dh996.project11re.dto.ChampDTO(...): 검색된 데이터를 ChampDTO 객체의 새 인스턴스로 매핑합니다.
c.champId: ChampName 엔티티의 champId 필드를 ChampDTO의 첫 번째 매개변수로 매핑합니다.
c.champName: ChampName 엔티티의 champName 필드를 ChampDTO의 두 번째 매개변수로 매핑합니다.
(SELECT t.champTags FROM ChampTags t WHERE t.champVersion = c.champVersion AND t.champId = c): 서브쿼리로 ChampTags 엔티티에서 champTags 필드를 선택합니다. 서브쿼리는 t.champVersion이 c.champVersion과 같고 t.champId가 c와 같은 조건을 가집니다.


FROM ChampName c:

FROM: JPQL 쿼리에서 데이터를 가져올 엔티티를 지정합니다.
ChampName: ChampName 엔티티에서 데이터를 가져옵니다.
c: ChampName 엔티티의 별칭으로, 쿼리 내에서 c를 사용하여 ChampName 엔티티의 필드에 접근합니다.


WHERE c.champVersion.champVersion = :version:

WHERE: 쿼리 결과를 제한하는 조건을 지정합니다.
c.champVersion.champVersion: ChampName 엔티티의 champVersion 필드의 champVersion 값을 의미합니다.
= :version: version 파라미터 값과 비교합니다.


:version:

@Param("version"): 메서드 매개변수로 전달된 값을 JPQL 쿼리의 version 파라미터에 매핑합니다.


쿼리 동작 설명
이 쿼리는 다음과 같은 작업을 수행합니다:

ChampName 엔티티에서 주어진 version 값과 일치하는 champVersion을 가진 레코드를 찾습니다.

각 레코드에 대해:
ChampName 엔티티의 champId와 champName 필드를 가져옵니다.
서브쿼리를 사용하여 ChampTags 엔티티에서 해당 champVersion과 champId를 가진 champTags 값을 가져옵니다.
이 값을 사용하여 ChampDTO 객체를 생성합니다.
생성된 ChampDTO 객체 리스트를 반환합니다.



두 번째 메소드 - 입력받은 배열 안에 있는id와 일치하는 id를 가진 모든 챔피언들의 데이터를 dto로 포장해 빼오는것을 요구

(SELECT t.champTags FROM ChampTags t WHERE t.champId = c): 서브쿼리로 ChampTags 엔티티에서 champTags 필드를 선택합니다. 서브쿼리는 t.champId가 c와 같은 조건을 가집니다.
FROM ChampName c:

첫 메소드와 중복부분 제외


WHERE c.champId IN :ids:

c.champId: ChampName 엔티티의 champId 필드를 의미합니다.
IN :ids: ids 파라미터 리스트 내의 값과 일치하는 champId를 가진 레코드를 선택합니다.


:ids:

@Param("ids"): 메서드 매개변수로 전달된 리스트 값을 JPQL 쿼리의 ids 파라미터에 매핑합니다.


쿼리 동작 설명
이 쿼리는 다음과 같은 작업을 수행합니다:

ChampName 엔티티에서 주어진 ids 리스트 내의 값과 일치하는 champId를 가진 레코드를 찾습니다.

각 레코드에 대해:
ChampName 엔티티의 champId와 champName 필드를 가져옵니다.
서브쿼리를 사용하여 ChampTags 엔티티에서 해당 champId를 가진 champTags 값을 가져옵니다.
이 값을 사용하여 ChampDTO 객체를 생성합니다.
생성된 ChampDTO 객체 리스트를 반환합니다.


사용 문법의 기능들 요약
쿼리에서 SELECT는 데이터를 선택합니다.
new 키워드는 검색된 데이터를 DTO 객체로 매핑합니다.
FROM 절은 데이터를 가져올 엔티티를 지정합니다.
WHERE 절은 쿼리 결과를 제한하는 조건을 지정합니다.
서브쿼리는 특정 조건을 가진 데이터를 선택합니다.
쿼리 파라미터는 @Param 어노테이션을 사용하여 메서드 매개변수와 매핑됩니다.
 */