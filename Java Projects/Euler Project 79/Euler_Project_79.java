import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import java.util.*;

// This program solves problem 79 on the Project Euler website. 
// I used Jung to create the graph used and had topological sort help solve the problem.

public class Euler_Project_79 {
	
	public static <V,E> List <V> topologicalSort (Graph <V, E> g) {
		
		// Nodes that have been removed from the graph
		List <V> output = new LinkedList <>();
		
		// Set of all nodes with no incoming edges
		Set <V> nodes = new HashSet <>();
		
		// While the graph has nodes in it 
		while (g.getVertexCount() > 0) {
			
			V target = null;
			for (V vertex : g.getVertices()) {
				if (g.inDegree(vertex) == 0) {					
					target = vertex;
				}
			}
			
			//add start to tail of output list
			output.add(target);
			if(target == null) {
				break;
			}
			// Remove the vertex so the out edges get removed as well
			g.removeVertex(target);
		
			 
			
		}
		
		return output;
		
	}

	public static void main(String[] args) {
		
		String fileName = "p079_keylog.txt";
		
		String edge = "";
		
		List <String> sorted = new LinkedList <>();
		
		// Integer is the vertex and string is the edge
		Graph <String, String> g = new DirectedSparseGraph<>();
		
		try {
			
			Scanner fileScanner = new Scanner (new File(fileName));
			
			while (fileScanner.hasNextLine()) {
				
				String key = fileScanner.nextLine();
				
				String x = key.charAt(0) +"";
				String y = key.charAt(1) +"";
				String z = key.charAt(2) +"";
				
				g.addEdge(x+y, x,y);
				g.addEdge(y+z, y,z);
			}
			
			fileScanner.close();
			
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
			
		}
		
		System.out.println(g);
		sorted = topologicalSort(g);
		
		System.out.println("The answer is: " + sorted);
		
	}

}