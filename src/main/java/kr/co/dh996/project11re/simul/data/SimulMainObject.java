package kr.co.dh996.project11re.simul.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import kr.co.dh996.project11re.simul.machin.setting.CreateSid;

@Getter
@Setter
public class SimulMainObject {
	
	//시뮬레이션은 이 인스턴스를 참조하여 실행된다. 이 인스턴스는 그 정보를 담아두고 있는 것만을 목적으로 하려 한다.
	private String sid;
	private String userName;
	private String version;
	private int winLose;
	private UsingSimulProcess usingSimulProcess;
	private List<UsingSimulData> simulDataList;
	private List<RecodSimulProcess> simulProcessList;
	private List<SimulLog> simulLogList;
	private List<BattlePower> battlePowerList;
	
	public SimulMainObject(String userName, String version) {
		
		this.sid = CreateSid.createSid();
		this.userName = userName;
		this.version = version;
		this.winLose = 2;
		this.usingSimulProcess = new UsingSimulProcess();
		this.simulDataList = new ArrayList<>();
		this.simulProcessList = new ArrayList<>();
		this.simulLogList = new ArrayList<>();
		this.battlePowerList = new ArrayList<>();
	}

	public void applyDataToMO(TurnData turnData) {
		// TODO Auto-generated method stub
        this.usingSimulProcess.setKillU(turnData.getKillU());
        this.usingSimulProcess.setKillE(turnData.getKillE());
        this.simulLogList.addAll(turnData.getTurnLog());
        this.simulDataList.isEmpty();
        this.simulDataList.addAll(turnData.getUserTeam());
        this.simulDataList.addAll(turnData.getEnemyTeam());
	}

}
