package abc;


import java.io.*;

class DecisionTree {
	

 private class BinTree {
 	
	/* FIELDS */
	
	private String     nodeID;
	private Float    Value;
 	private BinTree yesBranch  = null;
 	private BinTree noBranch   = null;
	
	/* CONSTRUCTOR */
	
	public BinTree(String newNodeID) {
	    nodeID     = newNodeID;
	    
         }
	}

 BinTree rootNode = null;



 public DecisionTree() {
	}

 public void createRoot(String newNodeID,float value) {
	rootNode = new BinTree(newNodeID);	
	rootNode.Value=value;
	System.out.println("Created root node " +newNodeID);	
	}
			
 /* ADD YES NODE */

 public void addYesNode(String existingNodeID, String newNodeID,float value) {
	// If no root node do nothing
	
	if (rootNode == null) {
	    System.out.println("ERROR: No root node!");
	    return;
	    }
	
	// Search tree
	
	if (searchTreeAndAddYesNode(rootNode,existingNodeID,newNodeID,value)) {
	    System.out.println("Added node " + newNodeID +
	    		" onto \"yes\" branch of node " + existingNodeID);
	    
	    }
	else System.out.println("Node " + existingNodeID + " not found");
	}

 /* SEARCH TREE AND ADD YES NODE */

 private boolean searchTreeAndAddYesNode(BinTree currentNode,
 			String existingNodeID, String newNodeID,float value) {
 	if (currentNode.nodeID == existingNodeID) {
	    // Found node
	    if (currentNode.yesBranch == null) {
	    	currentNode.yesBranch = new BinTree(newNodeID);
	    	currentNode.yesBranch.Value=value;
	    }
	    else {
	        System.out.println("WARNING: Overwriting previous node " +
			"(id = " + currentNode.yesBranch.nodeID +
			") linked to yes branch of node " +
			existingNodeID);
		currentNode.yesBranch = new BinTree(newNodeID);
		currentNode.yesBranch.Value=value;
		}		
 	    return(true);
	    }
	else {
	    // Try yes branch if it exists
	    if (currentNode.yesBranch != null) { 	
	        if (searchTreeAndAddYesNode(currentNode.yesBranch,
		        	existingNodeID,newNodeID,value)) {    	
	            return(true);
		    }	
		else {
 	        // Try no branch if it exists
	    	    if (currentNode.noBranch != null) {
 	    		return(searchTreeAndAddYesNode(currentNode.noBranch,
				existingNodeID,newNodeID,value));
			}
		    else return(false);	// Not found here
		    }
 		}
	    return(false);		// Not found here
	    }
	} 	
 		
 /* ADD NO NODE */

 public void addNoNode(String existingNodeID, String newNodeID) {
	// If no root node do nothing
	
	if (rootNode == null) {
	    System.out.println("ERROR: No root node!");
	    return;
	    }
	
	// Search tree
	
	if (searchTreeAndAddNoNode(rootNode,existingNodeID,newNodeID)) {
	    System.out.println("Added node " + newNodeID +
	    		" onto \"no\" branch of node " + existingNodeID);
	    }
	else System.out.println("Node " + existingNodeID + " not found");
	}
	
 /* SEARCH TREE AND ADD NO NODE */

 private boolean searchTreeAndAddNoNode(BinTree currentNode,
 			String existingNodeID, String newNodeID) {
 	if (currentNode.nodeID == existingNodeID) {
	    // Found node
	    if (currentNode.noBranch == null) currentNode.noBranch = new BinTree(newNodeID);
	    else {
	        System.out.println("WARNING: Overwriting previous node " +
			"(id = " + currentNode.noBranch.nodeID +
			") linked to yes branch of node " +
			existingNodeID);
		currentNode.noBranch = new BinTree(newNodeID);
		}		
 	    return(true);
	    }
	else {
	    // Try yes branch if it exists
	    if (currentNode.yesBranch != null) { 	
	        if (searchTreeAndAddNoNode(currentNode.yesBranch,
		        	existingNodeID,newNodeID)) {    	
	            return(true);
		    }	
		else {
 	        // Try no branch if it exists
	    	    if (currentNode.noBranch != null) {
 	    		return(searchTreeAndAddNoNode(currentNode.noBranch,
				existingNodeID,newNodeID));
			}
		    else return(false);	// Not found here
		    }
		 }
	    else return(false);	// Not found here
	    }
	} 	

 

 public void queryBinTree() throws IOException {
     queryBinTree(rootNode);
     queryBinTreeSample(rootNode);
     }

 private void queryBinTreeSample(BinTree currentNode) throws IOException{
	 
	// TODO Auto-generated method stub
	 String[] path=new String[5];
	 path[0]=new String("C:\\Users\\rishi2804\\workspace\\Final_year\\src\\abc\\sample20.1.txt");
	 path[1]=new String("C:\\Users\\rishi2804\\workspace\\Final_year\\src\\abc\\sample20.2.txt");
	 path[2]=new String("C:\\Users\\rishi2804\\workspace\\Final_year\\src\\abc\\sample20.3.txt");
	 path[3]=new String("C:\\Users\\rishi2804\\workspace\\Final_year\\src\\abc\\sample30.1.txt");
	 path[4]=new String("C:\\Users\\rishi2804\\workspace\\Final_year\\src\\abc\\sample30.2.txt");
	 
	 for(int i=0;i<=4;i++)
	 {
	 
	
	 FileInputStream fstream = new FileInputStream(path[i]);
	 BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

	 String strLine;

	System.out.println(" OUTPUT "+(i+1));
	   System.out.println("===================");
	 //Read File Line By Line
	 while ((strLine = br.readLine()) != null)   {
		 
		 String[] ar=strLine.split(",");
		 
		 float aggScore=Float.parseFloat(ar[0]);
		 float awaScore=Float.parseFloat(ar[1]);
		 float greScore=Float.parseFloat(ar[2]);
		 float toeflScore=Float.parseFloat(ar[3]);
		 String classLabel=ar[4];
		 
		 
		classLabel= classify(aggScore,awaScore,greScore,toeflScore,classLabel);
		
		System.out.println(" "+ar[0]+" "+ar[1]+" "+ar[2]+" "+ar[3]+" "+classLabel);
		 
		 	 
	 }
	 br.close();
	 System.out.println("\n\n");
	 }

}

private void queryBinTree(BinTree currentNode) throws IOException {

	 BufferedWriter writer = new BufferedWriter(
             new FileWriter("C:\\Users\\rishi2804\\workspace\\Final_year\\src\\abc\\Jlabeled_data.txt"));
	 
	 
	 
	 FileInputStream fstream = new FileInputStream("C:\\Users\\rishi2804\\workspace\\Final_year\\src\\abc\\Testing_data.txt");
	 BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

	 String strLine;

	 //Read File Line By Line
	 while ((strLine = br.readLine()) != null)   {
		 
		 String[] ar=strLine.split(",");
		 
		 float aggScore=Float.parseFloat(ar[0]);
		 float awaScore=Float.parseFloat(ar[1]);
		 float greScore=Float.parseFloat(ar[2]);
		 float toeflScore=Float.parseFloat(ar[3]);
		 String classLabel=ar[4];
		 
		 //System.out.println(" "+ar[0]+" "+ar[1]+" "+ar[2]+" "+ar[3]+" "+ar[4]+" ");
		 
		classLabel= classify(aggScore,awaScore,greScore,toeflScore,classLabel);
		 
		 //System.out.println(" "+classLabel);
		 String str=aggScore+","+awaScore+","+greScore+","+toeflScore+","+classLabel;
		 writer.write(str);
		 writer.newLine();
		 
		 	 
	 }
writer.close();
	 //Close the input stream
	 br.close();
	 
	 System.out.println("\n\nclassified data Saved into Jlabeled_data.txt\n");
	 
     
 }


 public void outputBinTree() {

     outputBinTree("1",rootNode);
     }

 private void outputBinTree(String tag, BinTree currentNode) {

     // Check for empty node

     if (currentNode == null) return;

     // Output

     System.out.println("[" + tag + "] nodeID = " + currentNode.nodeID);
     		
     // Go down yes branch

     outputBinTree(tag + ".1",currentNode.yesBranch);

     // Go down no branch

     outputBinTree(tag + ".2",currentNode.noBranch);
	}      		
 
 public String classify(float aggScore, float awaScore, float greScore, float toeflScore,String classLabel) {
		
		// TODO Auto-generated constructor stub
		
		if(rootNode.Value<awaScore)
		{
			BinTree currentNode = rootNode.yesBranch;
			
			if(currentNode.Value<toeflScore)
			{
				 currentNode = currentNode.yesBranch;
				 
				 if(currentNode.Value<greScore)
				 {
					 currentNode = currentNode.yesBranch;
					 
					 if(currentNode.Value<aggScore){
						 currentNode = currentNode.yesBranch;
						 classLabel="admit";
					 }
					 else{
						 currentNode = currentNode.noBranch;
						 classLabel="reject";
						 
					 }
				 }
				 else
				 {
					 currentNode = currentNode.noBranch;
					 classLabel="reject";
				 }
			}
			else{
				currentNode = currentNode.noBranch;
				classLabel="reject";
			}
			
		}
		else{
			BinTree currentNode = rootNode.noBranch;
			classLabel="reject";
			
			
		}
		
		return classLabel;
	}

 }