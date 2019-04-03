package program2;

public class City {
	private int[] timezone;
	private String cityname;
	
	public City(String cityname, int[] timezone){
		this.timezone = timezone;
		this.cityname = cityname;
	}
	
	public String getCityName(){
		return cityname;
	}
	
	public int[] getTimeZone(){
		return timezone;
	}
}
