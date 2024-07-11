package kr.co.dh996.project11re.simul.machin.round;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.machin.setting.SaveLogs;

@Component
public class ObjectSetting {
	//오브젝트 관리 기능을 수행합니다.
	
	Random random = new Random();

	public void setObj(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		if("x".equals(simulMO.getUsingSimulProcess().getDragonNest())) {
			dragonSetting(simulMO); //드래곤 오브젝트 설정 관련 기능을 수행합니다.
		}
		if(simulMO.getUsingSimulProcess().getRound()>6
				&& simulMO.getUsingSimulProcess().getBaronNest() == 0) {
			baronSetting(simulMO); //바론 오브젝트 관련 설정 기능을 수행합니다.
		}
	}

	//드래곤 오브젝트 관련 설정 기능을 수행합니다.
	private void dragonSetting(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		if(simulMO.getUsingSimulProcess().getDragonU().size()<4
				&& simulMO.getUsingSimulProcess().getDragonE().size()<4) {
			normalDragonSetting(simulMO); //일반 드래곤 설정 관련 기능을 수행합니다.
		}else {
			elderDragonSetting(simulMO); //장로 드래곤 설정 관련 기능을 수행합니다.
		}
	}

	//일반 드래곤 설정 관련 기능을 수행합니다.
	private void normalDragonSetting(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		incDragonCount(simulMO); //둥지가 비어있을때 드래곤카운트를 증가시킵니다.
		if(simulMO.getUsingSimulProcess().getDragonCount() == 3) {
			spawnNormalDragon(simulMO); //드래곤 카운트가 차면 드래곤을 소환합니다.
			//드래곤이 잡히게 되면 위 기능을 통해 1부터 시작, 3턴이후에 다시 리젠됩니다.
		}
	}

	//일반 드래곤을 소환할 때 어떤 드래곤을 소환할지 정합니다.
	private void spawnNormalDragon(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		if((simulMO.getUsingSimulProcess().getDragonU().size()
				+ simulMO.getUsingSimulProcess().getDragonE().size())<3) {
			//소환 가능한 드래곤들의 리스트를 생성합니다.
			List<String> spawnDragonList = generateDragonList(simulMO);
			String dragon = spawnDragonList.get(random.nextInt(spawnDragonList.size()));
			spawnDragon(simulMO, dragon);
			simulMO.getUsingSimulProcess().setLastDragon(dragon);
			//최종적으로 3회차 소환시 드래곤의 종류가 객채 내부에 남습니다.
		}else { //3회차 소환부터는 같은 드래곤만 소환됩니다.
			spawnDragon(simulMO, simulMO.getUsingSimulProcess().getLastDragon());
		}
	}

	//소환 가능한 드래곤들의 리스트를 생성합니다.
	private List<String> generateDragonList(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		List<String> spawnDragonList = new ArrayList<>();
		spawnDragonList.add("화염");
		spawnDragonList.add("대지");
		spawnDragonList.add("바람");
		spawnDragonList.add("바다");
		spawnDragonList.add("화학공학");
		spawnDragonList.add("마법공학");
		if(simulMO.getUsingSimulProcess().getDragonU().contains("화염")
				|| simulMO.getUsingSimulProcess().getDragonE().contains("화염")) {
			spawnDragonList.remove("화염");
		}
		if(simulMO.getUsingSimulProcess().getDragonU().contains("대지")
				|| simulMO.getUsingSimulProcess().getDragonE().contains("대지")) {
			spawnDragonList.remove("대지");
		}
		if(simulMO.getUsingSimulProcess().getDragonU().contains("바람")
				|| simulMO.getUsingSimulProcess().getDragonE().contains("바람")) {
			spawnDragonList.remove("바람");
		}
		if(simulMO.getUsingSimulProcess().getDragonU().contains("바다")
				|| simulMO.getUsingSimulProcess().getDragonE().contains("바다")) {
			spawnDragonList.remove("바다");
		}
		if(simulMO.getUsingSimulProcess().getDragonU().contains("화학공학")
				|| simulMO.getUsingSimulProcess().getDragonE().contains("화학공학")) {
			spawnDragonList.remove("화학공학");
		}
		if(simulMO.getUsingSimulProcess().getDragonU().contains("마법공학")
				|| simulMO.getUsingSimulProcess().getDragonE().contains("마법공학")) {
			spawnDragonList.remove("마법공학");
		}
		return spawnDragonList;
	}

	//장로 드래곤 설정 관련 기능을 수행합니다.
	private void elderDragonSetting(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		incDragonCount(simulMO);
		if(simulMO.getUsingSimulProcess().getDragonCount() == 4) {
			spawnDragon(simulMO, "장로"); //드래곤 카운트가 차면 드래곤을 소환합니다.
			//드래곤이 잡히게 되면 위 기능을 통해 1부터 시작, 3턴이후에 다시 리젠됩니다.
		}
	}

	//드래곤을 소환하고 드래곤 카운트를 초기화합니다.
	private void spawnDragon(SimulMainObject simulMO, String type) {
		// TODO Auto-generated method stub
		simulMO.getUsingSimulProcess().setDragonNest(type);
		simulMO.getUsingSimulProcess().setDragonCount(0);
		SaveLogs.saveSpawnLog(simulMO.getSid(), simulMO.getUsingSimulProcess().getRound(),
				simulMO.getSimulLogList(), type+" 드래곤", 2);
	}

	private void incDragonCount(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		simulMO.getUsingSimulProcess().setDragonCount(
				simulMO.getUsingSimulProcess().getDragonCount()+1);
		//드래곤이 소환되어있지 않으면 카운트를 증가시킵니다.
		//드래곤이 잡힌 턴에는 0인상태로 넘어오기 때문에 그 턴에 바로 1이 증가합니다.
	}

	//바론 오브젝트 설정 관련 기능을 수행합니다.
	private void baronSetting(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		simulMO.getUsingSimulProcess().setBaronCount(
				simulMO.getUsingSimulProcess().getBaronCount()+1);
		//바론이 소환되어있지 않으면 카운트를 증가시킵니다.
		//바론이 잡힌 턴에는 0인상태로 넘어오기 때문에 그 턴에 바로 1이 증가합니다.
		if(simulMO.getUsingSimulProcess().getRound() == 7) {
			spawnBaron(simulMO); //7턴이 되면 바론을 소환합니다.
		}
		if(simulMO.getUsingSimulProcess().getBaronCount() == 4) {
			spawnBaron(simulMO); //바론 카운트가 차면 바론을 소환합니다.
			//바론이 잡히게 되면 위 기능을 통해 1부터 시작, 3턴이후에 다시 리젠됩니다.
		}
	}

	//바론을 소환하고 카운트를 초기화합니다.
	private void spawnBaron(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		simulMO.getUsingSimulProcess().setBaronNest(1);
		simulMO.getUsingSimulProcess().setBaronCount(0);
		SaveLogs.saveSpawnLog(simulMO.getSid(), simulMO.getUsingSimulProcess().getRound(),
				simulMO.getSimulLogList(), "내셔 남작", 2);
	}
}
