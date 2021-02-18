package DiGraph_A5;

public class NodeId {
	private long idNum;
	private NodeId next;
	
	public NodeId(long id)
	{
		idNum = id;
		next = null;
	}
	
	public long getId()
	{
		return idNum;
	}
	
	public NodeId getNext()
	{
		return next;
	}
	
	public void setNext(NodeId n)
	{
		next = n;
	}
}
