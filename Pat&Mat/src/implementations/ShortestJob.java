package implementations;

import java.util.ArrayList;
import java.util.List;

public class ShortestJob {
	private SLLQueue<ClientOrder> inputQueue;
	private SLLQueue<ClientOrder> processingQueue;
	List<ClientOrder> terminatedJobs;
    List<ClientOrder> cancelJobs;
	
	public ShortestJob(){
		inputQueue = new SLLQueue<ClientOrder>();
		processingQueue = new SLLQueue<ClientOrder>();
		terminatedJobs = new ArrayList<ClientOrder>();
		cancelJobs = new ArrayList<ClientOrder>();
	}
	
	public void fillingInputQueue(ClientOrder proc){
		inputQueue.enqueue(proc);
	}
	
	public void methodShortestJob(){
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
			
			int count = 0;
			while(!inputQueue.isEmpty() && inputQueue.first().getMomentArrival() == timeUnit){
				count++;
				processingQueue.enqueue(inputQueue.dequeue());
			}
			
			if(count > 1)
				sortJobs(processingQueue);
			timeUnit++;
		}
		
		System.out.println("Pac's approach profit: $" + computingProfit(terminatedJobs));
		System.out.println("Pac's approach number of dissapointed customers: " + cancelJobs.size());
	}
	
	public double computingProfit(List<ClientOrder> list){
		double profit = 0.0;
		for(int i = 0; i < list.size(); i++){
			profit += list.get(i).getCost();
		}
		
		return profit;
	}
	
	public void sortJobs(SLLQueue<ClientOrder> processQueue){
		int n = processQueue.size();
		ClientOrder servingFirst = processQueue.dequeue();
		for(int i = 0; i < n-1; i++){
			ClientOrder x = processQueue.dequeue();
			for(int j = 0; j < n - 2; j++){
				ClientOrder y = processQueue.dequeue();
				if(x.getTimeOrder() > y.getTimeOrder())
					processQueue.enqueue(y);
				else{
					processQueue.enqueue(x);
					x=y;
				}
			}
			processQueue.enqueue(x);
		}
		
		processQueue.enqueue(servingFirst);
		for(int k = 0; k < n-1; k++){
			processQueue.enqueue(processQueue.dequeue());
		}
	}
}
