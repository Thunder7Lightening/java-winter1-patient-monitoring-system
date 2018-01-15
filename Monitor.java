import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Monitor
{
	private int _time;
	private Scanner input;
	private int _period;
	private ArrayList<Device> _devices = new ArrayList<>();
	private Database _database = new Database();
	
	public void setUp(File inputFile) throws Exception
	{
		input = new Scanner(inputFile);
		setPeriod();
		setDevices();
		initDatabase();
	}
	
	private void setPeriod()
	{
		_period = Integer.parseInt(input.next());
	}
	
	private void setDevices() throws Exception
	{
		while(input.hasNext())
		{
			Patient patient = createPatient();
			Device device = createDeviceAndAttachToPatient(patient);
			_devices.add(device);
		}
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
	
	private void initDatabase()
	{
		_database.init(_devices);
	}
	
	public void run()
	{
		for(_time = 0; _time <= _period; _time++)
		{
			for(Device device : _devices)
			{
				Patient patient = device.patient();
				if(_time % patient.period() == 0)
				{
					float factorValue = device.getFactorValue();
					keepARecordElementIntoDatabase(factorValue, patient, device);
					showAlarmMessagesWhenFactorValueIsStrange(factorValue, patient, device);	
				}
			}
		}
	}

	private void keepARecordElementIntoDatabase(float factorValue, Patient patient, Device device)
	{
		RecordElement element = new RecordElement(_time, factorValue);
		Record record = findSpecifiedRecordInDataBase(patient, device);
		if(record != null)
			record.addElement(element);
	}
	
	private Record findSpecifiedRecordInDataBase(Patient patient, Device device)
	{
		for(Record record : _database.records())
			if(arePatientAndDeviceInRecord(patient, device, record))
				return record;
		return null;
	}

	private boolean arePatientAndDeviceInRecord(Patient patient, Device device, Record record)
	{
		return isPatientInRecord(patient, record) && isDeviceInRecord(device, record);
	}
	
	private boolean isPatientInRecord(Patient patient, Record record)
	{
		return patient.name().equals(record.patient().name());
	}

	private boolean isDeviceInRecord(Device device, Record record)
	{
		return device.name().equals(record.device().name());
	}
	
	private void showAlarmMessagesWhenFactorValueIsStrange(float factorValue, Patient patient, Device device)
	{
		if(doesDeviceFall(factorValue))
			System.out.println("[" + _time + "] " +  device.name() + " falls");
		else if(isFactorValueOutOfSafetyRange(factorValue, device))
			System.out.println("[" + _time + "] " +  patient.name() + " is in danger! Cause: " + device.name() + " " +  factorValue);
	}

	private boolean doesDeviceFall(float factorValue)
	{
		return factorValue < 0;
	}

	private boolean isFactorValueOutOfSafetyRange(float factorValue, Device device)
	{
		return factorValue < device.lowerBound() || factorValue > device.upperBound();
	}
	
	public void showDatabase()
	{
		_database.print();
	}
	
	
}
