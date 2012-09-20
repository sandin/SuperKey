package com.lds.superkey.server.test;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.lds.superkey.config.Config;
import com.lds.superkey.model.SKMessage;

public class MockClient {
    
    public static void main(String[] args) throws Exception {
        
        SKMessage message = new SKMessage();
        message.setType(SKMessage.TYPE_KEY);
        message.setKeyCode(12);
        
        Socket s = new Socket(Config.DOMAIN, Config.PORT);
        OutputStream out = s.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(message);
        out.close();
        s.close();
    }

}
