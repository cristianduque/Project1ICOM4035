package fileReader;

import classes.ClientOrder;
import classes.FCFS;
import classes.LCFS;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;




/* This class reads data from a Comma-Separated Value file(CSV)
 * to determine the parameters to consider in the restaurant such
 * as moment of arrival, customer id
 */

public class CSVReader {
	public static void main(String[] args) {
        String csvFile = "/Users/cristianduquegonzalez/Desktop/Example.csv"; //CAMBIAR
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        FCFS patMethod = new FCFS();
        LCFS matMethod = new LCFS();
        
        
        try {
        		br = new BufferedReader(new FileReader(csvFile));
        		while ((line = br.readLine()) != null) {
        			String[] process = line.split(csvSplitBy);
        			
        			int arTime = Integer.parseInt(process[0]);
        			int custID = Integer.parseInt(process[1]);
        			int timePrep = Integer.parseInt(process[2]);
        			double costOrder = Double.parseDouble(process[3].substring(1));
        			int patience = Integer.parseInt(process[4]);
        			
        			ClientOrder client1 = new ClientOrder(arTime, custID, timePrep, costOrder, patience);
        			ClientOrder client2 = new ClientOrder(arTime, custID, timePrep, costOrder, patience);

        			patMethod.fillingInputQueue(client1);
        			matMethod.fillingInputStack(client2);
        			

        		}
        		
        		//METHOD
        		patMethod.methodFCFS();
        		matMethod.methodLCFS(); 
        		
        		
            	
        	    
        } catch (FileNotFoundException e) {
        		e.printStackTrace();
        } catch (IOException e) {
        		e.printStackTrace();
        } finally {
        		if (br != null) {
        			try {
        				br.close();
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
        		} 
        }
    }
}