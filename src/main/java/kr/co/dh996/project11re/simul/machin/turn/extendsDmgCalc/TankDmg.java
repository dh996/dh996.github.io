package kr.co.dh996.project11re.simul.machin.turn.extendsDmgCalc;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.machin.abs.DmgCalc;

@Component
class TankDmg extends DmgCalc{

	TankDmg(){
		super();
		setDmgRate(1);
		setCriRate(3);
	}
}
