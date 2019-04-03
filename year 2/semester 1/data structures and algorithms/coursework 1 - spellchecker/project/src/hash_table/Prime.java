package hash_table;

public class Prime {

	public Prime() {

	}

	public int getNextPrime(int number) {
		int i = number * 2;
		while (true) {
				int counter = 0;
				for (int num = i; num >= 1; num--) {

					if (counter > 2) {
						break;
					}
					if (i % num == 0) {
						counter = counter + 1;
					}
				}
				if (counter == 2) {
					return i;
				}
			i++;
		}
	}

	public int getPreviousPrime(int number) {
		int prime_number = number;
		int i = number;
		while (true) {
			if (i*2 <= prime_number) {
				int counter = 0;
				for (int num = i; num >= 1; num--) {

					if (counter > 2) {
						break;
					}
					if (i % num == 0) {
						counter = counter + 1;
					}
				}
				if (counter == 2) {
					prime_number = i;
					return prime_number;
				}
			}
			i--;
		}
		
	}
}

// int[] array_size = { 7, 17, 37, 79, 163, 331, 673, 1361, 2729, 5471, 10949,
// 21911, 43853, 87719, 175447, 350899};