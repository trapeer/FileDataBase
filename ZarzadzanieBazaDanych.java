package dataBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ZarzadzanieBazaDanych {
	
	private ArrayList<BazaDanych> bazyDanych = new  ArrayList<BazaDanych>();
	private BazaDanych wybranaBazaDanych;
	
	public static void main(String args[])
	{
		ZarzadzanieBazaDanych zarzadzanieBazaDanych = new ZarzadzanieBazaDanych();
		KomunikacjaZPlikiem komunikacjaZPlikiem = new KomunikacjaZPlikiem();
		try {
			komunikacjaZPlikiem.wczytajBazeDanych(zarzadzanieBazaDanych.getBazyDanych());
		}
		catch(IOException e) {
			System.out.println("nie udalo sie zaladowac bazy danych lub baza danych nie istnieje");
			System.out.println(e.getMessage());
			zarzadzanieBazaDanych.getBazyDanych().clear();
		}
		catch(NiepoprawnaKomendaDlaBazyDanychException e) {
			System.out.println("nie udalo sie wczytac bazy danych z pliku");
			System.out.println(e.getMessage());
			zarzadzanieBazaDanych.getBazyDanych().clear();
		}
		Scanner scanTerminala = new Scanner(System.in);
		Scanner scanWyrazow;
		String odczytanaLinia = "";
		ArrayList<String> komenda;
		while(true)
		{
			komenda = new ArrayList<String>();
			odczytanaLinia = scanTerminala.nextLine();
			odczytanaLinia = odczytanaLinia.replaceAll("=", " = ");
			scanWyrazow = new Scanner(odczytanaLinia);
			scanWyrazow.useDelimiter("[\\s,]+");
			komenda.clear();
			while(scanWyrazow.hasNext())
			{
				komenda.add(scanWyrazow.next());
			}
			scanWyrazow.close();
			if(komenda.size() == 0)
				continue;
			if(komenda.get(0).equalsIgnoreCase("exit"))
			{
				scanTerminala.close();
				break;
			}
			try {
				zarzadzanieBazaDanych.znajdzJakieZadanieZostaloZlecone(komenda, zarzadzanieBazaDanych, scanTerminala);
				komunikacjaZPlikiem.zapiszBazeDanych(zarzadzanieBazaDanych.getBazyDanych());
			}
			catch(NiepoprawnaKomendaDlaBazyDanychException e){
				System.out.println("error: " + e.getMessage());
			}
			catch(IOException e) {
				System.out.println("nie udalo sie zapisac bazy danych do pliku");
			}
		}
	}
	
	private void znajdzJakieZadanieZostaloZlecone(ArrayList<String> komenda, ZarzadzanieBazaDanych zarzadzanieBazaDanych, Scanner scanTerminala) throws NiepoprawnaKomendaDlaBazyDanychException
	{
		if(komenda.get(0).equalsIgnoreCase("create"))
		{
			komenda.remove(0);
			Create create = new Create(komenda);
			create.wykonajKomende(zarzadzanieBazaDanych);
		}
		else if(komenda.get(0).equalsIgnoreCase("delete"))
		{
			komenda.remove(0);
			Delete delete = new Delete(komenda);
			delete.wykonajKomende(zarzadzanieBazaDanych);
		}
		else if(komenda.get(0).equalsIgnoreCase("insert"))
		{
			komenda.remove(0);
			Insert insert = new Insert(komenda);
			insert.wykonajKomende(zarzadzanieBazaDanych, scanTerminala);
		}
		else if(komenda.get(0).equalsIgnoreCase("select"))
		{
			komenda.remove(0);
			Select select = new Select(komenda);
			select.wykonajKomende(zarzadzanieBazaDanych);
		}
		else if(komenda.get(0).equalsIgnoreCase("update"))
		{
			komenda.remove(0);
			Update update = new Update(komenda);
			update.wykonajKomende(zarzadzanieBazaDanych);
		}
		else if(komenda.get(0).equalsIgnoreCase("show"))
		{
			komenda.remove(0);
			Show show = new Show(komenda);
			show.wykonajKomende(zarzadzanieBazaDanych);
		}
		else if(komenda.get(0).equalsIgnoreCase("use"))
		{
			komenda.remove(0);
			Use use = new Use(komenda);
			use.wykonajKomende(zarzadzanieBazaDanych);
		}
		else if(komenda.get(0).equalsIgnoreCase("drop"))
		{
			komenda.remove(0);
			Drop drop = new Drop(komenda);
			drop.wykonajKomende(zarzadzanieBazaDanych);
		}
		else if(komenda.get(0).equalsIgnoreCase("help"))
		{
			zarzadzanieBazaDanych.wyswietlDostepneKomendy();
		}
		else throw new NiepoprawnaKomendaDlaBazyDanychException("nieznana komenda: " + (komenda.get(0)));
	}

	public BazaDanych getWybranaBazaDanych()
	{
		return wybranaBazaDanych;
	}
	public ArrayList<BazaDanych> getBazyDanych()
	{
		return bazyDanych;
	}
	public void setWybranaBazaDanych(BazaDanych bazaDanych)
	{
		wybranaBazaDanych = bazaDanych;
	}
	public void wyswietlDostepneKomendy()
	{
		System.out.println("CREATE DATABASE nazwa_bazy_danych;");
		System.out.println("CREATE TABLE nazwa_tabeli;");
		System.out.println("DELETE FROM nazwa_tabeli;");
		System.out.println("INSERT INTO nazwa_tabeli [(nazwa_kolumny1,nazwa_kolumny2,...)]");
		System.out.println("VALUES (wartosc1,wartosc2,...);");
		System.out.println("SELECT [*] lub [nazwa_kolumny1, nazwa_kolumny2,..] FROM nazwa_tabeli;");
		System.out.println("UPDATE nazwa_tabeli SET nazwa_kolumny1=wartosc1,.., nazwa_kolumnyN=wartoscN;");
		System.out.println("SHOW  [DATABASES;] lub [TABLES;]");
		System.out.println("USE  nazwa_bazy_danych;");
		System.out.println("DROP DATABASE  nazwa_bazy_danych;");
		System.out.println("DROP TABLE nazwa_tabeli;");
		System.out.println("EXIT");
	}
}
