import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CalculatorTest {
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		calculator.setVisible(true);
		calculator.setSize(500, 500);
		calculator.setTitle("Calculator");
		calculator.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
