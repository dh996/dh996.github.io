package kr.co.dh996.project11re.simul.program;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.dto.ChampDTO;
import kr.co.dh996.project11re.service.SimulDataService;
import kr.co.dh996.project11re.service.SimulService;
import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.data.UsersPick;
import kr.co.dh996.project11re.simul.data.UsingSimulData;
import kr.co.dh996.project11re.simul.machin.setting.BuildSetting;
import kr.co.dh996.project11re.simul.machin.setting.EnemySetting;

@Component
public class Simulation {
	//시뮬레이션의 실행과정 관련기능을 수행합니다.
	private final SimulService simulService;
	private final Round round;
	private final SimulDataService simulDataService;
	private final EnemySetting enemySetting;
	private final BuildSetting buildSetting;
	
	@Autowired
    public Simulation(SimulService simulService, Round round, SimulDataService simulDataService,
    		EnemySetting enemySetting, BuildSetting buildSetting) {
        this.round = round;
        this.simulService = simulService;
        this.simulDataService = simulDataService;
		this.enemySetting = enemySetting;
		this.buildSetting = buildSetting;
    }
	
	//시뮬레이션이 이곳에서 실행됩니다.
	public void simulation(UsersPick usersPick) {
		
		SimulMainObject simulMO = new SimulMainObject(usersPick.getUserName(),usersPick.getVersion());
		simulMO.setSimulDataList(setDataList(usersPick.getPickChamps())); //아군 팀 리스트를 추가합니다.
		enemySetting.setEnemy(simulMO.getSimulDataList(), callChampList()); //적 팀 리스트를 추가합니다.
		buildSetting.setBuild(simulMO.getSimulDataList(), 0); //빌드 정보를 설정합니다.
		buildSetting.setBuild(simulMO.getSimulDataList(), 1); //0은 아군의 정보, 1은 적군의 정보를 설정합니다.
		round.roundStart(simulMO); //시뮬레이션을 시작합니다.
		saveSimul(simulMO); //종료 판정시 데이터를 데이터베이스에 저장합니다.
	}

	//유저가 고른 챔피언들의 id를 받아서 그 id에 해당하는 챔피언의 정보를 DB로부터 호출하는 기능이 들어갑니다.
	private List<UsingSimulData> setDataList(List<String> usersPickChamp) {
		// TODO Auto-generated method stub
		return simulDataService.generateUsingData(simulService.getChampList(usersPickChamp));
	}

	//적 팀 리스트 추가를 위해 전체 챔피언 데이터를 불러옵니다.
	private List<ChampDTO> callChampList() {
		// TODO Auto-generated method stub
		return simulService.getAllChampList();
	}

	//종료 판정시 데이터를 서비스단으로 제출합니다.
	private void saveSimul(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		simulService.saveSimul(simulMO);
	}
}
