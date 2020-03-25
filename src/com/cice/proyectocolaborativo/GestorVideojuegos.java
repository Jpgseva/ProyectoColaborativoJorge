package com.cice.proyectocolaborativo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cice.proyectocolaborativo.videojuego.Videojuego;

public class GestorVideojuegos {
	public static void almacenarVideoJuego(Videojuego v) {
		// Crear directorio: 
		File fileCss = new File("C:\\colaborativo\\");
		if (!fileCss.exists()) {
			fileCss.mkdirs();
		}
		// Almacenar videojuego:
		FileOutputStream fos = null;
		try {
			String titulo = v.getTitulo().replace(" ", "_");
			fos = new FileOutputStream("C:\\colaborativo\\"+ titulo +".dat");
			ObjectOutputStream ous = new ObjectOutputStream(fos);
			ous.writeObject(v);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				System.out.println("No se encuentra la ruta");
			}
		}
		
		
	}
	
	public static Videojuego recuperarVideojuego(String titulo) {
		
		//Recuperación e instanciación videojuego:
		
		Videojuego v = new Videojuego();
		FileInputStream fis = null;
		try {
			titulo = titulo.replace(" ", "_");
			fis = new FileInputStream("C:\\colaborativo\\"+ titulo +".dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			v = (Videojuego)ois.readObject();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return v;
	}
	
	public static void crearHtml (Videojuego v) {
		
		String titulo = v.getTitulo().replace(" ", "_");
		File fileHtml = new File("C:\\colaborativo\\"+ titulo +".html");
		File fileCss = new File("C:\\colaborativo\\estilo.css");
		FileWriter writerHtml;
		FileWriter writerCss;
		
		try {
			if (!fileHtml.exists()) {
				fileHtml.createNewFile();
			}
			if (!fileCss.exists()) {
				fileCss.createNewFile();
			}
		
            writerHtml = new FileWriter (fileHtml, false);
            PrintWriter printerHtml = new PrintWriter(writerHtml);
            printerHtml.append("<!DOCTYPE html>\r\n" + 
            		"<html>\r\n" + 
            		"<head>\r\n" + 
            		"<meta charset=\"ISO-8859-1\">\r\n" + 
            		"<link rel=\"stylesheet\" href=\"estilo.css\">\r\n" + 
            		"<title>Consulta Videojuego</title>\r\n" + 
            		"</head>\r\n" + 
            		"<body>\r\n" + 
            		"<div id=\"formulario\">\r\n" + 
            		"	<div><h3 id=\"titulo\">Datos del videojuego</h3></div>\r\n" + 
            		"	<hr id=\"separacion\"/>\r\n" + 
            		"	<form action=\"\" method=\"get\">\r\n" + 
            		"		<p>\r\n" + 
            		"		  <label class=\"encabezados\">Titulo:</label><label class=\"datos\">"+ v.getTitulo() + "</label>\r\n" + 
            		"		</p>\r\n" + 
            		"		<p>\r\n" + 
            		"		  <label class=\"encabezados\">Plataforma:</label><label class=\"datos\">" + v.getPlataforma() + "</label>\r\n" + 
            		"		</p>\r\n" + 
            		"		<p>\r\n" + 
            		"		  <label class=\"encabezados\">Precio:</label><label class=\"datos\">"+ v.getPrecio() + " euros" + "</label>\r\n" + 
            		"		</p>\r\n" + 
            		"	</form>\r\n" + 
            		"</div>\r\n" + 
            		"</body>\r\n" + 
            		"</html>");
            printerHtml.close();
      
            writerCss = new FileWriter(fileCss, false);
            PrintWriter printerCss = new PrintWriter(writerCss);
            printerCss.append("@charset \"ISO-8859-1\";\r\n" + 
            		"\r\n" + 
            		"\r\n" + 
            		"body {\r\n" + 
            		"    \r\n" + 
            		"    background-color: bisque;\r\n" + 
            		"    font-family: 'Lucida Sans';	\r\n" + 
            		"}\r\n" + 
            		"\r\n" + 
            		"\r\n" + 
            		"#formulario {\r\n" + 
            		"\r\n" + 
            		"    display: grid;\r\n" + 
            		"    width: 60%;\r\n" + 
            		"    height: auto;\r\n" + 
            		"    margin-left: auto;\r\n" + 
            		"    margin-right: auto;\r\n" + 
            		"    padding-top: 4%;\r\n" + 
            		"    padding-bottom: 8%;\r\n" + 
            		"    padding-left: 10%;\r\n" + 
            		"    padding-right: 10%;\r\n" + 
            		"    background-color: darksalmon;\r\n" + 
            		"    border-width: 1px;\r\n" + 
            		"    border-color: maroon;\r\n" + 
            		"    border-style: solid;\r\n" + 
            		"    border-radius: 20px;\r\n" + 
            		"    box-shadow: 4px 4px grey;\r\n" + 
            		"	\r\n" + 
            		"}\r\n" + 
            		"\r\n" + 
            		"#titulo {\r\n" + 
            		"\r\n" + 
            		"    text-align: center;\r\n" + 
            		"    font-size: 20px;\r\n" + 
            		"    color: maroon;\r\n" + 
            		"    margin-bottom: 10px;\r\n" + 
            		"}\r\n" + 
            		"\r\n" + 
            		"#separacion{\r\n" + 
            		"    border-width: 2px;\r\n" + 
            		"    border-color: maroon;\r\n" + 
            		"    width: 100%;\r\n" + 
            		"}\r\n" + 
            		"\r\n" + 
            		".encabezados {\r\n" + 
            		"\r\n" + 
            		"    display: inline-block;\r\n" + 
            		"    text-align: right;\r\n" + 
            		"    font-weight: bold;\r\n" + 
            		"    font-size: 22px;\r\n" + 
            		"    color: bisque;\r\n" + 
            		"\r\n" + 
            		"}\r\n" + 
            		"\r\n" + 
            		".datos {\r\n" + 
            		"\r\n" + 
            		"    margin-left: 1em;\r\n" + 
            		"    margin-top: 4%;\r\n" + 
            		"    font-weight: normal;\r\n" + 
            		"    font-size: 22px;\r\n" + 
            		"    color:maroon;\r\n" + 
            		"\r\n" + 
            		"}");
            printerCss.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
	}
	
	public static Map<Integer,String> mostrarTitulosVideojuegos(List<Videojuego>lista) {
		
		int contador=0;
		Map<Integer, String> titulosVideojuegos = new HashMap<Integer, String>();
		
			for (Videojuego videojuego : lista) {
				contador++;
				titulosVideojuegos.put(contador, videojuego.getTitulo());
			
			}		
		return titulosVideojuegos;
	}
	
	public static void guardarXsl(Videojuego v) {
		
		  
        Workbook workbook = new XSSFWorkbook(); 
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Videojuego");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Crear columnas
        Row headerRow = sheet.createRow(0);

        // Crear celdas
        String[] columnas = {"Titulo", "Plataforma", "precio"};
        for(int i = 0; i < 3; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerCellStyle);
        }
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Rellenar celdas
        	Row fila = sheet.createRow(1);
            fila.createCell(0).setCellValue(v.getTitulo());
            fila.createCell(1).setCellValue(v.getPlataforma());
            fila.createCell(2).setCellValue(v.getPrecio());
            
        for(int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }
        String titulo = v.getTitulo().replace(" ", "_");
        FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("C:\\colaborativo\\"+ titulo +".xlsx");
			workbook.write(fileOut);
			fileOut.close();
			 workbook.close();
		} catch (Exception e) {
			System.out.println("Error en el guardado del xlsx");
			e.printStackTrace();
		}
        
        

        // Closing the workbook
       
    	
		
	}

}
