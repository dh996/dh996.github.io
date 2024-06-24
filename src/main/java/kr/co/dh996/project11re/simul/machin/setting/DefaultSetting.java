package kr.co.dh996.project11re.simul.machin.setting;

import java.util.ArrayList;
import java.util.List;

import kr.co.dh996.project11re.dto.champDTO;
import kr.co.dh996.project11re.simul.data.RecordSimulData;
import kr.co.dh996.project11re.simul.data.UsingSimulData;

public class DefaultSetting {
	//기본적인 설정들을 담당하는 클래스입니다.
	
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
	
	//유징 데이터 갈무리 기능입니다.
	public List<UsingSimulData> generateUsingData(List<champDTO> champDTOList) {
		// TODO Auto-generated method stub
		List<UsingSimulData> returnList = new ArrayList<>();
		for(int i=0; i<champDTOList.size(); i++) {
			UsingSimulData addData = new UsingSimulData(champDTOList.get(i));
			returnList.add(addData);
		}
		return returnList;
	}
}

