
public class Patient
{
	private String _name;
	private int _period;
	
	Patient(String name, int period)
	{
		_name = name;
		_period = period;
	}
	
	public String name()
	{
		return _name;
	}
	
	public int period()
	{
		return _period;
	}
}
