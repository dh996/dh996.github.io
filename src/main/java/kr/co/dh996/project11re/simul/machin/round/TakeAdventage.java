package kr.co.dh996.project11re.simul.machin.round;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.BattlePower;
import kr.co.dh996.project11re.simul.data.SimulLog;
import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.data.UsingSimulData;
import kr.co.dh996.project11re.simul.machin.setting.HP;
import kr.co.dh996.project11re.simul.machin.setting.SaveLogs;
import kr.co.dh996.project11re.simul.machin.turn.ExtractingValue;

@Component
public class TakeAdventage {
	//전투 결과에 따른 이점 분배를 실행합니다.
	
	private final ExtractingValue extractingValue;
	private List<UsingSimulData> winTeam;
	private int turnWin;
	private int totalSP;
	private int round;
	private String sid;
	
	@Autowired
	public TakeAdventage(ExtractingValue extractingValue) {
		this.extractingValue = extractingValue;
		this.winTeam = new ArrayList<>();
		this.turnWin = 0;
		this.totalSP = 0;
		this.round = 0;
		this.sid = null;
	}

	public void setAdv(SimulMainObject simulMO, int turnWin) {
		// TODO Auto-generated method stub
		init(simulMO, turnWin); //기본 변수를 대입합니다.
		setWinTeam(simulMO); //승리 팀 정보를 가져옵니다.
		this.totalSP = getTotalSPByList(); //승리 팀 sp를 총합해 추출합니다.
		if(!setObjAdv(simulMO)) { //오브젝트 분배 설정 기능을 수행합니다.
			setTowerAdv(simulMO); //오브젝트를 먹지 않았으면 타워 공격을 실행합니다.
		}
	}

	private void init(SimulMainObject simulMO, int turnWin) {
		// TODO Auto-generated method stub
		this.round = simulMO.getUsingSimulProcess().getRound();
		this.turnWin = turnWin;
		this.sid = simulMO.getSid();
	}

	//승리 팀 정보를 가져옵니다.
	private void setWinTeam(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		for(int i=0; i<simulMO.getSimulDataList().size(); i++) {
			if((turnWin == 0 && simulMO.getSimulDataList().get(i).getTeam() == 0) ||
				    (turnWin == 1 && simulMO.getSimulDataList().get(i).getTeam() == 1)) {
				winTeam.add(simulMO.getSimulDataList().get(i));
			}
		}
	}

	//승리 팀 sp를 총합해 추출합니다.
	private int getTotalSPByList() {
		// TODO Auto-generated method stub
		return extractingValue.checkPower(winTeam, BattlePower::getSiegePower);
	}

	//오브젝트 분배 설정 기능을 수행합니다.
	private boolean setObjAdv(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		boolean getObj = false;
		if(totalSP>500 && "elder".equals(simulMO.getUsingSimulProcess().getDragonNest())) {
			getObj(simulMO, "elder", 500);
			getObj = true;
		} //우선순위 1 장로
		if(totalSP>600 && simulMO.getUsingSimulProcess().getBaronNest() == 1) {
			getObj(simulMO, "baron", 600);
			getObj = true;
		} //우선순위 2 바론
		if(totalSP>200 && !"x".equals(simulMO.getUsingSimulProcess().getDragonNest())) {
			getObj(simulMO, "dragon", 200);
			getObj = true;
		} //우선순위 3 용
		return getObj;
	}

	private void getObj(SimulMainObject simulMO, String obj, int cost) {
		// TODO Auto-generated method stub
		this.totalSP -= cost;
		if("elder".equals(obj)){
			if(turnWin == 0) {
				simulMO.getUsingSimulProcess().setElderU(1);
				SaveLogs.saveHuntingLog(sid, round, simulMO.getSimulLogList(), "elder dragon", turnWin);
			}else if(turnWin == 1) {
				simulMO.getUsingSimulProcess().setElderE(1);
				SaveLogs.saveHuntingLog(sid, round, simulMO.getSimulLogList(), "elder dragon", turnWin);
			}
			simulMO.getUsingSimulProcess().setDragonNest("x");
		}
		if("baron".equals(obj)){
			if(turnWin == 0) {
				simulMO.getUsingSimulProcess().setBaronU(1);
				SaveLogs.saveHuntingLog(sid, round, simulMO.getSimulLogList(), "baron", turnWin);
			}else if(turnWin == 1) {
				simulMO.getUsingSimulProcess().setBaronE(1);
				SaveLogs.saveHuntingLog(sid, round, simulMO.getSimulLogList(), "baron", turnWin);
			}
			simulMO.getUsingSimulProcess().setBaronNest(0);
		}
		if("dragon".equals(obj)){
			if(turnWin == 0) {
				List<String> dragons = simulMO.getUsingSimulProcess().getDragonU();
				dragons.add(simulMO.getUsingSimulProcess().getDragonNest());
				simulMO.getUsingSimulProcess().setDragonU(dragons);
				SaveLogs.saveHuntingLog(sid, round, simulMO.getSimulLogList(),
						simulMO.getUsingSimulProcess().getDragonNest()+"dragon", turnWin);
			}else if(turnWin == 1) {
				List<String> dragons = simulMO.getUsingSimulProcess().getDragonE();
				dragons.add(simulMO.getUsingSimulProcess().getDragonNest());
				simulMO.getUsingSimulProcess().setDragonE(dragons);
				SaveLogs.saveHuntingLog(sid, round, simulMO.getSimulLogList(),
						simulMO.getUsingSimulProcess().getDragonNest()+"dragon", turnWin);
			}
			simulMO.getUsingSimulProcess().setDragonNest("x");
		}
	}

	//타워 공격 기능을 수행합니다.
	private void setTowerAdv(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		//지형 정보, 승리 팀 참조 어떤 타워 데이터 불러올지 결정
		String line = getLine(simulMO.getUsingSimulProcess().getField());
		//불러온 타워 데이터 참조 대미지 입히는 로직 작성
		attackTower(simulMO, line);
	}

	//지형 정보, 승리 팀 참조 어떤 타워 데이터 불러올지 결정
	private String getLine(String field) {
		// TODO Auto-generated method stub
		if("topLine".equals(field)
				|| "topFirstTowerU".equals(field)
				|| "topSecondTowerU".equals(field)
				|| "topThirdTowerU".equals(field)
				|| "topInhibitorU".equals(field)
				|| "topFirstTowerE".equals(field)
				|| "topSecondTowerE".equals(field)
				|| "topThirdTowerE".equals(field)
				|| "topInhibitorE".equals(field)
				) { //전투지형 탑라인인경우
			return "top"+returnWinTeam();
		}else if("botLine".equals(field)
				|| "botFirstTowerU".equals(field)
				|| "botSecondTowerU".equals(field)
				|| "botThirdTowerU".equals(field)
				|| "botInhibitorU".equals(field)
				|| "botFirstTowerE".equals(field)
				|| "botSecondTowerE".equals(field)
				|| "botThirdTowerE".equals(field)
				|| "botInhibitorE".equals(field)
				) { //전투지형 봇라인인경우
			return "bot"+returnWinTeam();
		}else if("baseU".equals(field)) { //전투지형 아군 진지
			if(turnWin == 0) {
				return "midE"; //아군이 승리시 적군 미드 공격
			}else {
				return "baseU"; //적군이 승리시 아군 진지 공격
			}
		}else if("baseE".equals(field)) { //전투지형 적군 진지
			if(turnWin == 0) {
				return "baseE"; //아군이 승리시 적군 진지 공격
			}else {
				return "midU"; //적군이 승리시 아군 미드 공격
			}
		}else { //이외 전투지형
			return "mid"+returnWinTeam();
		}
	}

	private String returnWinTeam() {
		// TODO Auto-generated method stub
		if(turnWin == 0) {
			return "E"; //아군이 승리시 적 타워 공격
		}else {
			return "U"; //적군이 승리시 아군 타워 공격
		}
	}

	//불러온 타워 데이터 참조 대미지 입히는 로직 작성
	private void attackTower(SimulMainObject simulMO, String line) {
		// TODO Auto-generated method stub
		if("topU".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setT1U(checkAndAttack("탑 1차 타워",
					simulMO.getUsingSimulProcess().getT1U(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setT2U(checkAndAttack("탑 2차 타워",
					simulMO.getUsingSimulProcess().getT2U(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setT3U(checkAndAttack("탑 3차 타워",
					simulMO.getUsingSimulProcess().getT3U(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setTiU(checkAndAttack("탑 억제기",
					simulMO.getUsingSimulProcess().getTiU(), simulMO.getSimulLogList()));
			if(totalSP>0) { //억제기까지 까고도 sp가 남으면
				attackTower(simulMO, "baseU");
			}
		}else if("topE".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setT1E(checkAndAttack("탑 1차 타워",
					simulMO.getUsingSimulProcess().getT1E(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setT2E(checkAndAttack("탑 2차 타워",
					simulMO.getUsingSimulProcess().getT2E(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setT3E(checkAndAttack("탑 3차 타워",
					simulMO.getUsingSimulProcess().getT3E(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setTiE(checkAndAttack("탑 억제기",
					simulMO.getUsingSimulProcess().getTiE(), simulMO.getSimulLogList()));
			if(totalSP>0) { //억제기까지 까고도 sp가 남으면
				attackTower(simulMO, "baseE");
			}
		}else if("midU".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setM1U(checkAndAttack("미드 1차 타워",
					simulMO.getUsingSimulProcess().getM1U(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setM2U(checkAndAttack("미드 2차 타워",
					simulMO.getUsingSimulProcess().getM2U(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setM3U(checkAndAttack("미드 3차 타워",
					simulMO.getUsingSimulProcess().getM3U(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setMiU(checkAndAttack("미드 억제기",
					simulMO.getUsingSimulProcess().getMiU(), simulMO.getSimulLogList()));
			if(totalSP>0) { //억제기까지 까고도 sp가 남으면
				attackTower(simulMO, "baseU");
			}
		}else if("midE".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setM1E(checkAndAttack("미드 1차 타워",
					simulMO.getUsingSimulProcess().getM1E(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setM2E(checkAndAttack("미드 2차 타워",
					simulMO.getUsingSimulProcess().getM2E(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setM3E(checkAndAttack("미드 3차 타워",
					simulMO.getUsingSimulProcess().getM3E(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setMiE(checkAndAttack("미드 억제기",
					simulMO.getUsingSimulProcess().getMiE(), simulMO.getSimulLogList()));
			if(totalSP>0) { //억제기까지 까고도 sp가 남으면
				attackTower(simulMO, "baseE");
			}
		}else if("botU".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setB1U(checkAndAttack("봇 1차 타워",
					simulMO.getUsingSimulProcess().getB1U(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setB2U(checkAndAttack("봇 2차 타워",
					simulMO.getUsingSimulProcess().getB2U(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setB3U(checkAndAttack("봇 3차 타워",
					simulMO.getUsingSimulProcess().getB3U(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setBiU(checkAndAttack("봇 억제기",
					simulMO.getUsingSimulProcess().getBiU(), simulMO.getSimulLogList()));
			if(totalSP>0) { //억제기까지 까고도 sp가 남으면
				attackTower(simulMO, "baseU");
			}
		}else if("botE".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setB1E(checkAndAttack("봇 1차 타워",
					simulMO.getUsingSimulProcess().getB1E(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setB2E(checkAndAttack("봇 2차 타워",
					simulMO.getUsingSimulProcess().getB2E(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setB3E(checkAndAttack("봇 3차 타워",
					simulMO.getUsingSimulProcess().getB3E(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setBiE(checkAndAttack("봇 억제기",
					simulMO.getUsingSimulProcess().getBiE(), simulMO.getSimulLogList()));
			if(totalSP>0) { //억제기까지 까고도 sp가 남으면
				attackTower(simulMO, "baseE");
			}
		}else if("baseU".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setLtU(checkAndAttack("왼쪽 쌍둥이 타워",
					simulMO.getUsingSimulProcess().getLtU(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setRtU(checkAndAttack("오른쪽 쌍둥이 타워",
					simulMO.getUsingSimulProcess().getRtU(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setNexusU(checkAndAttack("넥서스",
					simulMO.getUsingSimulProcess().getNexusU(), simulMO.getSimulLogList()));
		}else if("baseE".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setLtE(checkAndAttack("왼쪽 쌍둥이 타워",
					simulMO.getUsingSimulProcess().getLtE(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setRtE(checkAndAttack("오른쪽 쌍둥이 타워",
					simulMO.getUsingSimulProcess().getRtE(), simulMO.getSimulLogList()));
			simulMO.getUsingSimulProcess().setNexusE(checkAndAttack("넥서스",
					simulMO.getUsingSimulProcess().getNexusE(), simulMO.getSimulLogList()));
		}
	}

	//타워의 남은 체력 반환
	private int checkAndAttack(String tower, int hp, List<SimulLog> list) {
		// TODO Auto-generated method stub
		if(totalSP>0) {
			if(HP.checkHP(hp)) {
				int rHp = HP.returnHP(hp, totalSP);
				int dmg = hp-rHp;
				this.totalSP = HP.returnHP(totalSP, dmg);
				//returnHP매소드 사용이유 = 0보다 아래로 내려갈 경우를 배제한 메소드기 때문
				SaveLogs.saveSiegeLog(sid, round, list, tower, Integer.toString(dmg), turnWin);
				if(rHp == 0) { //남은 체력이 없으면 파괴 로그도 저장합니다.
					SaveLogs.saveDestroyLog(sid, round, list, tower, turnWin);
				}
				return rHp;
			}else {
				return hp;
			}
		}else {
			return hp;
		}
	}
}
