package com.batbyR.app.Boussole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.batbyR.app.Boussole.network.Network;

@SpringBootApplication
public class App 
{
	public static Network n;
	
    public static void main( String[] args )
    {
    	n = new Network();
    	n.init();    	
        SpringApplication.run(App.class, args);
    }   
}
