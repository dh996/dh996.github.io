package kr.co.dh996.project11re.simul.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurnData {
	//턴 내부에서 사용되는 변수들의 인스턴스입니다.
	
    private int initTeam;
    private List<UsingSimulData> userTeam;
    private List<UsingSimulData> enemyTeam;
    private int killU;
    private int killE;
    private List<SimulLog> turnLog;
    private int logSize;
    
    public TurnData(SimulMainObject simulMO) {
        this.initTeam = 0;
        this.userTeam = new ArrayList<>();;
        this.enemyTeam = new ArrayList<>();
        this.turnLog = new ArrayList<>();
        this.killU = simulMO.getUsingSimulProcess().getKillU();
        this.killE = simulMO.getUsingSimulProcess().getKillE();
        this.logSize = simulMO.getSimulLogList().size();
        for(int i=0; i<simulMO.getSimulDataList().size(); i++) {
        	if(simulMO.getSimulDataList().get(i).getTeam() == 0) {
        		this.userTeam.add(simulMO.getSimulDataList().get(i));
        	}else if(simulMO.getSimulDataList().get(i).getTeam() == 1) {
        		this.enemyTeam.add(simulMO.getSimulDataList().get(i));
        	}
        }
    }
}
