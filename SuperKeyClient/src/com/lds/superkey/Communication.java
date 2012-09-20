package com.lds.superkey;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Communication {
    
    public Communication() {
        
    }
    
    public static void sendMessage(final int keyCode) {
        new Thread() {
            public void run() {
                request(keyCode);
            };
        }.start();
    }
    
    public static void request(int keyCode) {
        System.out.println("sendMessage: " + keyCode);
        try {
            Socket s = new Socket("192.168.1.150", 1234);
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();
            out.write(keyCode);
            out.close();
            in.close();
            s.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
