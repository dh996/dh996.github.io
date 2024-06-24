package kr.co.dh996.project11re.simul.data;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsingSimulProcess extends SimulProcess {

	//시뮬레이션 가동에 필요한 오브젝트 소환 기능을 위한 변수를 추가합니다.
	private int dragonCount;
	private int baronCount;
	
	public UsingSimulProcess(){
		super();
		setRound(0);
	    setKillU(0);
	    setKillE(0);
	    setTowerU(0);
	    setTowerE(0);
	    setBaronU(0);
	    setBaronE(0);
	    setElderU(0);
	    setElderE(0);
	    setDragonU(new ArrayList<>());
	    setDragonE(new ArrayList<>());
	    setNexusU(1000);
	    setT1U(1000);
	    setT2U(1000);
	    setT3U(1000);
	    setTiU(1000);
	    setM1U(1000);
	    setM2U(1000);
	    setM3U(1000);
	    setMiU(1000);
	    setB1U(1000);
	    setB2U(1000);
	    setB3U(1000);
	    setBiU(1000);
	    setLtU(1000);
	    setRtU(1000);
	    setBaronNest(0);
	    setDragonNest("x");
	    setNexusE(1000);
	    setField("freeze");
	    setT1E(1000);
	    setT2E(1000);
	    setT3E(1000);
	    setTiE(1000);
	    setM1E(1000);
	    setM2E(1000);
	    setM3E(1000);
	    setMiE(1000);
	    setB1E(1000);
	    setB2E(1000);
	    setB3E(1000);
	    setBiE(1000);
	    setLtE(1000);
	    setRtE(1000);
	    this.dragonCount = 0;
	    this.baronCount = 0;
	}
}
