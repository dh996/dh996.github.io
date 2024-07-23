package kr.co.dh996.project11re.simul.program;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.dto.ChampDTO;
import kr.co.dh996.project11re.service.ChampService;
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
	private final SimulDataService simulDataService;
	private final ChampService champService;
	private final Round round;
	private final EnemySetting enemySetting;
	private final BuildSetting buildSetting;
	
	@Autowired
    public Simulation(SimulDataService simulDataService,
    		ChampService champService,
    		Round round, EnemySetting enemySetting,
    		BuildSetting buildSetting) {
        this.round = round;
        this.simulDataService = simulDataService;
        this.champService = champService;
		this.enemySetting = enemySetting;
		this.buildSetting = buildSetting;
    }
	
	//시뮬레이션이 이곳에서 실행됩니다.
	public SimulMainObject simulation(UsersPick usersPick) {
		
		SimulMainObject simulMO = new SimulMainObject(usersPick.getUserName(),usersPick.getVersion());
		simulMO.setSimulDataList(setDataList(usersPick.getPickChamps())); //아군 팀 리스트를 추가합니다.
		enemySetting.setEnemy(simulMO.getSimulDataList(), callChampList(usersPick.getVersion())); //적 팀 리스트를 추가합니다.
		buildSetting.setBuild(simulMO.getSimulDataList(), 0); //빌드 정보를 설정합니다.
		buildSetting.setBuild(simulMO.getSimulDataList(), 1); //0은 아군의 정보, 1은 적군의 정보를 설정합니다.
		round.roundStart(simulMO); //시뮬레이션을 시작합니다.
		return simulMO;
	}

	//유저가 고른 챔피언들의 id를 받아서 그 id에 해당하는 챔피언의 정보를 DB로부터 호출하는 기능이 들어갑니다.
	private List<UsingSimulData> setDataList(List<String> usersPickChamp) {
		// TODO Auto-generated method stub
		return simulDataService.generateUsingData(champService.getChampList(usersPickChamp));
	}

	//적 팀 리스트 추가를 위해 전체 챔피언 데이터를 불러옵니다.
	private List<ChampDTO> callChampList(String version) {
		// TODO Auto-generated method stub
		return champService.getAllChampList(version);
	}
}
