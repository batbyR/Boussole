package com.batbyR.app.Boussole.network;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public final class InputHelper {

	private InputHelper() {};

	public static class StationInput {
		public String name;
		public String full_name;		
		
		public StationInput(String name, String full_name) {
			this.name = name;
			this.full_name = full_name;
		}
	}
	
	public static class EdgeInput {
		public String from;
		public String to;
		public int value;
		
		public EdgeInput(String from, String to, int value) {
			this.from = from;
			this.to= to;
			this.value = value;
		}
	}		

	public static LinkedList<StationInput> readStationsInput(String file) {		
	    
		LinkedList<StationInput> l = new LinkedList<StationInput>();
		String line = "";
		String cvsSplitBy = ",";
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			//TEST FILE
			line = br.readLine();			
			String[] station = line.split(cvsSplitBy);			
			if(station[0].equals("stop_id") && station[2].equals("stop_name"))
			{
			}
			else {
				throw new IllegalArgumentException("readStationsInput: wrong file");				
			}
			
			while (( line = br.readLine()) != null) {
				
				station = line.split(cvsSplitBy);				
				
				StationInput s = new StationInput(station[0], station[2] );
				l.push(s);
				
			}
		} catch (IOException e) {
			System.out.println("readStationsInput: problem with file !");			
		};
		
		return l;
	}
	
	public static LinkedList<EdgeInput> readLineInput(String file) {		
		
		LinkedList<EdgeInput> l = new LinkedList<EdgeInput>();		
		
		int previous_time = 0;
		int time = 0;
		
		String line = "";
		String cvsSplitBy = ",";		
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {			
			
			//TEST FILE
			line = br.readLine();			
			String[] station = line.split(cvsSplitBy);			
			if(!station[2].equals("departure_time") && !station[3].equals("stop_id"))
			{
				throw new IllegalArgumentException("readLineInput: wrong file");
			}
			
			//INIT
			line = br.readLine();			
			station = line.split(cvsSplitBy);
			String[] date = station[2].split(":");			
			
			time = Integer.parseInt(date[0])*60 + Integer.parseInt(date[1]);
			
			String previous_station = station[3];			
			previous_time = time;
			
			while (( line = br.readLine()) != null) {			
				
				station = line.split(cvsSplitBy);
				
				if(station[4].equals("1")) {					
					break;
				}					
				
				date = station[2].split(":");
				
				time = Integer.parseInt(date[0])*60 + Integer.parseInt(date[1]);							
				
				EdgeInput e = new EdgeInput(previous_station, station[3], (time - previous_time));
				l.push(e);				
				
				previous_time = time;
				previous_station = e.to;
				
			}
		} catch (IOException e) {			
			System.out.println("readLineInput: problem with file !");
		};
		
		return l;
		
	}
	
	public static LinkedList<EdgeInput> readTransferInput(String file) {
		
		LinkedList<EdgeInput> l = new LinkedList<EdgeInput>();		
		
		String line = "";
		String cvsSplitBy = ",";		
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {			
			
			//TEST FILE
			line = br.readLine();			
			String[] station = line.split(cvsSplitBy);
			
			if(station[0].equals("from_stop_id") &&
			   station[1].equals("to_stop_id") &&
			   station[3].equals("min_transfer_time")){
				
			}
			else {
				throw new IllegalArgumentException("readTransferInput: wrong file");
			}		
			
			while (( line = br.readLine()) != null) {				
				
				station = line.split(cvsSplitBy);				
							
				EdgeInput e = new EdgeInput(station[0], station[1], Math.round((float)Integer.parseInt(station[3]) / 60) );
				l.push(e);				
			}
		} catch (IOException e) {			
			System.out.println("readTransfertInput: problem with file !");
		};
		
		return l;
	}
	
}
