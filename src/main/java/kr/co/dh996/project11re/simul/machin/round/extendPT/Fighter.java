package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.machin.abs.PowerTable;

@Component
class Fighter extends PowerTable{

	Fighter() {
		super();
		setDefaultHp(800);
		setDefaultAp(130);
		setDefaultSp(150);
		setDefaultIp(90);
		setDefaultAg(80);
		setDefaultAs(4);
	}
}
