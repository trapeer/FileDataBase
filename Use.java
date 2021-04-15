package dataBase;

import java.util.ArrayList;

public class Use {
	ArrayList<String> komenda;
	public Use(ArrayList<String> komenda)
	{
		this.komenda = komenda;
	}
	public void wykonajKomende(ZarzadzanieBazaDanych zarzadzanieBazaDanych) throws NiepoprawnaKomendaDlaBazyDanychException
	{
		if(komenda.size() < 1)  
			throw new NiepoprawnaKomendaDlaBazyDanychException("brak danych dla komendy");
		if(komenda.get(0).charAt(komenda.get(0).length() - 1) != ';')	
			throw new NiepoprawnaKomendaDlaBazyDanychException("blad skladni komendy");
		String nazwaBazyDanych = komenda.get(0).substring(0, komenda.get(0).length() - 1);
		for(int i = 0; i < zarzadzanieBazaDanych.getBazyDanych().size();i++)
		{
			if(zarzadzanieBazaDanych.getBazyDanych().get(i).getNazwa().equals(nazwaBazyDanych))
			{
				zarzadzanieBazaDanych.setWybranaBazaDanych(zarzadzanieBazaDanych.getBazyDanych().get(i));
				return;
			}
		}
		throw new NiepoprawnaKomendaDlaBazyDanychException("podana baza danych nie istnieje" + nazwaBazyDanych);
	}
}
