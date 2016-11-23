package com.batbyR.app.Boussole.network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

import com.batbyR.app.Boussole.graphs.Graph;
import com.batbyR.app.Boussole.graphs.Search;
import com.batbyR.app.Boussole.network.InputHelper.EdgeInput;
import com.batbyR.app.Boussole.network.InputHelper.StationInput;

public class Network implements Serializable {
	
	private Graph graph;
	private HashMap<String,String> stations;
	
	public Network(){
		this.graph = new Graph(false);
		this.stations = new HashMap<String,String>();
	}
	
	public Graph getGraph(){
		return this.graph;
	}
	
	public HashMap<String,String> getStations(){
		return this.stations;
	}
	
	public void init(){
		
		String path = "src/main/resources/data/";
		File main_folder = new File(path);
		String[] folders = main_folder.list();
		
		for(String f : folders){
			
			System.out.println(path+f);
			this.addStations(InputHelper.readStationsInput(path + f + "/stops.txt"));
			this.addLine(InputHelper.readLineInput(path + f + "/stop_times.txt"));
			this.addTransfer(InputHelper.readTransferInput(path + f + "/transfers.txt"));
		}		
	}
	
	public void save() {
		
		ObjectOutputStream oos = null;

	    try {
	      final FileOutputStream fichier = new FileOutputStream("src/main/resources/network.rb");
	      oos = new ObjectOutputStream(fichier);
	      oos.writeObject(this);
	      oos.flush();
	    } catch (final java.io.IOException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        if (oos != null) {
	          oos.flush();
	          oos.close();
	        }
	      } catch (final IOException ex) {
	        ex.printStackTrace();
	      }
	    }
	}
	
	public static Network load() {
		
		ObjectInputStream ois = null;
		Network n = null;

	    try {
	      final FileInputStream fichier = new FileInputStream("C:/Users/Romain/workspace/workspace2/Ratp/src/main/resources/network.rb");
	      ois = new ObjectInputStream(fichier);
	      n = (Network) ois.readObject();     
	      
	    } catch (final java.io.IOException e) {
	      e.printStackTrace();
	    } catch (final ClassNotFoundException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        if (ois != null) {
	          ois.close();
	        }
	      } catch (final IOException ex) {
	        ex.printStackTrace();
	      }
	    }
	    
	    return n;
	}
	
	public void addStations(LinkedList<StationInput> stations){
		for(StationInput s : stations){
			this.stations.put(s.name, s.full_name);
		}
	}
	
	public void addLine(LinkedList<EdgeInput> line){		
		for(EdgeInput e : line){
			this.graph.addVertex(e.to);
			this.graph.addVertex(e.from);
			this.graph.addEdge(this.graph.getIndexFromValue(e.from),
					           this.graph.getIndexFromValue(e.to),
					           e.value);			
		}		
	}
	
	public void addTransfer(LinkedList<EdgeInput> line){
		for(EdgeInput e : line){
			if(this.graph.getIndexFromValue(e.from)!=(-1)
			&& this.graph.getIndexFromValue(e.to)!=(-1)){
				this.graph.addEdge(this.graph.getIndexFromValue(e.from),
						           this.graph.getIndexFromValue(e.to),
						           e.value);
			}
		}
	}
	
	public LinkedList<String> shortestPath(String s1, String s2){		
		return Search.shortestPast(this.graph,
								   this.graph.getIndexFromValue(s1),
								   this.graph.getIndexFromValue(s2));
	}
	
	public int nb_stations(){
		return this.graph.getVertices().size();
	}

}
