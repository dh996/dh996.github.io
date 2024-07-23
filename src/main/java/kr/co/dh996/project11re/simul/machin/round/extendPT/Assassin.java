package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.machin.abs.PowerTable;

@Component
class Assassin extends PowerTable{

	Assassin() {
		super();
		setDefaultHp(400);
		setDefaultAp(150);
		setDefaultSp(160);
		setDefaultIp(70);
		setDefaultAg(30);
		setDefaultAs(3);
	}
}
