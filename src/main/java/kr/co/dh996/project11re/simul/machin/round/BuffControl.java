package kr.co.dh996.project11re.simul.machin.round;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.SimulMainObject;

@Component
public class BuffControl {
	//기간제 버프 관리 기능을 담당합니다.

	public void setBuff(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		simulMO.getUsingSimulProcess().setBaronU(
				setBuff(simulMO.getUsingSimulProcess().getBaronU()));
		simulMO.getUsingSimulProcess().setBaronE(
				setBuff(simulMO.getUsingSimulProcess().getBaronE()));
		simulMO.getUsingSimulProcess().setElderU(
				setBuff(simulMO.getUsingSimulProcess().getElderU()));
		simulMO.getUsingSimulProcess().setElderE(
				setBuff(simulMO.getUsingSimulProcess().getElderE()));
	}

	private int setBuff(int buff) {
		// TODO Auto-generated method stub
		if(buff == 1) {
			return 2;
		}else if(buff == 2) {
			return 0;
		}else {
			return 0;
		}
	}
}
