package autoBackground;
import java.io.*;
import autoBackground.WallpaperChanger.SPI;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;

public class Main 
{
	static UserInfo system = new UserInfo(); //Gets username, screen res, and subreddit
	
	public static void main(String[] args) throws Exception
	{
		Image pic = new Image();
		makeFolder(system.username);
		System.out.print(system.subreddit);
		System.out.print(pic.url);
		Image.downloadImage(pic.url, system.username);
		
		SPI.INSTANCE.SystemParametersInfo( //Changes the wallpaper to downloaded image
		        new UINT_PTR(SPI.SPI_SETDESKWALLPAPER), 
		        new UINT_PTR(0), 
		        "C:\\Users\\" + system.username + "\\Documents\\AutoBackground\\AutoBackground.jpg", 
		        new UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
	}
	
	public static void makeFolder(String username)
	{
	    File directory = new File("C:\\Users\\" + system.username + "\\Documents\\AutoBackground");
	    if (directory.exists() && directory.isFile())
	    {
	        System.out.println("The dir with name could not be" +
	        " created as it is a normal file");
	    }
	    else
	    {
            if (!directory.exists())
            {
                directory.mkdir();
            }
	    }
	}
}
