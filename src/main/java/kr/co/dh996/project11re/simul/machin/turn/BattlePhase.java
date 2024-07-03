package kr.co.dh996.project11re.simul.machin.turn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.BattlePower;
import kr.co.dh996.project11re.simul.data.TurnData;
import kr.co.dh996.project11re.simul.data.UsingSimulData;
import kr.co.dh996.project11re.simul.machin.round.extendPT.PositionTerminal;
import kr.co.dh996.project11re.simul.machin.turn.extendsDmgCalc.DmgTerminal;

@Component
public class BattlePhase {
	
	private DmgTerminal dmgTerminal;
	private final PositionTerminal positionTerminal;
	private final ExtractingValue extractingValue;
	private int initTeam;
	private int attackTeam;
	private int attackChamp;
	
	@Autowired
	public BattlePhase(DmgTerminal dmgTerminal, 
			PositionTerminal positionTerminal, ExtractingValue extractingValue) {
		this.dmgTerminal = dmgTerminal;
		this.positionTerminal = positionTerminal;
        this.extractingValue = extractingValue;
        this.initTeam = 0;
        this.attackTeam = 0;
        this.attackChamp = 0;
	}

	public void battleStart(TurnData turnData) {
		// TODO Auto-generated method stub
		this.initTeam = extractingValue.teamJudgment(
  				turnData.getUserTeam(), turnData.getEnemyTeam(), BattlePower::getInitPower); //선제공격을 가할 팀을 정합니다.
  		if(initTeam == 0) { //선제공격 여부에 따른 유불리를 설정합니다.
  			initDemeritte(turnData.getEnemyTeam()); //아군 선공시 적에게 불리를
  		}else if(initTeam == 1) {
  			initDemeritte(turnData.getUserTeam()); //적군 선공시 아군에게 불리를
  		}
  		do {
  		    battleProcess(turnData); //전투 과정을 실행합니다.
  		}while(turnCheck());
	}

	//선제공격 여부에 따른 유불리를 설정합니다.
	private void initDemeritte(List<UsingSimulData> data) {
		// TODO Auto-generated method stub
		for(int i=0; i<data.size(); i++) {
			positionTerminal.getDemerit(data.get(i));
		}
	}

	//전투 과정 1회의 내용에 해당합니다.
	private void battleProcess(TurnData turnData) {
		// TODO Auto-generated method stub
		setAttackTeam(turnData); //어떤 팀의 유닛이 공격하는지 정합니다.
		setAttackChampion(turnData); //어떤 유닛이 공격하는지 정합니다.
	}

	//어떤 팀의 유닛이 공격하는지 정합니다.
	private void setAttackTeam(TurnData turnData) {
		// TODO Auto-generated method stub
		do {
	        this.attackTeam = extractingValue.teamJudgment(
	                turnData.getUserTeam(), turnData.getEnemyTeam(), BattlePower::getAttackSpeed); // 공격 팀을 선정합니다.
	    } while (checkBattleBalance()); //한쪽 팀에서만 일방적인 공격이 나오는 것을 방지합니다.
	}

	//어떤 유닛이 공격하는지 정합니다.
	private void setAttackChampion(TurnData turnData) {
		// TODO Auto-generated method stub
		if(this.attackTeam == 0) {
			this.attackChamp = extractingValue.unitJudgment(turnData.getUserTeam(), BattlePower::getAttackSpeed);
		}else if(this.attackTeam == 1) {
			this.attackChamp = extractingValue.unitJudgment(turnData.getEnemyTeam(), BattlePower::getAttackSpeed);
		}
	}

	//한쪽 팀에서만 일방적인 공격이 나오는 것을 방지합니다.
	private boolean checkBattleBalance() {
		// TODO Auto-generated method stub
		return false;
	}

}
