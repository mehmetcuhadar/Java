package comp;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.LinkedList;

public class CustomerQueue {
	LinkedList<Customer>customerQueues=new LinkedList<Customer>();
	int maxQueue=0;
	int longestWaitTime;
	double avarageWaitingTime;

	public void enqueue(Customer x) {		
		customerQueues.add(x);

	}
	public void dequeue(int time) {
		setLongestWaitTime(time,customerQueues.getFirst().arrivalTime);
		setAvarageWaitingTime(time,customerQueues.getFirst().arrivalTime,customerQueues.getFirst().getID());
		customerQueues.remove(customerQueues.getFirst());

	}
	public LinkedList<Customer> getCustomerQueues() {
		return customerQueues;
	}
	public int getMaxQueue() {
		return maxQueue;
	}
	public void setMaxQueue(int current) {
		if(current>maxQueue) {
			maxQueue=current;
		}
	}
	public int getLongestWaitTime() {
		return longestWaitTime;
	}
	public void setLongestWaitTime(int time,int arrivalTime) {
		int customersWaitingTime = time-arrivalTime;
		if(customersWaitingTime>longestWaitTime) {
			longestWaitTime=customersWaitingTime;
		}
	}
	public double getAvarageWaitingTime() {
		return avarageWaitingTime;
	}
	public void setAvarageWaitingTime(int time, int arrivalTime, int id) {
		double x=(((double)id-1)*(getAvarageWaitingTime())+(double)(time-arrivalTime))/(double)id;
		BigDecimal bd = new BigDecimal(x);
		bd = bd.round(new MathContext(3));
		double rounded = bd.doubleValue();
		avarageWaitingTime = rounded;
	}

}
