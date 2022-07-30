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
		
		System.out.println("Order volume: " + order_volume);
		
		// Compute total weight of the shipment
		double laptop_weight = 6.5;
		double mouse_weight = 0.2;
		double desktop_weight = 20;
		double LCD_screen_weight = 2.6;		
		
		double order_weight = order.get(0) * laptop_weight + order.get(1) * mouse_weight + 
				  			  order.get(2) * desktop_weight + order.get(3) * LCD_screen_weight;
		
		System.out.println("Order weight : " + order_weight);
		
		// Compute total volume of available containers
		
		// Select the type of container according to shipment volume
		
		// Compare suitable and lower price shipping methods
		
		// Print the results?? - How many of which container at which price?
		
		

	}

}
