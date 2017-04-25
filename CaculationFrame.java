import Jama.*;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.NumberFormatException;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.filechooser.*;
import java.lang.Math;


class CaculationFrame extends JFrame {
	//build internal continer
	private JFrame win;
	private JPanel buttonPanel;
	private JButton save;
	private JMenuBar mbar;
	private TextArea ta;
	private JMenu fileMenu,HelpMenu;
	private JMenuItem openItem,saveItem,closeItem;
	public  String filename;
	public 	TextArea content;
	public  CircuitAnlysis CA;
	 
	private File file;

	// three way to build class
    CaculationFrame() {
		init();
	}

    CaculationFrame(String fn) {
		filename = fn;
		init();
	}

    CaculationFrame(ArrayList<Resistor> rt, ArrayList<Icurrent> ct, ArrayList<Voltage> vt) {
		init();
		calculateFunction(rt,ct,vt);
	}

	// init class
	public void init()
	{
		win = new JFrame("Circuit Analysis");
		win.setBounds(300,100,600,600);

		content = new TextArea();

		buttonPanel = new JPanel();

		save = new JButton("Save");
		buttonPanel.add(save);
			
		win.add(buttonPanel,BorderLayout.NORTH);
		win.add(content);

		
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myEvent();
		win.setVisible(true);
		
	}

	private void calculateFunction(ArrayList<Resistor> r,ArrayList<Icurrent> c,ArrayList<Voltage> v) {

				CA = new CircuitAnlysis(r,c,v);
				CA.GenerateMatricx();

				for(Resistor rrr: CA.Rstore)
				{
						content.print(new StringBuilder().append(rrr).toString());
						content.print("\n\r");
				}

				for(Icurrent ccc : CA.Cstore)
				{
					content.print(new StringBuilder().append(ccc).toString());
					content.print("\n\r");
				}


				for(Voltage vvv : CA.Vstore)
				{
					content.print(new StringBuilder().append(vvv).toString());
					content.print("\n\r");
				}
				

				content.print("\n\r");


				content.print("G=");
				content.print(CA.G,"%8.4f");
				content.print("\n\r");
				content.print("result=");
				content.print(CA.result,"%8.4f");
				content.print("\n");
				content.print("ImixV=");
				content.print(CA.ImixV,"%8.4f");
				content.print("\n");
				distinguishIorV(content,CA);
	}

	// event lisener 
	private void myEvent() {
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				FileWirter fw = new FileWirter(content.getText());
				fw.WriteToFile();

			}
		
		});
	
	}

	private void distinguishIorV(TextArea c,CircuitAnlysis a) {	
			int num1 = a.nodeNum;
			int num2 = a.Vnumber;
			for(int i=1;i<=num1;i++) {	
				c.print("V("+String.valueOf(i)+")=\t"+ String.valueOf(a.ImixV.get(i-1,0)) +"\n");
			}
			
			for(int i=1;i<=num2;i++) {
				c.print("I("+String.valueOf(i)+")=\t"+ String.valueOf(a.ImixV.get(num1+i-1,0))+ "\n");
			}
		
	}

	// valid class work part
	public static void main(String[] args) {
		CaculationFrame mf = new CaculationFrame();
		ReadFile rf = new ReadFile("111111111111111.txt");
//		CircuitAnlysis CA = new CircuitAnlysis(rf.R,rf.C,rf.V);
		mf.calculateFunction(rf.R,rf.C,rf.V);
	}
}  

