import java.util.Arrays;
import java.util.LinkedList;

public class SmallestLastAlgorithm {
	/**
	 * private class Node1 implements Comparable<Node1>{ public int index = 0;
	 * public double[] neigbours;
	 * 
	 * public Node1(int index, double[] neigbours) { this.index = index;
	 * this.neigbours = Arrays.copyOf(neigbours, neigbours.length); }
	 * 
	 * private int getDegree(){ int result = 0; for(int i=0;
	 * i!=neigbours.length; ++i){ if(neigbours[i] > 0) ++result; } return
	 * result; }
	 * 
	 * public void removeNeigbour(Node1 neigbour){ neigbours[neigbour.index] =
	 * 0; }
	 * 
	 * @Override public int compareTo(Node1 o) { return this.getDegree() -
	 *           o.getDegree(); }
	 * @Override public String toString() { return "[" + Integer.toString(index)
	 *           + "," + Integer.toString(getDegree()) + "]"; } }
	 **/

	public long execute(LinkedList<Integer>[] lists, int[] nodesColor, int k) {
		long startTime = System.nanoTime();
		try {
			int n = nodesColor.length;
			int[] arrangedVerts = new int[n];
			int count = n - 1;
			int degree, v;
			LinkedList<Integer>[] vertices = new LinkedList[n + 1];

			for (int i = 0; i != (n + 1); ++i) {
				vertices[i] = new LinkedList<Integer>();
			}

			// bucket sort by degree
			for (int i = 0; i != n; ++i) {
				degree = lists[i].size();
				vertices[degree].add(i);
			}

			while (count != -1) {
				for (int i = 0; i != (n + 1); ++i) {
					while (vertices[i].size() != 0) {
						v = vertices[i].getFirst();
						arrangedVerts[count] = v; // add vertex to sorted output
						vertices[i].remove(0); // remove vertex from graph
						count--;
						// check nodes adjacent to v and decrement their degree
						for (int u : lists[v]) {
							for (int l = 0; l != (n + 1); ++l) {
								if (vertices[l].contains(u)) {
									vertices[l].remove(vertices[l].indexOf(u));
									vertices[l - 1].add(u);
								}
							}
						}
					}
				}
			}

			Arrays.fill(nodesColor, -1);
			int colorsCount[] = new int[n];

			// colorize nodes
			for (int i = 0; i != n; ++i) { // for every node starting with node
											// with highest degree

				boolean[] possiblecolors = new boolean[n];
				Arrays.fill(possiblecolors, 0, n - 1, true);
				int nodeIndex = arrangedVerts[i];

				for (int j : lists[nodeIndex]) { // if there is connection
					// and some colour is assigned to neighbour
					if (nodesColor[j] != -1)
						possiblecolors[nodesColor[j]] = false;
				}

				for (int j = 0; j != n; ++j) {
					if (possiblecolors[j] && colorsCount[j] < k) {
						++colorsCount[j];
						nodesColor[nodeIndex] = j;
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println(elapsedTime);
		return elapsedTime;
	}

	/*
	 * Smallest Last implementation using matrix representation of graph
	 */
	/**
	 * public void execute(double[][] ways, int[] nodesColor, int k){ int n =
	 * nodesColor.length; // number of nodes
	 * 
	 * 
	 * 
	 * Node1[] arrangedNodes = new Node1[n]; List<Node1> nodes = new
	 * ArrayList<Node1>(); // init nodes and count degrees for(int
	 * i=0;i!=n;++i){ nodes.add(new Node1(i, ways[i])); }
	 * 
	 * // arrange verticies in the order for(int i=0; i!=n; ++i){ Node1 node =
	 * Collections.min(nodes); nodes.remove(node); for (Node1 node1 : nodes) {
	 * node1.removeNeigbour(node); } arrangedNodes[n - i - 1] = node; }
	 * 
	 * 
	 * Arrays.fill(nodesColor, -1); int colorsCount[] = new int[n];
	 * 
	 * // colorize nodes for(int i=0; i!=n; ++i){ // for every node starting
	 * with node with highest degree
	 * 
	 * boolean[] possiblecolors = new boolean[n]; Arrays.fill(possiblecolors, 0,
	 * n-1, true); int nodeIndex = arrangedNodes[i].index;
	 * 
	 * for(int j=0; j!=n; ++j){ // check all neigbours
	 * 
	 * if(ways[nodeIndex][j] > 0 && nodesColor[j] != -1){ // if there is
	 * connection and some color is assigned to the neigbour
	 * possiblecolors[nodesColor[j]] = false; } }
	 * 
	 * for(int j=0; j!=n; ++j){
	 * 
	 * if(possiblecolors[j] && colorsCount[j] < k){ ++colorsCount[j];
	 * nodesColor[nodeIndex] = j; break; } } } }
	 **/
}
