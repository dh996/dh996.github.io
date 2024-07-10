package kr.co.dh996.project11re.simul.program;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.machin.round.ChampGrow;
import kr.co.dh996.project11re.simul.machin.round.FieldSetting;
import kr.co.dh996.project11re.simul.machin.round.ObjectSetting;
import kr.co.dh996.project11re.simul.machin.round.PowerSetting;
import kr.co.dh996.project11re.simul.machin.round.RoundManage;
import kr.co.dh996.project11re.simul.machin.round.TakeAdventage;
import kr.co.dh996.project11re.simul.machin.round.RoundCheck;

@Component
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
    
    //라운드를 실행합니다.
    public void roundStart(SimulMainObject simulMO) {
    	do {
    		roundLoop(simulMO);
        } while (roundCheck.winCheck(simulMO)>2); //종료 조건을 만족할때까지 라운드루프를 반복합니다.
    }

    public void roundLoop(SimulMainObject simulMO) {
    	roundManage.incRound(simulMO); //라운드 카운트를 1회 증가시킵니다.
		fieldSetting.setField(simulMO); //이번 라운드 전투 지형을 설정합니다.
		champGrow.setGrow(simulMO); //이번 라운드 챔피언 성장 수치를 결정합니다.
		powerSetting.setPower(simulMO); //이번 라운드 전투력 수치를 결정합니다.
		int turnWin = turn.turnStart(simulMO); //전투 턴을 진행합니다.
		takeAdventage.setAdv(simulMO, turnWin); //전투 결과에 따른 어드밴티지를 결정합니다.
		objectSetting.setObj(simulMO); //이번 라운드의 오브젝트 관리 기능을 실행합니다.
		roundManage.recodingSimulProcess(simulMO); //이번 라운드의 전체적 진행상황을 저장합니다.
    }
}
