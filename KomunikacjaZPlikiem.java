package dataBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class KomunikacjaZPlikiem {

	public void zapiszBazeDanych(ArrayList<BazaDanych> bazyDanych) throws IOException
	{
		File file = new File("bibliotekaDanych.bd");
		file.createNewFile();
		FileWriter writer = new FileWriter(file);
		for(int bazaDanych = 0; bazaDanych<bazyDanych.size(); bazaDanych++)
		{
			writer.write(bazyDanych.get(bazaDanych).getNazwa() + "\n");
			if(bazyDanych.get(bazaDanych).getNazwaTabeli().equals(""))
			{
				writer.write("----------------------------\n");
				continue;
			}
			writer.write(bazyDanych.get(bazaDanych).getNazwaTabeli() + "\n");
			for(int kolumna = 0; kolumna < bazyDanych.get(bazaDanych).getNazwyKolumnTabeli().size(); kolumna++)
			{
				writer.write(bazyDanych.get(bazaDanych).getNazwyKolumnTabeli().get(kolumna) + " ");
			}
			Collection<WierszTabeli> kolekcja = bazyDanych.get(bazaDanych).getTabela().values();
			Iterator<WierszTabeli> itr = kolekcja.iterator();
	    	writer.write("\n");
		    while(itr.hasNext())
		    {
		    	WierszTabeli kolejnyElement = (WierszTabeli) itr.next();
		    	for(int poleWiersza = 0; poleWiersza < kolejnyElement.getPolaTabeli().size(); poleWiersza++)
				{
		    		if(kolejnyElement.getPolaTabeli().get(poleWiersza).equals(""))
		    			writer.write("* ");
		    		else 
		    			writer.write(kolejnyElement.getPolaTabeli().get(poleWiersza) + " ");
				}
		    	writer.write("\n");
		    }
		    writer.write("----------------------------\n");
		}	
		writer.flush();
		writer.close();
	}
	public void wczytajBazeDanych(ArrayList<BazaDanych> bazyDanych) throws IOException, NiepoprawnaKomendaDlaBazyDanychException
	{
		File file = new File("bibliotekaDanych.bd");
		Scanner scanFile = new Scanner(file); 
		int bazaDanych = 0;
		while(scanFile.hasNext())
		{
			String nazwaBazyDanych = scanFile.nextLine();
			bazyDanych.add(new BazaDanych(nazwaBazyDanych));
			if(!scanFile.hasNext())
				break;
			String nazwaTabeli = scanFile.nextLine();
			if(nazwaTabeli.equals("----------------------------"))
				continue;
			String odczytanaLinia = scanFile.nextLine();
			if(odczytanaLinia.equals("----------------------------"))
				continue;
			ArrayList<String> kolumnyTabeli = odczytajWyrazyZLinii(odczytanaLinia);
			bazyDanych.get(bazaDanych).stworzTabele(nazwaTabeli, kolumnyTabeli, false);
			while(scanFile.hasNext())
			{
				odczytanaLinia = scanFile.nextLine();
				if(odczytanaLinia.equals("----------------------------"))
					break;
				ArrayList<String> polaTabeli = odczytajWyrazyZLinii(odczytanaLinia);
				for(int i = 0; i < polaTabeli.size();i++)
				{
					if(polaTabeli.get(i).equals("*"))
						polaTabeli.set(i, "");
				}
				WierszTabeli wierszTabeli = new WierszTabeli(polaTabeli);
				bazyDanych.get(bazaDanych).getTabela().put(wierszTabeli.id, wierszTabeli);
			}
			bazaDanych++;
		}
		scanFile.close();
	}

	private ArrayList<String> odczytajWyrazyZLinii(String liniaTekstu)
	{
		Scanner scanLinii = new Scanner(liniaTekstu);
		ArrayList<String> wyrazy = new ArrayList<String>();
		while(scanLinii.hasNext())
		{
			wyrazy.add(scanLinii.next());
		}
		scanLinii.close();
		return wyrazy;
	}
}
