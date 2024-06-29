package kr.co.dh996.project11re.simul.machin.setting;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.dto.ChampDTO;
import kr.co.dh996.project11re.simul.data.UsingSimulData;

@Component
public class EnemySetting {
	//적 팀 정보 생성 기능을 담당합니다.
	private final Random random = new Random();
	private List<ChampDTO> allChampDTOList;

	public void setEnemy(List<UsingSimulData> simulDataList, List<ChampDTO> allChampDTOList) {
		// TODO Auto-generated method stub
		this.allChampDTOList = allChampDTOList;
		removeDuplicates(simulDataList); //아군 챔피언과 같은 챔피언이 선택되지 않도록 합니다.
		pickEnemy(1, simulDataList); //적군 챔피언을 선택합니다. 순서별 다른 역할군의 챔피언을 골라야 합니다.
		pickEnemy(2, simulDataList);		
		pickEnemy(3, simulDataList);		
		pickEnemy(4, simulDataList);		
		pickEnemy(5, simulDataList);		
	}

	//적군 챔피언을 선택합니다. 선택된 챔피언이 다시 선택되지 않도록 합니다.
	private void pickEnemy(int num, List<UsingSimulData> simulDataList) {
		// TODO Auto-generated method stub
		int champIndex = random.nextInt(allChampDTOList.size());
		switch(num) {
		case 1: 
			if(allChampDTOList.get(champIndex).getChampTags().contains("Tank")
					|| allChampDTOList.get(champIndex).getChampTags().contains("Fighter")) {
				UsingSimulData data = new UsingSimulData(allChampDTOList.get(champIndex), 1, 1);
				simulDataList.add(data);
				allChampDTOList.remove(champIndex);
				break;
			}else {
				pickEnemy(1, simulDataList);
				break;
			}
		case 2: 
			if(allChampDTOList.get(champIndex).getChampTags().contains("Fighter")
					|| allChampDTOList.get(champIndex).getChampTags().contains("Assassin")) {
				UsingSimulData data = new UsingSimulData(allChampDTOList.get(champIndex), 2, 1);
				simulDataList.add(data);
				allChampDTOList.remove(champIndex);
				break;
			}else {
				pickEnemy(2, simulDataList);
				break;
			}
		case 3: 
			if(allChampDTOList.get(champIndex).getChampTags().contains("Mage")) {
				UsingSimulData data = new UsingSimulData(allChampDTOList.get(champIndex), 3, 1);
				simulDataList.add(data);
				allChampDTOList.remove(champIndex);
				break;
			}else {
				pickEnemy(3, simulDataList);
				break;
			}
		case 4: 
			if(allChampDTOList.get(champIndex).getChampTags().contains("Marksman")) {
				UsingSimulData data = new UsingSimulData(allChampDTOList.get(champIndex), 4, 1);
				simulDataList.add(data);
				allChampDTOList.remove(champIndex);
				break;
			}else {
				pickEnemy(4, simulDataList);
				break;
			}
		case 5: 
			if(allChampDTOList.get(champIndex).getChampTags().contains("Support")) {
				UsingSimulData data = new UsingSimulData(allChampDTOList.get(champIndex), 5, 1);
				simulDataList.add(data);
				allChampDTOList.remove(champIndex);
				break;
			}else {
				pickEnemy(5, simulDataList);
				break;
			}
		}
	}

	//아군 챔피언과 같은 챔피언이 선택되지 않도록 합니다.
	private void removeDuplicates(List<UsingSimulData> simulDataList) {
		// TODO Auto-generated method stub
		for(int i=0; i<simulDataList.size(); i++) {
			for(int j=0; j<allChampDTOList.size(); j++) {
				if(simulDataList.get(i).getChampID().equals(allChampDTOList.get(j).getChampID())) {
					allChampDTOList.remove(j);
					break;
				}
			}
		}
	}

}
