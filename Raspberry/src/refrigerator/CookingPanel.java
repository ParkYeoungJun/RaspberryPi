package refrigerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import refrigerator.ColdPanel.ImageList;

public class CookingPanel extends JFrame{
	
	Dimension fulldim;
	
	Cook_Parsing cookdata = new Cook_Parsing();
	
	ArrayList data = new ArrayList<Cookdata>();
		
	FoodParsing food;
	
	JList list;
	JScrollPane scroll;
	
	BufferedImage image;
	
	
	public CookingPanel(Dimension fulldim, MainFrame mainframe)
	{
		this.fulldim = fulldim;
		
		try {
			image = ImageIO.read(new File("icon/recipe.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.setTitle("금일 추천요리!!");
		this.setBounds(fulldim.width/7, fulldim.height/10, fulldim.width-(fulldim.width*2)/7, fulldim.height/2+fulldim.height/3);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setContentPane(new ImagePanel(image));
		this.setResizable(false);
		
		data = cookdata.getData();
						
		food = new FoodParsing();
		
		countNum();
		
		Collections.sort(data);
		
		
		DefaultListModel modeltemp = new DefaultListModel();

		for(int i = 0 ; i < data.size() ; ++i)
		{
			Cookdata temp = (Cookdata) data.get(i);
		
			if(temp.getCount() == 0)
				break;
			
			modeltemp.addElement("<html><p align=left>요리명 : "+temp.getName()+"</p><p align=left>칼로리: "+temp.calorie+
					"</p>-----------------------------------------------------</html>");
		}
		
		list = new ImageList(modeltemp);
		list.setFont(new Font(null,Font.BOLD,20));
		list.addMouseListener(
				new MouseAdapter(){
					
					public void mousePressed(MouseEvent e)
					{
						
					}
					
				});
		
		scroll = new JScrollPane(list);		
		scroll.setBounds(fulldim.width/9, fulldim.height/13,  fulldim.width-(fulldim.width*2)/7-2*fulldim.width/9, fulldim.height/2+fulldim.height/3-2*fulldim.height/13-30);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(20,0));
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		this.add(scroll);
		
		
		
		this.setVisible(true);
	}
	
	public void countNum()
	{
		ArrayList cold = new ArrayList<UserData>();
		ArrayList freeze = new ArrayList<UserData>();
		
		cold = food.getcoldData();
		freeze = food.getfreezeData();
		
		for(int i = 0 ; i < data.size() ; ++i)
		{
			Cookdata temp = (Cookdata) data.get(i);
			
			int count = 0;
			
			String materialstr = temp.getMaterial();
			
			for(int j = 0 ; j < cold.size() ; ++j)
			{
				UserData coldtemp = (UserData)cold.get(j);
				
				if(materialstr.contains(coldtemp.getName()))
					count++;
			}
			
			for(int j = 0 ; j < freeze.size() ; ++j)
			{
				UserData freezetemp = (UserData)freeze.get(j);
				
				if(materialstr.contains(freezetemp.getName()))
					count++;	
			}
			
			temp.setCount(count);
			data.set(i, temp);
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
	}
	
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

}
