package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.UsingSimulData;

@Component
public class PositionTerminal {
	//전투력 설정 객체들을 모아서 선언하고 메인 인스턴트를 참조시켜 값을 변경시키는 기능을 수행합니다.

	private final ADC aDC;
	private final ADSupport aDSupport;
	private final Assassin assassin;
	private final DealSupport dealSupport;
	private final Fighter fighter;
	private final FightTank fightTank;
	private final FureADC fureADC;
	private final FureMage fureMage;
	private final FureTank fureTank;
	private final Mage mage;
	private final MageSupport mageSupport;
	private final MageTank mageTank;
	private final Support support;
	private final Tank tank;
	private final TankSupport tankSupport;
	private final UtilSupport utilSupport;
	private boolean utilSupFlagU;
	private boolean utilSupFlagE;
	
	@Autowired
	public PositionTerminal(ADC aDC, ADSupport aDSupport, Assassin assassin,
			DealSupport dealSupport, Fighter fighter, FightTank fightTank,
			FureADC fureADC, FureMage fureMage, FureTank fureTank, Mage mage,
			MageSupport mageSupport, MageTank mageTank, Support support,
			Tank tank, TankSupport tankSupport, UtilSupport utilSupport) {
		this.aDC = aDC;
		this.aDSupport = aDSupport;
		this.assassin = assassin;
		this.dealSupport = dealSupport;
		this.fighter = fighter;
		this.fightTank = fightTank;
		this.fureADC =fureADC;
		this.fureMage = fureMage;
		this.fureTank = fureTank;
		this.mage = mage;
		this.mageSupport = mageSupport;
		this.mageTank = mageTank;
		this.support = support;
		this.tank = tank;
		this.tankSupport = tankSupport;
		this.utilSupport = utilSupport;
		this.utilSupFlagU = false;
		this.utilSupFlagE = false;
	}

	public void setUtilSupFlag(boolean utilSupFlagU, boolean utilSupFlagE) {
		// TODO Auto-generated method stub
		this.utilSupFlagU = utilSupFlagU;
		this.utilSupFlagE = utilSupFlagE;
	}

	//들어온 데이터를 역할군별로 분류하여 전투력 설정을 진행합니다.
	public void setPower(UsingSimulData usingSimulData, int dragonSize, int baron, int elder) {
		// TODO Auto-generated method stub
		if("ADC".equals(usingSimulData.getPosition())) {
			aDC.setBattlePower(usingSimulData, dragonSize, baron, elder);
			if(utilSupFlagU && usingSimulData.getTeam() == 0) {
				aDC.checkUtilSup(usingSimulData.getBattlePower());
			}else if(utilSupFlagE && usingSimulData.getTeam() == 1) {
				aDC.checkUtilSup(usingSimulData.getBattlePower());
			}
		}else if("FureADC".equals(usingSimulData.getPosition())) {
			fureADC.setBattlePower(usingSimulData, dragonSize, baron, elder);
			if(utilSupFlagU && usingSimulData.getTeam() == 0) {
				fureADC.checkUtilSup(usingSimulData.getBattlePower());
			}else if(utilSupFlagE && usingSimulData.getTeam() == 1) {
				fureADC.checkUtilSup(usingSimulData.getBattlePower());
			}
		}else if("ADSupport".equals(usingSimulData.getPosition())) {
			aDSupport.setBattlePower(usingSimulData, dragonSize, baron, elder);
		}else if("Assassin".equals(usingSimulData.getPosition())) {
			assassin.setBattlePower(usingSimulData, dragonSize, baron, elder);
		}else if("DealSupport".equals(usingSimulData.getPosition())) {
			dealSupport.setBattlePower(usingSimulData, dragonSize, baron, elder);
		}else if("Fighter".equals(usingSimulData.getPosition())) {
			fighter.setBattlePower(usingSimulData, dragonSize, baron, elder);
		}else if("FightTank".equals(usingSimulData.getPosition())) {
			fightTank.setBattlePower(usingSimulData, dragonSize, baron, elder);
		}else if("FureMage".equals(usingSimulData.getPosition())) {
			fureMage.setBattlePower(usingSimulData, dragonSize, baron, elder);
		}else if("FureTank".equals(usingSimulData.getPosition())) {
			fureTank.setBattlePower(usingSimulData, dragonSize, baron, elder);
		}else if("Mage".equals(usingSimulData.getPosition())) {
			mage.setBattlePower(usingSimulData, dragonSize, baron, elder);
		}else if("MageSupport".equals(usingSimulData.getPosition())) {
			mageSupport.setBattlePower(usingSimulData, dragonSize, baron, elder);
		}else if("MageTank".equals(usingSimulData.getPosition())) {
			mageTank.setBattlePower(usingSimulData, dragonSize, baron, elder);
		}else if("Support".equals(usingSimulData.getPosition())) {
			support.setBattlePower(usingSimulData, dragonSize, baron, elder);
		}else if("Tank".equals(usingSimulData.getPosition())) {
			tank.setBattlePower(usingSimulData, dragonSize, baron, elder);
		}else if("TankSupport".equals(usingSimulData.getPosition())) {
			tankSupport.setBattlePower(usingSimulData, dragonSize, baron, elder);
		}else if("UtilSupport".equals(usingSimulData.getPosition())) {
			utilSupport.setBattlePower(usingSimulData, dragonSize, baron, elder);
		}
	}
}
