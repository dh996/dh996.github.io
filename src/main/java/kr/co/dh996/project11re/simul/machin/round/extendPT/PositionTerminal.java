package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PositionTerminal {

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
	}
}
