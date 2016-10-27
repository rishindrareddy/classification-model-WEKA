package weka.api;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.trees.J48;
import weka.core.FastVector;
import weka.core.Instances;
//import weka.core.Instances.numAttributes;
import weka.core.converters.ArffLoader.ArffReader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.matrix.Matrix;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;
 

public class classification{
	
	public static void main(String args[])throws Exception{
		
		try{
		
		//loading traing dataset
		DataSource source = new DataSource("C:\\Users\\rishi2804\\workspace\\Final_year\\src\\Training_data.arff");
		 Instances data = source.getDataSet();
		data.setClassIndex(data.numAttributes() - 1);
		//System.out.println("\n\nTRAINING DATA:");
		//System.out.println(data);
		
		
	//tree declaration, j48 refers to decision tree in weka api
		String[] options=new String[1];
		options[0]="-u";
		J48 tree=new J48();
		tree.setOptions(options);
		
	//building the tree based on training data. MACHINE LEARNING
		tree.buildClassifier(data);
		System.out.println("\n\n");
		//System.out.println(tree.getCapabilities().toString());
		System.out.println(tree.graph());
		
		
	//loading testing dataset		    
		DataSource source1 = new DataSource("C:\\Users\\rishi2804\\workspace\\Final_year\\src\\Testing_data.arff");
		 Instances data1 = source1.getDataSet();
	      data1.setClassIndex(data1.numAttributes() - 1);
	      Instances labeled = new Instances(data1);
	     // System.out.println("\n\nTESTING DATA:");
		//	System.out.println(data1);
		
		
		
	//classifying testing dataset instances
		for (int i = 0; i < data1.numInstances(); i++) {
			   double clsLabel = tree.classifyInstance(data1.instance(i));
			   labeled.instance(i).setClassValue(clsLabel);
			 }
			
		// saving labeled testing data into new arff file
			 BufferedWriter writer = new BufferedWriter(
			                           new FileWriter("C:\\Users\\rishi2804\\workspace\\Final_year\\src\\Labeled_data.arff"));
			 writer.write(labeled.toString());
			 writer.newLine();
			 writer.flush();
			 writer.close();
			 
			 
			 Evaluation eTest = new Evaluation(data);
			Random rand=new Random(1);
			
			DataSource source2 = new DataSource("C:\\Users\\rishi2804\\workspace\\Final_year\\src\\Jlabeled_data.arff");
			 Instances data2= source2.getDataSet();
		      data2.setClassIndex(data2.numAttributes() - 1);
		      Instances Jlabeled = new Instances(data2);
		    
			 eTest.crossValidateModel(tree,Jlabeled,10,rand, new Object[] { });
			 
			// System.out.println("\n\nLABELED TRAINING DATA:");
			//	System.out.println(labeled);
			 
			 
			 //displaying tree
		   	final javax.swing.JFrame jf =new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
		 		     jf.setSize(500,400);		 		     
		 		     jf.getContentPane().setLayout(new BorderLayout());
		 		     TreeVisualizer tv = new TreeVisualizer(null,
		 		         tree.graph(),
		 		         new PlaceNode2());
		 		     jf.getContentPane().add(tv, BorderLayout.CENTER);
		 		     jf.addWindowListener(new java.awt.event.WindowAdapter() {
		 		       public void windowClosing(java.awt.event.WindowEvent e) {
		 		         jf.dispose();
		 		       }
		 		     });
		 		 
		 		     jf.setVisible(true);
		 		     tv.fitToScreen();
		 		     
		 	//stats
		 		     
			 System.out.println(eTest.toSummaryString("\nResults\n======\n", false));
			 System.out.println(eTest.toClassDetailsString("=== Detailed Accuracy By Class ===\n"));
			 System.out.println(eTest.toMatrixString("=== Confusion Matrix ===\n"));
			 System.out.println("False Positive Rate  			:"+eTest.falsePositiveRate(0));
			 System.out.println("False Negative Rate(miss rate) 		:"+eTest.falseNegativeRate(0));
			 System.out.println("True Negative Rate 			:"+eTest.trueNegativeRate(0));
			 System.out.println("True Positive Rate(hit rate or recall)	:"+eTest.truePositiveRate(0));
			 System.out.println("F Score 				:"+eTest.fMeasure(0));
			
			
			 
			 
	}catch(Exception e)
    {
        e.printStackTrace();
    }  
	}
}