package kr.co.dh996.project11re.simul.machin.turn.extendsDmgCalc;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.machin.abs.DmgCalc;

@Component
class AssassinDmg extends DmgCalc{

	AssassinDmg(){
		super();
		setDmgRate(2);
		setCriRate(9);
	}
}
