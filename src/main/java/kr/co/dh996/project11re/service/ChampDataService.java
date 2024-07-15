package kr.co.dh996.project11re.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChampDataService {
	
	private final RestTemplate restTemplate;

    @Autowired
    public ChampDataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //버전 데이터를 받아오는 기능입니다.
	public String fetchChampionData(String version) {
		// TODO Auto-generated method stub
		String url = "https://ddragon.leagueoflegends.com/cdn/" + version + "/data/ko_KR/champion.json";
        return restTemplate.getForObject(url, String.class);
	}

	//최신 버전을 적용하는 기능입니다.
	public String findLatestVersion(List<String> versionList) {
		// TODO Auto-generated method stub
	    if (versionList == null || versionList.isEmpty()) {
            return null;
        }    
        int maxVersion1 = 0;
        int maxVersion2 = 0;
        String latestVersion = null;
        boolean flag1 = false;
        
        for (String version : versionList) {
            String[] numericVersionStr = version.split("\\.");
            for(int i=0; i<numericVersionStr.length; i++) {
                int numericVersion = convertToNumericVersion(numericVersionStr[i]);
                
                if(i == 0) {
                	if (numericVersion > maxVersion1) {
                        maxVersion1 = numericVersion;
                        flag1 = true;
                        maxVersion2 = 0;
        
                	}else if(numericVersion == maxVersion1) {
                		flag1 = true;
                	}
                }
                if(i == 1) {
                	if (flag1 == true && numericVersion > maxVersion2) {
                        maxVersion2 = numericVersion;
                        latestVersion = version;
                    }
                }
            }
        }
        return latestVersion;
	}

	private int convertToNumericVersion(String version) {
		// TODO Auto-generated method stub
        try {
        	return Integer.parseInt(version);
        } catch (NumberFormatException e) {
            // 숫자로 변환할 수 없는 경우 처리
            return Integer.MIN_VALUE;
        }
    }
}
