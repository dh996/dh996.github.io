package kr.co.dh996.project11re.simul.machin.setting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.UsingSimulData;

@Component
public class BuildSetting {
	//상세 역할군 설정 관련 기능을 수행합니다.
	
	private int containMageFlag;
	private int containTankerFlag;
	private int containSupporterFlag;
	private int containMarksmanFlag;
	private int containFighterFlag;
	private List<String> needPosition;
	private List<String> checkPosition;

	public void setBuild(List<UsingSimulData> simulDataList, int team) {
		// TODO Auto-generated method stub
		setPoitionFlag(simulDataList, team); //현재 팀의 조합을 바탕으로 역할군 조정 플래그를 설정합니다.
		setPosition(simulDataList, team); //플래그를 바탕으로 역할군을 설정합니다.
		if(checkSup(simulDataList, team)) { //서폿 역할군이 없는 경우를 체크합니다.
			setSupport(simulDataList, team); //없다면 서폿 역할군을 추가합니다.
		}
	}

	//긁어온 조합을 바탕으로 역할군 조정 플래그를 설정합니다.
	private void setPoitionFlag(List<UsingSimulData> simulDataList, int team) {
		// TODO Auto-generated method stub
		resetFlag();
		for(int i=0; i<simulDataList.size(); i++) {
			if(simulDataList.get(i).getTeam() == team) {
				if(simulDataList.get(i).getChampTags().contains("Marksman")) {
					containMarksmanFlag++;
				}
				if(simulDataList.get(i).getChampTags().contains("Tank")) {
					containTankerFlag++;
				}
				if(simulDataList.get(i).getChampTags().contains("Fighter")) {
					containFighterFlag++;
				}
				if(simulDataList.get(i).getChampTags().contains("Mage")) {
					containMageFlag++;
				}
				if(simulDataList.get(i).getChampTags().contains("Support")) {
					containSupporterFlag++;
				}
			}
		} //현재 팀의 조합을 긁어옵니다.
		if((containMageFlag+containMarksmanFlag)==1){
			needPosition.add("UniqDeal");
		}
		if(containTankerFlag==1){
			needPosition.add("UniqTank");
		}
		if(containSupporterFlag==1) {
			needPosition.add("UniqSupt");
		}
		if(containFighterFlag>0 && containSupporterFlag==1 && containTankerFlag>0) {
			needPosition.add("TankSupt");
		}
		if(containSupporterFlag==0) {
			needPosition.add("needSupt");
		}
		if(containFighterFlag>0 && containTankerFlag==0) {
			needPosition.add("FightTank");
		}
	}

	private void resetFlag() {
		// TODO Auto-generated method stub
		this.needPosition = new ArrayList<>();
		this.containMageFlag = 0;       
		this.containTankerFlag = 0;     
		this.containSupporterFlag = 0;  
		this.containMarksmanFlag = 0;   
		this.containFighterFlag = 0;
	}

	//플래그를 바탕으로 역할군을 설정합니다.
	private void setPosition(List<UsingSimulData> simulDataList, int team) {
		// TODO Auto-generated method stub
		for(int i=0; i<simulDataList.size(); i++) {
			if(simulDataList.get(i).getTeam() == team) {
				if(simulDataList.get(i).getChampTags().size() == 1) {
					singleBuild(simulDataList.get(i)); //등록 태그가 하나인 경우
				}else {
					multiBuild(simulDataList.get(i)); //둘 이상인 경우
				}
			}
		}
	}

	private void singleBuild(UsingSimulData usingSimulData) {
		// TODO Auto-generated method stub
		if(usingSimulData.getChampTags().contains("Tank") && needPosition.contains("UniqTank")) {
			usingSimulData.setPosition("FureTank");
		}
		if(usingSimulData.getChampTags().contains("Tank") && !needPosition.contains("UniqTank")) {
			usingSimulData.setPosition("Tank");
		}
		if(usingSimulData.getChampTags().contains("Assassin")) {
			usingSimulData.setPosition("Assassin");
		}
		if(usingSimulData.getChampTags().contains("Marksman") && needPosition.contains("UniqDeal")) {
			usingSimulData.setPosition("FureADC");
		}
		if(usingSimulData.getChampTags().contains("Marksman") && !needPosition.contains("UniqDeal")) {
			usingSimulData.setPosition("ADC");
		}
		if(usingSimulData.getChampTags().contains("Mage") && needPosition.contains("UniqDeal")) {
			usingSimulData.setPosition("FureMage");
		}
		if(usingSimulData.getChampTags().contains("Mage") && !needPosition.contains("UniqDeal")) {
			usingSimulData.setPosition("Mage");
		}
		if(usingSimulData.getChampTags().contains("Fighter") && needPosition.contains("FightTank")) {
			usingSimulData.setPosition("FightTank");
		}
		if(usingSimulData.getChampTags().contains("Fighter") && !needPosition.contains("FightTank")) {
			usingSimulData.setPosition("Fighter");
		}
		if(usingSimulData.getChampTags().contains("Support")){
			usingSimulData.setPosition("Support");
		}
	}

	private void multiBuild(UsingSimulData usingSimulData) {
		// TODO Auto-generated method stub
		String mainPosition = usingSimulData.getChampTags().get(0);
		String subPosition = usingSimulData.getChampTags().get(1);
		if("Tank".equals(mainPosition) && needPosition.contains("UniqTank")) {
			if("Mage".equals(subPosition) && needPosition.contains("UniqDeal")) {
				usingSimulData.setPosition("MageTank");
			}else if("Mage".equals(subPosition) && !needPosition.contains("UniqDeal")) {
				usingSimulData.setPosition("FureTank");
			}else if("Support".equals(subPosition) && needPosition.contains("TankSupt")) {
				usingSimulData.setPosition("TankSupport");
			}else if("Support".equals(subPosition) && !needPosition.contains("TankSupt")) {
				usingSimulData.setPosition("FureTank");
			}else if("Fighter".equals(subPosition)) {
				usingSimulData.setPosition("FightTank");
			}else{
				usingSimulData.setPosition("Tank");
			}
		}
		if("Tank".equals(mainPosition) && !needPosition.contains("UniqTank")) {
			if("Mage".equals(subPosition) && needPosition.contains("UniqDeal")) {
				usingSimulData.setPosition("MageTank");
			}else if("Mage".equals(subPosition) && !needPosition.contains("UniqDeal")) {
				usingSimulData.setPosition("Tank");
			}else if("Support".equals(subPosition) && needPosition.contains("TankSupt")) {
				usingSimulData.setPosition("TankSupport");
			}else if("Support".equals(subPosition) && !needPosition.contains("TankSupt")) {
				usingSimulData.setPosition("Tank");
			}else if("Fighter".equals(subPosition)) {
				usingSimulData.setPosition("Fighter");
			}else{
				usingSimulData.setPosition("Tank");
			}
		}
		if("Marksman".equals(mainPosition) && needPosition.contains("UniqDeal")) {
			usingSimulData.setPosition("FureADC");
		}
		if("Marksman".equals(mainPosition) && !needPosition.contains("UniqDeal")) {
			if("Support".equals(subPosition) && needPosition.contains("UniqSupt")) {
				usingSimulData.setPosition("ADSupport");
			}else {
				usingSimulData.setPosition("ADC");
			}
		}
		if("Assassin".equals(mainPosition) && needPosition.contains("UniqDeal")) {
			if("Marksman".equals(subPosition)) {
				usingSimulData.setPosition("FureADC");
			}else if("Mage".equals(subPosition)) {
				usingSimulData.setPosition("FureMage");
			}else{
				usingSimulData.setPosition("Assassin");
			}
		}
		if("Assassin".equals(mainPosition) && !needPosition.contains("UniqDeal")) {
			usingSimulData.setPosition("Assassin");
		}
		if("Mage".equals(mainPosition) && needPosition.contains("UniqDeal")) {
			usingSimulData.setPosition("FureMage");
		}else if("Mage".equals(mainPosition) && !needPosition.contains("UniqDeal")) {
			if("Support".equals(subPosition) && needPosition.contains("UniqSupt")) {
				usingSimulData.setPosition("MageSupport");
			}else {
				usingSimulData.setPosition("Mage");
			}
		}
		if("Fighter".equals(mainPosition) && needPosition.contains("FightTank")) {
			usingSimulData.setPosition("FightTank");
		}
		if("Fighter".equals(mainPosition) && !needPosition.contains("FightTank")) {
			if("Mage".equals(subPosition) && !needPosition.contains("UniqDeal")) {
				usingSimulData.setPosition("FureMage");
			}else if("Mage".equals(subPosition) && !needPosition.contains("UniqDeal")) {
				usingSimulData.setPosition("Mage");
			}else if("Tank".equals(subPosition)) {
				usingSimulData.setPosition("Fighter");
			}else{
				usingSimulData.setPosition("Fighter");
			}
		}
		if("Support".equals(mainPosition)) {
			if("Mage".equals(subPosition) && needPosition.contains("UniqDeal")) {
				usingSimulData.setPosition("MageSupport");
			}else if("Mage".equals(subPosition) && !needPosition.contains("UniqDeal")) {
				usingSimulData.setPosition("UtilSupport");
			}else if("Marksman".equals(subPosition) && needPosition.contains("UniqDeal")) {
				usingSimulData.setPosition("ADC");
			}else if("Marksman".equals(subPosition) && !needPosition.contains("UniqDeal")) {
				usingSimulData.setPosition("ADSupport");
			}else if("Tank".equals(subPosition) && needPosition.contains("TankSupt")) {
				usingSimulData.setPosition("TankSupport");
			}else if("Tank".equals(subPosition) && !needPosition.contains("TankSupt")) {
				usingSimulData.setPosition("Support");
			}else{
				usingSimulData.setPosition("Support");
			}
		}
	}

	//서폿 역할군이 없는 경우를 체크합니다.
	private boolean checkSup(List<UsingSimulData> simulDataList, int team) {
		// TODO Auto-generated method stub
		this.checkPosition = new ArrayList<>();
		for(int i=0; i<simulDataList.size(); i++) {
			if(simulDataList.get(i).getTeam() == team) {
				this.checkPosition.add(simulDataList.get(i).getPosition());
			}
		}
		if(checkPosition.contains("Support") || checkPosition.contains("TankSupport")
				|| checkPosition.contains("ADSupport") || checkPosition.contains("MageSupport")
				|| checkPosition.contains("UtilSupport")) {
			return false;
		}else {
			return true;
		}
	}

	//서폿 역할군이 없는 경우 서폿 역할군을 강제설정합니다.
	private void setSupport(List<UsingSimulData> simulDataList, int team) {
		// TODO Auto-generated method stub
		for(int i=0; i<simulDataList.size(); i++) {
			if(simulDataList.get(i).getTeam() == team && simulDataList.get(i).getNum() == 5) {
				if("FureTank".equals(simulDataList.get(i).getPosition())
						|| "MageTank".equals(simulDataList.get(i).getPosition())
						|| "FightTank".equals(simulDataList.get(i).getPosition())
						|| "Tank".equals(simulDataList.get(i).getPosition())) {
					simulDataList.get(i).setPosition("TankSupport");
				}else if("FureADC".equals(simulDataList.get(i).getPosition())
						|| "ADC".equals(simulDataList.get(i).getPosition())) {
					simulDataList.get(i).setPosition("ADSupport");
				}else if("FureMage".equals(simulDataList.get(i).getPosition())
						|| "Mage".equals(simulDataList.get(i).getPosition())) {
					simulDataList.get(i).setPosition("MageSupport");
				}else {
					simulDataList.get(i).setPosition("DealSupport");
				}
			}
		}
	}
}
