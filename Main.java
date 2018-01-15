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
}
