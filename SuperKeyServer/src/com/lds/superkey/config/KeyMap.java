package com.lds.superkey.config;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyMap {
    
    public static final int KEYCODE_SUPER_1 = 99901;
    public static final int KEYCODE_SUPER_2 = 99902;
    public static final int KEYCODE_SUPER_3 = 99903;
    public static final int KEYCODE_SUPER_4 = 99904;
    
    public static HashMap<Integer, Integer> keyMaps = new HashMap<Integer, Integer>();
    static {
        keyMaps.put(KEYCODE_SUPER_1, KeyEvent.VK_W);
        keyMaps.put(KEYCODE_SUPER_2, KeyEvent.VK_A);
        keyMaps.put(KEYCODE_SUPER_3, KeyEvent.VK_S);
        keyMaps.put(KEYCODE_SUPER_4, KeyEvent.VK_D);
        keyMaps.put(com.lds.superkey.model.KeyEvent.KEYCODE_DPAD_UP, KeyEvent.VK_UP);
        keyMaps.put(com.lds.superkey.model.KeyEvent.KEYCODE_DPAD_DOWN, KeyEvent.VK_DOWN);
        keyMaps.put(com.lds.superkey.model.KeyEvent.KEYCODE_DPAD_RIGHT, KeyEvent.VK_RIGHT);
        keyMaps.put(com.lds.superkey.model.KeyEvent.KEYCODE_DPAD_LEFT, KeyEvent.VK_LEFT);
    }
    
    public static int getKey(int keyCode) {
        if (keyMaps.containsKey(keyCode)) {
            return keyMaps.get(keyCode);
        } else {
            return -1;
        }
    }

}
