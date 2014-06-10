package map;

public class Door extends Block
{
	private boolean locked;
	private String ending; //stores where the door leads to
	
	public Door (int x, int y)
	{
		super (x, y, 'D');
	}
	
	public Door (int x, int y, char prop, String end)
	{
		super (x, y, prop);
		locked = !passable;
		ending = end;
	}
}

