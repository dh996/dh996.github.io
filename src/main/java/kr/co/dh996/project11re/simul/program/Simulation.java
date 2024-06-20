package kr.co.dh996.project11re.simul.program;

import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.data.UsersPick;
import kr.co.dh996.project11re.simul.machin.round.ChampGrow;
import kr.co.dh996.project11re.simul.machin.round.FieldSetting;
import kr.co.dh996.project11re.simul.machin.round.ObjectSetting;
import kr.co.dh996.project11re.simul.machin.round.PowerSetting;
import kr.co.dh996.project11re.simul.machin.round.TakeAdventage;
import kr.co.dh996.project11re.simul.machin.round.WinCheck;
import kr.co.dh996.project11re.simul.machin.turn.BattlePhase;
import kr.co.dh996.project11re.simul.machin.turn.InitSetting;

public class Simulation {
	
	//시뮬레이션이 이곳에서 실행됩니다.
	public void simullation(UsersPick usersPick) {
		
		SimulMainObject simulMO = new SimulMainObject(usersPick);
		roundLoop(simulMO);
		//종료 판정시 데이터를 데이터베이스에 저장합니다.
		simulService.saveData(simulMO.simulDataList);
		simulService.saveProcess(simulMO.simulProcessList);
		simulService.saveLog(simulMO.simulLogList);
	}

	//라운드 실행 기능입니다.
	private void roundLoop(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		
		simulMO.usingSimulProcess.round ++; //라운드 카운트를 1회 증가시킵니다.
		FieldSetting.setField(simulMO); //이번 라운드 전투 지형을 설정합니다.
		ChampGrow.setGrow(simulMO); //이번 라운드 챔피언 성장 수치를 결정합니다.
		PowerSetting.setPower(simulMO); //이번 라운드 전투력 수치를 결정합니다.
		turnLoop(simulMO,1); //전투 턴을 진행합니다.
		WinCheck.checkFight(simulMO); //전투 승패를 판정합니다.
		TakeAdventage.setAdv(simulMO); //전투 결과에 따른 어드밴티지를 결정합니다.
		ObjectSetting.setObj(simulMO); //이번 라운드의 오브젝트 관리 기능을 실행합니다.
		recodingSimulProcess(simulMO); //이번 라운드의 전체적 진행상황을 저장합니다.
		//다음 라운드 진행 여부를 판단합니다.
		if(WinCheck.checkRound(simulMO)) {
			roundLoop(simulMO); //다음 라운드 진행 판정시 다음 라운드를 시작합니다.
		}
	}

	//턴 실행 기능입니다.
	private void turnLoop(SimulMainObject simulMO, int i) {
		// TODO Auto-generated method stub
		InitSetting.initSetting(simulMO); //선제공격을 가할 팀을 정합니다.
		BattlePhase.battleStart(simulMO); //전투 기능을 시작합니다.
		//전투 턴 재실행 여부를 판단합니다.
		if(WinCheck.checkTurn(simulMO) && i<3) {
			turnLoop(simulMO, i++); //전투 턴 재실행 판정시 전투 턴을 재실행합니다.
		}
	}
	
	//라운드 전체적 진행상황 저장 기능입니다.
    private void recodingSimulProcess(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		
	}
}
