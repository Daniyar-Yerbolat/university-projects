package package1;

import java.io.Serializable;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

public class Item implements Serializable {
	 private static final AtomicInteger count = new AtomicInteger(0); 
	private int id= 0;
	private String type;
	private String title;
	private float cost;
	private String developer;
	private String publisher;
	private Calendar release_date;
	private String[] genre;
	private String game_engine;
	private String image;
	private static final long serialVersionUID = 1L;

	public Item(String type, String title, float cost, String developer, String publisher,
			Calendar release_date, String[] genre, String game_engine, String image) {
		this.id = count.incrementAndGet();
		this.type = type;
		this.title = title;
		this.cost = cost;
		this.developer = developer;
		this.publisher = publisher;
		this.release_date = release_date;
		this.genre = genre;
		this.game_engine = game_engine;
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public float getCost() {
		return cost;
	}

	public String getRelease_Date() {
		int year = release_date.get(Calendar.YEAR);
		int month = release_date.get(Calendar.MONTH);
		int day = release_date.get(Calendar.DAY_OF_MONTH);
		return String.format("%02d-%02d-%4d", day, (month + 1), year);
	}

	public String[] getGenre() {
		return genre;
	}

	public String getGame_engine() {
		return game_engine;
	}

	public String getDeveloper() {
		return developer;
	}

	public String getPublisher() {
		return publisher;
	}

	public int getId() {
		return id;
	}
	
	public void increment_and_assign_id(){
		id = count.getAndIncrement();
	}
}
