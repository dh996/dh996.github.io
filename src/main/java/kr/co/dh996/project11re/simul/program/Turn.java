package kr.co.dh996.project11re.simul.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.SimulLog;
import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.data.TurnData;
import kr.co.dh996.project11re.simul.data.UsingSimulData;
import kr.co.dh996.project11re.simul.machin.turn.TurnCheck;
import kr.co.dh996.project11re.simul.machin.turn.BattlePhase;
import kr.co.dh996.project11re.simul.machin.turn.InitSetting;

@Component
public class Turn {
	//턴 진행 관련 기능을 수행합니다.
	private final InitSetting initSetting;
    private final BattlePhase battlePhase;
    private final TurnCheck turnCheck;

    @Autowired
    public Turn(InitSetting initSetting, BattlePhase battlePhase,
    		TurnCheck turnCheck) {
        this.initSetting = initSetting;
        this.battlePhase = battlePhase;
        this.turnCheck = turnCheck;
    }
    
    //턴 실행 기능입니다.
  	public void turnStart(SimulMainObject simulMO) {
  		// TODO Auto-generated method stub
  		TurnData turnData = new TurnData(simulMO); //프로그램 가동에 필요한 요소들을 참조 인스턴스로 선언합니다.
  		turnLoop(simulMO, turnData, 1); //턴 루프를 실행합니다.
  	}
  	
  	public void turnLoop(SimulMainObject simulMO, TurnData turnData, int i) {
  		turnData.setInitTeam(initSetting.initSetting(
  				turnData.getUserTeam(), turnData.getEnemyTeam())); //선제공격을 가할 팀을 정합니다.
  		if(turnData.getInitTeam() == 0) { //전투 기능을 시작합니다. initTeam이 0이면 아군 선공,
  	  		battlePhase.battleStart(turnData, 0);
  		}else if(turnData.getInitTeam() == 1) { //initTeam이 1이면 적군 선공입니다.
  	  		battlePhase.battleStart(turnData, 1);
  		}
  		//전투 턴 재실행 여부를 판단합니다.
  		if(turnCheck.winCheck(turnData) == 2 && i<3) {
  			turnLoop(simulMO, turnData, i++); //전투 턴 재실행 판정시 전투 턴을 재실행합니다.
  		}
  	}
}
