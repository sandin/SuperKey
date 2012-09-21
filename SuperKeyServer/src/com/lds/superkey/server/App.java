package com.lds.superkey.server;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.lds.superkey.config.Config;
import com.lds.superkey.model.SKMessage;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("listen on port " + Config.PORT);
        ServerSocket ss = new ServerSocket(Config.PORT);
        while (true) {
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
            try {
                InputStream in = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(in);
                SKMessage message = (SKMessage) ois.readObject();
                System.out.println("Got message: " + message);

                int keyCode = translateKeyCode(message.getKeyCode());

                Robot robot = new Robot();
                try {
                    robot.keyPress(keyCode);
                } catch (IllegalArgumentException e) {
                    // keyCode wrong
                }
            } catch (AWTException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    /**
     * 将Android的keycode转成awt的keycode
     * 
     * @param keyCode of android
     * @return
     */
    public static int translateKeyCode(int keyCode) {
        if (keyCode >= com.lds.superkey.model.KeyEvent.KEYCODE_A
                && keyCode <= com.lds.superkey.model.KeyEvent.KEYCODE_Z) {
            return keyCode - com.lds.superkey.model.KeyEvent.KEYCODE_A + 'A';
        }
        else if (keyCode == com.lds.superkey.model.KeyEvent.KEYCODE_DPAD_UP) {
            return KeyEvent.VK_UP;
        }
        else if (keyCode == com.lds.superkey.model.KeyEvent.KEYCODE_DPAD_DOWN) {
            return KeyEvent.VK_DOWN;
        }
        else if (keyCode == com.lds.superkey.model.KeyEvent.KEYCODE_DPAD_LEFT) {
            return KeyEvent.VK_LEFT;
        }
        else if (keyCode == com.lds.superkey.model.KeyEvent.KEYCODE_DPAD_RIGHT) {
            return KeyEvent.VK_RIGHT;
        } else {
            return -1;
        }
    }
    
    
    private static boolean longPress = false; 
    
    public static void longPress(Robot rebot, int keyCode) {
        longPress = true;
        while (longPress) { // TODO: timer
            rebot.keyPress(keyCode);
        }
    }
    
    public static void keyRelease(Robot robot, int keyCode) {
        longPress = false;
        robot.keyRelease(keyCode);
    }

}
