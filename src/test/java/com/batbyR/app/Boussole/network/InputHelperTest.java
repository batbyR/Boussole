package com.batbyR.app.Boussole.network;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

import org.junit.Test;

import com.batbyR.app.Boussole.network.InputHelper.EdgeInput;
import com.batbyR.app.Boussole.network.InputHelper.StationInput;

public class InputHelperTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void testReadStationInput() {		
				
		LinkedList<StationInput> l = InputHelper.readStationsInput("src/test/resources/data/metro1/stops.txt");
		assertThat(l.size()).isEqualTo(50);
		assertThat(l.peek().name).isEqualTo("1955");
		
		
		l = InputHelper.readStationsInput("src/test/resources/data/metro1/transfers.txt");			
				
	}
	
	@Test
	public void testReadLineInput() {		
				
		LinkedList<EdgeInput> l = InputHelper.readLineInput("src/test/resources/data/metro1/stop_times.txt");
		assertThat(l.size()).isEqualTo(24);
		assertThat(l.peek().from).isEqualTo("1933");
		assertThat(l.peek().value).isEqualTo(2);
	}
	
	@Test
	public void testReadTransferInput() {		
				
		LinkedList<EdgeInput> l = InputHelper.readTransferInput("src/test/resources/data/metro1/transfers.txt");
		assertThat(l.size()).isEqualTo(1309);
		assertThat(l.peek().from).isEqualTo("5454894");
		assertThat(l.peek().value).isEqualTo(5);
	}	
	
}
