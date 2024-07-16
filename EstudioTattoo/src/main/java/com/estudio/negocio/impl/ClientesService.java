package com.estudio.negocio.impl;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estudio.dao.IClientesDAO;
import com.estudio.dtos.ClientesDTO;
import com.estudio.entities.ClientesEntity;
import com.estudio.negocio.IClientesService;
import com.estudio.repositories.IClientesRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

@Component
public class ClientesService implements IClientesService{
	
	String des = "..\\Registros\\RegistroDeClientes.pdf";
	
	@Autowired
	IClientesDAO clientesDAOImpl;
	
	@Autowired
	private IClientesRepository clientesRepository;
	
	
	@Override
	public List<ClientesDTO> buscarClientes(Integer id, String nombre, String apellidos, String telefono, String email,
			String password) throws ClassNotFoundException, SQLException {
		
		return clientesDAOImpl.buscarClientes(id, nombre, apellidos, telefono, email, password);
		
	}

	@Override
	public Integer insertarClientes(String nombre, String apellidos, String telefono, String email, String password)
			throws ClassNotFoundException, SQLException {
		
		Integer inser =  clientesDAOImpl.insertarClientes(nombre, apellidos, telefono, email, password);
		Iterable<ClientesEntity> clientes = clientesRepository.findAll();
		
		try {
			generarPdfClientes(clientes);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return inser;
	}

	@Override
	public Integer actualizarClientes(Integer id, String nombre, String apellidos, String telefono, String email,
			String password) throws ClassNotFoundException, SQLException {

		Integer actua = clientesDAOImpl.actualizarClientes(id, nombre, apellidos, telefono, email, password);
		
		Iterable<ClientesEntity> clientes = clientesRepository.findAll();
		
		try {
			generarPdfClientes(clientes);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return actua;
		
	}
	
	private void generarPdfClientes(Iterable<ClientesEntity> clientes) throws FileNotFoundException {
		PdfWriter writer = new PdfWriter(des);
		PdfDocument pdfDoc = new PdfDocument(writer);
		Document document = new Document(pdfDoc);
		
		document.setMargins(20, 20, 20, 20);
		float[] columnWidths = {1,4,10,2};
		Table table = new Table(columnWidths);
		table.setWidth(200);
		
		document.add(new Paragraph("Registro de Clientes: "));
		
		table.addHeaderCell(new Cell().add(new Paragraph("Id Cliente")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Nombre")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Apellidos")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Teléfono")));
	    table.addHeaderCell(new Cell().add(new Paragraph("Correo")));
	    
	    for (ClientesEntity cliente : clientes) {
	        table.addCell(new Cell().add(new Paragraph(cliente.getIdClientes().toString())));
	        table.addCell(new Cell().add(new Paragraph(cliente.getNombre())));
	        table.addCell(new Cell().add(new Paragraph(cliente.getApellidos() )));
	        table.addCell(new Cell().add(new Paragraph(cliente.getTelefono() )));
	        table.addCell(new Cell().add(new Paragraph(cliente.getEmail() )));
	    }
	    
	    document.add(table);
	    
		document.close();
	}

}
