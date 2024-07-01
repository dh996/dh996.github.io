package kr.co.dh996.project11re.simul.machin.turn;

import kr.co.dh996.project11re.simul.data.UsingSimulData;

public class RetireCheck {

	public static boolean retireCheck(UsingSimulData uSD) {
		if(uSD.getBattlePower().getHp()>0) {
			return true;
		}else {
			return false;
		}
	}
}
