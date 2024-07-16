package com.estudio.negocio.impl;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estudio.dao.ITatuajesDAO;
import com.estudio.dtos.TatuajesDTO;
import com.estudio.entities.TatuajesEntity;
import com.estudio.negocio.ITatuajesService;
import com.estudio.repositories.ITatuajesRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

@Component
public class TatuajesService implements ITatuajesService{

	String des = "..\\Registros\\RegistroDeTatuajes.pdf";
	
	@Autowired
	ITatuajesDAO tatuajesDAOImpl; 
	
	@Autowired
	private ITatuajesRepository tatuajesRepository;
	
	@Override
	public List<TatuajesDTO> buscarTatuajes(Integer idTatuajes, String descripcion, String color, double tamano, double precio)
			throws ClassNotFoundException, SQLException {
		
		return tatuajesDAOImpl.buscarTatuajes(idTatuajes, descripcion, color, tamano, precio);
		
	}

	@Override
	public Integer insertarTatuajes(String descripcion, String color, double tamano, double precio) throws ClassNotFoundException, SQLException {
		
		precio = calcularPrecio(Integer.parseInt(color), tamano);
	
		Integer inser =  tatuajesDAOImpl.insertarTatuajes(descripcion, color, tamano, precio);
		Iterable<TatuajesEntity> tatuajes = tatuajesRepository.findAll();
		
		try {
			generarPdfTatuajes(tatuajes);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return inser;
		
	}

	@Override
	public Integer actualizarTatuajes(Integer idTatuajes, String descripcion, String color, double tamano, double precio)
			throws ClassNotFoundException, SQLException {
		
		precio = calcularPrecio(Integer.parseInt(color), tamano);
		
		Integer actu = tatuajesDAOImpl.actualizarTatuajes(idTatuajes, descripcion, color, tamano, precio);
		Iterable<TatuajesEntity> tatuajes = tatuajesRepository.findAll();
		
		try {
			generarPdfTatuajes(tatuajes);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return actu;
	}

	public double calcularPrecio(Integer color, double tamano) {
		
		double precio = 60.00;
		
		if (color == 1) {
			precio += 15.00;
		}
		
		if (tamano <= 7) {
			precio += 5;
		}else if(tamano >7 && tamano<=15) {
			precio += 15;
		}else if(tamano > 15){
			precio += 30;
		}
		
		return precio;
	}

	private void generarPdfTatuajes(Iterable<TatuajesEntity> tatuajes) throws FileNotFoundException {
		PdfWriter writer = new PdfWriter(des);
		PdfDocument pdfDoc = new PdfDocument(writer);
		Document document = new Document(pdfDoc);
		
		document.setMargins(20, 20, 20, 20);
		float[] columnWidths = {1,15,2,4,4};
		Table table = new Table(columnWidths);
		table.setWidth(200);
		
		document.add(new Paragraph("Registro de Tatuajes: "));
		
		table.addHeaderCell(new Cell().add(new Paragraph("Id Tatuajes")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Descripción")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Color")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Tamaño")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Precio")));
	    
	    for (TatuajesEntity tatuaje : tatuajes) {
	        table.addCell(new Cell().add(new Paragraph(tatuaje.getIdTatuajes().toString())));
	        table.addCell(new Cell().add(new Paragraph(tatuaje.getDescripcion())));
	        
	        String color = (tatuaje.getColor() == 1) ? "si" : "no";
	        table.addCell(new Cell().add(new Paragraph(color)));
	        
	        String tamano = tatuaje.getTamano() + " cm";
	        table.addCell(new Cell().add(new Paragraph(tamano)));
	        
	        String precio = tatuaje.getPrecio() + " €";
	        table.addCell(new Cell().add(new Paragraph(precio)));
	    }
	    
	    document.add(table);
	    
		document.close();
	}
}
