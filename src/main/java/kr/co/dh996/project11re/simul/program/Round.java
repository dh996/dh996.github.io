package kr.co.dh996.project11re.simul.program;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.machin.round.ChampGrow;
import kr.co.dh996.project11re.simul.machin.round.FieldSetting;
import kr.co.dh996.project11re.simul.machin.round.ObjectSetting;
import kr.co.dh996.project11re.simul.machin.round.PowerSetting;
import kr.co.dh996.project11re.simul.machin.round.RoundManage;
import kr.co.dh996.project11re.simul.machin.round.TakeAdventage;
import kr.co.dh996.project11re.simul.machin.round.RoundCheck;

public class Round {
	//라운드 진행 관련 기능을 수행합니다.

	private final RoundManage roundManage;
	private final FieldSetting fieldSetting;
    private final ChampGrow champGrow;
    private final PowerSetting powerSetting;
    private final RoundCheck roundCheck;
    private final TakeAdventage takeAdventage;
    private final ObjectSetting objectSetting;
    private final Turn turn;

    @Autowired
    public Round(
    		RoundManage roundManage,
            FieldSetting fieldSetting,
            ChampGrow champGrow,
            PowerSetting powerSetting,
            RoundCheck roundCheck,
            TakeAdventage takeAdventage,
            ObjectSetting objectSetting,
            Turn turn) {
    	this.roundManage = roundManage;
        this.fieldSetting = fieldSetting;
        this.champGrow = champGrow;
        this.powerSetting = powerSetting;
        this.roundCheck = roundCheck;
        this.takeAdventage = takeAdventage;
        this.objectSetting = objectSetting;
        this.turn = turn;
    }

    public void roundLoop(SimulMainObject simulMO) {
    	roundManage.incRound(simulMO); //라운드 카운트를 1회 증가시킵니다.
		fieldSetting.setField(simulMO); //이번 라운드 전투 지형을 설정합니다.
		champGrow.setGrow(simulMO); //이번 라운드 챔피언 성장 수치를 결정합니다.
		powerSetting.setPower(simulMO); //이번 라운드 전투력 수치를 결정합니다.
		turn.turnLoop(simulMO,1); //전투 턴을 진행합니다.
		roundCheck.winCheck(simulMO); //전투 승패를 판정합니다.
		takeAdventage.setAdv(simulMO); //전투 결과에 따른 어드밴티지를 결정합니다.
		objectSetting.setObj(simulMO); //이번 라운드의 오브젝트 관리 기능을 실행합니다.
		roundManage.recodingSimulProcess(simulMO); //이번 라운드의 전체적 진행상황을 저장합니다.
		//다음 라운드 진행 여부를 판단합니다.
		if(winCheck.checkRound(simulMO)) {
			roundLoop(simulMO); //다음 라운드 진행 판정시 다음 라운드를 시작합니다.
		}
    }
}
