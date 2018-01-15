import java.io.File;
import java.util.Scanner;

public class Device
{
	private String _category;
	private String _name;
	private Scanner _dataset;
	private int _lowerBound;
	private int _upperBound;
	private Patient _patient;
	
	public Device(String category, String name, File datasetFile, int lowerBound, int upperBound, Patient patient)
	{
		_category = category;
		_name = name;
		setDataset(datasetFile);
		_lowerBound = lowerBound;
		_upperBound = upperBound;
		_patient = patient;
	}
	
	private void setDataset(File datasetFile)
	{
		try
		{
			_dataset = new Scanner(datasetFile);
		}
		catch(Exception e)
		{
			System.out.println("No such dataset to create a device.");
		}
	}
	
	public float getFactorValue()
	{
		if(_dataset.hasNext())
			return Float.parseFloat(_dataset.next());
		return -1;
	}
	
	public String category()
	{
		return _category;
	}
	
	public String name()
	{
		return _name;
	}
	
	public int lowerBound()
	{
		return _lowerBound;
	}
	
	public int upperBound()
	{
		return _upperBound;
	}
	
	public Patient patient()
	{
		return _patient;
	}
	
}
