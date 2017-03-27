import java.io.IOException;
import java.util.Properties;

public class Main 
{
	public static void main(String[] args)
	{
		String configPath = "config.properties";
		ReadConfig readConfig = new ReadConfig();
		try{
		Properties properties = readConfig.getProperties(configPath);
		Config.readConstants(properties);

		new MainWindow();
		} catch (IOException e) {
		    System.out.println("Exception when loading properties: " + e);
		}
	
	
		
	}
}
