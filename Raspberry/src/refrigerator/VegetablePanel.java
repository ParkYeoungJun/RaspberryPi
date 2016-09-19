package refrigerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import refrigerator.MeatPanel.ImageList;

public class VegetablePanel extends JPanel{
	
	String test[] = {"가지","감자","고추","깻잎","꽃양배추","당근","무","버섯","브로콜리","블루베리","빨간무","상추"
			,"애호박","양배추","양파","오이","완두콩","콩","토마토","파","파슬리","파프리카","호박","마늘","기타"};

	PlusPanel plusclass;
	
	JPanel list_panel;
	
	JPanel check_panel;
	JButton yes_button;
	JButton no_button;
	
	ImageIcon background;
	ImageIcon check_background;
	
	JList list;
	JScrollPane scroll;
	
	Database db;

	public VegetablePanel(int x, int y, PlusPanel plusclass){
		
		this.plusclass = plusclass;
		this.db = plusclass.db;
		
		this.setBounds(x, 0, x, y);
		this.setLayout(null);

		list = new ImageList(test);
		list.setFont(new Font(null, 20, 50));
		list.setCellRenderer(new DefaultListCellRenderer(){
			public int getHorizontalAlignment(){
				return CENTER;
			}
		});
		
		scroll = new JScrollPane(list);		
		scroll.setBounds(0, 0, x, y - y/5);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(30,0));
		
		this.add(scroll);
		
		
		check_panel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				
				Image img;

				try
				{
					check_background = new ImageIcon("icon/plus_background_below.png");
					img = check_background.getImage();

					g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);
				}
				catch(Exception e)
				{
					System.err.println(e);
				}
			}
		};
		check_panel.setLayout(null);
		check_panel.setBounds(0, y - y/5, x, y/5);
		yes_button = new JButton("확인");
		yes_button.setBounds(x/5,0, x/8,50);
		yes_button.setBorder(BorderFactory.createRaisedBevelBorder());
		yes_button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(!list.isSelectionEmpty())
				{
					Data getData = db.getShelflife("채소", list.getSelectedValue().toString());

					plusclass.lastpanel.setDB(getData);
				
					plusclass.animate(2);
				}
				else
					JOptionPane.showMessageDialog(null, "Please Choose");
			}
			
		});
		no_button = new JButton("뒤로");
		no_button.setBounds(x-x/3,0, x/8,50);
		no_button.setBorder(BorderFactory.createRaisedBevelBorder());
		no_button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				plusclass.animate_return(1);
			}
			
		});

		check_panel.add(yes_button);
		check_panel.add(no_button);
		
		this.add(check_panel);
		
		this.setVisible(true);
		
	}
	
	class ImageList extends JList
	{
		private BufferedImage background;

        public ImageList(Object[] values) {
            super(values);
            try {
                background = ImageIO.read(new File("icon/plus_background.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            setOpaque(false);
            setBackground(new Color(0, 0, 0, 0));
            setForeground(Color.WHITE);
        }
        
  

        @Override
        protected void paintComponent(Graphics g) {
            if (background != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                int x = getWidth() - background.getWidth();
                int y = getHeight() - background.getHeight();
                g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight() ,this);
                g2d.dispose();
            }
            super.paintComponent(g);
        }

	}
}
