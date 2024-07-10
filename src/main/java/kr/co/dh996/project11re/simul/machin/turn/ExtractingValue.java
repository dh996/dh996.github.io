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

	//수치의 합계를 반환하는 메소드입니다.
	public int checkPower(List<UsingSimulData> team, Function<BattlePower, Integer> bpFunction) {
		// TODO Auto-generated method stub
		int sum = 0;
		for(int i=0; i<team.size(); i++) {
			if(RetireCheck.retireCheck(team.get(i))) {
				sum += bpFunction.apply(team.get(i).getBattlePower());
			}
		}
		return sum;
	}

	//수치를 사용해 기능을 수행할 팀 선택시 사용됩니다.
	public int teamJudgment(List<UsingSimulData> userTeam, List<UsingSimulData> enemyTeam,
			Function<BattlePower, Integer> bpFunction) {
		// TODO Auto-generated method stub
		this.userP = checkPower(userTeam, bpFunction); 
		this.enemyP = checkPower(enemyTeam, bpFunction);
		if(random.nextInt(userP+enemyP)<=userP) {
			return 0;
		}else {
			return 1;
		}
	}

	//수치를 사용해 기능을 수행할 챔피언 선택시 사용됩니다.
	public int unitJudgment(List<UsingSimulData> team, Function<BattlePower, Integer> bpFunction) {
		// TODO Auto-generated method stub
		this.unitP = checkPower(team, bpFunction);
		this.unitList = aliveList(team, bpFunction); //생존해있는 인원들로만 추려냅니다.
		if(unitList.isEmpty()) {
			return 7;
		}
		this.returnFlagMin = 0;
		this.returnFlagMax = 0;
		int returnValue = random.nextInt(unitP);
		for(int i=0; i<unitList.size(); i++){
			this.returnFlagMax += unitList.get(i);
			if(returnFlagMin<=returnValue && returnValue<returnFlagMax) {
				return i;
			}else {
				this.returnFlagMin += unitList.get(i);
			}
		}
		return 7;
	}

	public List<Integer> aliveList(List<UsingSimulData> team, Function<BattlePower, Integer> bpFunction) {
		// TODO Auto-generated method stub
		List<Integer> aliveList = new ArrayList<>();
		for(int i=0; i<team.size(); i++) {
			if(RetireCheck.retireCheck(team.get(i))) {
				aliveList.add(bpFunction.apply(team.get(i).getBattlePower()));
			}
		}
		return aliveList;
	}

}
