package kr.co.dh996.project11re.simul.program;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.dh996.project11re.service.SimulService;
import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.data.UsersPick;
import kr.co.dh996.project11re.simul.data.UsingSimulData;
import kr.co.dh996.project11re.simul.machin.setting.DefaultSetting;

public class Simulation {
	//시뮬레이션의 실행과정 관련기능을 수행합니다.
	private final SimulService simulService;
	private final Round round;
	private final DefaultSetting defaultSetting;
	
	@Autowired
    public Simulation(SimulService simulService, Round round, DefaultSetting defaultSetting) {
        this.round = round;
        this.simulService = simulService;
        this.defaultSetting = defaultSetting;
    }
	
	//시뮬레이션이 이곳에서 실행됩니다.
	public void simulation(UsersPick usersPick) {
		
		SimulMainObject simulMO = new SimulMainObject(usersPick.getUserName(),usersPick.getVersion());
		simulMO.setSimulDataList(setDataList(usersPick.getPickChamps()));
		round.roundLoop(simulMO);
		saveSimul(simulMO); //종료 판정시 데이터를 데이터베이스에 저장합니다.
	}

	//유저가 고른 챔피언들의 id를 받아서 그 id에 해당하는 챔피언의 정보를 DB로부터 호출하는 기능이 들어갑니다.
	private List<UsingSimulData> setDataList(List<String> usersPickChamp) {
		// TODO Auto-generated method stub
		return defaultSetting.generateUsingData(simulService.getChampList(usersPickChamp));
	}

	//종료 판정시 데이터를 저장용으로 갈무리한 뒤 서비스단으로 제출합니다.
	private void saveSimul(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		simulService.saveSimul(defaultSetting.generateRecordData(simulMO.getSimulDataList(), simulMO.getSid()),
				simulMO.getSimulProcessList(), simulMO.getSimulLogList());
	}
}
