package firstVersion;

import java.util.ArrayList;
import java.util.Collections;

public class TestMain {

	public static void main(String[] args) {
		// Read orders from command line
		int laptop_order = Integer.parseInt(args[0]);
		int mouse_order = Integer.parseInt(args[1]);
		int desktop_order = Integer.parseInt(args[2]);
		int LCD_screen_order = Integer.parseInt(args[3]);
		
		// Save the orders in array list
		ArrayList<Integer> order = new ArrayList<Integer>();
		Collections.addAll(order, laptop_order, mouse_order, desktop_order, LCD_screen_order);
		
		for (Object x: order) {
			System.out.println(x);
		}
		
		// Compute total volume of shipment
		double laptop_volume = 60*50*50;
		double mouse_volume = 30*30*20;
		double desktop_volume = 100*150*50;
		double LCD_screen_volume = 120*40*80;
		
		double order_volume = order.get(0) * laptop_volume + order.get(1) * mouse_volume + 
							  order.get(2) * desktop_volume + order.get(3) * LCD_screen_volume;
		
		// Convert volume to cubic meters
		order_volume = order_volume/1_000_000;
		
		System.out.println("Order volume: " + order_volume + " m^3");
		
		// Compute total weight of the shipment
		double laptop_weight = 6.5;
		double mouse_weight = 0.2;
		double desktop_weight = 20;
		double LCD_screen_weight = 2.6;		
		
		double order_weight = order.get(0) * laptop_weight + order.get(1) * mouse_weight + 
				  			  order.get(2) * desktop_weight + order.get(3) * LCD_screen_weight;
		
		System.out.println("Order weight : " + order_weight + " kg");
		
		// Compute total volume of available containers
		double containerL_volume = 2.59 * 2.43 * 12.01;
		double containerS_volume = 2.59 * 2.43 * 6.06;
		
		System.out.println("Container(L) Volume : " + containerL_volume);
		System.out.println("Container(S) volume : " + containerS_volume);
		
		// Select the type of container according to shipment volume
		double containerL_number = order_volume / containerL_volume;
		containerL_number = Math.ceil(containerL_number);
		System.out.println(containerL_number);
		
		double total_price = containerL_number * 1800;
		System.out.println("Total Price: " + total_price);
		
		// Compare suitable and lower price shipping methods
		
			// Calculate possible container combinations
		ArrayList container_L = new ArrayList();
		ArrayList container_S = new ArrayList();
		
		for (int i = (int) containerL_number; i >= 0; i--) {
			for (int j = 0; j < 3 * containerL_number; j++) {
				if (i * containerL_volume + j * containerS_volume >= order_volume) {
					container_L.add(i);
					container_S.add(j);
					break;
				}
			}
		}
		
		for(Object x: container_L) {
			System.out.println(x);
		}
		System.out.println();
		for(Object y: container_S) {
			System.out.println(y);
		}
		
			// Go through all of them and save the best one
		int[] min = {(int) container_L.get(0), (int) container_S.get(0)};
		for (int i = 1; i < container_L.size(); i++) {
			if (( (int) container_L.get(i) * 1800) + ( (int) container_S.get(i) * 1200) < (min[0] * 1800) + (min[1] * 1200)) {
				min[0] = (int) container_L.get(i);
				min[1] = (int) container_S.get(i);
			}
		}
		
		// If there is a small container included (max 1 if there is a little volume leftover) we have to check how heavy it is to know the cost
		ArrayList<Integer> list_increasing_kg_per_volume = new ArrayList<Integer>();
		Collections.addAll(list_increasing_kg_per_volume, laptop_order, mouse_order, desktop_order, LCD_screen_order);
		
		// TODO - SORT OR CREATE THE LIST BASED ON THE HIGHEST WEIGHT/VOLUME
		
		int x = 1200; // initialize price of small container to large
		
		//min[1] != 0
		if (true) {
			double volume_left = min[0] * containerL_volume - order_volume;
			System.out.println("Volume left: " + volume_left);
			double weight_container_S = 0;	
			outerloop:
			for (int i = 0; i < list_increasing_kg_per_volume.size(); i++) {
				for (int j = 0; j < list_increasing_kg_per_volume.get(i); j++) {
					switch(i) {
					case 0:
						System.out.println("Test---1");
						if (volume_left - laptop_volume/1_000_000 < 0) {
							break outerloop; 
						}
						volume_left = volume_left - laptop_volume/1_000_000;
						System.out.println(volume_left);
						weight_container_S = weight_container_S + laptop_weight;
						break;
					case 1:
						System.out.println("Test---2");
						if (volume_left - mouse_volume/1_000_000 < 0) {
							break outerloop; 
						}
						volume_left = volume_left - mouse_volume/1_000_000;
						System.out.println(volume_left);
						weight_container_S = weight_container_S + mouse_weight;
						break;
					case 2:
						System.out.println("Test---3");
						if (volume_left - desktop_volume/1_000_000 < 0) {
							break outerloop; 
						}
						volume_left = volume_left - desktop_volume/1_000_000;
						System.out.println(volume_left);
						weight_container_S = weight_container_S + desktop_weight;
						break;
					default:
						System.out.println("Test---4");	
						if (volume_left - LCD_screen_volume/1_000_000 < 0) {
							break outerloop; 
						}
						volume_left = volume_left - LCD_screen_volume/1_000_000;
						System.out.println(volume_left);
						weight_container_S = weight_container_S + LCD_screen_weight;
					}
				}
			}
			
			System.out.println("Weight of the small container: " + weight_container_S);
			if (weight_container_S <= 500) {
				x = 1000;
			}
		}
		
		// Total price = x * Price of ContainerL + y * Volume of ContainerS
		int final_price = 1800 * min[0] + x * min[1];
		
		// Print the results?? - How many of which container at which price?
		System.out.println("Lowest price: " + final_price + "$ - with " + min[0] + " Large containers and " + min[1] + " Small containers");
		

	}

}
