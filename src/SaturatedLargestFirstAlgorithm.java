import java.util.Arrays;
import java.util.LinkedList;

public class SaturatedLargestFirstAlgorithm {
	private class Node1 implements Comparable<Node1> {

		public int index;
		public int[] neighbours;
		public int[] nodeColors;

		public Node1(int index, LinkedList<Integer> neighbours, int[] nodeColors) {
			this.index = index;
			int[] array = new int[neighbours.size()];
			for (int i = 0; i < neighbours.size(); i++) {
				array[i] = neighbours.get(i);
			}
			this.nodeColors = nodeColors;
			this.neighbours = array;
		}

		public int getDegree() {
			return this.neighbours.length;
		}

		public int getSaturationDegree() {
			int n = neighbours.length;
			int result = 0;

			boolean[] usedColor = new boolean[n];
			Arrays.fill(usedColor, false);

			for (int i = 0; i != n; ++i) {
				if (nodeColors[i] != -1) {
					usedColor[nodeColors[i]] = true;
				}
			}

			for (int i = 0; i != n; ++i) {
				if (usedColor[i])
					++result;
			}

			return result;
		}

		@Override
		public int compareTo(Node1 o) {
			int n = neighbours.length;

			return (this.getSaturationDegree() * n + this.getDegree())
					- (o.getSaturationDegree() * n + o.getDegree());
		}

		@Override
		public String toString() {
			return "[" + Integer.toString(index) + ","
					+ Integer.toString(getDegree()) + ","
					+ getSaturationDegree() + "]";
		}
	}

	public long execute(LinkedList<Integer>[] lists, int[] nodesColor, int k) {
		long startTime = System.nanoTime();
		try {
			int n = nodesColor.length;
			Node1[] notColoredNodes = new Node1[n];
			// init nodes with their neighbours
			for (int i = 0; i != n; ++i) {
				notColoredNodes[i] = new Node1(i, lists[i], nodesColor);
			}

			LinkedList<Node1>[] vertices = new LinkedList[n + 1];

			for (int i = 0; i != (n + 1); ++i) {
				vertices[i] = new LinkedList<Node1>();
			}

			for (int i = 0; i != n; ++i) { // sort by saturation degree
				int satdegree = notColoredNodes[i].getSaturationDegree();
				vertices[satdegree].add(notColoredNodes[i]);
			}

			Arrays.fill(nodesColor, -1);
			int colorsCount[] = new int[n + 1];
			int counter = n;
			int maxdegree = 0;
			int index = 0;
			int toRemove = 0;
			int i, size;

			while (counter > 0) {
				i = n;
				size = 0;
				do { // find greatest saturation degree in graph
					size = vertices[i].size();
					i--;
				} while (size == 0);

				i++;
				maxdegree = 0;
				index = 0;
				toRemove = 0;

				// pick node with greatest degree out of those with greatest
				// saturation
				for (int j = 0; j < vertices[i].size(); ++j) {
					if (vertices[i].get(j).getDegree() > maxdegree) {
						maxdegree = vertices[i].get(j).getDegree();
						index = vertices[i].get(j).index;
						toRemove = j;
					}
				}
				if (maxdegree == 0)
					index = vertices[i].get(0).index;
				//if (vertices[i].size() != 0)
					vertices[i].remove(toRemove); // remove coloured vertex
				counter--;
				// do the actual colouring
				boolean[] possiblecolors = new boolean[n];
				Arrays.fill(possiblecolors, 0, n - 1, true);
				for (int j : lists[index]) { // if there is connection
					// and some colour is assigned to neighbour
					if (nodesColor[j] != -1)
						possiblecolors[nodesColor[j]] = false;
				}
				for (int j = 0; j != n; ++j) {
					if (possiblecolors[j] && colorsCount[j] < k) {
						++colorsCount[j];
						nodesColor[index] = j;
						break;
					}
				}

				// update saturation degree of neighbours
				for (int u : lists[index]) {
					for (int l = 0; l != (n + 1); ++l) {
						if (vertices[l].contains(u)) {
							vertices[l].remove(vertices[l].indexOf(u));
							vertices[l + 1].add(new Node1(u, lists[u],
									nodesColor));
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println(elapsedTime);
		return elapsedTime;
	}
}
