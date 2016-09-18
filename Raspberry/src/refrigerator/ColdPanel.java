package refrigerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;

public class ColdPanel extends JPanel{

	Dimension fulldim;
	
	Calendar today;
	
	JLabel cold = new JLabel("냉장실",JLabel.CENTER);
	
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
	
	public ColdPanel(Dimension fulldim, FoodParsing fooddata , MainFrame mainframe)
	{
		this.fulldim = fulldim;
		this.mainframe = mainframe;
		this.fooddata = fooddata;
		
		x = fulldim.width/2;
		y = fulldim.height/2+fulldim.height/15;		
		
		today = Calendar.getInstance();
		
		this.setBounds(fulldim.width/2, fulldim.height/15, x, y);
		
		this.setBackground(Color.WHITE);
		
		this.setLayout(null);
		
		// label
		cold.setBounds(0, 0, x, 30);
		cold.setFont(new Font(null,Font.BOLD,30));
		this.add(cold);

		list = new ImageList(model);
		list.setFont(new Font(null,Font.BOLD,30));
		
//		update();
		
		list.addMouseListener(
				new MouseAdapter(){
					private java.util.Timer t;
					
					@SuppressWarnings("deprecation")
					public void mousePressed(MouseEvent e)
					{
						try {
							update.sleep(500);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						if(t == null)
						{
							t = new java.util.Timer();
						}
						
						
						t.schedule(new TimerTask()
						{
							public void run()
							{	
								/*
								 * Run
								 */
								int index = list.getSelectedIndex();
								
								mainframe.setEnabled(false);
								
								JFrame adjustframe = new JFrame();
								adjustframe.setBounds(fulldim.width/2-fulldim.width/15, fulldim.height/2-fulldim.height/15, fulldim.width/8, fulldim.height/10);
								adjustframe.setLayout(new FlowLayout());
								adjustframe.setResizable(false);
								adjustframe.addWindowListener(new WindowListener(){

									@Override
									public void windowActivated(WindowEvent arg0) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void windowClosed(WindowEvent arg0) {
										// TODO Auto-generated method stub
										mainframe.setEnabled(true);
									}

									@Override
									public void windowClosing(WindowEvent arg0) {
										// TODO Auto-generated method stub
										mainframe.setEnabled(true);
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
								JButton adjust = new JButton("수정");
								JButton delete = new JButton("삭제");

								adjust.addActionListener(new ActionListener(){

									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO Auto-generated method stub
										
										UserData temp = (UserData) userdata.get(index);
										
//										adjustframe.dispose();
										
//										JFrame insert = new JFrame();
//										insert.setBounds(fulldim.width/2-fulldim.width/15, fulldim.height/2-fulldim.height/5, fulldim.width/5, fulldim.height/5);
//										insert.setLayout(new BorderLayout());
										adjustframe.remove(adjust);
										adjustframe.remove(delete);
										adjustframe.setBounds(fulldim.width/2-fulldim.width/13, fulldim.height/2-fulldim.height/5, fulldim.width/5, fulldim.height/5);
										adjustframe.setLayout(new BorderLayout());
								
										
										JLabel info = new JLabel("변경할 수량을 선택 하세요");
										info.setFont(new Font(null, Font.BOLD, 20));
										adjustframe.add(info, BorderLayout.NORTH);
										JComboBox num = new JComboBox();
										num.setBounds(x/10, y/3-y/24, x/13, 30);
										num.setFont(new Font(null,Font.BOLD,30));
										for(int i = 1 ; i <= 200 ; ++i)
										{
											num.addItem(i);
										}
										adjustframe.add(num, BorderLayout.CENTER);
										
										JButton yes = new JButton("확인");
										yes.addActionListener(new ActionListener(){

											@Override
											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method stub
												
												mainframe.setEnabled(true);

												mainframe.UpgradeData(temp.getId(), temp.getGroup(),
														temp.getPurchase(), temp.getName(), 0, temp.getShelf(), num.getSelectedIndex()+1
														, Integer.parseInt(temp.getPosition()));
												
												adjustframe.dispose();
												
												JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");												
											}
											
										});
										adjustframe.add(yes, BorderLayout.SOUTH);
										
//										insert.setVisible(true);
										
										
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
								
								adjustframe.add(adjust);
								adjustframe.add(delete);
								
								adjustframe.setVisible(true);
								
								
							}
						}, 500);
					}
					
					public void mouseReleased(MouseEvent e)
					{						
						if(t != null)
						{	
							t.cancel();
							t = null;
						}
					}
				});
		
		scroll = new JScrollPane(list);		
		scroll.setBounds(0, 30, x, y-30);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(20,0));
		this.add(scroll);
		
		update = new updateThread();
		update.start();
				
				
		this.setVisible(true);
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
					Thread.sleep(3000);
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
					Thread.sleep(60000);
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
        
  

        @Override
        protected void paintComponent(Graphics g) {
            if (background != null) {
                Graphics2D g2d = (Graphics2D) g.create();
//                int x = getWidth() - background.getWidth();
//                int y = getHeight() - background.getHeight();
                g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight() ,this);
                g2d.dispose();
            }
            super.paintComponent(g);
        }

	}
	
}
