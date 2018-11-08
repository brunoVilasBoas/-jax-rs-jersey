package com.javaee.bruno.controllers;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javaee.bruno.objects.Veiculo;
import com.javaee.bruno.service.VeiculoService;
import com.javaee.bruno.service.VeiculoServiceImpl;

@Path("/veiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculosController {
	
	private static final Logger logger = LoggerFactory.getLogger(VeiculosController.class);
	
	private VeiculoService veiculoService;
	
	public VeiculosController() {
		veiculoService = new VeiculoServiceImpl();
	}
	
	//SERVIÇOS GET
	//Retorna todos os Veiculos
	@GET
	public List<Veiculo> getAll() {
		
		logger.info("getAllVeiculos: {}");
		List<Veiculo> veiculos = veiculoService.getAll();
		return veiculos;
	}
	
	//Retorna Veiculos pelo id
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
	
	//SERVIÇOS POST
	@POST
	public Response create(Veiculo veiculo, @Context UriInfo uriInfo) {
		logger.info("create: {}", veiculo);
		Veiculo veiculoSalvo = veiculoService.saveVeiculo(veiculo);
		logger.debug("Veiculo criado com id = ", veiculoSalvo);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(veiculoSalvo.getId().toString());
		return Response.created(builder.build()).entity(veiculoSalvo).build();
		
	}
	
	//SERVIÇOS PUT
	@PUT
    @Path("/{id : \\d+}")
    public Response update(@PathParam("id") Integer id, Veiculo veiculo) {
        logger.info("Veiculo ID: {} ", id, veiculo);
        Veiculo veiculoSalvo = veiculoService.findById(id);
        if (veiculoSalvo == null) {
        	return Response.status(Status.NOT_FOUND).build();
        }
        veiculoSalvo = veiculoService.saveVeiculo(veiculo);
        return Response.ok().entity(veiculoSalvo).build();
    }
    
	//SERVIÇOS DELETE
    @DELETE
    @Path("/{id : \\d+}")
    public Response delete(@PathParam("id") Integer id) {
    	logger.info("Veiculo ID: {} ", id);
    	veiculoService.deleteById(id);
    	return Response.noContent().build();
    }
}
