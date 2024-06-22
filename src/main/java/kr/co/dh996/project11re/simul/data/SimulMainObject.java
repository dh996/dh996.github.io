package kr.co.dh996.project11re.simul.data;

import java.util.ArrayList;
import java.util.List;

import kr.co.dh996.project11re.simul.machin.setting.DefaultSetting;
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
	private UsingSimulProcess usingSimulProcess;
	private List<SimulData> simulDataList;
	private List<SimulProcess> simulProcessList;
	private List<SimulLog> simulLogList;
	private List<BattlePower> battlePowerList;
	
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
