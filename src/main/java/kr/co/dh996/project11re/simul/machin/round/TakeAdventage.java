package kr.co.dh996.project11re.simul.machin.round;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.BattlePower;
import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.data.UsingSimulData;
import kr.co.dh996.project11re.simul.machin.setting.HP;
import kr.co.dh996.project11re.simul.machin.turn.ExtractingValue;

@Component
public class TakeAdventage {
	//전투 결과에 따른 이점 분배를 실행합니다.
	
	private final ExtractingValue extractingValue;
	private List<UsingSimulData> winTeam;
	private int totalSP;
	
	@Autowired
	public TakeAdventage(ExtractingValue extractingValue) {
		this.extractingValue = extractingValue;
		this.winTeam = new ArrayList<>();
		this.totalSP = 0;
	}

	public void setAdv(SimulMainObject simulMO, int turnWin) {
		// TODO Auto-generated method stub
		setWinTeam(simulMO, turnWin); //승리 팀 정보를 가져옵니다.
		this.totalSP = getTotalSPByList(); //승리 팀 sp를 총합해 추출합니다.
		if(!setObjAdv(simulMO, turnWin)) { //오브젝트 분배 설정 기능을 수행합니다.
			setTowerAdv(simulMO, turnWin); //오브젝트를 먹지 않았으면 타워 공격을 실행합니다.
		}
	}

	//승리 팀 정보를 가져옵니다.
	private void setWinTeam(SimulMainObject simulMO, int turnWin) {
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
	private boolean setObjAdv(SimulMainObject simulMO, int turnWin) {
		// TODO Auto-generated method stub
		boolean getObj = false;
		if(totalSP>500 && "elder".equals(simulMO.getUsingSimulProcess().getDragonNest())) {
			getObj(simulMO, turnWin, "elder", 500);
			getObj = true;
		} //우선순위 1 장로
		if(totalSP>600 && simulMO.getUsingSimulProcess().getBaronNest() == 1) {
			getObj(simulMO, turnWin, "baron", 600);
			getObj = true;
		} //우선순위 2 바론
		if(totalSP>200 && !"x".equals(simulMO.getUsingSimulProcess().getDragonNest())) {
			getObj(simulMO, turnWin, "dragon", 200);
			getObj = true;
		} //우선순위 3 용
		return getObj;
	}

	private void getObj(SimulMainObject simulMO, int turnWin, String obj, int cost) {
		// TODO Auto-generated method stub
		this.totalSP -= cost;
		if("elder".equals(obj)){
			if(turnWin == 0) {
				simulMO.getUsingSimulProcess().setElderU(1);
			}else if(turnWin == 1) {
				simulMO.getUsingSimulProcess().setElderE(1);
			}
			simulMO.getUsingSimulProcess().setDragonNest("x");
		}
		if("baron".equals(obj)){
			if(turnWin == 0) {
				simulMO.getUsingSimulProcess().setBaronU(1);
			}else if(turnWin == 1) {
				simulMO.getUsingSimulProcess().setBaronE(1);
			}
			simulMO.getUsingSimulProcess().setBaronNest(0);
		}
		if("dragon".equals(obj)){
			if(turnWin == 0) {
				List<String> dragons = simulMO.getUsingSimulProcess().getDragonU();
				dragons.add(simulMO.getUsingSimulProcess().getDragonNest());
				simulMO.getUsingSimulProcess().setDragonU(dragons);
			}else if(turnWin == 1) {
				List<String> dragons = simulMO.getUsingSimulProcess().getDragonE();
				dragons.add(simulMO.getUsingSimulProcess().getDragonNest());
				simulMO.getUsingSimulProcess().setDragonE(dragons);
			}
			simulMO.getUsingSimulProcess().setDragonNest("x");
		}
	}

	//타워 공격 기능을 수행합니다.
	private void setTowerAdv(SimulMainObject simulMO, int turnWin) {
		// TODO Auto-generated method stub
		//지형 정보, 승리 팀 참조 어떤 타워 데이터 불러올지 결정
		String line = getLine(simulMO.getUsingSimulProcess().getField(), turnWin);
		//불러온 타워 데이터 참조 대미지 입히는 로직 작성
		attackTower(simulMO, line);
	}

	//지형 정보, 승리 팀 참조 어떤 타워 데이터 불러올지 결정
	private String getLine(String field, int turnWin) {
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
			return "top"+returnWinTeam(turnWin);
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
			return "bot"+returnWinTeam(turnWin);
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
			return "mid"+returnWinTeam(turnWin);
		}
	}

	private String returnWinTeam(int turnWin) {
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
			simulMO.getUsingSimulProcess().setT1U(checkAndAttack(
					simulMO.getUsingSimulProcess().getT1U()));
			simulMO.getUsingSimulProcess().setT2U(checkAndAttack(
					simulMO.getUsingSimulProcess().getT2U()));
			simulMO.getUsingSimulProcess().setT3U(checkAndAttack(
					simulMO.getUsingSimulProcess().getT3U()));
			simulMO.getUsingSimulProcess().setTiU(checkAndAttack(
					simulMO.getUsingSimulProcess().getTiU()));
			if(totalSP>0) { //억제기까지 까고도 sp가 남으면
				attackTower(simulMO, "baseU");
			}
		}else if("topE".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setT1E(checkAndAttack(
					simulMO.getUsingSimulProcess().getT1E()));
			simulMO.getUsingSimulProcess().setT2E(checkAndAttack(
					simulMO.getUsingSimulProcess().getT2E()));
			simulMO.getUsingSimulProcess().setT3E(checkAndAttack(
					simulMO.getUsingSimulProcess().getT3E()));
			simulMO.getUsingSimulProcess().setTiE(checkAndAttack(
					simulMO.getUsingSimulProcess().getTiE()));
			if(totalSP>0) { //억제기까지 까고도 sp가 남으면
				attackTower(simulMO, "baseE");
			}
		}else if("midU".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setM1U(checkAndAttack(
					simulMO.getUsingSimulProcess().getM1U()));
			simulMO.getUsingSimulProcess().setM2U(checkAndAttack(
					simulMO.getUsingSimulProcess().getM2U()));
			simulMO.getUsingSimulProcess().setM3U(checkAndAttack(
					simulMO.getUsingSimulProcess().getM3U()));
			simulMO.getUsingSimulProcess().setMiU(checkAndAttack(
					simulMO.getUsingSimulProcess().getMiU()));
			if(totalSP>0) { //억제기까지 까고도 sp가 남으면
				attackTower(simulMO, "baseU");
			}
		}else if("midE".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setM1E(checkAndAttack(
					simulMO.getUsingSimulProcess().getM1E()));
			simulMO.getUsingSimulProcess().setM2E(checkAndAttack(
					simulMO.getUsingSimulProcess().getM2E()));
			simulMO.getUsingSimulProcess().setM3E(checkAndAttack(
					simulMO.getUsingSimulProcess().getM3E()));
			simulMO.getUsingSimulProcess().setMiE(checkAndAttack(
					simulMO.getUsingSimulProcess().getMiE()));
			if(totalSP>0) { //억제기까지 까고도 sp가 남으면
				attackTower(simulMO, "baseE");
			}
		}else if("botU".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setB1U(checkAndAttack(
					simulMO.getUsingSimulProcess().getB1U()));
			simulMO.getUsingSimulProcess().setB2U(checkAndAttack(
					simulMO.getUsingSimulProcess().getB2U()));
			simulMO.getUsingSimulProcess().setB3U(checkAndAttack(
					simulMO.getUsingSimulProcess().getB3U()));
			simulMO.getUsingSimulProcess().setBiU(checkAndAttack(
					simulMO.getUsingSimulProcess().getBiU()));
			if(totalSP>0) { //억제기까지 까고도 sp가 남으면
				attackTower(simulMO, "baseU");
			}
		}else if("botE".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setB1E(checkAndAttack(
					simulMO.getUsingSimulProcess().getB1E()));
			simulMO.getUsingSimulProcess().setB2E(checkAndAttack(
					simulMO.getUsingSimulProcess().getB2E()));
			simulMO.getUsingSimulProcess().setB3E(checkAndAttack(
					simulMO.getUsingSimulProcess().getB3E()));
			simulMO.getUsingSimulProcess().setBiE(checkAndAttack(
					simulMO.getUsingSimulProcess().getBiE()));
			if(totalSP>0) { //억제기까지 까고도 sp가 남으면
				attackTower(simulMO, "baseE");
			}
		}else if("baseU".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setLtU(checkAndAttack(
					simulMO.getUsingSimulProcess().getLtU()));
			simulMO.getUsingSimulProcess().setRtU(checkAndAttack(
					simulMO.getUsingSimulProcess().getRtU()));
			simulMO.getUsingSimulProcess().setNexusU(checkAndAttack(
					simulMO.getUsingSimulProcess().getNexusU()));
		}else if("baseE".equals(line)) {
			//타워를 공격하고 sp가 남으면 다음 타워를 순차적으로 공격
			simulMO.getUsingSimulProcess().setLtE(checkAndAttack(
					simulMO.getUsingSimulProcess().getLtE()));
			simulMO.getUsingSimulProcess().setRtE(checkAndAttack(
					simulMO.getUsingSimulProcess().getRtE()));
			simulMO.getUsingSimulProcess().setNexusE(checkAndAttack(
					simulMO.getUsingSimulProcess().getNexusE()));
		}
	}

	//타워의 남은 체력 반환
	private int checkAndAttack(int hp) {
		// TODO Auto-generated method stub
		if(totalSP>0) {
			if(HP.checkHP(hp)) {
				int rHp = HP.returnHP(hp, totalSP);
				int dmg = hp-rHp;
				this.totalSP = HP.returnHP(totalSP, dmg);
				//returnHP매소드 사용이유 = 0보다 아래로 내려갈 경우를 배제한 메소드기 때문
				return rHp;
			}else {
				return hp;
			}
		}else {
			return hp;
		}
	}
}
