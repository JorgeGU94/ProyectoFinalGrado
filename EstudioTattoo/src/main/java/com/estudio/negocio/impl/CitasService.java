package com.estudio.negocio.impl;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estudio.dao.ICitasDAO;
import com.estudio.dtos.CitasDTO;
import com.estudio.entities.CitasEntity;
import com.estudio.entities.TatuajesEntity;
import com.estudio.negocio.ICitasService;
import com.estudio.negocio.ITatuajesService;
import com.estudio.repositories.ICitasRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

@Component
public class CitasService implements ICitasService{
	
    String des = "..\\Registros\\RegistroDeCitas.pdf";

	@Autowired
	ICitasDAO citasDAOImpl;
	
	@Autowired
	ITatuajesService tatuajesService;
	
	@Autowired
	private ICitasRepository citasRepository;
	
	@Override
	public List<CitasDTO> buscarCitas(Integer id, String fecha, Integer cliente, Integer tatuador, Integer tatuajes,
			Integer activo) throws ClassNotFoundException, SQLException {
		
		return citasDAOImpl.buscarCitas(id, fecha, cliente, tatuador, tatuajes, activo);
	}

	@Override
	public Integer insertarCitas(String fecha, Integer cliente, Integer tatuador, Integer tatuajes, Integer activo)
			throws ClassNotFoundException, SQLException {
		
		Integer inse = citasDAOImpl.insertarCitas(fecha, cliente, tatuador, tatuajes, activo);
		
		Iterable<CitasEntity> citas = citasRepository.findAll();
				
		try {
			generarPdfCitas(citas);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return inse;
	}

	@Override
	public Integer actualizarCitas(Integer id, String fecha, Integer cliente, Integer tatuador, TatuajesEntity tatuajes,
			Integer activo) throws ClassNotFoundException, SQLException {
		
		Integer actua = citasDAOImpl.actualizarCitas(id, fecha, cliente, tatuador, tatuajes.getIdTatuajes(), activo);
		Integer actual = tatuajesService.actualizarTatuajes(tatuajes.getIdTatuajes(), tatuajes.getDescripcion(), tatuajes.getColor().toString(), tatuajes.getTamano(), tatuajes.getPrecio());
		
		Iterable<CitasEntity> citas = citasRepository.findAll();
		
		try {
			generarPdfCitas(citas);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return actua;
	}

	@Override
	public Integer borrarCitas(Integer id) throws ClassNotFoundException, SQLException {
		
		return citasDAOImpl.borrarCitas(id);
	}

	private void generarPdfCitas(Iterable<CitasEntity> citas) throws FileNotFoundException {
		PdfWriter writer = new PdfWriter(des);
		PdfDocument pdfDoc = new PdfDocument(writer);
		Document document = new Document(pdfDoc);
		
		document.setMargins(20, 20, 20, 20);
		float[] columnWidths = {1,5,2,2,10,2,1,2};
		Table table = new Table(columnWidths);
		table.setWidth(200);
		
		document.add(new Paragraph("Registro de citas: "));
		
		table.addHeaderCell(new Cell().add(new Paragraph("Id Cita")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Fecha")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Cliente")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Tatuador")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Descripcion")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Tamaño")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Color")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Precio")));
	    
	    for (CitasEntity cita : citas) {
	        table.addCell(new Cell().add(new Paragraph(cita.getIdCitas().toString())));
	        table.addCell(new Cell().add(new Paragraph(cita.getFecha())));
	        table.addCell(new Cell().add(new Paragraph(cita.getCliente().getNombre())));
	        table.addCell(new Cell().add(new Paragraph(cita.getTatuador().getNombre())));
	        table.addCell(new Cell().add(new Paragraph(cita.getTatuajes().getDescripcion() )));
	        
	        String tamano = "" + cita.getTatuajes().getTamano();
	        table.addCell(new Cell().add(new Paragraph(tamano)));
	        
	        
	        table.addCell(new Cell().add(new Paragraph(cita.getTatuajes().getColor().toString())));
	        
	        String precio = "" + cita.getTatuajes().getPrecio();
	        table.addCell(new Cell().add(new Paragraph(precio))); 
	    }
	    
	    document.add(table);
	    
		document.close();
	}
}
