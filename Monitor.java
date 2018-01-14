import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Monitor
{
	public Scanner input;
	public int _period;
	public ArrayList<Device> _devices = new ArrayList<>();
	public ArrayList<Record> _database = new ArrayList<>();
	
	public void setUp(File inputFile) throws Exception
	{
		input = new Scanner(inputFile);
		setPeriod();
		while(input.hasNext())
		{
			Patient patient = createPatient();
			Device device = createDeviceAndAttachToPatient(patient);
			_devices.add(device);
		}
		initDatabase();
	}
	
	private void initDatabase()
	{
		for(Device device : _devices)
		{
			Patient patient = device.patient();
			Record record = new Record(patient, device);
			_database.add(record);
		}
	}
	
	private void setPeriod()
	{
		_period = Integer.parseInt(input.next());
	}
	
	private Patient createPatient() throws Exception
	{
		if(input.next().equals("patient"))
		{
			String patientName = input.next();
			int patientPeriod = Integer.parseInt(input.next());
			return new Patient(patientName, patientPeriod);
		}
		else
			throw new Exception("Please type patient when creating a patient.");
	}
	
	private Device createDeviceAndAttachToPatient(Patient patient)
	{
		String deviceCategory = input.next();
		String deviceName = input.next();
		String deviceDatasetFileName = input.next();
		int deviceLowerBound = Integer.parseInt(input.next());
		int deviceUpperBound = Integer.parseInt(input.next());
		return new Device(deviceCategory, deviceName, new File(deviceDatasetFileName), deviceLowerBound, deviceUpperBound, patient);
	}
	
	public void run()
	{
		for(int time = 0; time <= _period; time++)
		{
			for(Device device : _devices)
			{
				Patient patient = device.patient();
				
				if(time % patient.period() == 0)
				{
					float factorValue = device.getFactorValue();
					// keep an element of record in database
					RecordElement element = new RecordElement(time, factorValue);
					Record record = findSpecifiedRecordInDataBase(patient, device);
					if(record != null)
					{
						record.addElement(element);
					}
					
					// decide whether show alarms
					if(factorValue < 0) // device falls
					{
						System.out.println("[" + time + "] " +  device.name() + " falls");
					}
					else if(factorValue < device.lowerBound() || factorValue > device.upperBound()) // patients are in danger.(out of safety range)
					{
						System.out.println("[" + time + "] " +  patient.name() + " is in danger! Cause: " + device.name() + " " +  factorValue);			
					}
				}
			}
		}
	}
	
	private Record findSpecifiedRecordInDataBase(Patient patient, Device device)
	{
		for(Record record : _database)
		{
			if(patient.name().equals(record.patient().name()) && device.name().equals(record.device().name()))
			{
				return record;
			}
		}
		return null;
	}
	
	
	public void showDatabase()
	{
		for(Record record : _database)
		{
			record.print();
		}
	}
	
	
}
