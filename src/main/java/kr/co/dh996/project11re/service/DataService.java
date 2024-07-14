package kr.co.dh996.project11re.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataService {
	
	private final RestTemplate restTemplate;

    @Autowired
    public DataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	public String fetchChampionData(String version) {
		// TODO Auto-generated method stub
		String url = "https://ddragon.leagueoflegends.com/cdn/" + version + "/data/ko_KR/champion.json";
        return restTemplate.getForObject(url, String.class);
	}

}
