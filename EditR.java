import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;


class EditR
{
	JTextField TrName,Tnode1,Tnode2,TrValue,TrAngle,TyPosition,TxPosition;
	JDialog jd;
	JButton Submit;
	JPanel wordPanel;	
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 300;
	Resistor Redit = new Resistor("r",12,4,3,5,0,100);

	
	EditR(JFrame j,Resistor r) {
		jd = new JDialog(j,"EditResistorPanel",true);
        Redit = r;
		jd.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		jd.setLayout(new BorderLayout());

		wordPanel = new JPanel();

			wordPanel.setLayout(new GridLayout(7, 1));
			
			wordPanel.add(new JLabel("R name:"));
			wordPanel.add(TrName = new JTextField(Redit.name));

			wordPanel.add(new JLabel("node1 number:"));
			wordPanel.add(Tnode1 = new JTextField(Integer.toString(Redit.node1)));

			wordPanel.add(new JLabel("node2 number:"));
			wordPanel.add(Tnode2 = new JTextField(Integer.toString(Redit.node2)));

			wordPanel.add(new JLabel("location x:"));
			wordPanel.add(TxPosition = new JTextField(Integer.toString(Redit.xloc)));
			wordPanel.add(new JLabel("location y:"));
			wordPanel.add(TyPosition = new JTextField(Integer.toString(Redit.yloc)));
		
			wordPanel.add(new JLabel("angle:"));
			wordPanel.add(TrAngle = new JTextField(Double.toString(Redit.orientation_angle)));

			wordPanel.add(new JLabel("value:"));
			wordPanel.add(TrValue = new JTextField(Double.toString(Redit.value)));
			
		Submit = new JButton("Submit");

		jd.add(wordPanel,BorderLayout.CENTER);
		jd.add(Submit, BorderLayout.SOUTH);
		myEvent();//�����ڿ��ļ�ǰ��
		jd.setVisible(true);

	}

	private void myEvent() {

		Submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				System.out.println("111111111111");
				Redit.setParameter(TrName.getText(),
				Integer.parseInt(Tnode1.getText()),
				Integer.parseInt(Tnode2.getText()),
				Integer.parseInt(TxPosition.getText()),
				Integer.parseInt(TyPosition.getText()),
				Double.parseDouble(TrAngle.getText()),
				Double.parseDouble(TrValue.getText())
				);
				System.out.println(Redit);
				jd.setVisible(false);	
			}
		});
	}

	public static void main(String[] args) {
			JFrame frame = new JFrame("test");
			Resistor r = new Resistor("r",1,2,30,10,0,100);
			EditR er = new EditR(frame,r);
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
			frame.setSize(500,300);
			frame.setVisible(true);	
	}
}
