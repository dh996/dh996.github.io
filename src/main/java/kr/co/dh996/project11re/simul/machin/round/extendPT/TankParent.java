package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.machin.abs.PowerTable;

@Component
class TankParent extends PowerTable{

	TankParent(){
		super();
		setDefaultAg(100);
		setDefaultAs(2);
	}
}
