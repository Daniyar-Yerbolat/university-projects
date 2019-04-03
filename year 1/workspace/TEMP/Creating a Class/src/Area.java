
public class Area 
{	
	private int Height;
	private int Width;
	private int recArea;

		public Area(int myHeight, int myWidth)
		{
			Height=myHeight;
			Width=myWidth;
			recArea=0;
		}
		public int getArea()
		{
			recArea = Width*Height;
			return recArea;
		}
		
}