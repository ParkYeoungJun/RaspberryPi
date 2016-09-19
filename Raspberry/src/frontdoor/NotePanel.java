package frontdoor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.util.ArrayList;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NotePanel extends JPanel
{

	Note_Parsing noteparse;
	
	ArrayList notedata = new ArrayList<String>();
	
	Dimension fulldim;
	
	JTextArea note;
	JScrollPane scroll;
	
	public NotePanel(Dimension fulldim)
	{
		this.fulldim = fulldim;
		this.setBounds(0, fulldim.height/15, fulldim.width/3*2, fulldim.height-fulldim.height/15);
		this.setLayout(null);
				
		Image tempimg = new ImageIcon("post.PNG").getImage();
		
		Image aimg = tempimg.getScaledInstance(fulldim.width/3*2, fulldim.height-fulldim.height/15, java.awt.Image.SCALE_SMOOTH);
		
		ImageIcon tmpicon = new ImageIcon(aimg);
		
		Image img = tmpicon.getImage();
		
		note = new JTextArea();//{
//			{setOpaque(false);}
//			
//			public void paintComponent(Graphics g){
//				g.drawImage(img,0,0,null);
//				super.paintComponent(g);
//			}
//		};
		//	note.setBounds(0, 0, fulldim.width/3*2, fulldim.height-fulldim.height/15);
		note.setEditable(false);
		note.setFont(new Font(null, Font.BOLD, 20));
		note.setBackground(Color.WHITE);
		note.setLineWrap(true);
		
		scroll = new JScrollPane(note, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(0, 0, fulldim.width/3*2, fulldim.height-fulldim.height/15);
//		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		this.add(scroll);
		
		updateThread up = new updateThread();
		up.start();
				
		
		this.setVisible(true);
	}
	
	class updateThread extends Thread
	{
		public void run()
		{
			while(true)
			{
				update();
			
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void update()
	{
		noteparse = new Note_Parsing();
	
		notedata = noteparse.getdata();
		
		if(notedata.size() == 0)
		{
			note.setText("");
			
			note.append("금일 노트가 비었어요\n\n좋은하루 되세요~");
		}
		else
		{
			note.setText("");
			
			for(int i = 0 ; i < notedata.size() ; ++i)
			{	
				note.append(notedata.get(i).toString());
			
				if(notedata.size()-1 != i)
					note.append("\n\n----------------------------\n\n");
			}
		}
	}
	
}
