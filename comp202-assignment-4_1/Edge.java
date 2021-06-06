
public class Edge {
	public String src;
	public String dst;
	public int cost;
	public int latency;
	
	public Edge(String src, String dst, int cost, int latency) {
		this.src = src;
		this.dst = dst;
		this.cost = cost;
		this.latency = latency;
	}
}
