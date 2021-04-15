package dataBase;

import java.util.ArrayList;

public class Update {

	ArrayList<String> komenda;
	public Update(ArrayList<String> komenda)
	{
		this.komenda = komenda;
	}
	public void wykonajKomende(ZarzadzanieBazaDanych zarzadzanieBazaDanych) throws NiepoprawnaKomendaDlaBazyDanychException
	{
		if(zarzadzanieBazaDanych.getWybranaBazaDanych() == null)  
			throw new NiepoprawnaKomendaDlaBazyDanychException("najpierw musisz wybrac baze danych");
		if(zarzadzanieBazaDanych.getWybranaBazaDanych().getNazwaTabeli().equalsIgnoreCase(""))
			throw new NiepoprawnaKomendaDlaBazyDanychException("nie ma zadnej tabeli w bazie danych");
		if(komenda.size() < 5)  
			throw new NiepoprawnaKomendaDlaBazyDanychException("brak danych dla komendy");
		if(!komenda.get(0).equals(zarzadzanieBazaDanych.getWybranaBazaDanych().getNazwaTabeli()))
			throw new NiepoprawnaKomendaDlaBazyDanychException("niepoprawna nazwa tabeli");
		komenda.remove(0);
		if(!komenda.get(0).equalsIgnoreCase("SET"))
			throw new NiepoprawnaKomendaDlaBazyDanychException("brak slowa kluczowego SET, albo w zlym miejscu uzyte");
		komenda.remove(0);
		ArrayList<String> kolumny = new ArrayList<String>();
		ArrayList<String> wartosci = new ArrayList<String>();
		for(int i = 0; i<komenda.size();i++)
		{
			if(i%3 == 0) kolumny.add(komenda.get(i));
			if(i%3 == 1)
			{
				if(!komenda.get(i).equals("="))
					throw new NiepoprawnaKomendaDlaBazyDanychException("brak = lub w zlym miejscu uzyte");
			}
			if(i%3 == 2) wartosci.add(komenda.get(i));
		}
		if(wartosci.get(wartosci.size() - 1).charAt(wartosci.get(wartosci.size() - 1).length() - 1) != ';')
			throw new NiepoprawnaKomendaDlaBazyDanychException("brak srednika lub w zlym miejscu uzyty");
		wartosci.set(wartosci.size() - 1, wartosci.get(wartosci.size() - 1).substring(0, wartosci.get(wartosci.size() - 1).length() - 1));
		zarzadzanieBazaDanych.getWybranaBazaDanych().update(kolumny,wartosci);
	}
}
