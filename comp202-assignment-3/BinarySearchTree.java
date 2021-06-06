public class BinarySearchTree
{
    Node root;

    // Node structure containing the subtrees
	static class Node
	{
		// Your code here
		int item;
		Node left;
		Node right;
		Node(int data){
			this.item=data;
			right=null;
			left=null;
		}
	}

    // Constructor
    public BinarySearchTree()
    {
        // Your code here
        this.root=null;
    }

    // Insert new item into the binary tree
    public void insert(int data)
    {
        // Your code here
		root= recursiveInsertion(root, data);
    }

	public Node recursiveInsertion (Node top,int data){
		if(top==null){
			return new Node(data);
		}
		if(data<top.item){
			top.left=recursiveInsertion(top.left, data);
		}else if(data>top.item){
			top.right=recursiveInsertion(top.right, data);
		}else{
			return top;
		}
		return top;
	}

    // Check if the tree is balanced or not
    public boolean isBalanced()
    {
        // Your code here
		return checkNodeBalanced(root);        
    }

	public boolean checkNodeBalanced(Node current){
		if(current==null){
			return true;
		}
		int hOfLeft=heightOfNode(current.left);
		int hOfRight=heightOfNode(current.right);
		if(absoluteOf(hOfLeft,hOfRight)<=1&& checkNodeBalanced(current.left)&&checkNodeBalanced(current.right)){
			return true;
		}
		return false;
	}

	public int absoluteOf(int x,int y){
		if(x-y < 0){
			return y-x;
		}
		return x-y;
	}

	//Finds height of node
	public static int heightOfNode(Node current){
		if(current==null){
			return 0;
		}
		return 1+ maxOf(heightOfNode(current.left), heightOfNode(current.right));
	}
	
	public static int maxOf(int x, int y){
		if(x>y){
			return x;
		}
		return y;
	}
	// Remove an item from the tree
	public void remove(int item)
	{
		// Your code here
		root=removeNode(root,item);
	}

	public Node removeNode(Node current,int data){
		if(current==null){
			return null;
		}
		if(data==current.item){
			//if there is no child node
			if(current.left==null&&current.right==null){
				current=null;
			}else if(current.right==null){ //if it has only left node
				current=current.left;
			}else if(current.left==null){ //if it has only right node
				current=current.right;
			}else{	//if it has two child
				int item=findMostLeftNode(current.right);
				current.item=item;
				current.right=removeNode(current.right, item);
			}
			
		}else if(data<current.item){
			current.left=removeNode(current.left, data);
			return current;
		}else{
			current.right=removeNode(current.right, data);
		}
		return current;


	}

	public int findMostLeftNode(Node current){
		if(current.left!=null){
			return findMostLeftNode(current.left);
		}
		return current.item;
	}
	
	// Compare two trees. Return true if both trees are same
	public boolean compareTo(BinarySearchTree tree)
	{
		// Your code here
		return nodeComparator(root, tree.root);
	}
	public boolean nodeComparator(Node first,Node second){
		if(first==null&&second==null){
			return true;
		}else if(first==null){
			return false;
		}else if(second==null){
			return false;
		}
		boolean left;
		boolean right;
		if(first.item==second.item){
			left=nodeComparator(first.left, second.left);
			right=nodeComparator(first.right, second.right);
			if(left&&right){
				return true;
			}
		}
			return false;
		
		
	}
	
	// Given function to print the tree
	public void printInOrder(Node node)
	{
		if (node != null)
		{
			printInOrder(node.left);
			System.out.print(node.item + " ");
			printInOrder(node.right);
		}
    }
}
