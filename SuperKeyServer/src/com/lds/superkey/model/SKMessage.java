package com.lds.superkey.model;

import java.io.Serializable;

public class SKMessage implements Serializable {
    private static final long serialVersionUID = 8319453402965432316L;

    public static final int TYPE_KEY = 1;
    
    private int type;
    
    private int keyCode;
    
    private int x;
    private int y;
    
    private int eventAction;
    
    public SKMessage() {
        
    }
    
	public static SKMessage valueOf(int keyCode, int eventAction) {
		SKMessage msg = new SKMessage();
		msg.setType(TYPE_KEY);
		msg.setKeyCode(keyCode);
		msg.setEventAction(eventAction);
		return msg;
	}
    

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

	public int getEventAction() {
		return eventAction;
	}

	public void setEventAction(int eventAction) {
		this.eventAction = eventAction;
	}


    

}
