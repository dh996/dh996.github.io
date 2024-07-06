package kr.co.dh996.project11re.simul.machin.turn.extendsDmgCalc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.UsingSimulData;

@Component
public class DmgTerminal {

	private final ADDmg aDDmg;
	private final AssassinDmg assassinDmg;
	private final FighterDmg fighterDmg;
	private final MageDmg mageDmg;
	private final SupportDmg supportDmg;
	private final TankDmg tankDmg;
	
	@Autowired
	public DmgTerminal(ADDmg aDDmg, AssassinDmg assassinDmg,
			FighterDmg fighterDmg, MageDmg mageDmg,
			SupportDmg supportDmg, TankDmg tankDmg) {
		this.aDDmg = aDDmg;
		this.assassinDmg = assassinDmg;
		this.fighterDmg = fighterDmg;
		this.mageDmg = mageDmg;
		this.supportDmg = supportDmg;
		this.tankDmg = tankDmg;
	}
	
	public int returnDmg(UsingSimulData uSD) {
		if("ADC".equals(uSD.getPosition()) || "FureADC".equals(uSD.getPosition())
				|| "ADSupport".equals(uSD.getPosition())) {
			return aDDmg.getDamage(uSD.getBattlePower().getAttackPower());
		}else if("Assassin".equals(uSD.getPosition())) {
			return assassinDmg.getDamage(uSD.getBattlePower().getAttackPower());
		}else if("Fighter".equals(uSD.getPosition()) || "FighterTank".equals(uSD.getPosition())
				|| "DealSupport".equals(uSD.getPosition())) {
			return fighterDmg.getDamage(uSD.getBattlePower().getAttackPower());
		}else if("Mage".equals(uSD.getPosition()) || "FureMage".equals(uSD.getPosition())
				|| "MageSupport".equals(uSD.getPosition()) || "MageTank".equals(uSD.getPosition())) {
			return mageDmg.getDamage(uSD.getBattlePower().getAttackPower());
		}else if("Tank".equals(uSD.getPosition()) || "FureTank".equals(uSD.getPosition())) {
			return tankDmg.getDamage(uSD.getBattlePower().getAttackPower());
		}else {
			return supportDmg.getDamage(uSD.getBattlePower().getAttackPower());
		}
	}
}
