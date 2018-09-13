/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filevibe;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joshi
 */
public class FileServer {
    private HttpServer srv;
    FileServer(InetSocketAddress iAddr,String path)
    {
        try {
            
            srv=HttpServer.create(iAddr, 999);
            srv.createContext("/",new FileSender(path));
            srv.start();
            System.out.println(path+" @ "+iAddr.getAddress()+":"+iAddr.getPort());
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public InetSocketAddress getAddress()
    {
        return srv.getAddress();
    }
}
