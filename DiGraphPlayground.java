package DiGraph_A5;

public class DiGraphPlayground {

  public static void main (String[] args) {
  
      // thorough testing is your responsibility
      //
      // you may wish to create methods like 
      //    -- print
      //    -- sort
      //    -- random fill
      //    -- etc.
      // in order to convince yourself your code is producing
      // the correct behavior
      exTest();
      //efficiencyTest();
	  //spTest0();
	  //spTest3();
    }
  
    public static void exTest(){
      DiGraph d = new DiGraph();
      d.addNode(1, "f");
      d.addNode(3, "s");
      d.addNode(7, "t");
      d.addNode(0, "fo");
      d.addNode(4, "fi");
      d.addNode(6, "si");
      d.addEdge(0, "f", "s", 0, null);
      d.addEdge(1, "f", "si", 0, null);
      d.addEdge(2, "s", "t", 0, null);
      d.addEdge(3, "fo", "fi", 0, null);
      d.addEdge(4, "fi", "si", 0, null);
      System.out.println("numEdges: "+d.numEdges());
      System.out.println("numNodes: "+d.numNodes());
    }
    
    public static void efficiencyTest()
    {
    	DiGraph d = new DiGraph();
    	long size = 1000000;
    	for (long i = 0; i < size; i++)
    	{
    		d.addNode(i, ((Long)i).toString());
    	}
    	long edgeNum = 0;
    	for (long i = 0; i < size; i++)
    	{
    		do 
    		{
    			d.addEdge(edgeNum, ((Long)i).toString(),
    					((Long)(long)(Math.random() * size)).toString(),
    					(long)(Math.random() * 100),
    					((Long) edgeNum).toString());
    		} while (Math.random() > 0.25);
    	}
    	d.shortestPath("0");
    	System.out.println("done");
    }
    
    public static void spTest0()
    {
    	DiGraph d = new DiGraph();
    	d.addNode(0, "a");
    	d.addNode(1, "b");
    	d.addNode(2, "c");
    	d.addEdge(0, "a", "b", 3, "a-b");
    	d.addEdge(1, "a", "c", 5, "a-c");
    	d.addEdge(2, "b", "c", 4, "b-c");
    	ShortestPathInfo[] paths = d.shortestPath("a");
    	System.out.println("Shortest Path Test 0");
    	for (int i = 0; i < paths.length; i++)
    	{
    		System.out.println("a->" + paths[i].getDest() + " == " + paths[i].getTotalWeight());
    	}
    }
    
    public static void spTest3()
    {
    	DiGraph d = new DiGraph();
    	d.addNode(0, "p");
    	d.addNode(1, "a");
    	d.addNode(2, "g");
    	d.addNode(3, "e");
    	d.addEdge(0, "p", "a", 10, "p-a");
    	d.addEdge(1, "p", "g", 4, "p-g");
    	d.addEdge(2, "p", "e", 8, "p-e");
    	d.addEdge(3, "a", "p", 9, "a-p");
    	d.addEdge(4, "a", "g", 12, "a-g");
    	d.addEdge(5, "a", "e", 100, "a-e");
    	d.addEdge(6, "g", "p", 2, "g-p");
    	d.addEdge(7, "g", "a", 15, "g-a");
    	d.addEdge(8, "g", "e", 1, "g-e");
    	d.addEdge(9, "e", "p", 6, "e-p");
    	d.addEdge(10, "e", "a", 3, "e-a");
    	ShortestPathInfo[] paths = d.shortestPath("p");
    	System.out.println("Shortest Path Test 0");
    	for (int i = 0; i < paths.length; i++)
    	{
    		System.out.println("p->" + paths[i].getDest() + " == " + paths[i].getTotalWeight());
    	}
    }
    
    public static void spTest4()
    {
    	
    }
}