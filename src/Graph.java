import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

/**
 * 
 * @author Anton
 */
public class Graph {
	private int n;
	// private double[][] ways;
	private Node[] nodes;
	private Node curent;
	private LinkedList<Integer>[] lists;

	/*
	 * Graph representation using a matrix
	 */
	/**
	 * public Graph(int n,int k,double[][] ways,Point center,int radius) {
	 * this.n = n; this.ways = ways; nodes = new Node[n];
	 * 
	 * double angle = 2*Math.PI/n; double r = radius - 60;
	 * 
	 * for(int i=0;i!=n;++i){ int x = (int) (center.x + r*Math.cos(i*angle));
	 * int y = (int) (center.y + r*Math.sin(i*angle));
	 * 
	 * int degree = 0; for(int j=0;j!=n;++j){ if(ways[i][j] > 0) ++degree; }
	 * 
	 * nodes[i] = new Node(Integer.toString(i) + "," +
	 * Integer.toString(degree),x,y); } }
	 **/

	public Graph(int n, LinkedList<Integer>[] lists, Point center, int radius) {
		this.n = n;
		this.lists = lists;
		nodes = new Node[n];

		double angle = 2 * Math.PI / n;
		double r = radius - 60;

		for (int i = 0; i != n; ++i) {

			int x = (int) (center.x + r * Math.cos(i * angle));
			int y = (int) (center.y + r * Math.sin(i * angle));

			nodes[i] = new Node(Integer.toString(i) + ","
					+ Integer.toString(lists[i].size()), x, y);
		}
	}

	public void paint(Graphics g) {
		/*
		 * Painting when using matrix representation commented out
		 */
		/**
		 * for(int i=0;i!=n;++i){ for(int j=i+1;j<n;++j){ if(ways[i][j]!=0){
		 * Point begin = new Point(nodes[i].x, nodes[i].y); Point end = new
		 * Point(nodes[j].x, nodes[j].y); //Point center = new
		 * Point((begin.x+end.x)/2, (begin.y+end.y)/2); g.drawLine(begin.x,
		 * begin.y, end.x, end.y); //g.drawString(Double.toString(ways[i][j]),
		 * center.x, center.y); } } }
		 **/
		for (int i = 0; i != n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				if (lists[i].contains(j)) {
					Point begin = new Point(nodes[i].x, nodes[i].y);
					Point end = new Point(nodes[j].x, nodes[j].y);
					// Point center = new Point((begin.x+end.x)/2,
					// (begin.y+end.y)/2);
					g.drawLine(begin.x, begin.y, end.x, end.y);
					// g.drawString(Double.toString(ways[i][j]), center.x,
					// center.y);
				}
			}
		}
		for (int i = 0; i != n; ++i) {
			nodes[i].paint(g);
		}
	}

	//void changeColor(int st, int k) {
	//	nodes[st].changeColor(k);
	//}

	public void colorize(int[] colors) {
		for (int i = 0; i != colors.length; ++i) {
			nodes[i].changeColor(colors[i], colors.length);
		}
	}

	void setDragged(int x, int y) {
		for (int i = 0; i != n; ++i) {
			if (nodes[i].isBelong(x, y)) {
				curent = nodes[i];
				return;
			}
		}
		curent = null;
	}

	void setNewCoord(int x, int y) {
		if (curent != null) {
			curent.x = x;
			curent.y = y;
		}
	}

}
