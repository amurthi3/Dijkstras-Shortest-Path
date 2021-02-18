package DiGraph_A5;
import java.util.*;

public class Vertex implements Comparable{
	private long id;
	private String name;
	private List<Edge> edgesIn;
	private List<Edge> edgesOut;
	private Vertex next;
	private boolean known;
	private long distance;
	private List<String> path;
	private long priority;
	
	public Vertex(long id, String name)
	{
		this.id = id;
		this.name = name;
		edgesIn = new ArrayList<Edge>();
		edgesOut = new ArrayList<Edge>();
		next = null;
		known = false;
		distance = 0;
		path = new ArrayList<String>();
		priority = 0;
	}
	
	public long getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addEdgeIn(Edge newEdge)
	{
		edgesIn.add(newEdge);
	}
	
	public void addEdgeOut(Edge newEdge)
	{
		edgesOut.add(newEdge);
	}
	
	public Edge getEdgeIn(int idx)
	{
		return edgesIn.get(idx);
	}
	
	public void setEdgeIn(int idx, Edge e)
	{
		edgesIn.set(idx, e);
	}
	
	public Edge getEdgeOut(int idx)
	{
		return edgesOut.get(idx);
	}
	
	public void setEdgeOut(int idx, Edge e)
	{
		edgesOut.set(idx, e);
	}
	
	public List<Edge> getEdgesIn()
	{
		return edgesIn;
	}
	
	public List<Edge> getEdgesOut()
	{
		return edgesOut;
	}
	
	public Vertex getNext()
	{
		return next;
	}
	
	public void setNext(Vertex v)
	{
		next = v;
	}
	
	public boolean getKnown()
	{
		return known;
	}
	
	public void setKnown(boolean b)
	{
		known = b;
	}
	
	public long getDistance()
	{
		return distance;
	}
	
	public void setDistance(long dv)
	{
		distance = dv;
	}
	
	public List<String> getPath()
	{
		return path;
	}
	
	public void setPath(List<String> newPath)
	{
		path = newPath;
	}
	
	public void extendPath(String s)
	{
		path.add(s);
	}
	
	public long getPriority()
	{
		return priority;
	}
	
	public void setPriority(long p)
	{
		priority = p;
	}
	
	public int compareTo(Object other)
	{
		return (int)(this.priority - ((Vertex)other).getPriority());
	}
}
