package refrigerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MeatPanel extends JPanel{
	
	JPanel list_panel;
	
	JPanel check_panel;
	JButton yes_button;
	JButton no_button;
	
	ImageIcon background;
	ImageIcon check_background;

	public MeatPanel(int x, int y){
		
		this.setBounds(x, 0, x, y);
		this.setLayout(null);
		this.setBackground(Color.WHITE);
	
		list_panel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				
				Image img;

				try
				{
					background = new ImageIcon("icon/plus_background.png");
					img = background.getImage();

					g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);

				}
				catch(Exception e)
				{
					System.err.println(e);
				}
			}
		};
		list_panel.setLayout(new GridLayout(3,2));
		list_panel.setBounds(0, 0, x, y - y/8);
		
		check_panel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				
				Image img;

				try
				{
					check_background = new ImageIcon("icon/menupanel_background.png");
					img = background.getImage();

					g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);

				}
				catch(Exception e)
				{
					System.err.println(e);
				}
			}
		};
		check_panel.setLayout(null);
		check_panel.setBounds(0, y - y/8, x, y/16);
		yes_button = new JButton("확인");
		yes_button.setBounds(x/5,0, x/8,50);
		yes_button.setBorder(BorderFactory.createRaisedBevelBorder());
		no_button = new JButton("취소");
		no_button.setBounds(x-x/3,0, x/8,50);
		no_button.setBorder(BorderFactory.createRaisedBevelBorder());
		check_panel.add(yes_button);
		check_panel.add(no_button);
		
		this.add(list_panel);
		this.add(check_panel);
		
		this.setVisible(true);
		
	}
	
}
