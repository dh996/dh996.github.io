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
}
