package kr.co.dh996.project11re.simul.machin.turn;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.UsingSimulData;

@Component
public class InitSetting {
	
	private final Random random = new Random();
	private int userIP;
	private int enemyIP;

	public int initSetting(List<UsingSimulData> userTeam, List<UsingSimulData> enemyTeam) {
		// TODO Auto-generated method stub
		this.userIP = 0; 
		this.enemyIP = 0;
		for(int i=0; i<userTeam.size(); i++) {
			if(RetireCheck.retireCheck(userTeam.get(i))) {
				userIP += userTeam.get(i).getBattlePower().getInitPower();
			}
			if(RetireCheck.retireCheck(enemyTeam.get(i))) {
				enemyIP += enemyTeam.get(i).getBattlePower().getInitPower();
			}
		}
		if(random.nextInt(userIP+enemyIP)<=userIP) {
			return 0;
		}else {
			return 1;
		}
	}

}
