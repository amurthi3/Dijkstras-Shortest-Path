package DiGraph_A5;
import java.util.*;

public class DijkstraNode {
	private Vertex node;
	private boolean known;
	private long distance;
	private List<String> path;
	private long priority;
	
	public DijkstraNode(Vertex v, long p)
	{
		node = v;
		known = false;
		distance = 0;
		path = new ArrayList<String>();
		priority = p;
	}
	
	public Vertex getNode()
	{
		return node;
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
}
