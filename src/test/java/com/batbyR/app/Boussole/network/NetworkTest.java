package com.batbyR.app.Boussole.network;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.batbyR.app.Boussole.network.InputHelper.EdgeInput;
import com.batbyR.app.Boussole.network.InputHelper.StationInput;

public class NetworkTest {

	private Network network;	
	
	@Before
	public void setUp() throws Exception {		
		network = new Network();
	}
	
	@After
	public void tearDown() throws Exception {		
		network = null;
	}
	
	@Test
	public void testNetwork() {
		assertNotNull("No instance", network);
	}
	
	@Test
	public void testInit() {
		network.init();
		assertTrue(!network.getStations().isEmpty());
	}
	
	@Test
	public void testAddStations() {
		LinkedList<StationInput> l = new LinkedList<StationInput>();
		StationInput s = new StationInput("1000","test");
		
		l.add(s);		
		network.addStations(l);
		
		assertThat(network.getStations().get("1000")).isEqualTo("test");
	}
	
	@Test
	public void testAddLine() {
		LinkedList<EdgeInput> l = new LinkedList<EdgeInput>();
		EdgeInput e = new EdgeInput("1000","1001",5);
		
		l.add(e);
		network.addLine(l);
		
		assertThat(network.getGraph().getIndexFromValue("1000")).isNotEqualTo(-1);
		assertThat(network.getGraph().hasEdge(network.getGraph().getIndexFromValue("1000"),					
				                              network.getGraph().getIndexFromValue("1001"))).isTrue();	
	}
	
	@Test
	public void testShortestPath() {
		network.init();
		assertThat(network.shortestPath("2008","1710").size()).isPositive();
		assertThat(network.shortestPath("2008","1710").peek()).isEqualTo("2008");
	}
	
}
