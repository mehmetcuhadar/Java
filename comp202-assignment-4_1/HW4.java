import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class HW4 {
	int [] parent;
	int [] rank;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Graph graph = new Graph();
				
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] parts = line.split(" ");
			if (parts[0].equals("end")) break;
			String src = parts[0];
			String dst = parts[1];
			int cost = Integer.parseInt(parts[2]);
			int latency = Integer.parseInt(parts[3]);
			
			graph.addVertex(src);
			graph.addVertex(dst);
			Edge edge = new Edge(src, dst, cost, latency);
			graph.addEdge(edge);
		}
		
		//System.out.println(Arrays.deepToString(graph.asArray(false)));
		//System.out.println(Arrays.deepToString(graph.asArray(true)));
		
		HW4 hw4 = new HW4();
		scan.close();
		
		System.out.println(hw4.totalLinkCost(graph));
		System.out.println(hw4.cheapestNetwork(graph.asArray(false)));
		System.out.println(hw4.savedAmount(graph));
	
	}
	
	// You can add any methods you need, both to this file and Graph.java file

	// The method for task 1 
	int totalLinkCost(Graph graph) {
		int sum=0;
		for(Edge e: graph.edges){
			sum+=e.cost;
		}
		return sum;
	}

	// The method for task 2 
	int cheapestNetwork(int[][] array_graph) {
		ArrayList <Edge> sortedEdge= new ArrayList<>();
        for(int i=0;i<array_graph.length;i++){
            for(int j=i+1;j<array_graph.length;j++){
                if(array_graph[i][j]!=0){
                    Edge edge= new Edge(""+i, ""+j,array_graph[i][j] , 0);
                    sortedEdge.add(edge);
                }
            }
        }
        Collections.sort(sortedEdge,(a,b) -> a.cost - b.cost);
		int sizeOfArray=array_graph.length;
		parent=new int[sizeOfArray];
		rank=new int[sizeOfArray];
		for(int i=0;i<sizeOfArray;i++){
			parent[i]=i;
			rank[i]=0;
		}
		int sum=0;
		int counter=0;
		while(counter<sizeOfArray-1){
			for(Edge edge: sortedEdge){
				int src=Integer.parseInt(edge.src);
				int dst=Integer.parseInt(edge.dst);
				if(find(src)!=find(dst)){
					union(src, dst);
					sum+=edge.cost;
					counter++;
				}
			}
		}

		return sum;
	}


	int find(int item){
		if(parent[item]==item){
			return item;
		}
		return find(parent[item]);
	}

	void union(int x,int y){
		if(rank[find(x)]>rank[find(y)]){
			parent[find(y)]=find(x);
			rank[x]+=rank[y];
		}else if(rank[find(y)]>rank[find(x)]){
			parent[find(x)]=find(y);
			rank[y]+=rank[x];
		}else{
			parent[find(x)]=find(y);
			rank[y]++;
		}
		
	}



	// The method for task 3 
	int savedAmount(Graph graph) {
		// TODO Auto-generated method stub
		return totalLinkCost(graph)-cheapestNetwork(graph.asArray(false));
	}
}
