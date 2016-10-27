package abc;


import java.io.*;

class DecisionTreeApp {

 static DecisionTree newTree;

 public static void main(String[] args) throws IOException {

     // Create instance of class DecisionTree

     newTree = new DecisionTree();

     // Generate tree

     generateTree();

     // Output tree

     System.out.println("\nOUTPUT DECISION TREE");
     System.out.println("====================");
     newTree.outputBinTree();

     newTree.queryBinTree();
     }

 /* GENERATE TREE */

 static void generateTree() {
     System.out.println("\nGENERATE DECISION TREE");
     System.out.println("======================");
     newTree.createRoot("awa",(float) 2.5);
     newTree.addNoNode("awa","reject");
     newTree.addYesNode("awa","toefl",89);
     newTree.addNoNode("toefl","reject");
     newTree.addYesNode("toefl","gre",299);
     newTree.addNoNode("gre","reject");
     newTree.addYesNode("gre","agg",65);
     newTree.addNoNode("agg","reject");
     newTree.addYesNode("agg","admit",0);
     
     }

 
 }