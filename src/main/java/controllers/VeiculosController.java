package controllers;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import objects.Veiculo;
import service.VeiculoService;
import service.VeiculoServiceImpl;

@Path("/Veiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculosController {
	
	private static final Logger logger = LoggerFactory.getLogger(VeiculosController.class);
	private VeiculoService veiculoService;
	
	private VeiculosController() {
		veiculoService = new VeiculoServiceImpl();
	}
	
	//Retorna todos os Veiculos
	@GET
	public List<Veiculo> getAll() {
		
		logger.info("getAllVeiculos: {}");
		List<Veiculo> veiculos = veiculoService.getAll();
		return veiculos;
	}
	
	@GET
	@Path("{id : \\d+}")
	public Response getById(@PathParam("id") Integer id) {
		
		logger.info("getById : {}", id);
		Veiculo veiculo = veiculoService.findById(id);
		
		if(veiculo == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(veiculo).build();
	}
	
	

}
