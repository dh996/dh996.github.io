package kr.co.dh996.project11re.simul.machin.abs;

import java.util.Random;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public abstract class DmgCalc {

	protected final Random random = new Random();
	private double dmgRate;
	private int criRate;
	
	public int getDamage(int attackPower) {
		return (int) (attackPower*(dmgRate+(random.nextInt(criRate)*0.1)));
	}
}
