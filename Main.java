import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	private static Monitor monitor = new Monitor();
	
	public static void main(String[] args) throws Exception
	{	
//		monitor.setUp(new File("sampleInput"));
		monitor.setUp(new File(args[0]));
		monitor.run();
		monitor.showDatabase();
	}
	
//	public static void testInputFile()
//	{
//		System.out.println(monitor._period);
//		for(Device device : monitor._devices)
//		{
//			System.out.println(device._patient.name() + " " + device._patient.period());
//			System.out.println(device._category + " " + device._name + " " + device._lowerBound + " " + device._upperBound);
//		}
//	}
//	
//	public static void testDataset()
//	{
//		Device device = monitor._devices.get(1);
//		Scanner dataset = device._dataset;
//		while(dataset.hasNext())
//		{
//			System.out.println(dataset.next());
//		}
//	}
}
