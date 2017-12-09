package implementations;

public class ClientOrder {
	private int momentArrival;
	private int customerID;
	private int timeOrder;
	private double cost;
	private int patienceLevel;

	public ClientOrder(){
		momentArrival = 0;
		customerID = 0;
		timeOrder = 0;
		cost = 0.0;
		patienceLevel = 0;
	}
	
	public ClientOrder(int momentArrival, int customerID, int timeOrder, double cost, int patienceLevel){
		this.momentArrival = momentArrival;
		this.customerID = customerID;
		this.timeOrder = timeOrder;
		this.cost = cost;
		this.patienceLevel = patienceLevel;
	}
	public int getMomentArrival() {
		return momentArrival;
	}
	public void setMomentArrival(int momentArrival) {
		this.momentArrival = momentArrival;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getTimeOrder() {
		return timeOrder;
	}
	public void setTimeOrder(int timeOrder) {
		this.timeOrder = timeOrder;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getPatienceLevel() {
		return patienceLevel;
	}
	public void setPatienceLevel(int patienceLevel) {
		this.patienceLevel = patienceLevel;
	}
}
