import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3555632227273453620L;
	private Graph graph = null;
	
	public MainPanel() {
//////////////////////////////////////////////////////
        addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if(graph!=null){
                    int x = e.getX();
                    int y = e.getY();
                    graph.setDragged(x, y); 
                }
            }

            public void mouseReleased(MouseEvent e) {
                
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        ///////////////////////////////////////////////////
        addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
                if(graph!=null){
                    int x = e.getX();
                    int y = e.getY();
                    graph.setNewCoord(x, y);
                    repaint();
                }
            }

            public void mouseMoved(MouseEvent e) {
            }
        });
      
	}
	
	public void setGraph(Graph gr){
		graph = gr;	
	}
	
	public Graph getGraph() {
		return graph;		
	}
	
	public void colorGraph(int[] colors) {
		graph.colorize(colors);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);        
        if(graph!=null){
            graph.paint(g);
            
            g.drawString("0", 0, 0);
        }
	}
}