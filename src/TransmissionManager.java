package filevibe;

import java.io.File;
import java.net.InetSocketAddress;

/**
 *
 * @author Joshi Jay
 */
public class TransmissionManager {
    
    TransmissionManager(String... files)
    {
        System.out.println("\nStarting Server........ \n");
        FileServer[] fs_ary=new FileServer[files.length];
        for(int i=0;i<fs_ary.length;i++)
        {
            File fp=new File(files[i]);
            if((!(fp.exists()))||(fp.isDirectory()))
            {
                System.out.println("File not found : "+files[i]);
            }
            else
            {
                fs_ary[i]=new FileServer(new InetSocketAddress(CONFIG.SYS_IP,29170+i),files[i]);
            }
        }
        
        System.out.println("...................................................\n");
        
    }
    
}
