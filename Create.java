package dataBase;

import java.util.ArrayList;

public class Create {

	ArrayList<String> komenda;
	
	public Create(ArrayList<String> komenda)
	{
		this.komenda = komenda;
	}
	
	public void wykonajKomende(ZarzadzanieBazaDanych zarzadzanieBazaDanych) throws NiepoprawnaKomendaDlaBazyDanychException
	{
		if(komenda.size() < 2) 
			throw new NiepoprawnaKomendaDlaBazyDanychException("niepoprawna ilosc polecen komendy");
		if(komenda.get(0).equalsIgnoreCase("database"))
		{
			if(komenda.get(1).charAt(komenda.get(1).length() - 1) != ';')	
				throw new NiepoprawnaKomendaDlaBazyDanychException("blad skladni komendy");
			String nazwaBazyDanych = komenda.get(1).substring(0, komenda.get(1).length() - 1);
			for(int i = 0; i<zarzadzanieBazaDanych.getBazyDanych().size();i++)
			{
				if(zarzadzanieBazaDanych.getBazyDanych().get(i).getNazwa().equals(nazwaBazyDanych))
					throw new NiepoprawnaKomendaDlaBazyDanychException("baza danych o podanej nazwie juz istnieje: " + nazwaBazyDanych);
			}
			zarzadzanieBazaDanych.getBazyDanych().add(new BazaDanych(nazwaBazyDanych));
		}
		else if(komenda.get(0).equalsIgnoreCase("table"))
		{
			if(zarzadzanieBazaDanych.getWybranaBazaDanych() == null) 
				throw new NiepoprawnaKomendaDlaBazyDanychException("najpierw musisz wybrac baze danych");
			String nazwaTabeli = komenda.get(1);
			if(komenda.size() < 3 )
				throw new NiepoprawnaKomendaDlaBazyDanychException("brak nazw kolumn tabeli");
			komenda.remove(0);
			komenda.remove(0);
			PobierzDaneZNawiasow daneZNawiasow = new PobierzDaneZNawiasow(komenda);
			zarzadzanieBazaDanych.getWybranaBazaDanych().stworzTabele(nazwaTabeli, daneZNawiasow.wypiszDane(true), true);
		}
		else throw new NiepoprawnaKomendaDlaBazyDanychException("nieznana komenda: " + komenda.get(0));
	}
}
