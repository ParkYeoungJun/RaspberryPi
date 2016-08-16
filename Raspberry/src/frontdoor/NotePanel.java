package frontdoor;

import java.awt.Dimension;
import java.awt.TextArea;
import java.util.ArrayList;

import javax.swing.JPanel;

public class NotePanel extends JPanel
{

	Note_Parsing noteparse;
	
	ArrayList notedata = new ArrayList<String>();
	
	Dimension fulldim;
	
	TextArea note;
	
	public NotePanel(Dimension fulldim)
	{
		this.fulldim = fulldim;
		this.setBounds(0, fulldim.height/15, fulldim.width/3*2, fulldim.height-fulldim.height/15);
		this.setLayout(null);
		
		note = new TextArea();
		note.setBounds(0, 0, fulldim.width/3*2, fulldim.height-fulldim.height/15);
		note.setEditable(false);
		
		this.add(note);
		
		update();
		
		this.setVisible(true);
	}
	
	
	public void update()
	{
		noteparse = new Note_Parsing();
	
		notedata = noteparse.getdata();
		
		for(int i = 0 ; i < notedata.size() ; ++i)
		{
			note.append(notedata.get(i).toString());
			
			if(notedata.size()-1 != i)
				note.append("\n\n----------------------------\n\n");
		}
	}
	
}
