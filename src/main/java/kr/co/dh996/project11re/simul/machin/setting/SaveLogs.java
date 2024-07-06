package kr.co.dh996.project11re.simul.machin.setting;

import java.util.List;

import kr.co.dh996.project11re.simul.data.SimulLog;

public class SaveLogs {
	//시뮬레이션 진행과정 로그 누적 기능을 수행합니다.

	//전투 지형 설정 관련 로그를 저장합니다.
	public static void saveFieldLog(
			String sid, int round, List<SimulLog> simulLogList, String field ) {
		// TODO Auto-generated method stub
		simulLogList.add(new SimulLog(sid, round, simulLogList.size()+1, field, "field", 2));
	}
	
	//레벨업 관련 로그를 저장합니다.
	public static void saveLevelLog(
			String sid, int round, List<SimulLog> simulLogList, String name, String level, int team) {
		// TODO Auto-generated method stub
		simulLogList.add(new SimulLog(sid, round, simulLogList.size()+1, name, level, "level", team));
	}

	//공격 관련 로그를 저장합니다.
	public static void saveAttackLog(String sid, int round, List<SimulLog> turnLog, int logSize,
			String attacker, String deffender, String dmg, int team) {
		// TODO Auto-generated method stub
		turnLog.add(new SimulLog(sid, round, logSize+1, attacker, deffender, dmg, "attack", team));
	}

	//사망 관련 로그를 저장합니다.
	public static void saveDeathLog(String sid, int round, List<SimulLog> turnLog, int logSize,
			String name, int team) {
		// TODO Auto-generated method stub
		turnLog.add(new SimulLog(sid, round, logSize+1, name, "death", team));
	}

	//선제공격 관련 로그를 저장합니다.
	public static void saveInitLog(String sid, int round, List<SimulLog> turnLog, int logSize,
			int team) {
		// TODO Auto-generated method stub
		turnLog.add(new SimulLog(sid, round, logSize+1, "init", team));
	}

	//다이브 관련 로그를 저장합니다.
	public static void saveDiveLog(String sid, int round, List<SimulLog> turnLog, int logSize,
			int team) {
		// TODO Auto-generated method stub
		turnLog.add(new SimulLog(sid, round, logSize+1, "dive", team));
	}
}
