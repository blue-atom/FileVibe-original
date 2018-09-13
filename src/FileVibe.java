package filevibe;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;


/**
 *
 * @author Joshi Jay
 */
public class FileVibe {

    /**
     * @param args the command line arguments
     */
    
    private static Scanner in=null;
    public static void main(String[] args) {
        try
        {
            in=new Scanner(System.in);
            CONFIG.SYS_IP=getIP();
            TransmissionManager tm=new TransmissionManager(getPaths());
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        finally
        {
            if(in!=null) in.close();
        }
    }
    private static String getIP()
    {
        
        try {
            String localIP=InetAddress.getLocalHost().getHostAddress();
            System.out.println(localIP);
            if(localIP.startsWith("192.168"))
            {
                return localIP;
            }
            
        } catch (UnknownHostException ex) {
            System.out.println("Can't resolve local IP due to Runtime Error");
        }
        do
        {
            System.out.println("Give IP : ");
            String ip_input=in.nextLine();
            if(isValidIP(ip_input))
            {
                return ip_input;
            }
            else
            {
                System.out.println("Invalid IPv4 IP, try again.");
            }
        }
        while(true);
    }
    private static boolean isValidIP(String s)
    {
        return s.matches("^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$");
    }
    private static String[] getPaths()
    {
        System.out.print("\nEnter Number of Files : ");
        String[] paths=new String[in.nextInt()];
        Arrays.fill(paths, "");
        in.nextLine();
        for(int i=0;i<paths.length;i++)
        {
            System.out.print("\nPath of file #"+(i+1)+" : ");
            paths[i]=in.nextLine();
            
        }
        in.close();
        return paths;
        
    }
    
}
