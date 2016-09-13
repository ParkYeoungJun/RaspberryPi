package refrigerator;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class LastPlusPanel extends JPanel{
	
	int x;
	int y;
	PlusPanel plusclass;
	Button b;
	
	public LastPlusPanel(int x, int y, PlusPanel plusclass)
	{
		this.x = x;
		this.y = y;
		this.plusclass = plusclass;
		
		this.setBounds(2*x, 0, x, y);
		this.setLayout(new GridLayout(3,2));
		
		b = new Button("Asdf");
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				plusclass.animate_return(2);
			}
			
		});
		
		this.add(b);
		
		this.setVisible(true);
	}

}
