import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 2521215918953549846L;
	private int k;
	private int n;
	//private double[][] ways;
	private LinkedList<Integer>[] lists;
	private int[] colors;
	private long executionTime;
	
	private MainPanel mainpanel = new MainPanel();
    
	private JFileChooser chooser = new JFileChooser();
	private JFileChooser chooser2 = new JFileChooser();
	private JFileChooser chooser3 = new JFileChooser();

    public MainFrame() {
    	Container c = getContentPane();
    	c.add(mainpanel);
        ////////////////////////////////////////////////////////////////////////////////        
        JMenuBar menu = new JMenuBar();        
        JMenu filemenu = new JMenu("File");
        JMenuItem bopen = new JMenuItem("Open");
        JMenuItem bsave = new JMenuItem("Save as image");
        JMenuItem bsavetxt = new JMenuItem("Save as text");
        filemenu.add(bopen);
        filemenu.add(bsave);
        filemenu.add(bsavetxt);
        menu.add(filemenu);        
        JMenu optionmenu = new JMenu("Option");
        JMenuItem bLF = new JMenuItem("Largest First");
        JMenuItem bSL = new JMenuItem("Smallest Last");
        JMenuItem bSLF = new JMenuItem("Saturated Largest First");
        chooser.setCurrentDirectory(new File("."));
        chooser.setSelectedFile(new File("unnamed.txt"));
        chooser2.setCurrentDirectory(new File("."));
        chooser2.setSelectedFile(new File("unnamed.jpg"));
        chooser3.setCurrentDirectory(new File("."));
        chooser3.setSelectedFile(new File("unnamed.txt"));
        
        optionmenu.add(bLF);
        optionmenu.add(bSL);
        optionmenu.add(bSLF);
        menu.add(optionmenu);        
        setJMenuBar(menu);
        //////////////////////////////////////////////////////////////////////////////////
        bopen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                chooser.setFileFilter(new FileFilter()
                {
                        public boolean accept(File f)
                        {
                            return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
                        }
                        public String getDescription()
                        {
                            return "TEXT files";
                        }

                });
                int returnVal = chooser.showOpenDialog(MainFrame.this);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    String filename = chooser.getSelectedFile().getPath();
                    try {
                        Scanner inn = new Scanner(new FileInputStream(filename));
                        k = inn.nextInt();
                        n = inn.nextInt();
                        int length;
                        String temp;
                        
                        lists = new LinkedList[n];
                        for (int i=0; i!=n; ++i) {
                        lists[i] = new LinkedList<Integer>();
                        temp = inn.next();
                        length = Integer.parseInt(temp);
                        for (int j=0; j!=length; ++j) {
                        	temp = inn.next();
                        	lists[i].add(Integer.parseInt(temp));
                        }
                        
                        }
                        //ways = new double[n][];
                        //for(int i=0;i!=n;++i){
                        //    ways[i] = new double[n];
                        //    for(int j=0;j!=n;++j){
                        //        String temp = inn.next();
                        //        ways[i][j] = Double.parseDouble(temp);
                        //  }
                        //}
                        //mainpanel.setGraph(new Graph(n, k, ways,new Point(mainpanel.getWidth()/2, mainpanel.getHeight()/2),Math.min(mainpanel.getWidth()/2, mainpanel.getHeight()/2)));
                        mainpanel.setGraph(new Graph(n, lists, new Point(mainpanel.getWidth()/2, mainpanel.getHeight()/2),Math.min(mainpanel.getWidth()/2, mainpanel.getHeight()/2)));
                        inn.close();                      
                    } catch (Exception ex) {
                        System.out.println(e.toString());
                    }
                }
                repaint();
            }
        });
        bsave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooser2.setFileFilter(new FileFilter()
                {
                        public boolean accept(File f)
                        {
                            return f.isDirectory() || f.getName().toLowerCase().endsWith(".jpg");
                        }
                        public String getDescription()
                        {
                            return "JPEG files";
                        }

                });
                int returnVal = chooser2.showOpenDialog(MainFrame.this);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    String filename = chooser2.getSelectedFile().getPath();
                    if(!filename.endsWith(".jpg")){
                    	filename += ".jpg";
                    }
                    Image capture = mainpanel.createImage(mainpanel.getWidth(),mainpanel.getHeight());
                    File file = new File(filename);
                    //FileOutputStream out = null;
                    //try{
                    //	out = new FileOutputStream(file);
                    //}catch (Exception ex) {
                    //	System.out.println(ex.toString());
                    //}
                
                    BufferedImage image = null;
         
                    Graphics captureG = capture.getGraphics();
                    mainpanel.paint(captureG);
         
                    image = (BufferedImage) capture;
                    if (image != null)
                    {
                    	try {
                    		ImageIO.write(image, "jpg", file);
						} catch (Exception ex) {
							System.out.println(ex.toString());
						}
                    	//JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                    	//JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
         
                    	//param.setQuality(1.0f, true);
                    	//try {
                    	//	encoder.encode(image, param);
                    	//} catch (Exception ex) {
                    	//	System.out.println(ex.toString());
                    	//}
                    	//try {
                    	//	out.flush();
                    	//} catch (Exception ex) {
                    	//	System.out.println(ex.toString());
                    	//}
                    	//try {
                    	//	out.close();
                    	//} catch (Exception ex) {
                    	//	System.out.println(ex.toString());
                    	//}
                    }
                }
            }
        });
        bsavetxt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooser3.setFileFilter(new FileFilter()
                {
                        public boolean accept(File f)
                        {
                            return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
                        }
                        public String getDescription()
                        {
                            return "TXT files";
                        }

                });
                int returnVal = chooser3.showOpenDialog(MainFrame.this);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    String filename = chooser3.getSelectedFile().getPath();
                    if(!filename.endsWith(".txt")){
                    	filename += ".txt";
                    }
                    try {
                        	if (colors != null) {
                        	      PrintStream out = new PrintStream(new FileOutputStream(
                        	          filename));
                        	      out.println(executionTime);
                        	      out.println("\n");
                        	      for (int i = 0; i < colors.length; ++i)
                        	        out.println(i + " " + colors[i]);
                        	      out.close();
                        }
                      } catch (IOException ioe) {
                        ioe.printStackTrace();
                      }
                }
            }
        });
        //////////////////////////////////////////////////////
        bLF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (lists != null) {
				colors = new int[n];
				LargestFirstAlgorithm lfa = new LargestFirstAlgorithm();
				//lfa.execute(ways, colors, k);
				executionTime = lfa.execute(lists, colors, k);
				mainpanel.colorGraph(colors);
				repaint();
				}
			}
		});
        //////////////////////////////////////////////////////
        bSL.addActionListener(new ActionListener() {

        	@Override
        	public void actionPerformed(ActionEvent arg0) {
        		if (lists != null) {
        		colors = new int[n];
        		SmallestLastAlgorithm sla = new SmallestLastAlgorithm();
        		//sla.execute(ways, colors, k);
        		executionTime = sla.execute(lists, colors, k);
        		mainpanel.colorGraph(colors);
        		repaint();
        		}
        	}
        });
        //////////////////////////////////////////////////////
        bSLF.addActionListener(new ActionListener() {

        	@Override
        	public void actionPerformed(ActionEvent arg0) {
        		if (lists != null) {
        		colors = new int[n];
        		SaturatedLargestFirstAlgorithm slfa = new SaturatedLargestFirstAlgorithm();
        		//slfa.execute(ways, colors, k);
        		executionTime = slfa.execute(lists, colors, k);
        		mainpanel.colorGraph(colors);
        		repaint();
        		}
        	}
        });
    }

    @Override
    public void paint(Graphics g) {
    	super.paint(g);
        mainpanel.repaint();   
    }
    
	public void copyFile(String source,String destination){
		File sourcefile = new File(source);
		File destfile = new File(destination);
		try {
			destfile.createNewFile();
			FileInputStream fis = new FileInputStream(sourcefile);
			FileOutputStream fos = new FileOutputStream(destfile);
			while(true){
				int ch = fis.read();
				if(ch!=-1){
					fos.write(ch);
				}else{
					break;
				}
			}
			fis.close();
			fos.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
   
}
