import java.awt.*;
import javax.swing.*;
import java.util.*;

class IcurrentDraw extends JPanel {

	public int Icenter_x;
	public int Icenter_y;
	public double angle = 0;
	public int width = 20;
	public int hight= 20;
	public String S = "I";
	private	ArrayList<Icurrent> Istore = new ArrayList<Icurrent>();
	int x,y;

	IcurrentDraw(ArrayList<Icurrent> i) {
		Istore = i;
	}

	IcurrentDraw(Point p) {
		Icenter_x = p.x;
		Icenter_y = p.y;	
	}

	IcurrentDraw(int xx, int yy) {
		Icenter_x = xx;
		Icenter_y = yy;	
	}


	public void setParameter(int x, int y, double angle,String s) {
		Icenter_x = x;
		Icenter_y = y;	
		this.angle = angle;
		this.S =s;
	}


	public void drawICU(Graphics g) {
		Graphics2D g2d = ( Graphics2D ) g;

		int x_topl = Icenter_x -width;
		int y_topl = Icenter_y -hight;
		
		Font orignal = g.getFont();
		g2d.setFont(new Font("Tahoma", Font.BOLD, 20));
		g2d.drawString(S,Icenter_x-2*width,Icenter_y);
		g2d.setFont(orignal);

		g2d.rotate( Math.toRadians(angle),Icenter_x,Icenter_y); // rotate coordinate system



      	g2d.drawArc(x_topl,y_topl,40,40,0,360);
		g2d.drawLine(x_topl+20,y_topl+10,x_topl+20,y_topl+30);	
		int [ ] x = {x_topl+15,x_topl+20,x_topl+25};
		int [ ] y = {y_topl+15, y_topl+10,y_topl+15};
		g2d.drawPolyline(x, y, 3);

		g2d.drawLine(x_topl+20,y_topl,x_topl+20,y_topl-15);
		g2d.drawLine(x_topl+20,y_topl+40,x_topl+20,y_topl+55);

		
		g2d.rotate( Math.toRadians(-angle),Icenter_x,Icenter_y); // rotate coordinate system

	}

	public void drawall(Graphics g) {
		for(Icurrent ic: Istore) {
			setParameter(ic.xloc,ic.yloc,ic.orientation_angle,ic.name);
			drawICU(g); 
		}
		
	}


   public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawICU(g);
   
	}


	public static void main(String[] args) {
		System.out.println("Hello World!");
		JFrame frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		frame.setVisible(true);

		IcurrentDraw ic= new IcurrentDraw(100,100);
		frame.add(ic);
	}
}
