package kr.co.dh996.project11re.simul.program;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.machin.turn.TurnCheck;
import kr.co.dh996.project11re.simul.machin.turn.BattlePhase;
import kr.co.dh996.project11re.simul.machin.turn.InitSetting;

public class Turn {
	//턴 진행 관련 기능을 수행합니다.

	private final InitSetting initSetting;
    private final BattlePhase battlePhase;
    private final TurnCheck turnCheck;

    @Autowired
    public Turn(InitSetting initSetting, BattlePhase battlePhase, TurnCheck turnCheck) {
        this.initSetting = initSetting;
        this.battlePhase = battlePhase;
        this.turnCheck = turnCheck;
    }
    
    //턴 실행 기능입니다.
  	public void turnLoop(SimulMainObject simulMO, int i) {
  		// TODO Auto-generated method stub
  		initSetting.initSetting(simulMO); //선제공격을 가할 팀을 정합니다.
  		battlePhase.battleStart(simulMO); //전투 기능을 시작합니다.
  		//전투 턴 재실행 여부를 판단합니다.
  		if(turnCheck.winCheck(simulMO) && i<3) {
  			turnLoop(simulMO, i++); //전투 턴 재실행 판정시 전투 턴을 재실행합니다.
  		}
  	}
}
