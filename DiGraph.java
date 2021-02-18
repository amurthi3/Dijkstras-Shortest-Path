package DiGraph_A5;
import java.util.*;

import javax.xml.soap.Node;

public class DiGraph implements DiGraphInterface {

	// in here go all your data and methods for the graph
	private Map<String, Vertex> nodes;
	private Map<Long, Edge> edges;
	private long numNodes;
	private long numEdges;
	private Map<Long, Vertex> nodeIds;
	private List<Vertex> nodesRecord;

	public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
		nodes = new HashMap<String, Vertex>();
		edges = new HashMap<Long, Edge>();
		numNodes = 0;
		numEdges = 0;
		nodeIds = new HashMap<Long, Vertex>();
		nodesRecord = new ArrayList<Vertex>();
	}
  
	// rest of your code to implement the various operations
  	public boolean addNode(long idNum, String label)
  	{
  		if (nodeIds.containsKey(idNum) || nodes.containsKey(label))
  		{
  			return false;
  		}	
  		Vertex newNode = new Vertex(idNum, label);
  		nodes.put(label, newNode);
  		nodeIds.put(idNum, newNode);
  		nodesRecord.add(newNode);
  		numNodes++;
  		return true;
  	}
  	
  	public boolean addEdge(long idNum, String sLabel, String dLabel,
  			long weight, String eLabel)
  	{
  		if (idNum < 0 || edges.containsKey(idNum) || !nodes.containsKey(sLabel) || !nodes.containsKey(dLabel))
  		{
  			return false;
  		}
  		Vertex srcNode = nodes.get(sLabel);
  		Vertex dstNode = nodes.get(dLabel);
  		boolean dstValid = false;
  		while (dstNode != null)
  		{
  			if (dstNode.getName().equals(dLabel))
  			{
  				dstValid = true;
  				break;
  			}
  			dstNode = dstNode.getNext();
  		}
  		if (dstValid == false)
  		{
  			return false;
  		}
  		for (int i = 0; i < srcNode.getEdgesOut().size(); i++)
  		{
  			if (srcNode.getEdgeOut(i).getTo().getId() == dstNode.getId())
  			{
  				return false;
  			}
  		}
  		Edge newEdge = new Edge(idNum, srcNode, dstNode, weight, eLabel);
  		edges.put(idNum, newEdge);
  		srcNode.addEdgeOut(newEdge);
  		dstNode.addEdgeIn(newEdge);
  		numEdges++;
  		return true;
  	}
  	
  	public boolean delNode(String label)
  	{
  		if (!nodes.containsKey(label))
  		{
  			return false;
  		}
  		Vertex current = nodes.get(label);
		Vertex connected;
		for (Edge edgeIn : current.getEdgesIn())
		{
			connected = edgeIn.getFrom();
			delEdge(connected.getName(), current.getName());
		}
		for (Edge edgeOut : current.getEdgesOut())
		{
			connected = edgeOut.getTo();
			delEdge(current.getName(), connected.getName());
		}
		nodes.remove(current.getName());
		nodeIds.remove(current.getId());
		nodesRecord.remove(current);
		numNodes--;
		return true;
  	}
  	
  	public boolean delEdge(String sLabel, String dLabel)
  	{
  		if (!(nodes.containsKey(sLabel) && nodes.containsKey(dLabel)))
  		{
  			return false;
  		}
  		Vertex srcNode = nodes.get(sLabel);
  		Vertex dstNode = nodes.get(dLabel);
  		for (int i = 0; i < srcNode.getEdgesOut().size(); i++)
  		{
  			if (srcNode.getEdgeOut(i).getTo().equals(dstNode))
  			{
  				for (int j = 0; j < dstNode.getEdgesIn().size(); j++)
  		  		{
  		  			if (dstNode.getEdgeIn(j).getFrom().equals(srcNode))
  		  			{
  		  				if (srcNode.getEdgeOut(i).getId() != dstNode.getEdgeIn(j).getId())
  		  				{
  		  					return false;
  		  				}
  		  				edges.remove(srcNode.getEdgeOut(i).getId());
  		  				dstNode.getEdgesIn().remove(j);
		  				srcNode.getEdgesOut().remove(i);
		  				numEdges--;
		  				return true;
  		  			}
  		  		}
  			}
  		}
  		return false;
  	}
  	
  	public long numNodes()
  	{
  		return numNodes;
  	}
  	
  	public long numEdges()
  	{
  		return numEdges;
  	}
  	
  	public ShortestPathInfo[] shortestPath(String label)
  	{
  		ShortestPathInfo[] pathsInfo = new ShortestPathInfo[(int)numNodes()];
  		if (!nodes.containsKey(label))
  		{
  			return null;
  		}
  		Vertex startNode = nodes.get(label);
  		Comparator<Vertex> calcPriority = new Comparator<Vertex>() {
  			public int compare(Vertex v1, Vertex v2)
  			{
  				return v1.compareTo(v2);
  			}
  		};
  		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(calcPriority);
  		pq.add(startNode);
  		int pathsIdx = 0;
  		while(!pq.isEmpty())
  		{
  			Vertex n = pq.remove();
  			long d = n.getDistance();
  			if (!n.getKnown())
  			{
  				n.setKnown(true);
  				pathsInfo[pathsIdx] = new ShortestPathInfo(n.getName(), n.getDistance());
  				pathsIdx++;
  				for (int i = 0; i < n.getEdgesOut().size(); i++)
  				{
  					Vertex a = n.getEdgeOut(i).getTo();
  					long newDistance = d + n.getEdgeOut(i).getWeight();
  					if (!a.getKnown() &&
  							(a.getPath().size() == 0 || a.getDistance() > newDistance))
  					{
						a.setDistance(newDistance);
						a.setPath(n.getPath());
						a.extendPath(n.getName());
						a.setPriority(newDistance);
						pq.add(a);
  					}
  				}
  			}
  		}
  		int numPaths = pathsIdx;
  		for (Vertex nodeRecord : nodesRecord)
  		{
  			boolean isAdded = false;
  			for (int i = 0; i < numPaths; i++)
  			{
  				if (pathsInfo[i].getDest().equals(nodeRecord.getName()))
  				{
  					isAdded = true;
  				}
  			}
  			if (!isAdded)
  			{
  				pathsInfo[pathsIdx] = new ShortestPathInfo(nodeRecord.getName(), -1);
  				pathsIdx++;
  			}
  		}
  		return pathsInfo;
  	}
}