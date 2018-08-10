import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Anton
 */
public class Node {
    public Color color = new Color(0, 0, 0);
    public int x;
    public int y;
    public int r = 30;
    public String text;
    public int zone = 0;

    public Node(String text,int x,int y) {
        this.x = x;
        this.y = y;
        this.text = text;
    }
    
    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(new Color(238, 238, 238));
        g.fillOval(x-r/2,y-r/2,r,r);
        g.setColor(color);
        g.drawOval(x-r/2,y-r/2,r,r);
        g.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        g.drawString(text, x-r/5, y+r/3);
        g.setColor(c);
    }
    public  boolean isBelong(int x,int y){
        return Math.sqrt((this.x-x)*(this.x-x)+(this.y-y)*(this.y-y))<r;
    }
    public void changeColor(int k, int n){
        zone = k;
        switch(k){
        	case -1:
        		color = new Color(127, 127, 127);
        		break;
            case 0:
                color = new Color(255, 0, 0);
                break;
            case 1:
                color = new Color(0, 255, 0);
                break;
            case 2:
                color = new Color(0, 0, 255);
                break;
            case 3:
            	color = new Color(255, 255, 0);
            	break;
            case 4:
            	color = new Color(255, 0, 255);
            	break;
            case 5:
            	color = new Color(0, 255, 255);
            	break;
            default:
            	color = new Color(Math.abs((k*37) % 255), 0, 0);
            	break;
        }
    }
}

