package kr.co.dh996.project11re.simul.machin.round;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.data.UsingSimulData;
import kr.co.dh996.project11re.simul.machin.round.extendPT.PositionTerminal;

@Component
public class PowerSetting {
	//라운드별 전투력 설정 기능을 담당합니다.
	private final PositionTerminal positionTerminal;
	private boolean utilSupFlagU;
	private boolean utilSupFlagE;
	
	@Autowired
	PowerSetting(PositionTerminal positionTerminal){
		this.positionTerminal = positionTerminal;
		this.utilSupFlagU = false;
		this.utilSupFlagE = false;
	}

	public void setPower(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		setUtilSupFlag(simulMO.getSimulDataList());
		positionTerminal.setUtilSupFlag(utilSupFlagU, utilSupFlagE); //유틸 서포터 보유 여부를 전달합니다.
		for(int i=0; i<simulMO.getSimulDataList().size(); i++) {
			if(simulMO.getSimulDataList().get(i).getTeam() == 0) {
				positionTerminal.setPower(simulMO.getSimulDataList().get(i),
						simulMO.getUsingSimulProcess().getDragonU().size(),
						simulMO.getUsingSimulProcess().getBaronU(),
						simulMO.getUsingSimulProcess().getElderU());
			}else {
				positionTerminal.setPower(simulMO.getSimulDataList().get(i),
						simulMO.getUsingSimulProcess().getDragonE().size(),
						simulMO.getUsingSimulProcess().getBaronE(),
						simulMO.getUsingSimulProcess().getElderE());
			}
		}
	}

	//조합에 유틸 서포터 포지션이 있는지 확인합니다.
	//유틸 서포터가 존재할 때 수행하는 기능 실행여부 판단을 위해서입니다.
	private void setUtilSupFlag(List<UsingSimulData> simulDataList) {
		// TODO Auto-generated method stub
		for(int i=0; i<simulDataList.size(); i++) {
			if("UtilSuport".equals(simulDataList.get(i).getPosition())){
				if(simulDataList.get(i).getTeam() == 0) {
					this.utilSupFlagU = true;
				}else {
					this.utilSupFlagE = true;
				}
			}
		}
	}

}
