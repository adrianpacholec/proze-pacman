import java.util.Properties;

public class Config {

	public static String ApplicationName;
	
	public static int WindowWidth;
	
	public static int WindowHeight;
	
	
	
	
	public static void readConstants(Properties Config){
		ApplicationName = Config.getProperty("ApplicationName");
		WindowWidth = Integer.parseInt(Config.getProperty("WindowWidth"));
        WindowHeight = Integer.parseInt(Config.getProperty("WindowHeight"));
        
		
		
		
	}
	
	
	
}
