import java.util.ArrayList;
import java.util.Arrays;
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
		
		System.out.println(hw4.totalTransitTime(graph));
		System.out.println(hw4.cheapestTransitTime(graph));
		System.out.println(hw4.timeIncrease(graph));
	
	}
	
	// You can add any methods you need, both to this file and Graph.java file

	// The method for task 1 
	int totalTransitTime(Graph graph) {
		// TODO Auto-generated method stub
		int[][] x=graph.asArray(true);
		int length=graph.asArray(true).length;
		long[][] distance=new long[length][length];
		for(int i=0;i<length;i++){
			for(int t=i+1;t<length;t++){
				distance[i][t]=distance[t][i]=x[i][t];
				if(x[i][t]==0){
					distance[i][t]=distance[t][i]=Integer.MAX_VALUE;
				}
				if(i==t){
					distance[i][t]=distance[t][i]=0;
				}
			}
		}
		
		for(int k=0;k<length;k++){
			for(int l=0;l<length;l++){
				for(int m=0;m<length;m++){
					if(distance[l][k]+distance[k][m]<distance[l][m]){
						distance[l][m]=distance[l][k]+distance[k][m];
					}
				}
			}
		}
		int sum=0;
		for(int a=0;a<length;a++){
			for(int b=a+1;b<length;b++){
				if(distance[a][b]>0){
					sum+=distance[a][b];
				}
			}
		}
		
		return 2*sum;
	}

	// The method for task 2 
	int cheapestTransitTime(Graph graph) {
		// TODO Auto-generated method stub

		return cheapestNetwork(graph);
	}
	int cheapestNetwork(Graph graph) {
		ArrayList <Edge> sortedEdge= new ArrayList<>();
		int [][] array_graph=graph.asArray(false);
		int [][] latency_graph=graph.asArray(true);
		Graph newGrph= new Graph();
        for(int i=0;i<array_graph.length;i++){
            for(int j=i+1;j<array_graph.length;j++){
                if(array_graph[i][j]!=0){
                    Edge edge= new Edge(""+i, ""+j,array_graph[i][j] , latency_graph[i][j]);
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
		int counter=0;
		while(counter<sizeOfArray-1){
			for(Edge edge: sortedEdge){
				int src=Integer.parseInt(edge.src);
				int dst=Integer.parseInt(edge.dst);
				
				if(find(src)!=find(dst)){
					newGrph.addVertex(""+src);
					newGrph.addVertex(""+dst);
					newGrph.addEdge(edge);
					union(src, dst);
					counter++;
				}
			}
		}
		return totalTransitTime(newGrph);
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
	int timeIncrease(Graph graph) {
		// TODO Auto-generated method stub
		return cheapestNetwork(graph)-totalTransitTime(graph);
	}
	
}
