package comp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SimulationFrame extends JFrame {
	int maxSimulationTime=1;
	int maxArrivalTime=1;
	int maxServiceTime=1;
	int ms=200;
	Timer timer=new Timer();
	int time=0;
	private final JLabel label1=new JLabel("Max Simulation Time");;
	private final JLabel label2=new JLabel("Max Arrival Time");
	private final JLabel label3=new JLabel("Max Serivce Time");
	private final JLabel label4=new JLabel("Timer Speed (Miliseconds)");
	private final JLabel label5=new JLabel("Current Simulation Time");
	private final JLabel label6=new JLabel("0");
	private final JLabel label7=new JLabel("Current Queue Length");
	private final JLabel label8=new JLabel("0");
	private final JLabel label9=new JLabel("Total Service Time");
	private final JLabel label10=new JLabel("0");
	private final JTextField jTextField1=new JTextField("");;
	private final JTextField jTextField2=new JTextField("");;
	private final JTextField jTextField3=new JTextField("");;
	private final JComboBox<Integer> jComboBox=new JComboBox<Integer>();;
	private final JButton jButton=new JButton("Start Simulation");;
	Cashier cashier=new Cashier();
	CustomerQueue customerQueue=new CustomerQueue();
	Customer customer=new Customer(maxArrivalTime,time);
	JFrame userInteractor = new JFrame("Comp-132 HW2");
	public SimulationFrame() {
		showUserInteractor();
	}

	public void showUserInteractor() {
		userInteractor.setVisible(true);
		userInteractor.setSize(500,500);
		userInteractor.setLayout(new GridLayout(5,2));
		userInteractor.add(label1);
		userInteractor.add(jTextField1);		
		userInteractor.add(label2);
		userInteractor.add(jTextField2);
		userInteractor.add(label3);
		userInteractor.add(jTextField3);
		userInteractor.add(label4);
		jComboBox.addItem(1);
		jComboBox.addItem(200);
		jComboBox.addItem(300);
		jComboBox.addItem(400);
		jComboBox.addItem(500);	
		jComboBox.addItem(1000);
		userInteractor.add(jComboBox);
		userInteractor.add(jButton);
		ChangeHandler listener=new ChangeHandler();
		jButton.addActionListener(listener);
		jTextField1.addActionListener(listener);
		jTextField2.addActionListener(listener);
		jTextField3.addActionListener(listener);
		jComboBox.addActionListener(listener);

	}
	public void hideUserInteractor() {
		userInteractor.setVisible(false);
	}

	JFrame newFrame = new JFrame("Comp-132 HW2");
	public void showCurrent() {
		newFrame.setSize(500,500);
		newFrame.setVisible(true);
		newFrame.setLayout(new GridLayout(3,2));
		newFrame.add(label5);
		newFrame.add(label6);
		newFrame.add(label7);
		newFrame.add(label8);
		newFrame.add(label9);
		newFrame.add(label10);

	}
	public void hideCurrent() {
		newFrame.setVisible(false);
	}

	@SuppressWarnings("static-access")
	public void showDialog() {
		JOptionPane t =new JOptionPane();
		t.showMessageDialog(null,"Number of customers : "+(cashier.getCounter()-1)+"\n"
				+ "Avarage Waiting Time : "+customerQueue.getAvarageWaitingTime()+"\n"
				+ "Avarage Service Time : "+cashier.getAvarage_service_time()+"\n"
				+ "Maximum Waiting Time : "+customerQueue.getLongestWaitTime()+"\n"
				+ "Maximum Queue Length : "+customerQueue.getMaxQueue(), "Final Staticts",t.PLAIN_MESSAGE);
		t.add(new JButton("OK"));

	}

	private class ChangeHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==jTextField1) {
				if(Integer.parseInt(jTextField1.getText())>1) {
					maxSimulationTime=Integer.parseInt(jTextField1.getText());
				}				
			}
			if(e.getSource()==jTextField2) {
				if(Integer.parseInt(jTextField2.getText())>1) {
					maxArrivalTime=Integer.parseInt(jTextField2.getText());
				}				
			}
			if(e.getSource()==jTextField3) {
				if(Integer.parseInt(jTextField3.getText())>1) {
					maxServiceTime=Integer.parseInt(jTextField3.getText());
				}				
			}
			if(e.getSource()==jButton) {
				hideUserInteractor();
				showCurrent();
				startSimulation();
			}
			if(e.getSource()==jComboBox) {
				ms=(int) jComboBox.getSelectedItem();
			}

		}




	}
	public void startSimulation() {
		TimerTask x= new TimerTask() {

			@Override
			public void run() {
				if(time==0) {
					customerQueue.enqueue(customer);
				}
				if(cashier.getService_time()==time) {
					cashier.setConvenient(true);
					cashier.setService_time(maxServiceTime,time);
				}
				if(customerQueue.getCustomerQueues().size()>0&&customerQueue.getCustomerQueues().getLast().getArrivalTime()==time&&time<maxSimulationTime) {
					customerQueue.enqueue(new Customer(maxArrivalTime,time));
				}
				if(customerQueue.getCustomerQueues().size()>0&&cashier.isConvenient()&&customerQueue.getCustomerQueues().getFirst().getArrivalTime()<=time) {
					customerQueue.dequeue(time);
					cashier.setConvenient(false);
				}

				time++;
				if (time >= maxSimulationTime&&customerQueue.getCustomerQueues().size()==0&&cashier.getService_time()==time) {
					timer.cancel();
					timer.purge();
					hideCurrent();
					showDialog();
					System.exit(0);
				}
				label6.setText(""+time);
				if(customerQueue.getCustomerQueues().size()>0) {
					label8.setText(""+(customerQueue.getCustomerQueues().size()-1));	
				}
				customerQueue.setMaxQueue((customerQueue.getCustomerQueues().size()-1));
				label10.setText(""+cashier.getTotalServiceTime());

			}
		};
		timer.schedule(x, 1000,ms);
	}





}
