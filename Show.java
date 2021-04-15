package dataBase;

import java.util.ArrayList;

public class Show {

	ArrayList<String> komenda;
	
	public Show(ArrayList<String> komenda)
	{
		this.komenda = komenda;
	}
	
	public void wykonajKomende(ZarzadzanieBazaDanych zarzadzanieBazaDanych) throws NiepoprawnaKomendaDlaBazyDanychException
	{
		if(komenda.size() < 1)
			throw new NiepoprawnaKomendaDlaBazyDanychException("brak danych dla komendy");
		if(komenda.get(0).equalsIgnoreCase("databases;"))
		{
			System.out.println("bazy danych:");
			for(int i = 0; i<zarzadzanieBazaDanych.getBazyDanych().size();i++)
			{
				System.out.println(zarzadzanieBazaDanych.getBazyDanych().get(i).getNazwa());
			}
		}
		else if(komenda.get(0).equalsIgnoreCase("tables;"))
		{
			if(zarzadzanieBazaDanych.getWybranaBazaDanych() == null)
				throw new NiepoprawnaKomendaDlaBazyDanychException("nie wybrano zadnej bazy danych");
			System.out.println("tabele:");
			System.out.println(zarzadzanieBazaDanych.getWybranaBazaDanych().getNazwaTabeli());
		}
		else throw new NiepoprawnaKomendaDlaBazyDanychException("bledne dane dla komendy SHOW: " + komenda.get(0));
	}
}
