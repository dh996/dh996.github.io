package kr.co.dh996.project11re.simul.machin.setting;

import java.util.List;

import kr.co.dh996.project11re.simul.data.SimulLog;

public class SaveLogs {
	//시뮬레이션 진행과정 로그 누적 기능을 수행합니다.

	//전투 지형 설정 관련 로그를 저장합니다.
	public static void saveFieldLog(
			String sid, String field, int round, List<SimulLog> simulLogList) {
		// TODO Auto-generated method stub
		simulLogList.add(new SimulLog(sid, round, simulLogList.size()+1, field, "field", 2));
	}
	
	//레벨업 관련 로그를 저장합니다.
	public static void saveLevelLog(
			String sid, String name, String level, int round, List<SimulLog> simulLogList, int team) {
		// TODO Auto-generated method stub
		simulLogList.add(new SimulLog(sid, round, simulLogList.size()+1, level, "level", team));
	}
}
