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
	
	MainFrame mainframe;
	JPanel imagepanel;
	
	Dimension fulldim;
	
	String explainstring = new String("<html><font color=#FFFFFFF><strong>상품을 선택해 주세요</strong></font></html>");
	JLabel explain = new JLabel(explainstring, JLabel.LEFT);
	
	// Small group
	JPanel smallgroup;
	int x;
	int y;
	int x_2;
	int y_2;
	int temp;
	Timer timer;
	
	LastPlusPanel lastpanel;
	
	
	// Database
	Database db;
	
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
	JLabel meat_tlabel = new JLabel("<html><font color = #FFFFFFF>Meat<br><br><br></font></html>", JLabel.CENTER);
	JLabel beverage_tlabel = new JLabel("<html><font color = #FFFFFFF>Beverage<br><br><br></font></html>", JLabel.CENTER);
	JLabel fish_tlabel = new JLabel("<html><font color = #FFFFFFF>Fish<br><br><br></font></html>", JLabel.CENTER);
	JLabel marine_tlabel = new JLabel("<html><font color = #FFFFFFF>Marine<br><br><br></font></html>", JLabel.CENTER);
	JLabel fruit_tlabel = new JLabel("<html><font color = #FFFFFFF>Fruit<br><br><br></font></html>", JLabel.CENTER);
	JLabel dairy_tlabel = new JLabel("<html><font color = #FFFFFFF>Dairy<br><br><br></font></html>", JLabel.CENTER);
	JLabel etc_tlabel = new JLabel("<html><font color = #FFFFFFF>Etc<br><br><br></font></html>", JLabel.CENTER);
	JLabel vegetable_tlabel = new JLabel("<html><font color = #FFFFFFF>Vegetable<br><br><br></font></html>", JLabel.CENTER);
	
	
	// Image URL
	ImageIcon meat;
	ImageIcon beverage;
	ImageIcon fish;
	ImageIcon marine;
	ImageIcon fruit;
	ImageIcon dairy;
	ImageIcon etc;
	ImageIcon vegetable;
	
	public PlusPanel(Dimension fulldim, MainFrame mainframe, Database db)
	{
		this.db = db;
		this.fulldim = fulldim;
		
		x = fulldim.width-(fulldim.width*2)/7;
		y = fulldim.height/2+fulldim.height/3;
		x_2 = 2*x;
		y_2 = 2*y;
		temp = 0;
		
		this.mainframe = mainframe;
		this.setTitle("Add");
		this.setLayout(null);
		this.setBounds(fulldim.width/7, fulldim.height/10, fulldim.width-(fulldim.width*2)/7, fulldim.height/2+fulldim.height/3);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
	
		
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
			img = beverage.getImage();
			beveragelabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
			img = fish.getImage();
			fishlabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
			img = marine.getImage();
			marinelabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
			img = fruit.getImage();
			fruitlabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
			img = dairy.getImage();
			dairylabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
			img = etc.getImage();
			etclabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
			img = vegetable.getImage();
			vegetablelabel.setIcon(new ImageIcon(img.getScaledInstance(fulldim.width/10, fulldim.height/7, Image.SCALE_SMOOTH)));
			
			/*
			 *  MouseListenr
			 */
			
			meatlabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new MeatPanel(x,y, PlusPanel.this);
					
					PlusPanel.this.add(smallgroup);
					
					lastpanel = new LastPlusPanel(x,y,PlusPanel.this);
					
					PlusPanel.this.add(lastpanel);

					animate(1);
				
					explain.setVisible(false);
				}
			});
			beveragelabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new BeveragePanel(x,y, PlusPanel.this);
					
					PlusPanel.this.add(smallgroup);
					
					lastpanel = new LastPlusPanel(x,y,PlusPanel.this);
					
					PlusPanel.this.add(lastpanel);
					
					animate(1);
				
					explain.setVisible(false);	
				}
			});
			fishlabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new FishPanel(x,y, PlusPanel.this);
					
					PlusPanel.this.add(smallgroup);
					
					lastpanel = new LastPlusPanel(x,y,PlusPanel.this);
					
					PlusPanel.this.add(lastpanel);
										
					animate(1);
				
					explain.setVisible(false);
				}
			});
			fruitlabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new FruitPanel(x,y, PlusPanel.this);
					
					PlusPanel.this.add(smallgroup);

					lastpanel = new LastPlusPanel(x,y,PlusPanel.this);
					
					PlusPanel.this.add(lastpanel);
					
					animate(1);
				
					explain.setVisible(false);
				}
			});
			marinelabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new MarinePanel(x,y, PlusPanel.this);
					
					PlusPanel.this.add(smallgroup);

					lastpanel = new LastPlusPanel(x,y,PlusPanel.this);
					
					PlusPanel.this.add(lastpanel);
					
					animate(1);
				
					explain.setVisible(false);
				}
			});
			etclabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new EtcPanel(x,y, PlusPanel.this);
					
					PlusPanel.this.add(smallgroup);
				
					lastpanel = new LastPlusPanel(x,y,PlusPanel.this);
					
					PlusPanel.this.add(lastpanel);

					animate(1);
				
					explain.setVisible(false);
				}
			});
			vegetablelabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new VegetablePanel(x,y, PlusPanel.this);
					
					PlusPanel.this.add(smallgroup);
					
					lastpanel = new LastPlusPanel(x,y,PlusPanel.this);
					
					PlusPanel.this.add(lastpanel);

					animate(1);
				
					explain.setVisible(false);
				}
			});
			dairylabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					smallgroup = new DairyPanel(x,y, PlusPanel.this);
					
					PlusPanel.this.add(smallgroup);
					
					lastpanel = new LastPlusPanel(x,y,PlusPanel.this);
					
					PlusPanel.this.add(lastpanel);

					animate(1);
				
					explain.setVisible(false);
				}
			});
			
			/*
			 * 
			 */
			
			
			// explain label
			explain.setFont(new Font(null, 10, 20));
			explain.setBounds(10, 0, fulldim.width/2+fulldim.width/4, 30);
			this.add(explain);
			
			meat_tlabel.setFont(new Font(null,Font.BOLD,20));
			beverage_tlabel.setFont(new Font(null,Font.BOLD,20));
			fish_tlabel.setFont(new Font(null,Font.BOLD,20));
			marine_tlabel.setFont(new Font(null,Font.BOLD,20));
			fruit_tlabel.setFont(new Font(null,Font.BOLD,20));
			dairy_tlabel.setFont(new Font(null,Font.BOLD,20));
			etc_tlabel.setFont(new Font(null,Font.BOLD,20));
			vegetable_tlabel.setFont(new Font(null,Font.BOLD,20));
			
			
			
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
			imagepanel.add(fishlabel);
			imagepanel.add(marinelabel);
			imagepanel.add(vegetablelabel);
			imagepanel.add(meat_tlabel);
			imagepanel.add(fish_tlabel);
			imagepanel.add(marine_tlabel);
			imagepanel.add(vegetable_tlabel);
			imagepanel.add(fruitlabel);
			imagepanel.add(dairylabel);
			imagepanel.add(beveragelabel);
			imagepanel.add(etclabel);
			imagepanel.add(fruit_tlabel);
			imagepanel.add(dairy_tlabel);
			imagepanel.add(beverage_tlabel);
			imagepanel.add(etc_tlabel);
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
	
	public void animate(int flag)
	{
		if(flag == 1)
		{
			timer = new Timer(1, new ActionListener(){
				public void actionPerformed(ActionEvent ae){

					if(x > 0)
					{
						x = x-30;
						temp = temp - 30;
						x_2 = x_2-30;
										
						imagepanel.setLocation(temp, 40);
						imagepanel.repaint();
					
						smallgroup.setLocation(x, 0);
						smallgroup.repaint();
						
						lastpanel.setLocation(x_2,0);
						lastpanel.repaint();
					}
					else
					{
						x = 0;
						temp = -(fulldim.width-(fulldim.width*2)/7);
						x_2 = fulldim.width-(fulldim.width*2)/7;
						
						imagepanel.setLocation(temp,40);
						imagepanel.repaint();
					
						smallgroup.setLocation(x, 0);
						smallgroup.repaint();
						
						lastpanel.setLocation(x_2,0);
						lastpanel.repaint();
						
						
						x = x-15;
						temp = temp - 15;
						x_2 = x_2-15;
						
						timer.stop();
					}							
				}
			});
		}
		else if(flag == 2)
		{
			timer = new Timer(1, new ActionListener(){
				public void actionPerformed(ActionEvent ae){

					if(x_2 > 0)
					{
						x = x-30;
						temp = temp - 30;
						x_2 = x_2-30;
										
						imagepanel.setLocation(temp, 40);
						imagepanel.repaint();
					
						smallgroup.setLocation(x, 0);
						smallgroup.repaint();
						
						lastpanel.setLocation(x_2,0);
						lastpanel.repaint();
					}
					else
					{
						x = -(fulldim.width-(fulldim.width*2)/7);
						temp = -2*(fulldim.width-(fulldim.width*2)/7);
						x_2 = 0;
						
						imagepanel.setLocation(temp,40);
						imagepanel.repaint();
					
						smallgroup.setLocation(x, 0);
						smallgroup.repaint();
						
						lastpanel.setLocation(x_2,0);
						lastpanel.repaint();
						
						timer.stop();
					}							
				}
			});	
		}
		
		timer.start();
	}
	
	public void animate_return(int flag)
	{		
		if(flag == 1)
		{
			explain.setVisible(true);

			timer = new Timer(1, new ActionListener(){
				public void actionPerformed(ActionEvent ae){

					if(x < fulldim.width-(fulldim.width*2)/7)
					{
						x = x+30;
						temp = temp + 30;
						x_2 = x_2 + 30;
										
						imagepanel.setLocation(temp, 40);
						imagepanel.repaint();
					
						smallgroup.setLocation(x, 0);
						smallgroup.repaint();
						
						lastpanel.setLocation(x_2,0);
						lastpanel.repaint();
					}
					else
					{
						x = fulldim.width-(fulldim.width*2)/7;
						x_2 = 2*(fulldim.width-(fulldim.width*2)/7);
						temp = 0;
						
						imagepanel.setLocation(temp,40);
						imagepanel.repaint();
					
						smallgroup.setLocation(x, 0);
						smallgroup.repaint();
						
						lastpanel.setLocation(x_2,0);
						lastpanel.repaint();
					
						
						timer.stop();
					}							
				}
			});
		}
		else if(flag == 2)
		{
			timer = new Timer(1, new ActionListener(){
				public void actionPerformed(ActionEvent ae){

					if(x_2 < (fulldim.width-(fulldim.width*2)/7))
					{
						x = x+30;
						temp = temp + 30;
						x_2 = x_2+30;
										
						imagepanel.setLocation(temp, 40);
						imagepanel.repaint();
					
						smallgroup.setLocation(x, 0);
						smallgroup.repaint();
						
						lastpanel.setLocation(x_2,0);
						lastpanel.repaint();
					}
					else
					{
						x = 0;
						x_2 = fulldim.width-(fulldim.width*2)/7;
						temp = -(fulldim.width-(fulldim.width*2)/7);
						
						imagepanel.setLocation(temp,40);
						imagepanel.repaint();
					
						smallgroup.setLocation(x, 0);
						smallgroup.repaint();
						
						lastpanel.setLocation(x_2,0);
						lastpanel.repaint();
					
						
						timer.stop();
					}							
				}
			});
		}
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
