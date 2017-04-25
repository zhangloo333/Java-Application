import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.awt.geom.*;

class  FileWirte extends JFileChooser {

	public int nodeNum;
	public int Elementlenth;
	public ArrayList<Resistor> R = new ArrayList<Resistor>();
	public ArrayList<Point> P = new ArrayList<Point>();
	
	public ArrayList<Voltage>  V = new ArrayList<Voltage>();
	public ArrayList<Icurrent> I = new ArrayList<Icurrent>();
	public ArrayList<Line1>	L = new ArrayList<Line1>();

	public String FilePath;
	JFileChooser fc; 
	File F = new File("netlist.txt");;


	public void setParameter(ArrayList<Resistor> r1) {
			R = r1;
			initFile();

	}

	public void setParameter(ArrayList<Resistor> r1,ArrayList<Point> p1) {
			R = r1;
			P = p1;
		    initFile();
	}

	public void setParameter(ArrayList<Resistor> r1,ArrayList<Voltage> v1,ArrayList<Icurrent> i1,ArrayList<Line1> l1) {
		R = r1;
		V = v1;
		I = i1;
		L = l1;
		initFile();		
	}

	private void initFile() {
			fc = new JFileChooser(System.getProperty("user.dir"));
			fc.setSelectedFile(F);
	}


   public void WriteToFile() {
	 int open = fc.showSaveDialog(null);
		if (open == JFileChooser.APPROVE_OPTION) {
			F = fc.getSelectedFile();
			 try(BufferedWriter bw = new BufferedWriter(new FileWriter(F));) {
				 for (Resistor r:R) { 
					 bw.write(r.name+"\t"+r.node1+"\t"+r.node2+"\t"+r.xloc+"\t"+r.yloc+"\t"+r.orientation_angle+"\t"+r.value);
					 bw.newLine();
				 }
				
				for (Voltage v : V ) {
					bw.write(v.name+"\t"+v.node1+"\t"+v.node2+"\t"+v.xloc+"\t"+v.yloc+"\t"+v.orientation_angle+"\t"+v.value);
					bw.newLine();
				}

				for (Icurrent i : I ) {
					bw.write(i.name+"\t"+i.node1+"\t"+i.node2+"\t"+i.xloc+"\t"+i.yloc+"\t"+i.orientation_angle+"\t"+i.value);
					bw.newLine();
				}
				
				for (Line1 l : L) {
					bw.write("wire"+"\t"+l.begin.x+"\t"+l.begin.y+"\t"+l.end.x+"\t"+l.end.y);
					bw.newLine();
				}
				
				 for(Point p : P) {
					 bw.write("wire"+"\t"+p.x+"\t"+p.y);
					 bw.newLine();
				 }

				 bw.close();
			 
			 }
			 catch(IOException e) {
			  	e.printStackTrace();
			 }
		}
		
	}

}


