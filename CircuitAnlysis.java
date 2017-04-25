import Jama.*;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.NumberFormatException;
import java.io.IOException;


class CircuitAnlysis {
	// inneral continer 
	public int nodeNum,Inumber,Vnumber;
	public	ArrayList<Resistor> Rstore = new ArrayList<Resistor>();
	public ArrayList<Icurrent> Cstore = new ArrayList<Icurrent>();
	public ArrayList<Voltage> Vstore = new ArrayList<Voltage>();
	private int Elementlenth;
	private int RVnum;
	public Matrix G = new Matrix(RVnum,RVnum);
	public Matrix ImixV = new Matrix(RVnum,1);
	public Matrix result = new Matrix(RVnum,1);

	// 
	CircuitAnlysis( int node_num, 
					int v_number,
				    int Element_lenth, 
					ArrayList<Resistor> r,
					ArrayList<Icurrent> c,
					ArrayList<Voltage> v
				   ) {
			Vnumber = v_number;
			nodeNum = node_num;
			Elementlenth = Element_lenth;
			Rstore = r;
			Cstore = c;
			Vstore = v;
			RVnum = nodeNum+Vnumber;
	}

	CircuitAnlysis(ReadFile R) {
				Vnumber = R.Vnumber;
				nodeNum = R.nodeNum;
				Elementlenth = R.Elementlenth;
				Rstore = R.R;
				Cstore = R.C;
				Vstore = R.V;
				RVnum = nodeNum+Vnumber;
				
				
		}
		
		CircuitAnlysis(
						ArrayList<Resistor> r,
						ArrayList<Icurrent> c,
						ArrayList<Voltage> v
					) {
				Rstore = r;
				Cstore = c;
				Vstore = v;
				
				
				for(Resistor rr: Rstore) {   
					nodeNum = Math.max(nodeNum,Math.max(rr.node1,rr.node2));
				}
					
				// System.out.println(nodeNum);
				Vnumber = v.size();
				RVnum = nodeNum+Vnumber;	
				
		}	
		
 // build internal matrix 
 private void getMetrixG1() {

		double r_temp[][]=new double[nodeNum][nodeNum];
		for(Resistor RS : Rstore) {
			int indx = RS.node1 -1;
			int indy = RS.node2 -1;
				if(indx > 0)
				{
					r_temp[indx][indx] += (1.0/RS.value);
					if(indy >0)
					{
						r_temp[indx][indy] -= (1.0/RS.value);
					}
				}
				if(indy > 0)
				{
					r_temp[indy][indy] += (1.0/RS.value);
					if(indx >0)
					{
						r_temp[indy][indx] -= (1.0/RS.value);
					}
				}	
		}
		
		G = new Matrix(r_temp);
		Print("G=");
		G.print(8,5);
		
 }// END OF get MeTRIX

 	// build the matrix
	private void getMetrixG() {
		double G_temp[][]=new double[RVnum][RVnum];
		for(Resistor RS : Rstore) {
			double G = 1/RS.value;
			int indx = RS.node1 -1;
            int indy = RS.node2 -1;			 

            if(indx>=0) {
                G_temp[indx][indx]+= G;
                if(indy>=0) 
				{
					G_temp[indx][indy] -= G;
				}
            }

            if(indy>=0) {
                G_temp[indy][indy]+= G;
                if(indx>=0) 
				{
					G_temp[indy][indx] -= G;
				}
            }

			
		}
		
			int n=nodeNum-1;
			for ( Voltage v :Vstore ) {

				int indx=v.node1-1;
				int indy=v.node2-1;

					n++;

				if(indy<0) {
					G_temp[indx][n]=1;
					G_temp[n][indx]=1;
				}

				if(indy>=0) {
					G_temp[indy][n]=-1;
					G_temp[n][indy]=-1;
					if(indx>=0) {
						G_temp[indx][n]=1;
						G_temp[n][indx]=1;
						
					}
			     }
			}

		
		G = new Matrix(G_temp);
		Print("G=");
		G.print(8,5);
		
	}// END OF get MeTRIX





	// build and caculate matrix
	private void getMetrixresult() {
		double result_temp[][]=new double[RVnum][1];
		if (Cstore.isEmpty()) {
			for (int i= 0;i< nodeNum;i++ )
			{		
				result_temp[i][0] = 0;
			}
		} else {
			for (Icurrent ic : Cstore)
			{
				int indxN = ic.node1 -1;
				int indxP = ic.node2 -1;
				if (indxP >= 0) {
					result_temp[indxP][0] += ic.value;
				}
				if (indxN >= 0) {
					result_temp[indxN][0] -= ic.value;
				}
			}
		}

		if(Vstore != null) {
			for(int i = 0;i<Vstore.size();i++)
			{		
				result_temp[nodeNum+i][0]=Vstore.get(i).value;
			}
		}
		
		result = new Matrix(result_temp);
		Print("result=");
		result.print(8,5);
		
	}// END OF get MeTRIXI


	public void GenerateMatricx() {	

		getMetrixG();
		getMetrixresult();
		ImixV= G.solve(result);
		Print("ImixV=");
		ImixV.print(8,3);
	}


	private void Print(String s) {
		System.out.println(s);
	} // END OF PRINT
	
	// for valid class work
	public static void main(String[ ] args) {
		ReadFile rf = new ReadFile("111111111111111.txt");
		CircuitAnlysis CA = new CircuitAnlysis(rf.R,rf.C,rf.V);
		CA.GenerateMatricx();
	}

}


