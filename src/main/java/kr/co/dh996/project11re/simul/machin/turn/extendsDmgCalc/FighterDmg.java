package kr.co.dh996.project11re.simul.machin.turn.extendsDmgCalc;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.machin.abs.DmgCalc;

@Component
class FighterDmg extends DmgCalc{

	FighterDmg(){
		super();
		setDmgRate(1.3);
		setCriRate(5);
	}
}
