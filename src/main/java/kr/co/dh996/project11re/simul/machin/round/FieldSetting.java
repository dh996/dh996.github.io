package kr.co.dh996.project11re.simul.machin.round;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.machin.setting.SaveLogs;

@Component
public class FieldSetting {
	//전투 지형 설정 관련 작업을 수행합니다.
	private List<String> fieldList;
	private final Random random = new Random();

	//전투 지형 설정 기능 호출 메소드입니다.
	public void setField(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		setDefault(); //설정 가능 전투지형을 기본값으로 세팅합니다.
		roundCheck(simulMO.getUsingSimulProcess().getRound()); //이른 시점에 깊은 곳에서 전투를 치르지 않도록 조정합니다.
		towerCheck(simulMO); //구조물 파괴 여부에 따른 설정 가능 전투지형을 조정합니다.
		objectCheck(simulMO.getUsingSimulProcess().getDragonNest(),
				simulMO.getUsingSimulProcess().getBaronNest());
		    //오브젝트 소환 여부에 따라 설정 가능 전투지형을 조정합니다.
		simulMO.getUsingSimulProcess().setField(setField()); //최종 전투 지형을 설정합니다.
		SaveLogs.saveFieldLog(simulMO.getSid(), simulMO.getUsingSimulProcess().getRound(),
				simulMO.getSimulLogList(),simulMO.getUsingSimulProcess().getField());
		    //이번 라운드에 설정된 전투지형의 로그를 저장합니다.
	}

	//설정 가능 전투지형을 기본값으로 세팅합니다.
	private void setDefault() {
		// TODO Auto-generated method stub
		this.fieldList = new ArrayList<>();
		fieldList.add("freeze");
		fieldList.add("freeze");
		fieldList.add("freeze");
		fieldList.add("midLine");
		fieldList.add("topLine");
		fieldList.add("bottomLine");
		fieldList.add("baronNest");
		fieldList.add("dragonNest");
	}

	//이른 시점에 깊은 곳에서 전투를 치르지 않도록 조정합니다.
	private void roundCheck(int round) {
		// TODO Auto-generated method stub
		if(round>3) {
			fieldList.add("topFirstTowerU");
			fieldList.add("midFirstTowerU");
			fieldList.add("botFirstTowerU");
			fieldList.add("topFirstTowerE");
			fieldList.add("midFirstTowerE");
			fieldList.add("botFirstTowerE");
		}
	}

	//구조물 파괴 여부에 따른 설정 가능 전투지형을 조정합니다.
	private void towerCheck(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		checkAndSetField(simulMO.getUsingSimulProcess().getT1U(), "topSecondTowerU");
		checkAndSetField(simulMO.getUsingSimulProcess().getT2U(), "topThirdTowerU");
		checkAndSetField(simulMO.getUsingSimulProcess().getT3U(), "topInhibitorU");
		checkAndSetField(simulMO.getUsingSimulProcess().getTiU(), "baseU");
		checkAndSetField(simulMO.getUsingSimulProcess().getM1U(), "midSecondTowerU");
		checkAndSetField(simulMO.getUsingSimulProcess().getM2U(), "midThirdTowerU");
		checkAndSetField(simulMO.getUsingSimulProcess().getM3U(), "midInhibitorU");
		checkAndSetField(simulMO.getUsingSimulProcess().getMiU(), "baseU");
		checkAndSetField(simulMO.getUsingSimulProcess().getB1U(), "botSecondTowerU");
		checkAndSetField(simulMO.getUsingSimulProcess().getB2U(), "botThirdTowerU");
		checkAndSetField(simulMO.getUsingSimulProcess().getB3U(), "botInhibitorU");
		checkAndSetField(simulMO.getUsingSimulProcess().getBiU(), "baseU");
		checkAndSetField(simulMO.getUsingSimulProcess().getT1E(), "topSecondTowerE");
		checkAndSetField(simulMO.getUsingSimulProcess().getT2E(), "topThirdTowerE");
		checkAndSetField(simulMO.getUsingSimulProcess().getT3E(), "topInhibitorE");
		checkAndSetField(simulMO.getUsingSimulProcess().getTiE(), "baseE");
		checkAndSetField(simulMO.getUsingSimulProcess().getM1E(), "midSecondTowerE");
		checkAndSetField(simulMO.getUsingSimulProcess().getM2E(), "midThirdTowerE");
		checkAndSetField(simulMO.getUsingSimulProcess().getM3E(), "midInhibitorE");
		checkAndSetField(simulMO.getUsingSimulProcess().getMiE(), "baseE");
		checkAndSetField(simulMO.getUsingSimulProcess().getB1E(), "botSecondTowerE");
		checkAndSetField(simulMO.getUsingSimulProcess().getB2E(), "botThirdTowerE");
		checkAndSetField(simulMO.getUsingSimulProcess().getB3E(), "botInhibitorE");
		checkAndSetField(simulMO.getUsingSimulProcess().getBiE(), "baseE");
	}

	//구조물의 남은 체력을 확인한 뒤 해당하는 지형을 추가합니다.
	private void checkAndSetField(int hp, String field) {
		// TODO Auto-generated method stub
		if(hp == 0) {
			fieldList.add(field);
		}
	}

	//오브젝트 소환 여부에 따라 설정 가능 전투지형을 조정합니다.
	private void objectCheck(String dragonNest, int baronNest) {
		// TODO Auto-generated method stub
		if(!dragonNest.equals("x")) {
			fieldList.add("dragonNest");
			fieldList.add("dragonNest");
			fieldList.add("dragonNest");
		}
		if(baronNest == 1) {
			fieldList.add("baronNest");
			fieldList.add("baronNest");
			fieldList.add("baronNest");
		}
	}

	//최종 전투 지형을 설정합니다.
	private String setField() {
		// TODO Auto-generated method stub
		return fieldList.get(random.nextInt(fieldList.size()));
	}
}
