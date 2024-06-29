package kr.co.dh996.project11re.simul.machin.round;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.machin.round.extendPT.PositionTerminal;

@Component
public class PowerSetting {
	//라운드별 전투력 설정 기능을 담당합니다.
	private final PositionTerminal positionTerminal;
	
	@Autowired
	PowerSetting(PositionTerminal positionTerminal){
		this.positionTerminal = positionTerminal;
	}

	public void setPower(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		
	}

}
