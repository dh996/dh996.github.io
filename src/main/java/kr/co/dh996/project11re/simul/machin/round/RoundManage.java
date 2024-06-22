package kr.co.dh996.project11re.simul.machin.round;

import kr.co.dh996.project11re.simul.data.SimulMainObject;

public class RoundManage {
	//이 클래스는 라운드 진행에 따른 메인 객체의 기초적인 값 수정에 관여합니다.
	
	//라운드가 시작될 때 라운드 값을 1 증가시킵니다.
	public void incRound(SimulMainObject simulMO) {
		int round = simulMO.getUsingSimulProcess().getRound();
		simulMO.getUsingSimulProcess().setRound(round + 1);
	}
}
