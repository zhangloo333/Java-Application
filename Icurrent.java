import javax.swing.*;
import java.awt.*;

public class Icurrent {
	String name;
	int node1;
	int node2;
	int xloc;
	int yloc;
	double orientation_angle;
	double value;

	Icurrent(String name,int ND1, int ND2,int xloc,int yloc,double angle,double value) {
		this.name = name;
		this.value = value;
		this.node1 = ND1;
		this.node2 = ND2;
		this.xloc = xloc;
		this.yloc = yloc;
		this.orientation_angle = angle;
	}


	public void setParameter(String name,int ND1, int ND2,int xloc,int yloc,double angle,double value) {
		this.name = name;
		this.value = value;
		this.node1 = ND1;
		this.node2 = ND2;
		this.xloc = xloc;
		this.yloc = yloc;
		this.orientation_angle = angle;
	}

	public void setParameter(Icurrent i) {
		this.name = i.name;
		this.value = i.value;
		this.node1 = i.node1;
		this.node2 = i.node1;
		this.xloc  = i.xloc;
		this.yloc  = i.yloc;
		this.orientation_angle = i.orientation_angle;
	}

	public void SetLocation(int x,int y) {
		this.xloc=x;
		this.yloc=y;
	}
	
	public void SetLocation(Point p) {
		this.xloc=p.x;
		this.yloc=p.y;
	}
	
	public String toString() // should put public befor String toSting(),otherwise the error will happen {
		//return String.format("%g",value);
		return(name + "\t"+node1+ "\t"+node2 + "\t"+value+"A"+"\tx= "+xloc+"\ty= "+yloc+"\tAngle= "+ orientation_angle);
	}
}