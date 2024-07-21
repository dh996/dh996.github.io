package kr.co.dh996.project11re.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.dh996.project11re.dto.ChampDTO;
import kr.co.dh996.project11re.simul.data.RecordSimulData;
import kr.co.dh996.project11re.simul.data.UsersPick;
import kr.co.dh996.project11re.simul.data.UsingSimulData;

@Service
public class SimulDataService {

	//레코드 데이터 갈무리 기능입니다.
	public List<RecordSimulData> generateRecordData(List<UsingSimulData> simulDataList, String sid) {
		// TODO Auto-generated method stub
		List<RecordSimulData> returnList = new ArrayList<>();
		for(int i=0; i<simulDataList.size(); i++) {
			RecordSimulData addData = new RecordSimulData(simulDataList.get(i), sid);
			returnList.add(addData);
		}
		return returnList;
	}
	
	//시뮬레이션 데이터를 저장용으로 갈무리하는 기능입니다.
	public List<UsingSimulData> generateUsingData(List<ChampDTO> champDTOList) {
		// TODO Auto-generated method stub
		List<UsingSimulData> returnList = new ArrayList<>();
		//유저가 고른 정보를 바탕으로 유저 팀 리스트를 작성합니다.
		for(int i=0; i<champDTOList.size(); i++) {
			UsingSimulData addData = new UsingSimulData(champDTOList.get(i), i+1, 0);
			returnList.add(addData);
		}
		return returnList;
	}

	public UsersPick getUserpick(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		return null;
	}
}
