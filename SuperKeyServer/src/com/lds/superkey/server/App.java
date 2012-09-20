package com.lds.superkey.server;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    
    public static void main(String[] args) throws Exception {
        System.out.println("listen on port 1234");
        ServerSocket ss = new ServerSocket(1234);
        while(true) {
            Socket s = ss.accept();
            new Thread(new SocketHandler(s)).start();
        }
    }
    
    private static class SocketHandler implements Runnable {
        private Socket socket;
        
        public SocketHandler(Socket s) {
            socket = s;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                InputStream in = socket.getInputStream();
                int keycode = in.read();
                System.out.println("accespt: " + keycode);
                
                try {
                    Robot robot = new Robot();
                    robot.keyPress(keycode);
                } catch (AWTException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }

}
