package dataBase;

import java.util.ArrayList;

public class PobierzDaneZNawiasow {

	ArrayList<String> dane;
	
	public PobierzDaneZNawiasow(ArrayList<String> dane)
	{
		this.dane = dane;
	}
	public ArrayList<String> wypiszDane(boolean czyJestSrednik) throws NiepoprawnaKomendaDlaBazyDanychException //czyJestSrednik = false nie ma œrednika, w innym przypadku po nawiasach powinien byæ œrednik
	{
		if( dane.get(0).charAt(0) != '(') 
			throw new NiepoprawnaKomendaDlaBazyDanychException("blad skladni komendy");
		dane.set(0, dane.get(0).substring(1));
		String ostatniWyraz = dane.get(dane.size() - 1);
		if(czyJestSrednik)
		{
			if(ostatniWyraz.length() < 2) 
				throw new NiepoprawnaKomendaDlaBazyDanychException("blad skladni komendy");
			if(ostatniWyraz.substring(ostatniWyraz.length() - 2, ostatniWyraz.length()).equalsIgnoreCase(");"))
			{
				dane.set(dane.size() - 1, ostatniWyraz.substring(0, ostatniWyraz.length() - 2));
				if(dane.get(0).equalsIgnoreCase("")) 
					dane.remove(0);
				if(dane.size() == 0)
					throw new NiepoprawnaKomendaDlaBazyDanychException("nie ma danych w nawiasach");
				if(dane.get(dane.size() - 1).equalsIgnoreCase(""))
					dane.remove(dane.size() - 1);
				if(dane.size() == 0)
					throw new NiepoprawnaKomendaDlaBazyDanychException("nie ma danych w nawiasach");
				return dane;
			}
			else throw new NiepoprawnaKomendaDlaBazyDanychException("blad skladni komendy");
		}
		else
		{
			if(ostatniWyraz.charAt(ostatniWyraz.length() - 1) == ')')
			{
				dane.set(dane.size() - 1, ostatniWyraz.substring(0, ostatniWyraz.length() - 1));
				if(dane.get(0).equalsIgnoreCase("")) 
					dane.remove(0);
				if(dane.size() == 0)
					throw new NiepoprawnaKomendaDlaBazyDanychException("nie ma danych w nawiasach");
				if(dane.get(dane.size() - 1).equalsIgnoreCase(""))
					dane.remove(dane.size() - 1);
				if(dane.size() == 0)
					throw new NiepoprawnaKomendaDlaBazyDanychException("nie ma danych w nawiasach");
				return dane;
			}
			else throw new NiepoprawnaKomendaDlaBazyDanychException("blad skladni komendy");
		}
	}
}