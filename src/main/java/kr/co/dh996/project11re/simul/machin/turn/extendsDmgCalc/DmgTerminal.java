package kr.co.dh996.project11re.simul.machin.turn.extendsDmgCalc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
	public int returnDmg(String type, int attackPower) {
		if("ADC".equals(type) || "FureADC".equals(type) || "ADSupport".equals(type)) {
			return aDDmg.getDamage(attackPower);
		}else if("Assassin".equals(type)) {
			return assassinDmg.getDamage(attackPower);
		}else if("Fighter".equals(type) || "FighterTank".equals(type) || "DealSupport".equals(type)) {
			return fighterDmg.getDamage(attackPower);
		}else if("Mage".equals(type) || "FureMage".equals(type)
				|| "MageSupport".equals(type) || "MageTank".equals(type)) {
			return mageDmg.getDamage(attackPower);
		}else if("Tank".equals(type) || "FureTank".equals(type)) {
			return tankDmg.getDamage(attackPower);
		}else {
			return supportDmg.getDamage(attackPower);
		}
	}
}
