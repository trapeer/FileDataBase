package dataBase;

import java.util.ArrayList;

public class Select {

	ArrayList<String> komenda;
	
	public Select(ArrayList<String> komenda)
	{
		this.komenda = komenda;
	}
	
	public void wykonajKomende(ZarzadzanieBazaDanych zarzadzanieBazaDanych) throws NiepoprawnaKomendaDlaBazyDanychException
	{
		if(zarzadzanieBazaDanych.getWybranaBazaDanych() == null)  
			throw new NiepoprawnaKomendaDlaBazyDanychException("najpierw musisz wybrac baze danych");
		if(zarzadzanieBazaDanych.getWybranaBazaDanych().getNazwaTabeli().equalsIgnoreCase(""))
			throw new NiepoprawnaKomendaDlaBazyDanychException("nie ma zadnej tabeli w bazie danych");
		if(komenda.size() < 3)  
			throw new NiepoprawnaKomendaDlaBazyDanychException("brak danych dla komendy");
		if(!komenda.get(komenda.size() - 1).equals(zarzadzanieBazaDanych.getWybranaBazaDanych().getNazwaTabeli() + ';'))
			throw new NiepoprawnaKomendaDlaBazyDanychException("zla skladnia");
		komenda.remove(komenda.size() - 1);
		if(!komenda.get(komenda.size() - 1).equalsIgnoreCase("FROM"))
			throw new NiepoprawnaKomendaDlaBazyDanychException("brak slowa kluczowego FROM, albo w zlym miejscu uzyte");
		komenda.remove(komenda.size() - 1);
		ArrayList<String> kolumnyTabeli;
		if(komenda.get(0).equalsIgnoreCase("*"))
		{
			kolumnyTabeli = zarzadzanieBazaDanych.getWybranaBazaDanych().getNazwyKolumnTabeli();
		}
		else
		{
			kolumnyTabeli = komenda;
		}
		zarzadzanieBazaDanych.getWybranaBazaDanych().select(kolumnyTabeli);
	}
}
