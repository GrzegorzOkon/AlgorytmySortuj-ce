package algorytmy;

import java.util.Random;
import java.util.Scanner;

public class Main {
	private static Scanner odczyt = new Scanner(System.in);	//s�u�y do wczytywaniea danych od u�ytkownika
	
	public static void main(String[] args) {
		String liczba;
		int wersja, wielko��, l;
		int[] tablica;
		 
		wersja = pobierzWersj�WybranegoAlgorytmu();
		wielko�� = pobierzIlo��Liczb();
		tablica = utw�rzLosowoUzupe�nion�Tablic�(wielko��);

		//posortuj elementy tablicy
		switch (wersja) {
			case 1 : wypiszPosortowan�Zawarto��(sortujPrzezWstawianie(tablica));
					 break;
			case 2 : wypiszPosortowan�Zawarto��(sortujPrzezWybieranie(tablica));
					 break;
			case 3 : wypiszPosortowan�Zawarto��(sortujB�belkowo(tablica));
					 break;
			case 4 : wypiszPosortowan�Zawarto��(sortujMetod�Shella(tablica));
					 break;
			case 5 : wypiszPosortowan�Zawarto��(sortujPrzezKopcowanie(tablica));
					 break;
			case 6 : wypiszPosortowan�Zawarto��(sortujMetod�Szybk�(tablica, 0, tablica.length - 1));
					 break;
		}	
	}
	
	private static int pobierzWersj�WybranegoAlgorytmu() {
		//wy�wietl tytu� i menu wyboru
		System.out.println("\n\nSortowanie");
		System.out.println("#1 Sortowanie przez wstawianie\n");
		System.out.println("#2 Sortowanie przez wybieranie\n");
		System.out.println("#3 Sortowanie b�belkowe\n");
		System.out.println("#4 Sortowanie Shella\n");
		System.out.println("#5 Sortowanie przez kopcowanie\n");
		System.out.println("#6 Sortowanie szybkie\n");
		
		System.out.println("Kt�ym algorytmem chcesz sortowa� ?:");
		String wersja = odczyt.nextLine();
		System.out.println("\n");
		
		return Integer.parseInt(wersja);
	}
	
	private static int pobierzIlo��Liczb() {
		//pobierz od u�ytkownika liczbe element�w do sortowania
		System.out.println("Jak du�o liczb chcesz posortowa� ?:");
		String ileLiczb = odczyt.nextLine();
		System.out.println("\n");
		
		return Integer.parseInt(ileLiczb);
	}
	
	private static int[] utw�rzLosowoUzupe�nion�Tablic�(int wielko��) {
		int[] tablica = new int[wielko��];
		Random generator = new Random();
		
		for(int i = 0; i < wielko��; i++){
			tablica[i] = generator.nextInt(100);
		}
		
		return tablica;
	}
	
	private static int[] sortujPrzezWstawianie(int[] tablica){
		int klucz, j;
	 
		//dla ka�dego elementu tablicy do posortowania, pocz�wszy od drugiego
		for (int i = 1; i < tablica.length; i++) {
			j=i;
			klucz=tablica[i];
			//poszukaj miejsca dla aktualnego elementu
			//szukaj tylko w posortowanej ju� cz�ci tablicy (czyli wsrod elementow o indeksach mniejszych od aktualnego)
			//przesuwaj element w kiedunku poczatku tablicy tak dlugo, a� przed nim jest element wi�kszy i nie znajduje sie na poczatku tablicy
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
	
	private static int[] sortujB�belkowo(int[] tablica) {
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
	
	private static int[] sortujMetod�Shella(int[] tablica) {
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
	
	private static int[] sortujMetod�Szybk�(int[] tablica, int x, int y) {
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
			sortujMetod�Szybk�(tablica, x, j);
		if (i < y)
			sortujMetod�Szybk�(tablica, i, y);
		
		return tablica;
	}
	
	private static int[] sortujPrzezKopcowanie(int[] tablica) {	     
		zbudujKopiec(tablica);
		int rozmiarKopca = tablica.length-1;
		
		for(int i = rozmiarKopca; i > 0; i--) {
			zamie�(tablica, 0, i);
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
			zamie�(tablica,i, max);
		    kopcuj(tablica, max, size);
		}
	}
		 
	public static void zamie�(int[] tablica, int i, int j) {
		int t = tablica[i];
		tablica[i] = tablica[j];
		tablica[j] = t; 
	}
		 
	private static void wypiszPosortowan�Zawarto��(int[] tablica) {
		for (int i = 0 ; i < tablica.length; i++) {
			System.out.println("#"+ (i+1) +" :\t" + tablica[i]);
		}
		
		System.out.println();		
	}
}
