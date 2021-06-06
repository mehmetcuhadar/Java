package comp;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;

public class Cashier {
	boolean convenient=true;
	int service_time;
	double counter=0;
	double totalServiceTime;
	double avarage_service_time;
	
	public void setService_time(int max_service_time,int time) {
		if(counter!=0) {
			setAvarage_service_time(totalServiceTime/counter);
		}
		
		Random random = new Random();
		service_time = random.nextInt(max_service_time)+1+time;
		totalServiceTime+=(service_time-time);
		counter++;
		
		
		
	}
	
	
	public double getTotalServiceTime() {
		return totalServiceTime;
	}


	public int getService_time() {
		return service_time;
	}


	public boolean isConvenient() {
		return convenient;
	}


	public void setConvenient(boolean convenient) {
		this.convenient = convenient;
	}


	public double getAvarage_service_time() {
		return avarage_service_time;
	}

	public void setAvarage_service_time(double avarage_service_time) {
		BigDecimal bd = new BigDecimal(avarage_service_time);
		bd = bd.round(new MathContext(3));
		double rounded = bd.doubleValue();
		this.avarage_service_time = rounded;
	}


	public double getCounter() {
		return counter;
	}

	
	
	
	
	
}
