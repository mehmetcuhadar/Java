import java.util.Scanner;

public class HW2_test {
	  
public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	
	double coeff;
	int power;

	HW2.Node poly1 = null;
	HW2.Node poly2 = null;
	HW2.Node current = null;

	// reads integer tokens from the console and constructs a LinkedList from them
	while (scan.hasNextDouble()) {
		coeff = scan.nextDouble();
		power = scan.nextInt();
		if (poly1 == null) {
			poly1 = HW2.createNode(coeff, power);
			current = poly1;
		} 
		else {
			current.next = HW2.createNode(coeff, power);;
			current = current.next;
		}
	}
	scan.next(); // gets rid of the "end" token in the end of each list in the test cases
    
	current = null;
	while (scan.hasNextDouble()) 
	{
		coeff = scan.nextDouble();
		power = scan.nextInt();
		if (poly2 == null)
		{
			poly2 = HW2.createNode(coeff, power);
			current = poly2;
		} 
		else 
		{
			current.next = HW2.createNode(coeff, power);;
			current = current.next;
		}
	}
	scan.next(); // gets rid of the "end" token in the end of each list in the test cases

	HW2.printList(HW2.add(poly1, poly2));
	HW2.printList(HW2.multiply(poly1, poly2));

	scan.close();
	}
}
