package kr.co.dh996.project11re.simul.machin.turn;

import kr.co.dh996.project11re.simul.data.UsingSimulProcess;
import kr.co.dh996.project11re.simul.machin.setting.HP;

public class DiveCheck {
	//타워 다이브 여부를 판단 관련 기능들을 수행합니다.
	
	public static int diveCheck(String field) {
		if("topFirstTowerU".equals(field) ||
		        "midFirstTowerU".equals(field) ||
	            "botFirstTowerU".equals(field) ||
	            "topSecondTowerU".equals(field) ||
	            "topThirdTowerU".equals(field) ||
		        "midSecondTowerU".equals(field) ||
		        "midThirdTowerU".equals(field) ||
		        "botSecondTowerU".equals(field) ||
		        "botThirdTowerU".equals(field) ||
		        "baseU".equals(field)) {
			return 0;
		}else if("topFirstTowerE".equals(field) ||
			    "midFirstTowerE".equals(field) ||
				"botFirstTowerE".equals(field) ||
				"topSecondTowerE".equals(field) ||
				"topThirdTowerE".equals(field) ||
			    "midSecondTowerE".equals(field) ||
			    "midThirdTowerE".equals(field) ||
			    "botSecondTowerE".equals(field) ||
			    "botThirdTowerE".equals(field) ||
			    "baseE".equals(field)) {
			return 1;
		}else {
			return 2;
		}
	}

	public static boolean checkTower(UsingSimulProcess usingSimulProcess) {
		// TODO Auto-generated method stub
		if("topFirstTowerU".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getT1U())) {
			return true;
		}else if("topSecondTowerU".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getT2U())){
			return true;
		}else if("topThirdTowerU".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getT3U())){
			return true;
		}else if("midFirstTowerU".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getM1U())){
			return true;
		}else if("midSecondTowerU".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getM2U())){
			return true;
		}else if("midThirdTowerU".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getM3U())){
			return true;
		}else if("botFirstTowerU".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getB1U())){
			return true;
		}else if("botSecondTowerU".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getB2U())){
			return true;
		}else if("botThirdTowerU".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getB3U())){
			return true;
		}else if("baseU".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getLtU())){
			return true;
		}else if("baseU".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getRtU())){
			return true;
		}else if("topFirstTowerE".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getT1E())) {
			return true;
		}else if("topSecondTowerE".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getT2E())){
			return true;
		}else if("topThirdTowerE".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getT3E())){
			return true;
		}else if("midFirstTowerE".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getM1E())){
			return true;
		}else if("midSecondTowerE".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getM2E())){
			return true;
		}else if("midThirdTowerE".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getM3E())){
			return true;
		}else if("botFirstTowerE".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getB1E())){
			return true;
		}else if("botSecondTowerE".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getB2E())){
			return true;
		}else if("botThirdTowerE".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getB3E())){
			return true;
		}else if("baseE".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getLtE())){
			return true;
		}else if("baseE".equals(usingSimulProcess.getField())
				&& HP.checkHP(usingSimulProcess.getRtE())){
			return true;
		}else {
			return false;
		}
	}

}
