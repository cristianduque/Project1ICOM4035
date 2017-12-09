package implementations;

import java.util.ArrayList;
import java.util.List;

public class LCFS {
	private SLLStack<ClientOrder> inputStack = new SLLStack<ClientOrder>();
    private SLLStack<ClientOrder> processingStack = new SLLStack<ClientOrder>();
    private SLLStack<ClientOrder> processingStack1 = new SLLStack<ClientOrder>();
    private SLLStack<ClientOrder> processingStack2 = new SLLStack<ClientOrder>();
    private SLLStack<ClientOrder> processingStack3 = new SLLStack<ClientOrder>();
    private List<ClientOrder> terminatedJobs = new ArrayList<ClientOrder>();
    private List<ClientOrder> cancelJobs = new ArrayList<ClientOrder>();
    
    public LCFS(){
    	inputStack = new SLLStack<ClientOrder>();
		processingStack = new SLLStack<ClientOrder>();
		processingStack1 = new SLLStack<ClientOrder>();
		processingStack2 = new SLLStack<ClientOrder>();
		processingStack3 = new SLLStack<ClientOrder>();

		terminatedJobs = new ArrayList<ClientOrder>();
		cancelJobs = new ArrayList<ClientOrder>();
		
    }
    
    public void fillingInputStack(ClientOrder proc){
    	inputStack.push(proc);
    }
    
    public void methodLCFS(){
    	int pL = 0;
		double profit = 0.0;
		while(!inputStack.isEmpty()) {
			processingStack.push(inputStack.pop());
		}
		
		processingStack1.push(processingStack.pop());
		
		while(!processingStack.isEmpty()) {
			if(processingStack.top().getMomentArrival() < processingStack1.top().getTimeOrder()) {
				processingStack2.push(processingStack.pop());
			} else {
				processingStack3.push(processingStack.pop());
			}
		}
		while(!processingStack2.isEmpty()) {
			processingStack1.push(processingStack2.pop());
		}
		while(!processingStack3.isEmpty()) {
			processingStack1.push(processingStack3.pop());
		}
		while(!processingStack1.isEmpty()) {
			processingStack.push(processingStack1.pop());
		}
	    
		while(!processingStack.isEmpty()) {
			if (processingStack.top().getPatienceLevel() > pL) {
				pL = pL + processingStack.top().getTimeOrder();
				profit = profit + processingStack.top().getCost();
				terminatedJobs.add(processingStack.pop());
			} else {
				cancelJobs.add(processingStack.pop());
			}
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
