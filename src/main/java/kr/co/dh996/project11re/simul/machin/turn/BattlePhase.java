package kr.co.dh996.project11re.simul.machin.turn;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.BattlePower;
import kr.co.dh996.project11re.simul.data.TurnData;
import kr.co.dh996.project11re.simul.data.UsingSimulData;
import kr.co.dh996.project11re.simul.machin.round.extendPT.PositionTerminal;
import kr.co.dh996.project11re.simul.machin.setting.HP;
import kr.co.dh996.project11re.simul.machin.setting.SaveLogs;
import kr.co.dh996.project11re.simul.machin.turn.extendsDmgCalc.DmgTerminal;

@Component
public class BattlePhase {
	
	private DmgTerminal dmgTerminal;
	private final PositionTerminal positionTerminal;
	private final ExtractingValue extractingValue;
	private String sid;
	private int round;
	private int initTeam;
	private int attackTeam;
	private int attackChamp;
	private int deffendChamp;
	private Queue<Integer> attackTeamList;
	
	@Autowired
	public BattlePhase(DmgTerminal dmgTerminal, 
			PositionTerminal positionTerminal, ExtractingValue extractingValue) {
		this.dmgTerminal = dmgTerminal;
		this.positionTerminal = positionTerminal;
        this.extractingValue = extractingValue;
		this.round = 0;
		this.sid = null;
        this.initTeam = 0;
        this.attackTeam = 0;
        this.attackChamp = 0;
        this.deffendChamp = 0;
        this.attackTeamList = new LinkedList<>();
	}

	public void battleStart(TurnData turnData, int round, String sid, boolean towerA) {
		// TODO Auto-generated method stub
		this.round = round;
		this.sid = sid;
		this.initTeam = extractingValue.teamJudgment(
  				turnData.getUserTeam(), turnData.getEnemyTeam(), BattlePower::getInitPower); //선제공격을 가할 팀을 정합니다.
		SaveLogs.saveInitLog(sid, round, turnData.getTurnLog(),
				turnData.getTurnLog().size()+turnData.getLogSize(),
				initTeam); //선제공격 팀 로그를 저장합니다.
  		if(initTeam == 0) { //선제공격 여부에 따른 유불리를 설정합니다.
  			initDemeritte(turnData.getEnemyTeam()); //아군 선공시 적에게 불리를
  		}else if(initTeam == 1) {
  			initDemeritte(turnData.getUserTeam()); //적군 선공시 아군에게 불리를
  		}
  		if(towerA) {
  			diveDemeritte(turnData); //전투지형 타워 생존여부에 따른 유불리를 설정합니다.
  		}
  		do {
  		    battleProcess(turnData); //전투 과정을 실행합니다.
  		}while(turnCheck(turnData)); //전투 종료를 판정합니다.
	}

	//선제공격 여부에 따른 유불리를 설정합니다.
	private void initDemeritte(List<UsingSimulData> data) {
		// TODO Auto-generated method stub
		for(int i=0; i<data.size(); i++) {
			positionTerminal.getInitDemerit(data.get(i));
		}
	}

	//타워 생존여부에 따른 유불리를 설정합니다.
	private void diveDemeritte(TurnData turnData) {
		// TODO Auto-generated method stub
		int diveCheck = DiveCheck.diveCheck(turnData.getField());
		if(diveCheck == 0) {
			diveDemeritte(turnData.getEnemyTeam());
		}else if(diveCheck == 1) {
			diveDemeritte(turnData.getUserTeam());
		}
		if(diveCheck != 2) {
			SaveLogs.saveDiveLog(sid, round, turnData.getTurnLog(),
					turnData.getTurnLog().size()+turnData.getLogSize(),
					diveCheck);
		}
	}

	private void diveDemeritte(List<UsingSimulData> data) {
		// TODO Auto-generated method stub
		for(int i=0; i<data.size(); i++) {
			data.get(i).getBattlePower().setHp(
					(data.get(i).getBattlePower().getHp()*9)/10);
		}
	}

	//전투 과정 1회의 내용에 해당합니다.
	private void battleProcess(TurnData turnData) {
		// TODO Auto-generated method stub
		setAttackTeam(turnData); //어떤 팀의 유닛이 공격하는지 정합니다.
		if(this.attackTeam == 0) { //아군 선공일 경우
			setAttackChampion(turnData.getUserTeam()); //어떤 유닛이 공격하는지 정합니다.
			attackSpeedCounting(turnData.getUserTeam()); //공격하는 유닛의 남은 공격 횟수를 조정합니다.
			setDeffendChampion(turnData.getEnemyTeam()); //어떤 유닛이 공격당하는지 정합니다.
		}else if(this.attackTeam == 1) { //적군 선공일 경우
			setAttackChampion(turnData.getEnemyTeam()); //어떤 유닛이 공격하는지 정합니다.
			attackSpeedCounting(turnData.getEnemyTeam()); //공격하는 유닛의 남은 공격 횟수를 조정합니다.
			setDeffendChampion(turnData.getUserTeam()); //어떤 유닛이 공격당하는지 정합니다.
		}
		attack(turnData, getDmg(turnData)); //공격을 실행합니다.
	}

	//어떤 팀의 유닛이 공격하는지 정합니다.
	private void setAttackTeam(TurnData turnData) {
		// TODO Auto-generated method stub
		do {
	        this.attackTeam = extractingValue.teamJudgment(
	                turnData.getUserTeam(), turnData.getEnemyTeam(), BattlePower::getAttackSpeed); // 공격 팀을 선정합니다.
	    } while (checkBattleBalance(attackTeam, turnData)); //공격 팀 확정 여부를 판단합니다.
		attackTeamList.add(attackTeam);
	}

	//공격 팀 확정 여부를 판단합니다.
	private boolean checkBattleBalance(int attackTeam, TurnData turnData) {
		// TODO Auto-generated method stub
		if(checkBattleBalance(turnData)) { //공격횟수가 남지 않은 팀이 있는지 우선검사합니다.
			return true;
		}else if(checkBattleBalance(attackTeam)) { //한쪽 팀에서만 일방적인 공격이 나오는 것을 방지합니다.
			return true;
		}else {
			return false;
		}
	}

	//공격횟수가 남지 않은 팀이 있는지 우선검사합니다.
	private boolean checkBattleBalance(TurnData turnData) {
		// TODO Auto-generated method stub
		if(extractingValue.unitJudgment(turnData.getUserTeam(), BattlePower::getAttackSpeed) == 0) {
			return true;
		}else if(extractingValue.unitJudgment(turnData.getEnemyTeam(), BattlePower::getAttackSpeed) == 0){
			return true;
		}else {
			return false;
		}
	}

	//한쪽 팀에서만 일방적인 공격이 나오는 것을 방지합니다.
	private boolean checkBattleBalance(int attackTeam) {
		// TODO Auto-generated method stub
		int flag = attackTeam;
		if(attackTeamList.size()>4) {
			for (Integer element : attackTeamList) {
	            flag += element;
	        }
			if(flag<2) {
				return false;
			}else if(flag>4) {
				return false;
			}else {
				attackTeamList.remove();
				return true;
			}
		}
		return true;
	}

	//어떤 유닛이 공격하는지 정합니다.
	private void setAttackChampion(List<UsingSimulData> list) {
		// TODO Auto-generated method stub
		this.attackChamp = extractingValue.unitJudgment(list, BattlePower::getAttackSpeed);
	}

	//공격하는 유닛의 남은 공격 횟수를 조정합니다.
	private void attackSpeedCounting(List<UsingSimulData> list) {
		// TODO Auto-generated method stub
		if(list.get(attackChamp).getBattlePower().getAttackSpeed()>0) {
			list.get(attackChamp).getBattlePower().setAttackSpeed(
					list.get(attackChamp).getBattlePower().getAttackSpeed()-1);
		}
	}

	//어떤 유닛이 공격당하는지 정합니다.
	private void setDeffendChampion(List<UsingSimulData> list) {
		// TODO Auto-generated method stub
		this.deffendChamp = extractingValue.unitJudgment(list, BattlePower::getAggroGauge);
	}

	//대미지를 구합니다.
	private int getDmg(TurnData turnData) {
		// TODO Auto-generated method stub
		if(this.attackTeam == 0) {
			return dmgTerminal.returnDmg(turnData.getUserTeam().get(attackChamp));
		}else {
			return dmgTerminal.returnDmg(turnData.getEnemyTeam().get(attackChamp));
		}
	}

	//공격을 실행합니다.
	private void attack(TurnData turnData, int dmg) {
		// TODO Auto-generated method stub
		if(this.attackTeam == 0) { //아군 공격일때
			attack(turnData.getEnemyTeam().get(deffendChamp), dmg);
			SaveLogs.saveAttackLog(sid, round, turnData.getTurnLog(),
					turnData.getTurnLog().size()+turnData.getLogSize(),
					turnData.getUserTeam().get(attackChamp).getChampName(),
					turnData.getEnemyTeam().get(deffendChamp).getChampName(),
					Integer.toString(dmg), attackTeam);
			if(checkAlive(turnData.getEnemyTeam().get(deffendChamp), turnData)) {//피격 후 사망여부를 판단합니다.
				reflectingScore(turnData.getUserTeam(),
						turnData.getEnemyTeam().get(deffendChamp), turnData.getKillU());
				//득점상황을 프로그램에 반영합니다.
			}
		}else if(this.attackTeam == 1){ //적군 공격일때
			attack(turnData.getUserTeam().get(deffendChamp), dmg);
			SaveLogs.saveAttackLog(sid, round, turnData.getTurnLog(),
					turnData.getTurnLog().size()+turnData.getLogSize(),
					turnData.getEnemyTeam().get(attackChamp).getChampName(),
					turnData.getUserTeam().get(deffendChamp).getChampName(),
					Integer.toString(dmg), attackTeam);
			if(checkAlive(turnData.getUserTeam().get(deffendChamp), turnData)) {
				reflectingScore(turnData.getEnemyTeam(),
						turnData.getUserTeam().get(deffendChamp), turnData.getKillE());
			}
		}
	}

	//공격시 hp적용 메서드입니다. get,set으로만 표현하니 가독성이 너무 심하게 망가져서 새 메소드를 만들었습니다.
	private void attack(UsingSimulData uSD, int dmg) {
		// TODO Auto-generated method stub
		uSD.getBattlePower().setHp(HP.returnHP(uSD.getBattlePower().getHp(), dmg));
	}

	//피격 후 사망여부를 판단하여 로그로 저장합니다.
	private boolean checkAlive(UsingSimulData usingSimulData, TurnData turnData) {
		// TODO Auto-generated method stub
		if(!RetireCheck.retireCheck(usingSimulData)) { //사망시 로그를 기록합니다.
			SaveLogs.saveDeathLog(sid, round, turnData.getTurnLog(),
					turnData.getTurnLog().size()+turnData.getLogSize(),
					usingSimulData.getChampName(), attackTeam);
			return true;
		}else {
			return false;
		}
	}

	//득점상황을 프로그램에 반영합니다.
	private void reflectingScore(List<UsingSimulData> attackTeam, UsingSimulData defChamp, int kill) {
		// TODO Auto-generated method stub
		kill++;
		for(int i=0; i<attackTeam.size(); i++) {
			if(RetireCheck.retireCheck(attackTeam.get(i)) && i != attackChamp){
				attackTeam.get(i).setAssist(attackTeam.get(i).getAssist()+1);
			}else if(i == attackChamp) {
				attackTeam.get(i).setKill(attackTeam.get(i).getKill()+1);
			}
		}
		defChamp.setDeath(defChamp.getDeath()+1);
	}

	//전투 종료 여부를 판정합니다.
	private boolean turnCheck(TurnData turnData) {
		// TODO Auto-generated method stub
		return false;
	}
}
