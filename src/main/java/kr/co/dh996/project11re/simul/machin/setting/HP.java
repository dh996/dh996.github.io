package kr.co.dh996.project11re.simul.machin.setting;

public class HP {

	public static int returnHP(int hp, int dmg) {
		if(hp>dmg) {
			return hp-dmg;
		}else {
			return 0;
		}
	}
	
	public static boolean checkHP(int hp) {
		if(hp>0) {
			return true;
		}else {
			return false;
		}
	}
}
