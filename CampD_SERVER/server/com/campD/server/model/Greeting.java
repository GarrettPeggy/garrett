/**
 * 
 */
package com.campD.server.model;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class Greeting implements Serializable{

    
	private static final long serialVersionUID = -1149558612574495806L;
	
	private long id;
    private String content;
    
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    
    //Introducing the dummy constructor
    public Greeting() {
    }
}
