package dataBase;

import java.util.ArrayList;
import java.util.Scanner;

public class Insert {

	ArrayList<String> komenda;
	
	public Insert(ArrayList<String> komenda)
	{
		this.komenda = komenda;
	}
	public void wykonajKomende(ZarzadzanieBazaDanych zarzadzanieBazaDanych, Scanner scanTerminala) throws NiepoprawnaKomendaDlaBazyDanychException
	{
		if(zarzadzanieBazaDanych.getWybranaBazaDanych() == null)  
			throw new NiepoprawnaKomendaDlaBazyDanychException("najpierw musisz wybrac baze danych");
		if(zarzadzanieBazaDanych.getWybranaBazaDanych().getNazwaTabeli().equalsIgnoreCase(""))
			throw new NiepoprawnaKomendaDlaBazyDanychException("nie ma zadnej tabeli w bazie danych");
		if(komenda.size() < 2)  
			throw new NiepoprawnaKomendaDlaBazyDanychException("brak danych dla komendy");
		if(!komenda.get(0).equalsIgnoreCase("into"))
			throw new NiepoprawnaKomendaDlaBazyDanychException("brak komendy INTO");
		if(!komenda.get(1).equals(zarzadzanieBazaDanych.getWybranaBazaDanych().getNazwaTabeli()))
			throw new NiepoprawnaKomendaDlaBazyDanychException("zla nazwa tabeli");
		if(komenda.size() == 2)
			pobierzValues(zarzadzanieBazaDanych, scanTerminala, zarzadzanieBazaDanych.getWybranaBazaDanych().getNazwyKolumnTabeli());
		else 
		{
				komenda.remove(0);
				komenda.remove(0);
				PobierzDaneZNawiasow daneZNawiasow = new PobierzDaneZNawiasow(komenda);
				pobierzValues(zarzadzanieBazaDanych, scanTerminala, daneZNawiasow.wypiszDane(false));
		}
	}
	
	private void pobierzValues(ZarzadzanieBazaDanych zarzadzanieBazaDanych, Scanner scanTerminala, ArrayList<String> kolumny) throws NiepoprawnaKomendaDlaBazyDanychException
	{
		String odczytanaLinia = scanTerminala.nextLine();
		Scanner scanWyrazow = new Scanner(odczytanaLinia);
		scanWyrazow.useDelimiter("[\\s,]+");
		ArrayList<String> wartosci = new ArrayList<String>();
		while(scanWyrazow.hasNext())
		{
			wartosci.add(scanWyrazow.next());
		}
		scanWyrazow.close();
		if(wartosci.size() < 2)  
			throw new NiepoprawnaKomendaDlaBazyDanychException("brak danych dla komendy");
		if(!wartosci.get(0).equalsIgnoreCase("values"))
			throw new NiepoprawnaKomendaDlaBazyDanychException("brak komendy VALUES");
		wartosci.remove(0);
		PobierzDaneZNawiasow daneZNawiasow = new PobierzDaneZNawiasow(wartosci);
		zarzadzanieBazaDanych.getWybranaBazaDanych().insert(kolumny, daneZNawiasow.wypiszDane(true));
	}
	
}
