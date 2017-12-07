package classes;

import java.util.ArrayList;
import java.util.List;

public class FCFS {
	private SLLQueue<ClientOrder> inputQueue;
	private SLLQueue<ClientOrder> processingQueue;
	List<ClientOrder> terminatedJobs;
    List<ClientOrder> cancelJobs;
	
	public FCFS(){
		inputQueue = new SLLQueue<ClientOrder>();
		processingQueue = new SLLQueue<ClientOrder>();
		terminatedJobs = new ArrayList<ClientOrder>();
		cancelJobs = new ArrayList<ClientOrder>();
	}
	
	public void fillingInputQueue(ClientOrder proc){
		inputQueue.enqueue(proc);
	}
	
	public void methodFCFS(){
		int timeUnit = 0;
		while(!inputQueue.isEmpty() || !processingQueue.isEmpty()){
			int size = processingQueue.size() - 1;
			if(!processingQueue.isEmpty()){
				
				processingQueue.first().setTimeOrder(processingQueue.first().getTimeOrder() - 1);
				if(processingQueue.first().getTimeOrder() == 0){
					terminatedJobs.add(processingQueue.dequeue());
				}

				else
					processingQueue.enqueue(processingQueue.dequeue());
				
				for(int j = 0; j < size; j++){
					processingQueue.first().setPatienceLevel(processingQueue.first().getPatienceLevel() - 1);
					if(processingQueue.first().getPatienceLevel() == 0)
						cancelJobs.add(processingQueue.dequeue());
					else
						processingQueue.enqueue(processingQueue.dequeue());
				}
				
			}
			
			while(!inputQueue.isEmpty() && inputQueue.first().getMomentArrival() == timeUnit){
				processingQueue.enqueue(inputQueue.dequeue());
				
			}
			timeUnit++;
		}
		
		System.out.println("Pat's approach profit: $" + computingProfit(terminatedJobs));
		System.out.println("Pat's approach number of dissapointed customers: " + cancelJobs.size());
	}
	
	public double computingProfit(List<ClientOrder> list){
		double profit = 0.0;
		for(int i = 0; i < list.size(); i++){
			profit += list.get(i).getCost();
		}
		
		return profit;
	}
}
