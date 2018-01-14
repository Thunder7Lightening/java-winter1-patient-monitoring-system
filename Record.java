import java.util.ArrayList;

public class Record
{
	private Patient _patient;
	private Device _device;
	private ArrayList<RecordElement> _elements = new ArrayList<>();
	
	public Patient patient()
	{
		return _patient;
	}
	
	public Device device()
	{
		return _device;
	}
	
	public void addElement(RecordElement element)
	{
		_elements.add(element);
	}
	
	public Record(Patient patient, Device device)
	{
		_patient = patient;
		_device = device;
	}
	
	public void print()
	{
		printPatientName();
		printDeviceCategoryAndName();
		printElements();
	}
	
	private void printPatientName()
	{
		System.out.println("patient " + _patient.name());
	}
	
	private void printDeviceCategoryAndName()
	{
		System.out.println(_device.category() + " " + _device.name());
	}
	
	private void printElements()
	{
		for(RecordElement element : _elements)
		{
			System.out.println("[" + element.time() + "] " + element.factorValue());
		}
	}
}

