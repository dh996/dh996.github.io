package kr.co.dh996.project11re.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.dh996.project11re.simul.data.RecodSimulProcess;
import kr.co.dh996.project11re.simul.data.RecordSimulData;
import kr.co.dh996.project11re.simul.data.SimulLog;
import kr.co.dh996.project11re.dto.ChampDTO;

@Service
public class SimulService {
	//데이터베이스와 연결된 서비스 클래스입니다.
	
	public void saveSimul(List<RecordSimulData> recordData, List<RecodSimulProcess> recordProcess,
			List<SimulLog> recordLog) {
		// TODO Auto-generated method stub
		saveData(recordData);
		saveProcess(recordProcess);
		saveLog(recordLog);
	}
	
	private void saveData(List<RecordSimulData> simulDataList) {
		// TODO Auto-generated method stub
		
	}
	
	private void saveProcess(List<RecodSimulProcess> simulProcessList) {
		// TODO Auto-generated method stub
		
	}

	private void saveLog(List<SimulLog> simulLogList) {
		// TODO Auto-generated method stub
		
	}

	public List<ChampDTO> getChampList(List<String> usersPickChamp) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ChampDTO> getAllChampList() {
		// TODO Auto-generated method stub
		return null;
	}

}
