
public class Hero {
	String name;
	int health;
	int armor;

	int mana;

	public Hero(String name, int base_str, int base_agl, int base_int, int base_health, int base_armor, int base_mana,
			double base_healthregen, double base_attackspeed, double base_manaregen, double strength, double agility,
			double intelligence, int level) {
		double health = base_health + (base_str * 19) + (strength * 19 * (level - 1));
		double mana = base_mana + (base_int * 13) + (intelligence * 13 * (level - 1));
		double armor = base_armor + (base_agl * 0.14) + (agility * 0.14 * (level - 1));
		double manaregen = base_manaregen + (base_int * 0.04) + (intelligence * 0.04 * (level - 1));
		double healthregen = base_healthregen + (base_str * 0.03) + (strength * 0.03 * (level - 1));
		double IAS = (base_agl * 1) + (agility * 1 * (level - 1));
		double attackspeed = ((100+(base_agl * 1) + (agility * 1 * (level - 1))) * 0.01) / base_attackspeed;
		double attackspeed2 = base_attackspeed / ((100+(base_agl * 1) + (agility * 1 * (level - 1))) * 0.01) ;

		System.out.println(name);
		System.out.println(level);
		System.out.println("Health: " + health);
		System.out.println("Mana: " + mana);
		System.out.printf("Armor: %.2f \n", armor);
		System.out.println("Health Regen: " + healthregen);
		System.out.println("Mana Regen: " + manaregen);
		System.out.printf("Attack Speed: %.3f seconds \n", attackspeed);
		System.out.printf("Attacks per second: %.3f \n", attackspeed2);
		System.out.println("IAS: "+ IAS);
	}
}
