package algorytmy;

import java.util.Random;
import java.util.Scanner;

public class Main {
	private static Scanner odczyt = new Scanner(System.in);	//s³u¿y do wczytywaniea danych od u¿ytkownika
	
	public static void main(String[] args) {
		String liczba;
		int wersja, wielkoœæ, l;
		int[] tablica;
		 
		wersja = pobierzWersjêWybranegoAlgorytmu();
		wielkoœæ = pobierzIloœæLiczb();
		tablica = utwórzLosowoUzupe³nion¹Tablicê(wielkoœæ);

		//posortuj elementy tablicy
		switch (wersja) {
			case 1 : wypiszPosortowan¹Zawartoœæ(sortujPrzezWstawianie(tablica));
					 break;
			case 2 : wypiszPosortowan¹Zawartoœæ(sortujPrzezWybieranie(tablica));
					 break;
			case 3 : wypiszPosortowan¹Zawartoœæ(sortujB¹belkowo(tablica));
					 break;
			case 4 : wypiszPosortowan¹Zawartoœæ(sortujMetod¹Shella(tablica));
					 break;
			case 5 : wypiszPosortowan¹Zawartoœæ(sortujPrzezKopcowanie(tablica));
					 break;
			case 6 : wypiszPosortowan¹Zawartoœæ(sortujMetod¹Szybk¹(tablica, 0, tablica.length - 1));
					 break;
		}	
	}
	
	private static int pobierzWersjêWybranegoAlgorytmu() {
		//wyœwietl tytu³ i menu wyboru
		System.out.println("\n\nSortowanie");
		System.out.println("#1 Sortowanie przez wstawianie\n");
		System.out.println("#2 Sortowanie przez wybieranie\n");
		System.out.println("#3 Sortowanie b¹belkowe\n");
		System.out.println("#4 Sortowanie Shella\n");
		System.out.println("#5 Sortowanie przez kopcowanie\n");
		System.out.println("#6 Sortowanie szybkie\n");
		
		System.out.println("Któym algorytmem chcesz sortowaæ ?:");
		String wersja = odczyt.nextLine();
		System.out.println("\n");
		
		return Integer.parseInt(wersja);
	}
	
	private static int pobierzIloœæLiczb() {
		//pobierz od u¿ytkownika liczbe elementów do sortowania
		System.out.println("Jak du¿o liczb chcesz posortowaæ ?:");
		String ileLiczb = odczyt.nextLine();
		System.out.println("\n");
		
		return Integer.parseInt(ileLiczb);
	}
	
	private static int[] utwórzLosowoUzupe³nion¹Tablicê(int wielkoœæ) {
		int[] tablica = new int[wielkoœæ];
		Random generator = new Random();
		
		for(int i = 0; i < wielkoœæ; i++){
			tablica[i] = generator.nextInt(100);
		}
		
		return tablica;
	}
	
	private static int[] sortujPrzezWstawianie(int[] tablica){
		int klucz, j;
	 
		//dla ka¿dego elementu tablicy do posortowania, pocz¹wszy od drugiego
		for (int i = 1; i < tablica.length; i++) {
			j=i;
			klucz=tablica[i];
			//poszukaj miejsca dla aktualnego elementu
			//szukaj tylko w posortowanej ju¿ czêœci tablicy (czyli wsrod elementow o indeksach mniejszych od aktualnego)
			//przesuwaj element w kiedunku poczatku tablicy tak dlugo, a¿ przed nim jest element wiêkszy i nie znajduje sie na poczatku tablicy
			while (j > 0 && tablica[j-1] > klucz) {
				tablica[j] = tablica[j-1];
				j--;
			}
			
			tablica[j] = klucz;
		}
		
		return tablica;
	}
	
	private static int[] sortujPrzezWybieranie(int[] tablica) {
		int min, i, j, temp;
		 
		for (i = 0; i < tablica.length - 1; i++)  {
			min = i;
			
			for (j = i + 1; j < tablica.length; j++)
				if (tablica[j] < tablica[min])
					min = j;
			
			temp = tablica[min];
			tablica[min] = tablica[i];
			tablica[i] = temp;
		}
		
		return tablica;
	}
	
	private static int[] sortujB¹belkowo(int[] tablica) {
		int temp, i ,zmiana;
		
		do {
			zmiana = 0;
			i = tablica.length - 1;
		
			do {
				i--;
				if (tablica[i + 1] < tablica[i]) {
					temp = tablica[i];            
					tablica[i] = tablica[i + 1];
					tablica[i + 1] = temp;
					zmiana = 1;
				}
			} while (i != 0);
		} while (zmiana != 0);
		
		return tablica;
	}
	
	private static int[] sortujMetod¹Shella(int[] tablica) {
		int inner, outer, temp;
		int h = 1;
		
		while (h <= tablica.length / 3) {
			h = h * 3 + 1;
		}
		
		while (h > 0) {
			for (outer = h; outer < tablica.length; outer++) {
				temp = tablica[outer];
			    inner = outer;
			 
			    while (inner > h - 1 && tablica[inner - h] >= temp) {
			    	tablica[inner] = tablica[inner - h];
			        inner -= h;
			    }
			    
			    tablica[inner] = temp;
			}
			
			h = (h - 1) / 3;
		}
		
		return tablica;
	}
	
	private static int[] sortujMetod¹Szybk¹(int[] tablica, int x, int y) {
		int i, j, v, temp;
		 
		i = x;
		j = y;
		v = tablica[(x+y) / 2];
		
		do {
			while (tablica[i] < v)
				i++;
			
			while (v < tablica[j])
				j--;
			
			if (i <= j) {
				temp = tablica[i];
				tablica[i] = tablica[j];
				tablica[j] = temp;
				i++;
				j--;
			}
		} while (i <= j);
		
		if (x < j)
			sortujMetod¹Szybk¹(tablica, x, j);
		if (i < y)
			sortujMetod¹Szybk¹(tablica, i, y);
		
		return tablica;
	}
	
	private static int[] sortujPrzezKopcowanie(int[] tablica) {	     
		zbudujKopiec(tablica);
		int rozmiarKopca = tablica.length-1;
		
		for(int i = rozmiarKopca; i > 0; i--) {
			zamieñ(tablica, 0, i);
		    rozmiarKopca = rozmiarKopca - 1;
		    kopcuj(tablica, 0, rozmiarKopca);
		}
		      
		return tablica;
	}
	
	public static void zbudujKopiec(int[] tablica) {
		for(int i = (tablica.length - 1) / 2; i >= 0; i--){
			kopcuj(tablica, i, tablica.length-1);
		}
	}
		 
	public static void kopcuj(int[] tablica, int i, int size) { 
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int max;
		
		if (left <= size && tablica[left] > tablica[i]){
			max = left;
		} else {
		    max = i;
		}
		 
		if (right <= size && tablica[right] > tablica[max]) {
			max=right;
		}

		if (max != i) {
			zamieñ(tablica,i, max);
		    kopcuj(tablica, max, size);
		}
	}
		 
	public static void zamieñ(int[] tablica, int i, int j) {
		int t = tablica[i];
		tablica[i] = tablica[j];
		tablica[j] = t; 
	}
		 
	private static void wypiszPosortowan¹Zawartoœæ(int[] tablica) {
		for (int i = 0 ; i < tablica.length; i++) {
			System.out.println("#"+ (i+1) +" :\t" + tablica[i]);
		}
		
		System.out.println();		
	}
}
