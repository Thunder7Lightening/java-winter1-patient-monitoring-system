
public class RecordElement
{
	private int _time;
	private float _factorValue;
	
	public RecordElement(int time, float factorValue)
	{
		_time = time;
		_factorValue = factorValue;
	}
	
	public int time()
	{
		return _time;
	}
	
	public float factorValue()
	{
		return _factorValue;
	}
}
