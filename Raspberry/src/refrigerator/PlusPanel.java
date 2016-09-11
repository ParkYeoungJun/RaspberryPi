package refrigerator;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

public class PlusPanel extends JFrame{
	
	JFrame mainframe;
	JPanel imagepanel;
	
	Dimension fulldim;
	
	String explainstring = new String("<html><font color=#FFFFFFF><strong>상품을 선택해 주세요</strong></font></html>");
	JLabel explain = new JLabel(explainstring, JLabel.LEFT);
	
	// Small group
	JPanel smallgroup;
	int x;
	int y;
	int temp;
	Timer timer;
	
	// Image JLabel
	JLabel meatlabel = new JLabel("", JLabel.CENTER);
	JLabel beveragelabel = new JLabel("", JLabel.CENTER);
	JLabel fishlabel = new JLabel("", JLabel.CENTER);
	JLabel marinelabel = new JLabel("", JLabel.CENTER);
	JLabel fruitlabel = new JLabel("", JLabel.CENTER);
	JLabel dairylabel = new JLabel("", JLabel.CENTER);
	JLabel etclabel = new JLabel("", JLabel.CENTER);
	JLabel vegetablelabel = new JLabel("", JLabel.CENTER);
	
	// text JLabel
	JLabel meat_tlabel = new JLabel("<html>Meat<br><br><br><br><br><br><br><br></html>", JLabel.CENTER);
	JLabel beverage_tlabel = new JLabel("<html>Beverage<br><br><br><br><br><br><br><br></html>", JLabel.CENTER);
	JLabel fish_tlabel = new JLabel("<html>Fish<br><br><br><br><br><br><br><br></html>", JLabel.CENTER);
	JLabel marine_tlabel = new JLabel("<html>Marine<br><br><br><br><br><br><br><br></html>", JLabel.CENTER);
	JLabel fruit_tlabel = new JLabel("<html>Fruit<br><br><br><br><br><br><br><br></html>", JLabel.CENTER);
	JLabel dairy_tlabel = new JLabel("<html>Dairy<br><br><br><br><br><br><br><br></html>", JLabel.CENTER);
	JLabel etc_tlabel = new JLabel("<html>Etc<br><br><br><br><br><br><br><br></html>", JLabel.CENTER);
	JLabel vegetable_tlabel = new JLabel("<html>Vegetable<br><br><br><br><br><br><br><br></html>", JLabel.CENTER);
	
	
	// Image URL
	ImageIcon meat;
	ImageIcon beverage;
	ImageIcon fish;
	ImageIcon marine;
	ImageIcon fruit;
	ImageIcon dairy;
	ImageIcon etc;
	ImageIcon vegetable;
	
	public PlusPanel(Dimension fulldim, JFrame mainframe)
	{
		this.fulldim = fulldim;
		x = fulldim.width-(fulldim.width*2)/7;
		y = fulldim.height/2+fulldim.height/3;
		temp = 0;
		this.mainframe = mainframe;
		
		this.setTitle("Add");
		this.setLayout(null);
		this.setBounds(fulldim.width/7, fulldim.height/10, fulldim.width-(fulldim.width*2)/7, fulldim.height/2+fulldim.height/3);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		try
		{
			BufferedImage im = ImageIO.read(new File("icon/plus_background_up.png"));
			this.setContentPane(new ImagePanel(im));
			
			/*
			 *  group
			 */
			// Initial Image
			meat = new ImageIcon("icon/meat_icon.png");
			beverage = new ImageIcon("icon/beverage_icon.png");
			fish = new ImageIcon("icon/fish_icon.png");
			marine = new ImageIcon("icon/seafood_icon.png");
			fruit = new ImageIcon("icon/fruit_icon.png");
			dairy = new ImageIcon("icon/dairy_icon.png");
			etc = new ImageIcon("icon/etc_icon.png");
			vegetable = new ImageIcon("icon/vegetable_icon.png");
		
			
			// Initial Image Label
			Image img;
			img = meat.getImage();
			meatlabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
//			meatlabel.setIcon(meat);
			img = beverage.getImage();
			beveragelabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
//			beveragelabel.setIcon(beverage);
			img = fish.getImage();
			fishlabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
//			fishlabel.setIcon(fish);
			img = marine.getImage();
			marinelabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
//			marinelabel.setIcon(marine);
			img = fruit.getImage();
			fruitlabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
//			fruitlabel.setIcon(fruit);
			img = dairy.getImage();
			dairylabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
//			dairylabel.setIcon(dairy);
			img = etc.getImage();
			etclabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
//			etclabel.setIcon(etc);
			img = vegetable.getImage();
			vegetablelabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
//			vegetablelabel.setIcon(vegetable);
			
			
			/*
			 *  MouseListenr
			 */
			
			meatlabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new MeatPanel(x,y);
					
					PlusPanel.this.add(smallgroup);
					
					animate();
				
					explain.setVisible(false);
				}
			});
			beveragelabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new BeveragePanel(x,y);
				}
			});
			fishlabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new FishPanel(x,y);
				}
			});
			fruitlabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new FruitPanel(x,y);
				}
			});
			marinelabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new MarinePanel(x,y);
				}
			});
			etclabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new EtcPanel(x,y);
				}
			});
			vegetablelabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new VegetablePanel(x,y);
				}
			});
			dairylabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new DairyPanel(x,y);
				}
			});
			
			/*
			 * 
			 */
			
			
			// explain label
			explain.setFont(new Font(null, 10, 20));
			explain.setBounds(10, 0, fulldim.width/2+fulldim.width/4, 30);
			this.add(explain);
			
			meat_tlabel.setFont(new Font(null,10,20));
			beverage_tlabel.setFont(new Font(null,10,20));
			fish_tlabel.setFont(new Font(null,10,20));
			marine_tlabel.setFont(new Font(null,10,20));
			fruit_tlabel.setFont(new Font(null,10,20));
			dairy_tlabel.setFont(new Font(null,10,20));
			etc_tlabel.setFont(new Font(null,10,20));
			vegetable_tlabel.setFont(new Font(null,10,20));
			
			
			
			imagepanel = new JPanel()
			{
				@Override
				protected void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					
					Image img;

					try
					{
						ImageIcon background = new ImageIcon("icon/plus_background.png");
						img = background.getImage();

//						g.drawImage(img.getScaledInstance(fulldim.width/4, fulldim.height-fulldim.height/15, Image.SCALE_SMOOTH), 0,0,this.getWidth(),this.getHeight(),this);
						g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);

					}
					catch(Exception e)
					{
						System.err.println(e);
					}
				}
			};
			
			imagepanel.setLayout(new GridLayout(4,4,10,0));
			imagepanel.setBounds(0, 40, fulldim.width-(fulldim.width*2)/7, fulldim.height/2+fulldim.height/3-40);
			imagepanel.add(meatlabel);
			imagepanel.add(beveragelabel);
			imagepanel.add(fishlabel);
			imagepanel.add(marinelabel);
			imagepanel.add(meat_tlabel);
			imagepanel.add(beverage_tlabel);
			imagepanel.add(fish_tlabel);
			imagepanel.add(marine_tlabel);
			imagepanel.add(fruitlabel);
			imagepanel.add(dairylabel);
			imagepanel.add(etclabel);
			imagepanel.add(vegetablelabel);
			imagepanel.add(fruit_tlabel);
			imagepanel.add(dairy_tlabel);
			imagepanel.add(etc_tlabel);
			imagepanel.add(vegetable_tlabel);
			this.add(imagepanel);
			
			
			/*
			 * 
			 */
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		
		
		/*
		 *  Initial URL
		 */
		
		
		/*
		 * 
		 */
		
		this.addWindowListener(new java.awt.event.WindowAdapter(){
			
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent)
			{
				mainframe.setEnabled(true);
			}
			
		});	
		
		
		this.setVisible(true);
		
	}
	
	public void animate()
	{
		timer = new Timer(5, new ActionListener(){
			public void actionPerformed(ActionEvent ae){

//				int temp = fulldim.width-(fulldim.width*2)/7;
				
				if(x > 0)
				{
					x = x-20;
					temp = temp - 20;
										
					imagepanel.setLocation(temp, 40);
					imagepanel.repaint();
					
					smallgroup.setLocation(x, 0);
					smallgroup.repaint();
				}
				else
				{
					timer.stop();
				}							
			}
		});
		
		timer.start();
	}

	class ImagePanel extends JComponent {
	    private Image image;
	    public ImagePanel(Image image) {
	        this.image = image;
	    }
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, fulldim.width-(fulldim.width*2)/7, fulldim.height/2+fulldim.height/3,this);
	    }
	}
}
