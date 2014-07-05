package map;

//this class is used to direct the character through different maps
public class Door extends Block
{
	//public boolean locked;
	public String ending; //stores where the door leads to
	public boolean newMap;
	public int endCor [] = new int [2]; //stores the coordinates at the ending
	public Door (int x, int y)
	{
		super (x, y, 'D');
	}
	
	public Door (int x, int y, char prop, String end, int[] eC)
	{
		super (x, y, prop);
		ending = end;
		endCor = eC;
	}
}

