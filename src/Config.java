import java.util.Properties;

public class Config {

	public static String ApplicationName;
	
	public static int WindowWidth;
	
	public static int WindowHeight;
	public static String ButtonCancel;
	public static String ButtonBack;
	public static String ButtonNewGame;
	public static String ButtonInstruction;
	public static String ButtonHighscores;
	public static String ButtonEndGame;
	public static String ButtonOK;
	public static String ButtonUserName;
	
	
	
	
	public static void readConstants(Properties Config){
		ApplicationName = Config.getProperty("ApplicationName");
		WindowWidth = Integer.parseInt(Config.getProperty("WindowWidth"));
        WindowHeight = Integer.parseInt(Config.getProperty("WindowHeight"));
        ButtonCancel = Config.getProperty("ButtonCancel");
        ButtonBack = Config.getProperty("ButtonBack");
        ButtonNewGame = Config.getProperty("ButtonNewGame");
        ButtonInstruction = Config.getProperty("ButtonInstruction");
        ButtonHighscores = Config.getProperty("ButtonHighscores");
        ButtonEndGame = Config.getProperty("ButtonEndGame");
        ButtonOK = Config.getProperty("ButtonOK");
        ButtonUserName = Config.getProperty("ButtonUserName");
        
        
	}
	
	
	
}
