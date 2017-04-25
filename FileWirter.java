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

class  FileWirter extends JFileChooser {

	JFileChooser fc; 
	File F = new File("circuitResult.txt");;
	public String text;

	FileWirter() {
		fc = new JFileChooser(System.getProperty("user.dir"));
		fc.setSelectedFile(F);
	}

	FileWirter(String ss) {
		text = ss;
		fc = new JFileChooser(System.getProperty("user.dir"));
		fc.setSelectedFile(F);
	}

	public void setParameter(String ss) {
		text = ss;
	}

   public void WriteToFile() {
	 int open = fc.showSaveDialog(null);
		if (open == JFileChooser.APPROVE_OPTION) {
			F = fc.getSelectedFile();
			 try(BufferedWriter bw = new BufferedWriter(new FileWriter(F))) {
				bw.write(text);
				bw.close();
			 
			 }
			 catch(IOException e) {
			  	e.printStackTrace();

			 }
		}
		
	}
}


