package xyz.pixelatedw.MineMineNoMi3.api.telemetry;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.minecraft.client.Minecraft;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;

public class WyTelemetry 
{
	
	private static String urlConnection;
	
	static
	{
		if(WyDebug.isDebug())
			urlConnection = "http://wynd.go.ro/mineminenomi/globalstats.php";
		else
			urlConnection = "http://stats.pixelatedw.xyz/globalstats.php";
	}
	
	public static void addStat(final String statName, final int value) 
	{
        Thread newThread = new Thread()
        {
            public void run()
            {
    			String data =  "stats" + ID.PROJECT_VERSION.replace(".", "") + ":" + statName + "=" + value;
    			
    			sendData(data);
            }
        };
        newThread.setName("MMnM Stats Thread");
        newThread.start();
	}
	
	private static void sendData(String data)
	{
		if(MainConfig.enableTelemetry && !data.isEmpty() && data != null)
		{
			try 
			{
	            HttpURLConnection conn = (HttpURLConnection)(new URL(urlConnection)).openConnection();
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            conn.setRequestProperty("Content-Length", "" + data.getBytes().length);
	            conn.setRequestProperty("Content-Language", "en-US");
	            conn.setUseCaches(false);
	            conn.setDoOutput(true);
	            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	            out.writeBytes(data);
	            out.flush();
	            out.close();
	 
	            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            StringBuffer ret = new StringBuffer();
	            String line;
	 
	            while ((line = in.readLine()) != null)
	            {
	                ret.append(line);
	                ret.append('\r');
	            }
	
	            in.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
}
