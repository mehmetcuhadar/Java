package comp;

import java.util.Random;

public class Customer {
	private static int counter=0;
	private int ID;
	int arrivalTime;
	Random x=new Random();
	
	public Customer(int maxArrivalTime,int time) {
		
		counter++;
		ID=counter;
		arrivalTime=time+x.nextInt(maxArrivalTime)+1;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}


	public int getID() {
		return ID;
	}
	
	
}
