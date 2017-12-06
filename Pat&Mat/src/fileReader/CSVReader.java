package fileReader;

import classes.ClientOrder;
import classes.SLLQueue;
import classes.Node;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



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
        SLLQueue<ClientOrder> inputQueue = new SLLQueue<ClientOrder>();
        SLLQueue<ClientOrder> processingQueue = new SLLQueue<ClientOrder>();
        SLLQueue<ClientOrder> temp = new SLLQueue<ClientOrder>();
        List<ClientOrder> terminatedJobs = new ArrayList<ClientOrder>();
        List<ClientOrder> cancelJobs = new ArrayList<ClientOrder>();
        
        try {
        		br = new BufferedReader(new FileReader(csvFile));
        		while ((line = br.readLine()) != null) {
        			String[] process = line.split(csvSplitBy);
        			
        			int arTime = Integer.parseInt(process[0]);
        			int custID = Integer.parseInt(process[1]);
        			int timePrep = Integer.parseInt(process[2]);
        			double costOrder = Double.parseDouble(process[3].substring(1));
        			int patience = Integer.parseInt(process[4]);
        			
        			ClientOrder client = new ClientOrder(arTime, custID, timePrep, costOrder, patience);
        			
        			inputQueue.enqueue(client);
        		}
        		
        		//METHOD
        		int timeUnit = 0;
        		while(!inputQueue.isEmpty() || !processingQueue.isEmpty()){
        			if(!processingQueue.isEmpty()){
        				
        				processingQueue.first().setTimeOrder(processingQueue.first().getTimeOrder() - 1);
        				processingQueue.enqueue(processingQueue.dequeue());
        				
        				int size = processingQueue.size() - 1;
        				for(int j = 0; j < size; j++){
        					processingQueue.first().setPatienceLevel(processingQueue.first().getPatienceLevel() - 1);
        					if(processingQueue.first().getPatienceLevel() == 0)
        						cancelJobs.add(processingQueue.dequeue());
        					else
        						processingQueue.enqueue(processingQueue.dequeue());
        				}
        				
        				if(processingQueue.first().getTimeOrder() == 0){
        					terminatedJobs.add(processingQueue.dequeue());
        				}
    
        				else{
        					processingQueue.enqueue(processingQueue.dequeue());
        				}
        			}
        			
        			while(!inputQueue.isEmpty() && inputQueue.first().getMomentArrival() == timeUnit){
        				processingQueue.enqueue(inputQueue.dequeue());
        				
        			}
        			timeUnit++;
        		}
    
        		System.out.println(cancelJobs.size());
        		
        	    
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