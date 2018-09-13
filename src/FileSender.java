/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filevibe;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

/**
 *
 * @author Joshi
 */
public class FileSender implements HttpHandler {

    private String path="";
    
    public FileSender(String path) {
        this.path=path;     
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        /*String s="<h1>Hello</h1>";
        he.sendResponseHeaders(200, s.length());
        he.getResponseBody().write(s.getBytes());*/
        File f=new File(path);
        
        OutputStream out=he.getResponseBody();
        byte[] ary=new byte[1048576];
        //StringBuilder s=new StringBuilder();
        FileInputStream in=new FileInputStream(f);
        
        
        he.getResponseHeaders().add("Content-Type","application/octet-stream");
        he.getResponseHeaders().add("Content-Transfer-Encoding", "Binary");
        he.getResponseHeaders().add("Content-Disposition", "attachment; filename="+f.getName());
        he.sendResponseHeaders(200,f.length());
        
        System.out.println(he.getLocalAddress()+" : Sending Data to "+he.getRemoteAddress()+" ("+he.getRemoteAddress().getHostName()+") ");
        while(in.available()>1048576)
        {
            in.read(ary);
            out.write(ary);
            out.flush();
        }
        ary=new byte[1024];
        while(in.available()>1024)
        {
            in.read(ary);
            out.write(ary);
            out.flush();
        }
        while(in.available()>0)
        {
            out.write(in.read());
            out.flush();
        }
        out.flush();
        
        out.close();
        in.close();
        //he.getResponseBody().write(s.toString().getBytes());
        
    }
    
}
