package refrigerator;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;

import refrigerator.PlusPanel.ImagePanel;

public class ColdPanel extends JPanel{

	Dimension fulldim;
	
	Calendar today;
	
	JLabel cold = new JLabel("<html><font color=#FFFFFFF>냉장실</font></html>",JLabel.RIGHT);
	
	JList list;
	JScrollPane scroll;
	
	int x;
	int y;
	String[] data;
	
	FoodParsing fooddata;
	
	MainFrame mainframe;
	
	ArrayList userdata = new ArrayList<UserData>();
	
	DefaultListModel model = new DefaultListModel();
	
	updateThread update;
	
	boolean paneflag = false;
	boolean whe = true;
	
	boolean blockflag = false;
	
	public ColdPanel(Dimension fulldim, MainFrame mainframe)
	{
		this.fulldim = fulldim;
		this.mainframe = mainframe;
		
		this.fooddata = new FoodParsing();
		
		x = fulldim.width/2-60;
		y = fulldim.height*4/5-20;		
		
		today = Calendar.getInstance();
		
		this.setBounds(fulldim.width/2+30, fulldim.height/15+10, x,y);
		
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder());
		
		this.setLayout(null);
		
		// label
		cold.setBounds(0, 0, x-10, 30);
		cold.setFont(new Font(null,Font.BOLD,20));
		this.add(cold);

		list = new ImageList(model);
		list.setFont(new Font(null,Font.BOLD,20));
		list.setForeground(Color.WHITE);
//		update();
		
		list.addMouseListener(
				new MouseAdapter(){
					
					public void mousePressed(MouseEvent e)
					{
//						try {
//							update.sleep(500);
//						} catch (Exception e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
						
//						if(t == null)
//						{
//							t = new java.util.Timer();
//						}
//						
//						
//						t.schedule(new TimerTask()
//						{
//							public void run()
//							{	
								/*
								 * Run
								 */
						if(!blockflag)
						{
														
							int index = list.getSelectedIndex();
								
//								mainframe.setEnabled(false);
								
								
								JFrame adjustframe = new JFrame();
								adjustframe.setResizable(false);
								adjustframe.setUndecorated(true);
								try {
									BufferedImage im = ImageIO.read(new File("icon/adjust.png"));
									adjustframe.setContentPane(new ImagePanel(im));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								adjustframe.addWindowListener(new WindowListener(){

									@Override
									public void windowActivated(WindowEvent arg0) {
										// TODO Auto-generated method stub
									}

									@Override
									public void windowClosed(WindowEvent arg0) {
										// TODO Auto-generated method stub
									}

									@Override
									public void windowClosing(WindowEvent arg0) {
										// TODO Auto-generated method stub
									}

									@Override
									public void windowDeactivated(WindowEvent arg0) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void windowDeiconified(WindowEvent arg0) {
										// TODO Auto-generated method stub
									}

									@Override
									public void windowIconified(WindowEvent arg0) {
										// TODO Auto-generated method stub
										
			
									}

									@Override
									public void windowOpened(WindowEvent arg0) {
										// TODO Auto-generated method stub
										
									}
									
								});
								adjustframe.setBounds(fulldim.width/2-fulldim.width/6, fulldim.height/2-fulldim.height/15, fulldim.width/3, fulldim.height/4);
								adjustframe.setLayout(new FlowLayout(FlowLayout.CENTER,40,20));
								JButton adjust = new JButton("수정");
								adjust.setBorder(BorderFactory.createRaisedBevelBorder());
								adjust.setFont(new Font(null, Font.BOLD, 17));
								JButton delete = new JButton("삭제");
								delete.setBorder(BorderFactory.createRaisedBevelBorder());
								delete.setFont(new Font(null, Font.BOLD, 17));
								JButton cancel = new JButton("취소");
								cancel.setBorder(BorderFactory.createRaisedBevelBorder());
								cancel.setFont(new Font(null, Font.BOLD, 17));


								adjust.addActionListener(new ActionListener(){

									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO Auto-generated method stub
										
										UserData temp = (UserData) userdata.get(index);
										
										mainframe.setEnabled(true);
										
										adjustframe.dispose();
										
										JFrame insert = new JFrame();
										BufferedImage im;
										try {
											im = ImageIO.read(new File("icon/adjust.png"));
											insert.setContentPane(new ImagePanel(im));
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										insert.setBounds(fulldim.width/2-fulldim.width/6, fulldim.height/2-fulldim.height/15, fulldim.width/3, fulldim.height/4);
										insert.setLayout(new FlowLayout(FlowLayout.CENTER, 40,20));
										insert.setUndecorated(true);
										//										insert.setBounds(fulldim.width/2-fulldim.width/15, fulldim.height/2-fulldim.height/5, fulldim.width/5, fulldim.height/5);
//										insert.setLayout(new BorderLayout());
//										adjustframe.remove(adjust);
//										adjustframe.remove(delete);
//										adjustframe.remove(cancel);

//										adjustframe.setBounds(fulldim.width/2-fulldim.width/15, fulldim.height/2-fulldim.height/15, fulldim.width/3, fulldim.height/6);
//										adjustframe = new JFrame();
//										adjustframe.setBounds(fulldim.width/2-fulldim.width/6, fulldim.height/2-fulldim.height/15, fulldim.width/3, fulldim.height/4);
//										adjustframe.setLayout(new FlowLayout(FlowLayout.CENTER, 40,20));
								
										
										JLabel info = new JLabel("<html><font color=#FFFFFFF>변경할 수량을 선택 하세요</font></html>");
										info.setFont(new Font(null, Font.BOLD, 20));
										insert.add(info);
										JComboBox num = new JComboBox();
										num.setBounds(x/10, y/3-y/24, x/13, 30);
										num.setFont(new Font(null,Font.BOLD,30));
										for(int i = 1 ; i <= 200 ; ++i)
										{
											num.addItem(i);
										}
										insert.add(num);
										
										JButton yes = new JButton("확인");
										yes.addActionListener(new ActionListener(){

											@Override
											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method stub
												
												mainframe.setEnabled(true);

												mainframe.UpgradeData(temp.getId(), temp.getGroup(),
														temp.getPurchase(), temp.getName(), 0, temp.getShelf(), num.getSelectedIndex()+1
														, Integer.parseInt(temp.getPosition()));
												
												insert.dispose();
												
												JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");												
											}
											
										});
										insert.add(yes);
										
										insert.setVisible(true);
										
										mainframe.setEnabled(false);
										
									}
									
								});
								
								delete.addActionListener(new ActionListener(){

									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO Auto-generated method stub
										
										UserData temp = (UserData) userdata.get(index);
										
										mainframe.DeleteData(temp.getId());
										
										mainframe.setEnabled(true);
										
										adjustframe.dispose();
										
										JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.");
										
									}
									
								});
								
								cancel.addActionListener(new ActionListener(){

									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO Auto-generated method stub

										mainframe.setEnabled(true);
										adjustframe.dispose();
									}	
									
								});
								
								adjustframe.add(adjust);
								adjustframe.add(delete);
								adjustframe.add(cancel);
								
								adjustframe.setVisible(true);
								
								
//							}
//						}, 300);
						}
					}
					
					public void mouseReleased(MouseEvent e)
					{						
//						if(t != null)
//						{	
//							t.cancel();
//							t = null;
//						}
					}
				});
		
		scroll = new JScrollPane(list);		
		scroll.setBounds(0, 30, x, y-30);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(20,0));
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		this.add(scroll);
		
		update = new updateThread();
		update.start();
		
		this.setVisible(true);
	}
	
	public void setflag(boolean flag)
	{
		blockflag = flag;
	}

	class updateThread extends Thread
	{
		public void run()
		{
			while(true)
			{
				fooddata.update(1);
				userdata.clear();
			
				ArrayList dataarray = fooddata.getcoldData();
								
				DefaultListModel modeltemp = new DefaultListModel();

//				model.removeAllElements();
				
				for(int i = 0 ; i < dataarray.size() ; ++i)
				{			
					UserData temp = (UserData) dataarray.get(i);
					userdata.add(temp);	
					
//					data[i] = "<html><p align=left>"+temp.getName()+"</p><p align=left>수량 : "+temp.getNum()+"</p><p alignt=right>유통기한 : "+temp.getShelf()+"</p><hr noshade></html>";
					modeltemp.addElement("<html><p align=left>"+temp.getName()+"</p><p align=left>수량 : "+temp.getNum()+"</p><p alignt=right>유통기한 : "+temp.getShelf()+"</p><hr noshade></html>");
	
					Calendar instance = Calendar.getInstance();
					instance.set(Integer.parseInt(temp.getShelf().split("-")[0]), Integer.parseInt(temp.getShelf().split("-")[1])-1, Integer.parseInt(temp.getShelf().split("-")[2].split(" ")[0])
							, Integer.parseInt(temp.getShelf().split(" ")[1].split(":")[0]), Integer.parseInt(temp.getShelf().split(":")[1]));
				
					
					if(mainframe.getalarmflag())
					{
						if(today.get(Calendar.HOUR_OF_DAY) == 8 || today.get(Calendar.HOUR_OF_DAY) == 18)
						{
							if(whe)
							{
								if(today.getTimeInMillis()-instance.getTimeInMillis() > 0)
								{
									JOptionPane.showMessageDialog(null, "냉장실에 있는 "+temp.getName()+"의 유통기한이 다 되었습니다.");
								}
								else if(today.getTimeInMillis()-instance.getTimeInMillis() > -172800000)
								{
									JOptionPane.showMessageDialog(null, "냉장실에 있는 "+temp.getName()+"의 유통기한이 임박하였습니다.");
								}
								
								paneflag = true;
							}
						}
						else
						{
							paneflag = false;
							whe = true;
						}
					}
				}
				
				if(paneflag)
					whe = false;
					
				list.setModel(modeltemp);
								
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	class timeThread extends Thread
	{
		public void run()
		{
			while(true)
			{
				today.getInstance();
			
				try
				{
					Thread.sleep(30000);
				}
				catch(Exception e)
				{
					System.err.println(e);
				}
			}
		}
	}
	
	class ImageList extends JList
	{
		private BufferedImage background;

        public ImageList(DefaultListModel model) {
            super(model);
            try {
                background = ImageIO.read(new File("icon/back_co.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            setOpaque(false);
            setBackground(new Color(0, 0, 0, 0));
        }
        
  

//        @Override
//        protected void paintComponent(Graphics g) {
//            if (background != null) {
//                Graphics2D g2d = (Graphics2D) g.create();
//                g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight() ,this);
//                g2d.dispose();
//            }
//            super.paintComponent(g);
//        }

	}
	
//	@Override
//	protected void paintComponent(Graphics g){
//		super.paintComponent(g);
//		
//		Image img;
//
//		try
//		{
//			ImageIcon background = new ImageIcon("icon/winebackground.png");
//			img = background.getImage();
//
//			g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);
//
//		}
//		catch(Exception e)
//		{
//			System.err.println(e);
//		}
//	}

	
	class ImagePanel extends JComponent {
	    private Image image;
	    public ImagePanel(Image image) {
	        this.image = image;
	    }
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(),this);
	    }
	}
	
	 protected int strokeSize = 1;
	    protected Color _shadowColor = Color.BLACK;
	    protected boolean shadowed = true;
	    protected boolean _highQuality = true;
	    protected Dimension _arcs = new Dimension(30, 30);
	    protected int _shadowGap = 5;
	    protected int _shadowOffset = 4;
	    protected int _shadowAlpha = 150;

	    protected Color _backgroundColor = Color.LIGHT_GRAY;
	    protected BufferedImage image = null;
	    
	@Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int shadowGap = this._shadowGap;
        
        try {
			image = ImageIO.read(new File("icon/winebackground.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Color shadowColorA = new Color(_shadowColor.getRed(), _shadowColor.getGreen(), _shadowColor.getBlue(), _shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        if(_highQuality)
        {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if(shadowed)
        {
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(_shadowOffset, _shadowOffset, width - strokeSize - _shadowOffset,
                    height - strokeSize - _shadowOffset, _arcs.width, _arcs.height);
        }
        else
        {
            _shadowGap = 1;
        }

        RoundRectangle2D.Float rr = new RoundRectangle2D.Float(0, 0, (width - shadowGap), (height - shadowGap), _arcs.width, _arcs.height);

        Shape clipShape = graphics.getClip();

        if(image == null)
        {
            graphics.setColor(_backgroundColor);
            graphics.fill(rr);
        }
        else
        {
            RoundRectangle2D.Float rr2 =  new RoundRectangle2D.Float(0, 0, (width - strokeSize - shadowGap), (height - strokeSize - shadowGap), _arcs.width, _arcs.height);

            graphics.setClip(rr2);
            graphics.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), null);
            graphics.setClip(clipShape);
        }

        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(strokeSize));
        graphics.draw(rr);
        graphics.setStroke(new BasicStroke());
    }
}
