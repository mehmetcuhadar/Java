
class HW2
{
 
	// Node structure containing power and coefficient of variable
	static class Node
	{
	 // Your code here
		double coeff;
		int power;
		Node next;
	};
	
	//Function To Display The Linked list
	static void printList( Node ptr)
	{
		if (ptr == null) 
		{
			System.out.println();
			return;
		}
		else if (ptr.next != null)
		{
			while (ptr.next != null)
			{
				System.out.print( ptr.coeff + "x^" + ptr.power + " + ");
				ptr = ptr.next;
			}
		}
		System.out.println( ptr.coeff + "x^" + ptr.power);
	}
  
	// Create a node and return
	static Node createNode(double coeff, int power)
	{
	// Your code here
		Node node = new Node();
		node.coeff=coeff;
		node.power=power;
		return node;
	}
  
  	// Function add a new node
	static Node addnode(Node head, double coeff, int power)
	{
	// Your code here
		Node newNode= createNode(coeff, power);
		if(head==null){
			head=newNode;
			return head;
		}
		newNode.next=null;
		Node current=head;
		while(current.next!=null){
			current=current.next;
		}
		current.next=newNode;
		return newNode;
	}
  
	static Node multiply(Node poly1, Node poly2)
	{
	// Your code here
		int largestPow=0;
		if(largestPower(poly1)>largestPower(poly2)){
			largestPow=largestPower(poly1);					//Finding the largest power
		}else{
			largestPow=largestPower(poly2);
		}
		double current_coeff;
		Node result= null;
		for(int i=largestPow;i>=0;i--){
			Node temp=null;
			for(int j=largestPow;j>=0;j--){					//go through the list
				current_coeff=powerFinder(poly1, i)*powerFinder(poly2, j);
				if(current_coeff!=0){
					if(temp==null){
						temp=createNode(current_coeff, i+j);
					}else{
						addnode(temp, current_coeff, i+j);	
					}
					
				}
			
			}			
			result=add(result, temp);			//adding the terms coming from multiplication
		}
		return result;
	}
  
	static Node add(Node poly1, Node poly2)
	{
	// Your code here
		int largestPow=0;
		if(largestPower(poly1)>largestPower(poly2)){
			largestPow=largestPower(poly1);				//Finding the largest power
		}else{
			largestPow=largestPower(poly2);
		}
		double current_coeff=powerFinder(poly1, largestPow)+powerFinder(poly2, largestPow);
		Node result= createNode(current_coeff, largestPow);
		for(int i=largestPow-1;i>=0;i--){
			current_coeff=powerFinder(poly1, i)+powerFinder(poly2, i);		//summing the terms
			if(current_coeff!=0){
				addnode(result, current_coeff, i);	
			}		
		}
		return result;

	}

	public static int largestPower(Node pol_head){		//finds the largest power in the polynomial
		if(pol_head==null){
			return 0;				
		}
		int max=0;
		Node current=pol_head;
		while(current!=null){
			
			if(current.power>max){
				max=current.power;
			}
			current=current.next;
		}

		return max;
	}

	public static double powerFinder(Node pol_head,int value){	//finds the coefficient of the given power in the polynomial
		if(pol_head==null){
			return 0;
		}
		double coff=0;
		Node current=pol_head;
		while(current!=null){
			if(current.power==value){
				coff=current.coeff;
			}
			current=current.next;
		}

		return coff;
	}
	
  
}
