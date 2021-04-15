package dataBase;

import java.util.ArrayList;

public class Delete {
	
	ArrayList<String> komenda;
	
	public Delete(ArrayList<String> komenda)
	{
		this.komenda = komenda;
	}
	
	public void wykonajKomende(ZarzadzanieBazaDanych zarzadzanieBazaDanych) throws NiepoprawnaKomendaDlaBazyDanychException
	{
		if(zarzadzanieBazaDanych.getWybranaBazaDanych() == null) 
			throw new NiepoprawnaKomendaDlaBazyDanychException("najpierw musisz wybrac baze danych");
		if(komenda.size() < 2) 
			throw new NiepoprawnaKomendaDlaBazyDanychException("brak danych dla polecenia DELETE");
		if(!komenda.get(0).equalsIgnoreCase("from")) 
			throw new NiepoprawnaKomendaDlaBazyDanychException("brakuje slowa kluczowego FROM");
		if(komenda.get(1).charAt(komenda.get(1).length() - 1) != ';')
			throw new NiepoprawnaKomendaDlaBazyDanychException("blad skladni");
		if(!komenda.get(1).substring(0, komenda.get(1).length() - 1).equals(zarzadzanieBazaDanych.getWybranaBazaDanych().getNazwaTabeli()))
			throw new NiepoprawnaKomendaDlaBazyDanychException("zla nazwa tabeli");
		zarzadzanieBazaDanych.getWybranaBazaDanych().delete();
	}
}
