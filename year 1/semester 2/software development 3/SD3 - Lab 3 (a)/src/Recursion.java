
public class Recursion {
	private int total;
	private int total2 = 0;

	public int sum(int n) {
		if (n > 0) {
			total = n + sum(n - 1);}
		return total;
}
	public int multiply(int x, int y){
		if(y>0){
		total2 = total2 + x;
		multiply(x, y-1);}
		
		return total2;
	}
	public int fibonacci(int number){
		if(number == 0){
			return 0;
		}
		else if(number == 1){
			return 1;
		}
		else{
			return fibonacci(number-1) + fibonacci(number-2);
		}
	}
}
