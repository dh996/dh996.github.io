package kr.co.dh996.project11re.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.dh996.project11re.dto.ChampDTO;

@Service
public class JsonService {

    private final ObjectMapper objectMapper;

    @Autowired
	public JsonService(ObjectMapper objectMapper) {
	    this.objectMapper = objectMapper;
	}

	public List<ChampDTO> parseChampionData(String jsonData, String version) throws IOException {
	    // 1. JSON 데이터 파싱을 위해 루트 노드를 읽어온다.
	    JsonNode rootNode = objectMapper.readTree(jsonData);
	    
	    // 2. 루트 노드에서 "data" 필드를 찾아 그 노드를 가져온다.
	    JsonNode dataNode = rootNode.path("data");
	    
	    // 3. "data" 노드의 모든 요소를 순회할 수 있는 이터레이터를 생성한다.
	    Iterator<JsonNode> elements = dataNode.elements();
	    
	    // 4. 챔피언 정보를 담을 리스트를 초기화한다.
	    List<ChampDTO> champDTOList = new ArrayList<>();

	    // 5. "data" 노드의 각 요소를 순회하면서 챔피언 정보를 파싱한다.
	    while (elements.hasNext()) {
	        JsonNode champNode = elements.next();
	        
	        // 6. 새로운 ChampDTO 객체를 생성한다.
	        ChampDTO champDTO = new ChampDTO();
	        
	        // 7. 챔피언의 id를 파싱하여 ChampDTO 객체에 설정한다.
	        champDTO.setChampID(version+"_"+champNode.path("id").asText());
	        
	        // 8. 챔피언의 name을 파싱하여 ChampDTO 객체에 설정한다.
	        champDTO.setChampName(champNode.path("name").asText());
	        
	        // 9. 챔피언의 태그들을 파싱하여 리스트에 추가하고, 이를 ChampDTO 객체에 설정한다.
	        List<String> tags = new ArrayList<>();
	        champNode.path("tags").forEach(tagNode -> tags.add(tagNode.asText()));
	        champDTO.setChampTags(tags);

	        // 10. 파싱된 ChampDTO 객체를 리스트에 추가한다.
	        champDTOList.add(champDTO);
	    }
	    // 11. 모든 챔피언 정보를 담은 리스트를 반환한다.
	    return champDTOList;
	}
}
