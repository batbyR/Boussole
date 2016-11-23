package com.batbyR.app.Boussole;

import java.util.LinkedList;

public class Path {
	
	private String start;
	private String end;
	private LinkedList<String> shortestPath;
	
	public Path(String start, String end) {
		this.start = start;
		this.end = end;		
		this.shortestPath = App.n.shortestPath(start, end);
	}
	
	public String getStart() {
		return this.start;
	}
	
	public String getEnd() {
		return this.end;
	}
	
	public LinkedList<String> getShortestPath(){
		return this.shortestPath;
	}

}
