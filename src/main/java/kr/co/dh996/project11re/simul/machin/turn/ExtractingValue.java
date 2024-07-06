package kr.co.dh996.project11re.simul.machin.turn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.BattlePower;
import kr.co.dh996.project11re.simul.data.UsingSimulData;

@Component
public class ExtractingValue {
	//데이터 내부값을 참조해 기능을 수행할 주체를 결정해줍니다.
	
	private final Random random = new Random();
	private int userP;
	private int enemyP;
	private int unitP;
	private int returnFlagMin;
	private int returnFlagMax;
	private List<Integer> unitList;

	//수치를 사용해 기능을 수행할 팀 선택시 사용됩니다.
	public int teamJudgment(List<UsingSimulData> userTeam, List<UsingSimulData> enemyTeam,
			Function<BattlePower, Integer> bpFunction) {
		// TODO Auto-generated method stub
		this.userP = 0; 
		this.enemyP = 0;
		for(int i=0; i<userTeam.size(); i++) {
			if(RetireCheck.retireCheck(userTeam.get(i))) {
				userP += bpFunction.apply(userTeam.get(i).getBattlePower());
			}
			if(RetireCheck.retireCheck(enemyTeam.get(i))) {
				enemyP += bpFunction.apply(enemyTeam.get(i).getBattlePower());
			}
		}
		if(random.nextInt(userP+enemyP)<=userP) {
			return 0;
		}else {
			return 1;
		}
	}

	public int unitJudgment(List<UsingSimulData> team, Function<BattlePower, Integer> bpFunction) {
		// TODO Auto-generated method stub
		this.unitP = 0;
		this.unitList = new ArrayList<>();
		for(int i=0; i<team.size(); i++) {
			if(RetireCheck.retireCheck(team.get(i))) {
				userP += bpFunction.apply(team.get(i).getBattlePower());
				unitList.add(bpFunction.apply(team.get(i).getBattlePower()));
			}
		}
		if(unitList.isEmpty()) {
			return 7;
		}
		this.returnFlagMin = 0;
		this.returnFlagMax = 0;
		int returnValue = random.nextInt(unitP);
		for(int i=0; i<unitList.size(); i++){
			this.returnFlagMax = unitList.get(i);
			if(returnFlagMin<=returnValue && returnValue<returnFlagMax) {
				return i;
			}else {
				this.returnFlagMin = unitList.get(i);
			}
		}
		return 7;
	}

}
