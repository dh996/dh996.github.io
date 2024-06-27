package kr.co.dh996.project11re.simul.machin.round;

import java.util.Random;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.BattlePower;
import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.machin.setting.SaveLogs;

@Component
public class ChampGrow {
	//챔피언 성장 관련 기능을 수행합니다.
	private final Random random = new Random();
	private int getExp;
	private int level;

	public void setGrow(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		for(int i=0; i<simulMO.getSimulDataList().size(); i++) {
			this.getExp = 6000+random.nextInt(2000); //기본 성장치를 설정합니다.
			kdaAsExp(simulMO.getSimulDataList().get(i).getBattlePower()); //점수를 성장치에 반영합니다.
			positionAsExp(simulMO.getSimulDataList().get(i).getPosition()); //포지션을 성장치에 반영합니다.
			simulMO.getSimulDataList().get(i).setExp(getExp+simulMO.getSimulDataList().get(i).getExp());
			this.level = levelUp(simulMO.getSimulDataList().get(i).getExp());
			simulMO.getSimulDataList().get(i).setLevel(level); //성장 결과를 저장합니다.
			SaveLogs.saveLevelLog(simulMO.getSid(), simulMO.getSimulDataList().get(i).getChampName(),
					Integer.toString(level), simulMO.getUsingSimulProcess().getRound(),
					simulMO.getSimulLogList(), simulMO.getSimulDataList().get(i).getTeam());
			    //성장 로그를 저장합니다.
		}
	}

	//점수를 성장치에 반영합니다.
	private void kdaAsExp(BattlePower battlePower) {
		// TODO Auto-generated method stub
		//이전 라운드의 점수만 성장에 반영이 됩니다.
		this.getExp += (battlePower.getRoundKill()*200);
		this.getExp -= (battlePower.getRoundDeath()*200);
		this.getExp += (battlePower.getRoundAssist()*100);
	}

	//포지션을 성장치에 반영합니다.
	private void positionAsExp(String position) {
		// TODO Auto-generated method stub
		if(position.equals("Support") || position.equals("ADSupport")
				|| position.equals("TankSupport") || position.equals("MageSupport")
				|| position.equals("DealSupport") || position.equals("UtilSupport")) {
			getExp *= 0.7;
		}
		if(position.equals("FureADC") || position.equals("FureMage")) {
			getExp *= 1.2;
		}
	}

	//성장치를 바탕으로 레벨을 산출합니다.
	private int levelUp(int exp) {
		// TODO Auto-generated method stub
		if(exp>=0 && exp<=5000) {
			return 1;
		}else if(exp>5000 && exp<=10000) {
			return 2;
		}else if(exp>10000 && exp<=15000) {
			return 3;
		}else if(exp>15000 && exp<=22000) {
			return 4;
		}else if(exp>22000 && exp<=29000) {
			return 5;
		}else if(exp>29000 && exp<=36000) {
			return 6;
		}else if(exp>36000 && exp<=44000) {
			return 7;
		}else if(exp>44000 && exp<=51000) {
			return 8;
		}else if(exp>51000 && exp<=60000) {
			return 9;
		}else if(exp>60000 && exp<=71000) {
			return 10;
		}else if(exp>71000 && exp<=85000) {
			return 11;
		}else if(exp>85000 && exp<=100000) {
			return 12;
		}else if(exp>100000 && exp<=116000) {
			return 13;
		}else if(exp>116000 && exp<=133000) {
			return 14;
		}else if(exp>133000 && exp<=150000) {
			return 15;
		}else if(exp>150000 && exp<=167000) {
			return 16;
		}else if(exp>167000 && exp<=185000) {
			return 17;
		}else{
			return 18;
		}
	}
}
