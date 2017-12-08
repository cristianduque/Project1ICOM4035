package classes;
import java.util.ArrayList;
import java.util.List;

public class MaxProfit {
	private SLLQueue<ClientOrder> inputQueue;
	private SLLQueue<ClientOrder> processingQueue;
	List<ClientOrder> terminatedJobs;
    List<ClientOrder> cancelJobs;
	
	public MaxProfit(){
		inputQueue = new SLLQueue<ClientOrder>();
		processingQueue = new SLLQueue<ClientOrder>();
		terminatedJobs = new ArrayList<ClientOrder>();
		cancelJobs = new ArrayList<ClientOrder>();
	}
	
	public void fillingInputQueue(ClientOrder proc){
		inputQueue.enqueue(proc);
	}
	
	public void methodMaxProfit(){
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
				sortCost(processingQueue);
			timeUnit++;
		}
		
		System.out.println("Max approach profit: $" + computingProfit(terminatedJobs));
		System.out.println("Max approach number of dissapointed customers: " + cancelJobs.size());
	}
	
	public double computingProfit(List<ClientOrder> list){
		double profit = 0.0;
		for(int i = 0; i < list.size(); i++){
			profit += list.get(i).getCost();
		}
		
		return profit;
	}
	
	public void sortCost(SLLQueue<ClientOrder> q){
		int n = q.size();
		for(int i = 0; i < n; i++){
			ClientOrder x = q.dequeue();
			for(int j = 0; j < n - 1; j++){
				ClientOrder y = q.dequeue();
				if(x.getCost() <= y.getCost())
					q.enqueue(y);
				else{
					q.enqueue(x);
					x=y;
				}
			}
			
			q.enqueue(x);
		}
	}
}
