package com.lds.superkey.config;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeMap;

public class KeyMap {
    
  
    public static HashMap<Integer, Integer> keysMap = new HashMap<Integer, Integer>();
    static {
        // 方向键
        keysMap.put(com.lds.superkey.model.KeyEvent.KEYCODE_DPAD_UP, KeyEvent.VK_UP);
        keysMap.put(com.lds.superkey.model.KeyEvent.KEYCODE_DPAD_DOWN, KeyEvent.VK_DOWN);
        keysMap.put(com.lds.superkey.model.KeyEvent.KEYCODE_DPAD_RIGHT, KeyEvent.VK_RIGHT);
        keysMap.put(com.lds.superkey.model.KeyEvent.KEYCODE_DPAD_LEFT, KeyEvent.VK_LEFT);
    }
    
    public static int getKey(int keyCode) {
        if (keysMap.containsKey(keyCode)) {
            return keysMap.get(keyCode);
        } else {
            return -1;
        }
    }
    
    public static void readConfig() {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("config.ini"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        String key01 = p.getProperty("key01");
        String key02 = p.getProperty("key02");
        String key03 = p.getProperty("key03");
        String key04 = p.getProperty("key04");
        String key05 = p.getProperty("key05");
        String key06 = p.getProperty("key06");
        String key07 = p.getProperty("key07");
        String key08 = p.getProperty("key08");
        String key09 = p.getProperty("key09");
        String key10 = p.getProperty("key10");
        
        keysMap.put(Config.KEYCODE_SUPER_01, valueOf(key01));
        keysMap.put(Config.KEYCODE_SUPER_02, valueOf(key02));
        keysMap.put(Config.KEYCODE_SUPER_03, valueOf(key03));
        keysMap.put(Config.KEYCODE_SUPER_04, valueOf(key04));
        keysMap.put(Config.KEYCODE_SUPER_05, valueOf(key05));
        keysMap.put(Config.KEYCODE_SUPER_06, valueOf(key06));
        keysMap.put(Config.KEYCODE_SUPER_07, valueOf(key07));
        keysMap.put(Config.KEYCODE_SUPER_08, valueOf(key08));
        keysMap.put(Config.KEYCODE_SUPER_09, valueOf(key09));
        keysMap.put(Config.KEYCODE_SUPER_10, valueOf(key10));
        
        if (Config.DEBUG) {
            Iterator<Integer> it = new TreeMap(keysMap).keySet().iterator();
            while (it.hasNext()) {
                Integer key = it.next();
                Integer value = keysMap.get(key);
                System.out.println("key-" + key + ": "  + KeyEvent.getKeyText(value));
            }
        }
    }
    
    public static int valueOf(String keyText) {
        if (keyText.length() == 1)
            return keyText.charAt(0);
        if (keyText.equalsIgnoreCase("LEFT"))
            return KeyEvent.VK_LEFT;
        if (keyText.equalsIgnoreCase("UP"))
            return KeyEvent.VK_UP;
        if (keyText.equalsIgnoreCase("RIGHT"))
            return KeyEvent.VK_RIGHT;
        if (keyText.equalsIgnoreCase("DOWN"))
            return KeyEvent.VK_DOWN;
        return -1;
    }
    
    public static void main(String[] args) {
        readConfig();
    }

}
