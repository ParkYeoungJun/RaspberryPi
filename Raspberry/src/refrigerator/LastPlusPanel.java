package refrigerator;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class LastPlusPanel extends JPanel{
	
	Data data;
	
	int x;
	int y;
	PlusPanel plusclass;
	Button b;
	
	JLabel category;
	JLabel name;
	JLabel date;
	JLabel shelf;
	JLabel question;
	JLabel whe_c_f;
	JRadioButton cold;
	JRadioButton freeze;
	ButtonGroup bg;

	JButton yes;
	JButton no;
	
	BufferedImage image;
	
	boolean on = false;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Calendar current;
	String shelf_cold;
	String shelf_freeze;
	
	JComboBox num;
	JLabel num_label;
	
	public LastPlusPanel(int x, int y, PlusPanel plusclass)
	{
		this.x = x;
		this.y = y;
		this.plusclass = plusclass;
		
		this.setBounds(2*x, 0, x, y);
		this.setLayout(null);
		
		try {
			image = ImageIO.read(new File("icon/plus_background.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		category = new JLabel("",JLabel.LEFT);
		category.setFont(new Font(null,Font.BOLD,20));
		category.setBounds(30, y/8, x, 50);
		this.add(category);		
	
		name = new JLabel("",JLabel.LEFT);
		name.setFont(new Font(null,Font.BOLD,20));
		name.setBounds(30, y/5, x, 50);
		this.add(name);
		
		num_label = new JLabel("<html><font color = #FFFFFFF>수량 : </font></html>", JLabel.LEFT);
		num_label.setBounds(30, y/3-y/20, x/13, 50);
		num_label.setFont(new Font(null, Font.BOLD,20));
		num = new JComboBox();
		num.setBounds(x/10, y/3-y/24, x/13, 30);
		num.setFont(new Font(null,Font.BOLD,30));
		for(int i = 1 ; i <= 200 ; ++i)
		{
			num.addItem(i);
		}
		this.add(num_label);
		this.add(num);
		
		date = new JLabel("", JLabel.LEFT);
		date.setFont(new Font(null,Font.BOLD,20));
		date.setBounds(30, y/3+y/8, x, 50);
		this.add(date);
		
		whe_c_f = new JLabel("<html><font color = #FFFFFFF>어디에 저장할지 선택하세요 : </font></html>");
		whe_c_f.setFont(new Font(null, Font.BOLD, 20));
		whe_c_f.setBounds(30, y/3+y/5, x/4, 50);
		this.add(whe_c_f);

		cold = new JRadioButton("<html><font color = #FFFFFFF>냉장실</font></html>");
		freeze = new JRadioButton("<html><font color = #FFFFFFF>냉동실</font><html>");
		cold.setBounds(x/4, y/3+y/5, x/10, 50);
		freeze.setBounds(x/3+x/12, y/3+y/5, x/10, 50);
		cold.setFont(new Font(null, Font.BOLD, 20));
		freeze.setFont(new Font(null, Font.BOLD, 20));
		cold.setOpaque(false);
		freeze.setOpaque(false);
		bg = new ButtonGroup();
		bg.add(cold);
		bg.add(freeze);
		cold.setSelected(true);
		cold.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(on)
					shelf.setText("<html><font color = #FFFFFFF>유통 기한 : "+shelf_cold+"</font></html>");
			}
			
		});
		freeze.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(on)
					shelf.setText("<html><font color = #FFFFFFF>유통 기한 : "+shelf_freeze+"</font></html>");
			}
			
		});	
		this.add(cold);
		this.add(freeze);

		
		shelf = new JLabel("", JLabel.LEFT);
		shelf.setFont(new Font(null,Font.BOLD,20));
		shelf.setBounds(30, y/3+y/3, x, 30);
		this.add(shelf);
		
		question = new JLabel("<html><font color = #FFFFFFF>입력된 내용이 맞습니까?</font></html>", JLabel.CENTER);
		question.setFont(new Font(null,Font.BOLD,20));
		question.setBounds(0, (y*4)/5 , x, 30);
		this.add(question);
		
		yes = new JButton("네");
		yes.setBounds(x/4, (y*6)/7, x/10, 30);
		yes.setBorder(BorderFactory.createRaisedBevelBorder());
		no = new JButton("아니요");
		no.setBounds(x/2+x/6, (y*6)/7, x/10, 30);
		no.setBorder(BorderFactory.createRaisedBevelBorder());
		yes.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				current = Calendar.getInstance();
				
				if(cold.isSelected())
				{
					plusclass.mainframe.InsertData(data.getGroup(), format.format(current.getTime()), data.getName(), 0,
						shelf_cold, num.getSelectedIndex()+1, 1);
					
				}
				else
				{
					plusclass.mainframe.InsertData(data.getGroup(), format.format(current.getTime()), data.getName(), 0,
							shelf_freeze, num.getSelectedIndex()+1, 0);
		
				}
				
				JOptionPane.showMessageDialog(null,"추가가 완료 되었습니다.");
				plusclass.mainframe.setEnabled(true);
				plusclass.dispose();
				
			}
			
		});
		no.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				plusclass.animate_return(2);
				
				on = false;
			}
			
		});
		this.add(yes);
		this.add(no);
		
		this.setVisible(true);
	}
	
	public void setDB(Data data)
	{
		this.data = data;

		category.setText("<html><font color = #FFFFFFF>카테고리 : "+data.group+"</font></html>");
	
		name.setText("<html><font color = #FFFFFFF>품명 : "+data.name+"</font></html>");
		
		current = Calendar.getInstance();
		date.setText("<html><font color = #FFFFFFF>현재 날짜 : "+format.format(current.getTime())+"</font></html>");
		
		cold.setSelected(true);
		
		current.add(current.DATE, Integer.parseInt(data.shelf_life_cold));
		shelf_cold = format.format(current.getTime());
		shelf.setText("<html><font color = #FFFFFFF>유통 기한 : "+shelf_cold+"</font></html>");
		
		current = Calendar.getInstance();
		current.add(current.DATE, Integer.parseInt(data.shelf_life_freeze));
		shelf_freeze = format.format(current.getTime());
		
		on = true;
			
	}
	
	 @Override
	 protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 g.drawImage(image, 0, 0, x, y,this);
	 }

}
