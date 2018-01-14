import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Monitor
{
	public Scanner input;
	public int _period;
	public ArrayList<Device> _devices = new ArrayList<>();
	
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
	}
	
	private void setPeriod()
	{
		_period = Integer.parseInt(input.next());
	}
	
	private Patient createPatient()
	{
		Patient ret = null;
		if(input.next().equals("patient"))
		{
			String patientName = input.next();
			int patientPeriod = Integer.parseInt(input.next());
			ret = new Patient(patientName, patientPeriod);
		}
		return ret;
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
	
	
}
