// Fig. 17.20: FileChooser.java
// Demonstrating JFileChooser.
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FileChooser extends JFrame {
   public  String filename;
   
   // set up GUI
   public FileChooser() {
	 getFileOrDirectory();

   } // end FileChooser constructor

   // allow user to specify file or directory name
   private void getFileOrDirectory() {
      // display file dialog, so user can choose file or directory to open
      JFileChooser fileChooser = new JFileChooser();
	   fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
      fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );

      int result = fileChooser.showOpenDialog(this);

      // if user clicked Cancel button on dialog, return
      if (result == JFileChooser.CANCEL_OPTION )
         System.exit( 1 );

      File name = fileChooser.getSelectedFile(); // get File

      // display error if invalid
      if ((name == null) || (name.getName().equals( "" ))){
         JOptionPane.showMessageDialog(this, "Invalid Name",
            "Invalid Name", JOptionPane.ERROR_MESSAGE );
         System.exit( 1 );
      } // end if
	  
	  filename = name.getPath();
   } // end method getFile

} // end class FileChooser

