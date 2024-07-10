package kr.co.dh996.project11re.simul.machin.round;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.BattlePower;
import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.data.UsingSimulData;
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
		//불러온 타워 데이터 참조 대미지 입히는 로직 작성
		//타워를 공격하고 sp가 남았을 경우 다음 타워 공격
	}
}
