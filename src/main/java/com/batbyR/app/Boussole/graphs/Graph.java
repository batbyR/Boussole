package com.batbyR.app.Boussole.graphs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Graph implements Serializable {
	
	private boolean directed;
	private boolean has_cycle = false;
	private ArrayList<Vertex> vertices= null;	
	
	public class Vertex implements Serializable {
	
		private int index;
		private String name;		
		private ArrayList<Vertex> outnodes = null;
		private HashMap<Vertex,Integer> weights = null;
				
		public Vertex(int index, String name){
			this.index = index;
			this.name = name;			
			outnodes = new ArrayList<Vertex>();
			weights = new HashMap<Vertex,Integer>();
		}
		
		public int getIndex(){
			return this.index;
		}
		
		public String getName(){
			return this.name;
		}
		
		public ArrayList<Vertex> getOutnodes(){
			return this.outnodes;
		}	
				
		public void setOutnodes(ArrayList<Vertex> outnodes){
			this.outnodes = outnodes;
		}		
		
	}
	
	//GRAPH
	
	public Graph(boolean directed){
		this.directed = directed;
		this.has_cycle = false;
		this.vertices = new ArrayList<Vertex>();		
	}
	
	public ArrayList<Vertex> getVertices(){
		return this.vertices;
	}
	
	public boolean has_cycle(){
		return this.has_cycle;
	}
	
	public void setCycle(boolean v){
		this.has_cycle = v;
	}
	
	//EDGES
	
	public void addEdge(int i, int j, int value){
		if(!this.hasEdge(i, j)){
			this.vertices.get(i).getOutnodes().add(this.vertices.get(j));
			(this.vertices.get(i).weights).put(this.vertices.get(j),value);
			
			if(!this.directed){
				this.vertices.get(j).getOutnodes().add(this.vertices.get(i));
				(this.vertices.get(j).weights).put(this.vertices.get(i),value);
			}
		}
	}
	
	public void removeEdge(int i, int j){
		if(hasEdge(i,j)){
			this.getVertices().get(i).getOutnodes().remove(this.getVertices().get(j));
			this.getVertices().get(i).weights.remove(this.getVertices().get(j));
			
			if(!this.directed){
				this.getVertices().get(j).getOutnodes().remove(this.getVertices().get(i));
				this.getVertices().get(j).weights.remove(this.getVertices().get(i));
			}
		}
	}	
	
	public boolean hasEdge(int i, int j){
		return this.getVertices().get(i).getOutnodes().contains(this.vertices.get(j));
	}
	
	public int get_weight(int i, int j){
		if(this.vertices.get(i).getOutnodes().contains(this.vertices.get(j))){
			return this.vertices.get(i).weights.get(this.vertices.get(j));
		}
		else return (-1);
	}
	
	//VERTICES
	
	public void addVertex(String name){
		if(this.getIndexFromValue(name)==(-1)){
			Vertex vertex = new Vertex(this.vertices.size(), name);
			this.vertices.add(vertex);
		}
	}
	
	public void removeVertex(int v){			
		for(Vertex v2: this.vertices){
			removeEdge(v2.getIndex(),v);
		}
		this.vertices.remove(v);
	}
	
	public Vertex getVertex(int i){
		return vertices.get(i);
	}	
	
	public int getIndexFromValue(String s){
		for(int i=0; i < this.vertices.size(); i++){
			if(this.vertices.get(i).getName().equals(s)){
				return i;
			}
		}
		return (-1);
	}
	
	public void print(){
		for (Vertex v : this.getVertices()) {
			System.out.print(v.getName() + ": ");
			for (Vertex w : v.getOutnodes()) {
				System.out.print(w.getName() + " ");
			}
			System.out.println();
		}
	}
}
