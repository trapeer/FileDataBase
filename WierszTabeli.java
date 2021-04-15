package dataBase;

import java.util.ArrayList;

public class WierszTabeli {
	public Id id;
	private static int counter = 0;
	private ArrayList<String> polaTabeli;
	WierszTabeli(ArrayList<String> polaTabeli)
	{
		this.polaTabeli = polaTabeli;
		id = new Id(counter);
		counter++;
	}
	public ArrayList<String> getPolaTabeli()
	{
		return polaTabeli;
	}
}
