package kr.co.dh996.project11re.simul.machin.turn;

import java.util.List;

import kr.co.dh996.project11re.simul.data.UsingSimulData;

public class RetireCheck {

	public static boolean retireCheck(UsingSimulData uSD) {
		if(uSD.getBattlePower().getHp()>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean retireCheck(List<UsingSimulData> uSDList) {
		boolean flag = false;
		for(int i=0; i<uSDList.size(); i++) {
			if(!retireCheck(uSDList.get(i))) {
				flag = true; 
			}
		}
		if(flag) {
			return true;
		}else {
			return false;
		}
	}
}
