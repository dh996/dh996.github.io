package kr.co.dh996.project11re.simul.data;

import java.util.ArrayList;
import java.util.List;

import kr.co.dh996.project11re.simul.machin.setting.DefaultSetting;
import kr.co.dh996.project11re.simul.machin.setting.CreateSid;

public class SimulMainObject {
	
	//시뮬레이션은 이 인스턴스를 참조하여 실행된다.
	public String sid;
	public String userName;
	public String version;
	public UsingSimulProcess usingSimulProcess;
	public List<SimulData> simulDataList;
	public List<SimulProcess> simulProcessList;
	public List<SimulLog> simulLogList;
	public List<BattlePower> battlePowerList;
	
	public SimulMainObject(UsersPick usersPick) {
		
		DefaultSetting defaultSetting = new DefaultSetting();
		
		this.sid = CreateSid.createSid();
		this.userName = usersPick.getUserName();
		this.version = usersPick.getVersion();
		this.usingSimulProcess = defaultSetting.setUSP();
		this.simulDataList = defaultSetting.setDataList(usersPick);
		this.simulProcessList = new ArrayList<>();
		this.simulLogList = new ArrayList<>();
		this.battlePowerList = new ArrayList<>();
		
		//이런 식으로 설정하면 defaultSetting객체가 생성자 안에서만 생존하다가 가비지 컬랙션이 된다고 한다
		defaultSetting = null;
	}

}
