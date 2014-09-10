package autoBackground;
import java.io.*;
import java.net.*;

public class Image 
{
	public String url;
	
	public Image() throws Exception
	{
		UserInfo system = new UserInfo(); //Gets username, screen res, and subreddit
		URL subredditURL = new URL("http://reddit.com/r/" + system.subreddit + "/.json");
		getImageURL(subredditURL);
	}
	
	public void getImageURL(URL subreddit) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(subreddit.openStream()));
		int urlIndex, endQuoteIndex, startDomainIndex = 0;
		String imageUrl;
        String inputLine, fullText = "";        
        while ((inputLine = in.readLine()) != null)
            fullText += inputLine;
        System.out.println(fullText);
        
        do //Ensures a picture is downloaded, and skips over self posts
        {
        	urlIndex = fullText.indexOf("\"url\":", startDomainIndex);
            endQuoteIndex = fullText.indexOf("\"", urlIndex + 10);
            imageUrl = fullText.substring((urlIndex + 8), endQuoteIndex);
            startDomainIndex = urlIndex + 1;
        }
        while(imageUrl.contains("reddit.com"));
        
        System.out.println(imageUrl);
        in.close();
        url = imageUrl + ".jpg";
	}
	
	public static void downloadImage(String imageUrl, String username) throws Exception
	{
		URL url = new URL(imageUrl);
		String fileName = "AutoBackground.jpg";
		String destName = "C:\\Users\\" + username + "\\Documents\\AutoBackground\\" + fileName;
		System.out.println(destName);
	 
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destName);
	 
		byte[] b = new byte[2048];
		int length;
	 
		while((length = is.read(b)) != -1) 
		{
			os.write(b, 0, length);
		}
	 
		is.close();
		os.close();
	}
}
