package com.lds.superkey;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.lds.superkey.config.Config;
import com.lds.superkey.model.SKMessage;

public class Communication {
    
    public Communication() {
        
    }
    
    public static void sendMessage(final SKMessage msg) {
        new Thread() {
            public void run() {
                request(msg);
            };
        }.start();
    }
    
    public static void request(SKMessage message) {
        System.out.println("sendMessage: " + message);
        try {
            Socket s = new Socket(Config.DOMAIN, Config.PORT);
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(message);
            out.close();
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
