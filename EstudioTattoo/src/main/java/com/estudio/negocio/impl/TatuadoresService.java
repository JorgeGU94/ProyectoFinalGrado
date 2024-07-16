package com.estudio.negocio.impl;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estudio.dao.ITatuadoresDAO;
import com.estudio.dtos.TatuadoresDTO;
import com.estudio.entities.TatuadoresEntity;
import com.estudio.negocio.ITatuadoresService;
import com.estudio.repositories.ITatuadoresRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

@Component
public class TatuadoresService implements ITatuadoresService{
	
	String des = "..\\Registros\\RegistroDeTatuadores.pdf";

	@Autowired
	ITatuadoresDAO tatuadoresDAOImpl;
	
	@Autowired
	private ITatuadoresRepository tatuadoresRepository;
	
	@Override
	public List<TatuadoresDTO> buscarTatuadores(Integer id, String nombre, String apellidos, String email,
			String especialidad, String descripcion, Integer activo) throws ClassNotFoundException, SQLException {
		
		return tatuadoresDAOImpl.buscarTatuadores(id, nombre, apellidos, email, especialidad, descripcion, activo);
		
	}

	@Override
	public Integer insertarTatuador(String nombre, String apellidos, String email, String especialidad, String descripcion, Integer activo)
			throws ClassNotFoundException, SQLException {
		
		Integer inser = tatuadoresDAOImpl.insertarTatuador(nombre, apellidos, email, especialidad, descripcion, activo);
		Iterable<TatuadoresEntity> tatuadores = tatuadoresRepository.findAll();
		
		try {
			generarPdfTatuadores(tatuadores);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return inser;
		
	}

	@Override
	public Integer actualizarTatudor(Integer id, String nombre, String apellidos, String email, String especialidad, String descripcion, 
			Integer activo) throws ClassNotFoundException, SQLException {
		
		Integer actu = tatuadoresDAOImpl.actualizarTatudor(id, nombre, apellidos, email, especialidad, descripcion, activo);
		Iterable<TatuadoresEntity> tatuadores = tatuadoresRepository.findAll();
		
		try {
			generarPdfTatuadores(tatuadores);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return actu;
		
	}

	@Override
	public Integer borrarTatuador(Integer id) throws ClassNotFoundException, SQLException {
		
		return tatuadoresDAOImpl.borrarTatuador(id);
		
	}
	
	private void generarPdfTatuadores(Iterable<TatuadoresEntity> tatuadores) throws FileNotFoundException {
		PdfWriter writer = new PdfWriter(des);
		PdfDocument pdfDoc = new PdfDocument(writer);
		Document document = new Document(pdfDoc);
		
		document.setMargins(20, 20, 20, 20);
		float[] columnWidths = {1,4,10,10,5,30,2};
		Table table = new Table(columnWidths);
		table.setWidth(200);
		
		document.add(new Paragraph("Registro de Tatuadores: "));
		
		table.addHeaderCell(new Cell().add(new Paragraph("Id Tatuajes")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Nombre")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Apellidos")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Email")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Especialidad")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Descripción")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Activo")));
	    
	    for (TatuadoresEntity tatuador : tatuadores) {
	        table.addCell(new Cell().add(new Paragraph(tatuador.getIdTatuadores().toString())));
	        table.addCell(new Cell().add(new Paragraph(tatuador.getNombre())));
	        table.addCell(new Cell().add(new Paragraph(tatuador.getApellidos())));
	        table.addCell(new Cell().add(new Paragraph(tatuador.getEmail())));
	        table.addCell(new Cell().add(new Paragraph(tatuador.getEspecialidad())));
	        table.addCell(new Cell().add(new Paragraph(tatuador.getDescripcion())));

	        String activo = (tatuador.getActivo() == 1) ? "si" : "no";
	        table.addCell(new Cell().add(new Paragraph(activo)));
	    }
	    
	    document.add(table);
	    
		document.close();
	}

}
