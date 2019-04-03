import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class TestWhite {
	public static void main(String[] args) {
		White w,r,b,g;
		w = new White(Color.WHITE);
		r = new White(Color.RED);
		b = new White(Color.BLUE);
		g = new White(Color.GREEN);
		w.setSize(200, 220);
		r.setSize(250,250);
		b.setSize(350,100);
		g.setSize(250,450);
		r.setTitle("Red");
		b.setTitle("Blue");
		g.setTitle("Green");
		w.setTitle("White");
		w.setVisible(true);
		r.setVisible(true);
		b.setVisible(true);
		g.setVisible(true);
		w.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}