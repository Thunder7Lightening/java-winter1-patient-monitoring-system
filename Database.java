import java.util.ArrayList;

public class Database
{
	private ArrayList<Record> _records = new ArrayList<>();
	
	public void init(ArrayList<Device> devices)
	{
		for(Device device : devices)
		{
			Patient patient = device.patient();
			Record record = new Record(patient, device);
			_records.add(record);
		}
	}
	
	public ArrayList<Record> records()
	{
		return _records;
	}
	
	public void print()
	{
		for(Record record : _records)
			record.print();
	}
}
