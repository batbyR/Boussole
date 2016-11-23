package com.batbyR.app.Boussole.graphs;

import java.util.HashMap;
import java.util.LinkedList;

import com.batbyR.app.Boussole.graphs.Graph.Vertex;

public final class Search {
	
	private Search() {};
	
	public static void breadthFirstSearch(Graph g, int vertex){
		System.out.println("Performing depth bread first search");
		HashMap<Integer,String> state = new HashMap<Integer,String>();
		HashMap<Integer,Integer> p = new HashMap<Integer,Integer>();
		LinkedList<Integer> Q = new LinkedList<Integer>();	
		
		int u = 0;
		
		for(int i=0;i < g.getVertices().size(); i++){
			state.put(i, "undiscovered");
			p.put(i, (-1));
		}
		
		state.put(vertex, "discovered");
		p.put(vertex, (-1));		
		Q.add(vertex);
		
		while(Q.size()!=0){
			u = (int)Q.pop();			
			
			for(Vertex v : g.getVertex(u).getOutnodes()){
				if(state.get(v.getIndex()).equals("undiscovered")){					
					state.put(v.getIndex(),"discovered");
					p.put(v.getIndex(), u);
					Q.addLast(v.getIndex());
				}
			}
			state.put(u,"completely-explored");
			System.out.println(g.getVertex(u).getName());
		}		
	}
	
	private static void aux_depthFirstSearch(Graph g, int s, HashMap<Integer,String> state, HashMap<Integer,Integer> p){
		state.put(s, "discovered");
		System.out.println(g.getVertex(s).getName());	    		    
			
		for(Vertex v : g.getVertex(s).getOutnodes()){
			if(state.get(v.getIndex()).equals("undiscovered")){
				p.put(v.getIndex(), s);
				aux_depthFirstSearch(g, v.getIndex(), state, p);
			}			
		}
		state.put(s, "completely-explored");
	}
	
	public static void depthFirstSearch(Graph g){
		System.out.println("Performing depth first search");
		HashMap<Integer,String> state = new HashMap<Integer,String>();
		HashMap<Integer,Integer> p = new HashMap<Integer,Integer>();
		
		for(int i=0;i<g.getVertices().size();i++){
			state.put(i, "undiscovered");
		}
		
		for(int i=0; i<g.getVertices().size(); i++){
			if(state.get(i).equals("undiscovered")){
				aux_depthFirstSearch(g, i, state, p);
			}
		}
	}
	
	private static void aux_detectCycle(Graph g, int s, HashMap<Integer,String> state, HashMap<Integer,Integer> p){
		state.put(s, "discovered");		
		
		for(Vertex v : g.getVertex(s).getOutnodes()){
			if(state.get(v.getIndex()).equals("undiscovered")){
				p.put(v.getIndex(), s);
				aux_detectCycle(g, v.getIndex(), state, p);
			}
			else if(state.get(v.getIndex()).equals("discovered")&&v.getIndex()!=p.get(s)){				
				g.setCycle(true);
			}
		}
		state.put(s, "completely-explored");
	}
	
	public static boolean detectCycle(Graph g){
		System.out.println("Detecting cycle");		
		HashMap<Integer,String> state = new HashMap<Integer,String>();
		HashMap<Integer,Integer> p = new HashMap<Integer,Integer>();
		
		for(int i=0;i<g.getVertices().size();i++){
			state.put(i, "undiscovered");
		}
		
		for(int i=0; i<g.getVertices().size(); i++){
			if(state.get(i).equals("undiscovered")){
				aux_detectCycle(g, i, state, p);
			}
		}
		
		return g.has_cycle();
	}
	
	public static LinkedList<String> shortestPast(Graph g, int s, int t){
		System.out.println("Finding shortest path between "+ g.getVertex(s).getName() + " et "+ g.getVertex(t).getName());
		LinkedList<String> path = new LinkedList<String>();
		LinkedList<Integer> known = new LinkedList<Integer>();
		HashMap<Integer,Integer> dist = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> p = new HashMap<Integer,Integer>();
		int last = 0;
		int vnext = 0;
		
		known.add(s);
		p.put(s,(-1));
		for(int i=0; i < g.getVertices().size(); i++){
			dist.put(i, 10000);
		}
		
		for(Vertex v : g.getVertex(s).getOutnodes()){
			dist.put(v.getIndex(), g.get_weight(s, v.getIndex()));
			p.put(v.getIndex(), s);	
		}
		last = s;
		
		while(last!=t){
			//select vnext the unknown vertex mimnimizing dist			
			int min = 10000;
			vnext = 1;
			for(int i=0; i < g.getVertices().size(); i++){				
				if(!known.contains(i) && dist.get(i) < min){
					vnext = i;
					min = dist.get(vnext);
				}				
			}			
			for(Vertex x : g.getVertex(vnext).getOutnodes()){
				if((g.get_weight(vnext, x.getIndex()) + dist.get(vnext)) < dist.get(x.getIndex()) ){
					dist.put(x.getIndex(), (g.get_weight(vnext, x.getIndex()) + dist.get(vnext)));
					p.put(x.getIndex(), vnext);					
				}				
			}			
			last = vnext;
			known.add(vnext);			
		}		
		
		while(last!=s){			
			path.push(g.getVertex(last).getName());			
			last = p.get(last);			
		}		
		System.out.println("length: " + dist.get(t));
		path.push(g.getVertex(s).getName());
		return path;
	}
}