package classes;

import java.util.ArrayList;
import java.util.List;

public class LCFS {
	SLLQueue<ClientOrder> inputStack;
	SLLStack<ClientOrder> processingStack;
	SLLStack<ClientOrder> temporaryStack;
	List<ClientOrder> terminatedJobs;
    List<ClientOrder> cancelJobs;
    
    public LCFS(){
    	inputStack = new SLLQueue<ClientOrder>();
		processingStack = new SLLStack<ClientOrder>();
		terminatedJobs = new ArrayList<ClientOrder>();
		cancelJobs = new ArrayList<ClientOrder>();
		temporaryStack = new SLLStack<ClientOrder>();
    }
    
    public void fillingInputStack(ClientOrder proc){
    	inputStack.enqueue(proc);
    }
    
    public void methodLCFS(){
    	int timeUnit = 0;
    	ClientOrder serviceClient = null;
		while(!inputStack.isEmpty() || !processingStack.isEmpty()){
			int size = processingStack.size() - 1;
			if(!processingStack.isEmpty()){
				processingStack.top().setTimeOrder(processingStack.top().getTimeOrder() - 1);
				if(processingStack.top().getTimeOrder() == 0){
					terminatedJobs.add(processingStack.pop());
				}
				else
					serviceClient = processingStack.pop();
				
				for(int j = 0; j < size; j++){
					processingStack.top().setPatienceLevel(processingStack.top().getPatienceLevel() - 1);
					if(processingStack.top().getPatienceLevel() == 0)
						cancelJobs.add(processingStack.pop());
					else
						temporaryStack.push(processingStack.pop());
				}
				
				for(int i = 0; i < size; i++){
					processingStack.push(temporaryStack.pop());
				}
				
				processingStack.push(serviceClient);
			}
			
			while(!inputStack.isEmpty() && inputStack.first().getMomentArrival() == timeUnit){
				if(processingStack.isEmpty())
					processingStack.push(inputStack.dequeue());
				else{
					ClientOrder tempTop = processingStack.pop();
					processingStack.push(inputStack.dequeue());
					processingStack.push(tempTop);
				}
					
			}
			timeUnit++;
		}
		
		System.out.println("Mat's approach profit: $" + computingProfit(terminatedJobs));
		System.out.println("Mat's approach number of dissapointed customers: " + cancelJobs.size());
    }
    
    public float computingProfit(List<ClientOrder> list){
		float profit = 0;
		for(int i = 0; i < list.size(); i++){
			profit += list.get(i).getCost();
		}
		
		return profit;
	}
}
