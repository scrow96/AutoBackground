package autoBackground;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.Properties;

public class UserInfo 
{
	public String username, subreddit;
	public double resHeight, resWidth;
	
	public UserInfo()
	{ 
		getUsername();
		getResolution();
		getSubreddit();
	}
	
	public void getUsername()
	{
		username = System.getProperty("user.name");
	}
	
	public void getResolution()
	{
		Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
		resHeight = resolution.getHeight();
		resWidth = resolution.getWidth();
	}
	
	//Checks for a config file, then pulls subreddit property from it
	public void getSubreddit()
	{
		Properties configFile = new Properties();
		try
		{
			configFile.load(UserInfo.class.getClassLoader().getResourceAsStream("config.properties"));
			subreddit = configFile.getProperty("subreddit");
		}
		catch(Exception e)
		{
			System.out.println("File/Property not found");
		}
	}
}
