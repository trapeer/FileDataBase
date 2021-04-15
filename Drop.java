package dataBase;

import java.util.ArrayList;

public class Drop {

	ArrayList<String> komenda;
	
	public Drop(ArrayList<String> komenda)
	{
		this.komenda = komenda;
	}
	
	public void wykonajKomende(ZarzadzanieBazaDanych zarzadzanieBazaDanych) throws NiepoprawnaKomendaDlaBazyDanychException
	{
		if(komenda.size() < 2) 
			throw new NiepoprawnaKomendaDlaBazyDanychException("brak danych do wykonania komendy");
		if(komenda.get(0).equalsIgnoreCase("database"))
		{
			if(komenda.get(1).charAt(komenda.get(1).length() - 1) != ';')	
				throw new NiepoprawnaKomendaDlaBazyDanychException("blad skladni komendy");
			String nazwaBazyDanych = komenda.get(1).substring(0, komenda.get(1).length() - 1);
			for(int i = 0; i<zarzadzanieBazaDanych.getBazyDanych().size();i++)
			{
				if(zarzadzanieBazaDanych.getBazyDanych().get(i).getNazwa().equals(nazwaBazyDanych))
				{
					zarzadzanieBazaDanych.getBazyDanych().remove(i);
					return;
				}
			}
			throw new NiepoprawnaKomendaDlaBazyDanychException("baza danych o podanej nazwie nie istnieje: " + nazwaBazyDanych);
		}
		else if(komenda.get(0).equalsIgnoreCase("table"))
		{
			if(komenda.get(1).charAt(komenda.get(1).length() - 1) != ';')	
				throw new NiepoprawnaKomendaDlaBazyDanychException("blad skladni komendy");
			String nazwaTabeli = komenda.get(1).substring(0, komenda.get(1).length() - 1);
			if(zarzadzanieBazaDanych.getWybranaBazaDanych().getNazwaTabeli().equals(nazwaTabeli))
				zarzadzanieBazaDanych.getWybranaBazaDanych().usunTabele();
			else throw new NiepoprawnaKomendaDlaBazyDanychException("tabela o podanej nazwie nie istnieje: " + nazwaTabeli);
		}
		else throw new NiepoprawnaKomendaDlaBazyDanychException("nieznana komenda: " + komenda.get(0));
	}
}
