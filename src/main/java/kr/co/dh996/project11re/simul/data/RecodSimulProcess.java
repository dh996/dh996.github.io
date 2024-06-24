package kr.co.dh996.project11re.simul.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecodSimulProcess extends SimulProcess {

	//진행과정 기록을 위한 챔피언 생존여부 확인 관련 변수를 추가합니다.
	//진행과정 기록 관련 기능 역시 수행합니다.
	private String sid;
    private int c1u;
    private int c2u;
    private int c3u;
    private int c4u;
    private int c5u;
    private int c1e;
    private int c2e;
    private int c3e;
    private int c4e;
    private int c5e;
    
    //진행과정 기록을 위한 생성자입니다.
    RecodSimulProcess(UsingSimulProcess usingSimulProcess, 
    		List<UsingSimulData> simulDataList, String sid){
    	super();
    	this.sid = sid;
		setRound(usingSimulProcess.getRound());
	    setKillU(usingSimulProcess.getKillU());
	    setKillE(usingSimulProcess.getKillE());
	    setTowerU(usingSimulProcess.getTowerU());
	    setTowerE(usingSimulProcess.getTowerE());
	    setBaronU(usingSimulProcess.getBaronU());
	    setBaronE(usingSimulProcess.getBaronE());
	    setElderU(usingSimulProcess.getElderU());
	    setElderE(usingSimulProcess.getElderE());
	    setDragonU(usingSimulProcess.getDragonU());
	    setDragonE(usingSimulProcess.getDragonU());
	    setNexusU(checkAlive(usingSimulProcess.getNexusU()));
	    setT1U(checkAlive(usingSimulProcess.getT1U()));
	    setT2U(checkAlive(usingSimulProcess.getT2U()));
	    setT3U(checkAlive(usingSimulProcess.getT3U()));
	    setTiU(checkAlive(usingSimulProcess.getTiU()));
	    setM1U(checkAlive(usingSimulProcess.getM1U()));
	    setM2U(checkAlive(usingSimulProcess.getM2U()));
	    setM3U(checkAlive(usingSimulProcess.getM3U()));
	    setMiU(checkAlive(usingSimulProcess.getMiU()));
	    setB1U(checkAlive(usingSimulProcess.getB1U()));
	    setB2U(checkAlive(usingSimulProcess.getB2U()));
	    setB3U(checkAlive(usingSimulProcess.getB3U()));
	    setBiU(checkAlive(usingSimulProcess.getBiU()));
	    setLtU(checkAlive(usingSimulProcess.getLtU()));
	    setRtU(checkAlive(usingSimulProcess.getRtU()));
	    setBaronNest(usingSimulProcess.getBaronNest());
	    setDragonNest(usingSimulProcess.getDragonNest());
	    setNexusE(checkAlive(usingSimulProcess.getNexusE()));
	    setField(usingSimulProcess.getField());
	    setT1E(checkAlive(usingSimulProcess.getT1E()));
	    setT2E(checkAlive(usingSimulProcess.getT2E()));
	    setT3E(checkAlive(usingSimulProcess.getT3E()));
	    setTiE(checkAlive(usingSimulProcess.getTiE()));
	    setM1E(checkAlive(usingSimulProcess.getM1E()));
	    setM2E(checkAlive(usingSimulProcess.getM2E()));
	    setM3E(checkAlive(usingSimulProcess.getM3E()));
	    setMiE(checkAlive(usingSimulProcess.getMiE()));
	    setB1E(checkAlive(usingSimulProcess.getB1E()));
	    setB2E(checkAlive(usingSimulProcess.getB2E()));
	    setB3E(checkAlive(usingSimulProcess.getB3E()));
	    setBiE(checkAlive(usingSimulProcess.getBiE()));
	    setLtE(checkAlive(usingSimulProcess.getLtE()));
	    setRtE(checkAlive(usingSimulProcess.getRtE()));
	    checkAlive(simulDataList);
    }

	//기록용 데이터엔 생존,파괴 여부만 표시하기 때문에 체력 잔량이 있으면 1, 없으면 0을 반환하는 메소드를 만듭니다.
	private int checkAlive(int value) {
		if(value<=0) {
			return 0;
		}else {
			return 1;
		}
	}

	//챔피언들의 생존,사망 여부를 저장하기 위한 메소드입니다.
	//저장용 데이터를 관리하는 부분을 챔피언 정보를 저장했던 부분과 같이 리스트의 형태로
	//사용할지 고민이 되었는데 데이터베이스 저장시 어떤 형태가 더 효율적인지 알지 못했기에
	//우선은 데이터베이스 접근을 줄이는 방법으로 진행하였습니다.
    private void checkAlive(List<UsingSimulData> simulDataList) {
		// TODO Auto-generated method stub
		for(int i=0; i<simulDataList.size(); i++) {
			if(simulDataList.get(i).getTeam() == 0) {
				if(simulDataList.get(i).getNum() == 1) {
					this.c1u = checkAlive(simulDataList.get(i).getBattlePower().getHp());
				}else if(simulDataList.get(i).getNum() == 2) {
					this.c2u = checkAlive(simulDataList.get(i).getBattlePower().getHp());
				}else if(simulDataList.get(i).getNum() == 3) {
					this.c3u = checkAlive(simulDataList.get(i).getBattlePower().getHp());
				}else if(simulDataList.get(i).getNum() == 4) {
					this.c4u = checkAlive(simulDataList.get(i).getBattlePower().getHp());
				}else if(simulDataList.get(i).getNum() == 5) {
					this.c5u = checkAlive(simulDataList.get(i).getBattlePower().getHp());
				}
			}else if(simulDataList.get(i).getTeam() == 1) {
				if(simulDataList.get(i).getNum() == 1) {
					this.c1e = checkAlive(simulDataList.get(i).getBattlePower().getHp());
				}else if(simulDataList.get(i).getNum() == 2) {
					this.c2e = checkAlive(simulDataList.get(i).getBattlePower().getHp());
				}else if(simulDataList.get(i).getNum() == 3) {
					this.c3e = checkAlive(simulDataList.get(i).getBattlePower().getHp());
				}else if(simulDataList.get(i).getNum() == 4) {
					this.c4e = checkAlive(simulDataList.get(i).getBattlePower().getHp());
				}else if(simulDataList.get(i).getNum() == 5) {
					this.c5e = checkAlive(simulDataList.get(i).getBattlePower().getHp());
				}
			}
		}
	}
}
