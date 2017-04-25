import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;


class EditV {
	JTextField TrName,Tnode1,Tnode2,TrValue,TrAngle,TyPosition,TxPosition;
	JDialog jd;
	JButton Submit;
	JPanel wordPanel;	
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 300;
	Voltage Vedit = new Voltage("v",1,2,100,100,0,4);

	
	EditV(JFrame j,Voltage v) {
		jd = new JDialog(j,"EditVesistorPanel",true);
        Vedit = v;
		jd.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		jd.setLayout(new BorderLayout());

		wordPanel = new JPanel();

			wordPanel.setLayout(new GridLayout(7, 1));
			
			wordPanel.add(new JLabel("R name:"));
			wordPanel.add(TrName = new JTextField(Vedit.name));

			wordPanel.add(new JLabel("node1 number:"));
			wordPanel.add(Tnode1 = new JTextField(Integer.toString(Vedit.node1)));

			wordPanel.add(new JLabel("node2 number:"));
			wordPanel.add(Tnode2 = new JTextField(Integer.toString(Vedit.node2)));

			wordPanel.add(new JLabel("location x:"));
			wordPanel.add(TxPosition = new JTextField(Integer.toString(Vedit.xloc)));
			wordPanel.add(new JLabel("location y:"));
			wordPanel.add(TyPosition = new JTextField(Integer.toString(Vedit.yloc)));
		
			wordPanel.add(new JLabel("angle:"));
			wordPanel.add(TrAngle = new JTextField(Double.toString(Vedit.orientation_angle)));

			wordPanel.add(new JLabel("value:"));
			wordPanel.add(TrValue = new JTextField(Double.toString(Vedit.value)));
			
		Submit = new JButton("Submit");

		jd.add(wordPanel,BorderLayout.CENTER);
		jd.add(Submit, BorderLayout.SOUTH);
		myEvent();//必须在看的见前面
		jd.setVisible(true);

	}

	private void myEvent() {

		Submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				System.out.println("111111111111");
				Vedit.setParameter(TrName.getText(),
					Integer.parseInt(Tnode1.getText()),
					Integer.parseInt(Tnode2.getText()),
					Integer.parseInt(TxPosition.getText()),
					Integer.parseInt(TyPosition.getText()),
					Double.parseDouble(TrAngle.getText()),
					Double.parseDouble(TrValue.getText())
				);
				System.out.println(Vedit);
				jd.setVisible(false);	
			}
		});
	}

	public static void main(String[] args) {
			JFrame frame = new JFrame("test");
			Voltage v = new Voltage("v",1,2,30,10,0,100);
			EditV er = new EditV(frame,v);
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
			frame.setSize(500,300);
			frame.setVisible(true);	
	}
}
