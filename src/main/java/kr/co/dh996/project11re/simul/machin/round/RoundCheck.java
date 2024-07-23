package kr.co.dh996.project11re.simul.machin.round;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.machin.itf.WinCheck;

@Component
public class RoundCheck implements WinCheck {

	//다음 라운드 진행 여부를 판단합니다.
	@Override
	public int winCheck(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		if(simulMO.getUsingSimulProcess().getNexusU() == 0) {
			simulMO.setWinLose(1);
			return 1;
		}else if(simulMO.getUsingSimulProcess().getNexusE() == 0) {
			simulMO.setWinLose(0);
			return 0;
		}else {
			return 2;
		}
	}

}
