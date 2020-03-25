package com.cice.proyectocolaborativo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.cice.proyectocolaborativo.videojuego.Videojuego;

/**
 * 
 * En este ejutable creamos los videojuegos y los ponemos en una lista
 * 
 * 
 * 
 * 
 */

public class Ejecutador extends GestorVideojuegos{
	
	public static void main(String[] args) {
		
		// Crear los vidoejuentos
		
		Videojuego v1 = new Videojuego("Call Of Duty Modern Warfare", "PS4", 59.95);
		Videojuego v2 = new Videojuego("Call Of Duty Black Ops 4", "PS4", 14.95);
		Videojuego v3 = new Videojuego("Call Of Duty WWII", "PS4", 19.95);
		Videojuego v4 = new Videojuego("Call Of Duty Black Ops 3", "PC", 19.95);
		List<Videojuego>listaJuegos = new ArrayList<Videojuego>();
		listaJuegos.add(v1);
		listaJuegos.add(v2);
		listaJuegos.add(v3);
		listaJuegos.add(v4);
		
		// Probar los metodos para generar html
		
		int contador = 0;
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("**************VIDEOJUEGOS DISPONIBLES*****************");
		
		Map<Integer, String> lista = mostrarTitulosVideojuegos(listaJuegos);
		
		for (Integer indice: lista.keySet()) {
			System.out.println(indice + " - " + lista.get(indice));
			}
		
		String tituloElegido ="";
		int numero = 0;
		do {
			System.out.println("*************************************************");
			System.out.println("Selecione el numero del titulo del juego:");
			
	
			try {
			
				numero = Integer.parseInt(entrada.nextLine());
				tituloElegido = lista.get(numero);
				if (numero <= lista.size()) {
				System.out.println("Has seleccionado el juego: " + tituloElegido);
				}
				for (Videojuego videojuego : listaJuegos) {
					if (videojuego.getTitulo().equals(tituloElegido)) {
						almacenarVideoJuego(videojuego);
						System.out.println("El juego se ha almacenado en: \"D:\\colaborativo\\"+ tituloElegido +".dat\"");
					}
				}
			} catch (Exception e) {
				System.out.println("Introduce un numero valido!");
		}
			} while (numero > lista.size());
			while (numero != 9) {
				System.out.println("**************OPCIONES*******************************");
				System.out.println("1 - Guardar datos del videojuego " + tituloElegido + " en html");
				System.out.println("2 - Guardar datos del videojuego " + tituloElegido + " en xsl");
				System.out.println("9 - Para salir");
				numero = Integer.parseInt(entrada.nextLine());
				Videojuego recuperado = recuperarVideojuego(tituloElegido.replace(" ", "_"));
				
				if (numero == 1) {
					crearHtml(recuperado);
				} else if (numero == 2) {
					guardarXsl(recuperado);
					System.out.println("Archivo guardado en C:\\colaborativo\\"+ recuperado.getTitulo() +".xlsx");
				} else if (numero == 9) {
					break;	
				} else {
						System.out.println("Opción incorrecta");
				}
					
			}
				System.out.println("Salida");
	}

}
