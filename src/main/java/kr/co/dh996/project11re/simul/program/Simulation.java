package kr.co.dh996.project11re.simul.program;

import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.data.UsersPick;
import kr.co.dh996.project11re.simul.machin.round.ChampGrow;
import kr.co.dh996.project11re.simul.machin.round.FieldSetting;
import kr.co.dh996.project11re.simul.machin.round.ObjectSetting;
import kr.co.dh996.project11re.simul.machin.round.PowerSetting;
import kr.co.dh996.project11re.simul.machin.round.RoundManage;
import kr.co.dh996.project11re.simul.machin.round.TakeAdventage;
import kr.co.dh996.project11re.simul.machin.round.WinCheck;
import kr.co.dh996.project11re.simul.machin.turn.BattlePhase;
import kr.co.dh996.project11re.simul.machin.turn.InitSetting;

public class Simulation {
	//시뮬레이션의 실행과정 관련기능을 수행합니다.
	private final Round round;
	private final SimulService simulService;
	
	//시뮬레이션이 이곳에서 실행됩니다.
	public void simullation(UsersPick usersPick) {
		
		SimulMainObject simulMO = new SimulMainObject(usersPick);
		round.roundLoop(simulMO);
		//종료 판정시 데이터를 데이터베이스에 저장합니다.
		//simulService.saveData(simulMO.simulDataList);
		//simulService.saveProcess(simulMO.simulProcessList);
		//simulService.saveLog(simulMO.simulLogList);
	}
}
