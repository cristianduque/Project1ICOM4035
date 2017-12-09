package fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import implementations.ClientOrder;
import implementations.FCFS;
import implementations.LCFS;
import implementations.MaxProfit;
import implementations.ShortestJob;

/* This class reads data from a Comma-Separated Value file(CSV)
 * to determine the parameters to consider in the restaurant such
 * as moment of arrival, customer identification, time to prepare order,
 * cost, and level of patience the customer is waiting for the order
 * 
 * @author Cristian G. Duque Gonzalez
 * @author Rafael Cruz Candelario
 */

public class CSVReader {
	public static void main(String[] args) {
        String csvFile = "/Users/cristianduquegonzalez/Desktop/Example.csv"; 
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        
        //Creating the four instances to execute the methods
        FCFS patMethod = new FCFS();
        LCFS matMethod = new LCFS();
        MaxProfit maxMethod = new MaxProfit();
        ShortestJob pacMethod = new ShortestJob();
        
        
        try {
        		br = new BufferedReader(new FileReader(csvFile));
        		while ((line = br.readLine()) != null) {
        			String[] process = line.split(csvSplitBy);
        			
        			int arTime = Integer.parseInt(process[0]);
        			int custID = Integer.parseInt(process[1]);
        			int timePrep = Integer.parseInt(process[2]);
        			double costOrder = Double.parseDouble(process[3].substring(1));
        			int patience = Integer.parseInt(process[4]);
        			
        			ClientOrder clientPat = new ClientOrder(arTime, custID, timePrep, costOrder, patience);
        			ClientOrder clientMat = new ClientOrder(arTime, custID, timePrep, costOrder, patience);
        			ClientOrder clientMax = new ClientOrder(arTime, custID, timePrep, costOrder, patience);
        			ClientOrder clientPac = new ClientOrder(arTime, custID, timePrep, costOrder, patience);

        			//FILLING THE INPUT INTO THE LISTS OF EACH MEHTOD
        			patMethod.fillingInputQueue(clientPat);
        			matMethod.fillingInputStack(clientMat);
        			maxMethod.fillingInputQueue(clientMax);
        			pacMethod.fillingInputQueue(clientPac);
        		
        		}
        		
        		//METHODS EXECUTING
        		patMethod.methodFCFS();
        		matMethod.methodLCFS(); 
        		maxMethod.methodMaxProfit();
        		pacMethod.methodShortestJob();
        		
        	    
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