package dataBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

public class BazaDanych {

	private String nazwa;
	private String nazwaTabeli;
	private ArrayList<String> nazwyKolumnTabeli;
	private TreeMap<Id, WierszTabeli> tabela;
	
	public BazaDanych(String nazwa)
	{
		this.nazwa = nazwa;
		nazwaTabeli = "";
		nazwyKolumnTabeli = new ArrayList<String>();
		tabela = new TreeMap<Id, WierszTabeli>();
	}
	public TreeMap<Id, WierszTabeli> getTabela()
	{
		return tabela;
	}
	public String getNazwa()
	{
		return nazwa;
	}
	public ArrayList<String> getNazwyKolumnTabeli()
	{
		return nazwyKolumnTabeli;
	}
	public void setNazwaTabeli(String nazwaTabeli)
	{
		this.nazwaTabeli = nazwaTabeli;
	}
	public String getNazwaTabeli()
	{
		return  nazwaTabeli;
	}
	public void delete()
	{
		tabela.clear();
	}
	public void stworzTabele(String nazwaTabeli, ArrayList<String> kolumnyTabeli, boolean wyswietlInformacje) throws NiepoprawnaKomendaDlaBazyDanychException
	{
		if(!this.nazwaTabeli.equals(""))
			throw new NiepoprawnaKomendaDlaBazyDanychException("tabela w tej bazie danych juz istnieje");
		if(kolumnyTabeli.size() == 0)
			throw new NiepoprawnaKomendaDlaBazyDanychException("nie podano zadnej nazwy kolumny");
		this.nazwaTabeli = nazwaTabeli;
		nazwyKolumnTabeli = kolumnyTabeli;
		if(wyswietlInformacje)
			System.out.println("stworzono tabele o kolumnach " + nazwyKolumnTabeli);
	}
	public void usunTabele()
	{
		System.out.println("usunieto tabele o nazwie: " + nazwaTabeli);
		this.nazwaTabeli = "";
		nazwyKolumnTabeli.clear();
		tabela.clear();
	}
	public void insert(ArrayList<String> kolumny, ArrayList<String> wartosci) throws NiepoprawnaKomendaDlaBazyDanychException
	{
		if(kolumny.size() != wartosci.size())
			throw new NiepoprawnaKomendaDlaBazyDanychException("podane kolumny nie zgadzaja sie z podanymi wartosciami");
		ArrayList<String> daneDoWpisania= new ArrayList<String>();
		for(int i = 0; i < nazwyKolumnTabeli.size(); i++)
		{
			daneDoWpisania.add("");
		}
		for(int i = 0; i < kolumny.size(); i++)
		{
			int index = nazwyKolumnTabeli.lastIndexOf(kolumny.get(i));
			if(index == -1)
				throw new NiepoprawnaKomendaDlaBazyDanychException("nie znaleziono kolumny " + kolumny.get(i));
			daneDoWpisania.set(index, wartosci.get(i));
		}
		WierszTabeli wierszTabeli = new WierszTabeli(daneDoWpisania);
		tabela.put(wierszTabeli.id, wierszTabeli);
		System.out.println("dodano nowa kolumne do tabeli " + daneDoWpisania);
	}
	public void select(ArrayList<String> kolumnyTabeli) throws NiepoprawnaKomendaDlaBazyDanychException
	{
		ArrayList<Integer> indexyKolumn = new ArrayList<Integer>();
		for(int i = 0; i < kolumnyTabeli.size(); i++)
		{
			int index = nazwyKolumnTabeli.lastIndexOf(kolumnyTabeli.get(i));
			if(index == -1)
				throw new NiepoprawnaKomendaDlaBazyDanychException("nie znaleziono kolumny " + kolumnyTabeli.get(i));
			indexyKolumn.add(index);
		}
		for(int i = 0; i < kolumnyTabeli.size(); i++)
		{
			System.out.printf("%15s",kolumnyTabeli.get(i));
		}
		System.out.printf("\n");
		Collection<WierszTabeli> kolekcja = tabela.values();
		Iterator<WierszTabeli> itr = kolekcja.iterator();
	    while(itr.hasNext())
	    {
	    	WierszTabeli kolejnyElement = (WierszTabeli) itr.next();
	    	for(int i = 0; i < indexyKolumn.size(); i++)
			{
				System.out.printf("%15s",kolejnyElement.getPolaTabeli().get(indexyKolumn.get(i)));
			}
	    	System.out.printf("\n");
	    }
	}
	public void update(ArrayList<String> kolumnyTabeli, ArrayList<String> wartosciTabeli) throws NiepoprawnaKomendaDlaBazyDanychException
	{
		if(kolumnyTabeli.size() != wartosciTabeli.size())
			throw new NiepoprawnaKomendaDlaBazyDanychException("podane kolumny nie zgadzaja sie z podanymi wartosciami");
		ArrayList<Integer> indexyKolumn = new ArrayList<Integer>();
		for(int i = 0; i < kolumnyTabeli.size(); i++)
		{
			int index = nazwyKolumnTabeli.lastIndexOf(kolumnyTabeli.get(i));
			if(index == -1)
				throw new NiepoprawnaKomendaDlaBazyDanychException("nie znaleziono kolumny " + kolumnyTabeli.get(i));
			indexyKolumn.add(index);
		}
		Collection<WierszTabeli> kolekcja = tabela.values();
		Iterator<WierszTabeli> itr = kolekcja.iterator();
	    while(itr.hasNext())
	    {
	    	WierszTabeli kolejnyElement = (WierszTabeli) itr.next();
	    	for(int i = 0; i < indexyKolumn.size(); i++)
			{
	    		kolejnyElement.getPolaTabeli().set(indexyKolumn.get(i), wartosciTabeli.get(i));
			}
	    }
	}
}
