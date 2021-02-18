package DiGraph_A5;

public class Edge {
	private Vertex vertexFrom;
	private Vertex vertexTo;
	private long id;
	private long weight;
	private String name;
	private Edge next;
	
	public Edge(long idNum, Vertex from, Vertex to, long weight, String edgeName)
	{
		id = idNum;
		vertexFrom = from;
		vertexTo = to;
		this.weight = weight;
		name = edgeName;
		next = null;
	}
	
	public long getId()
	{
		return id;
	}
	
	public long getWeight()
	{
		return weight;
	}
	
	public Vertex getFrom()
	{
		return vertexFrom;
	}
	
	public Vertex getTo()
	{
		return vertexTo;
	}
	
	public Edge getNext()
	{
		return next;
	}
	
	public void setNext(Edge e)
	{
		next = e;
	}
}
