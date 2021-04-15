package dataBase;

public class Id implements Comparable<Id>{
	private int numerId;
	public Id(int numerId)
	{
		this.numerId = numerId;
	}
	@Override
	public int compareTo(Id o) {
		if(numerId == o.numerId) return 0;
		else if(numerId > o.numerId) return 1;
		else return -1;
	}
}
