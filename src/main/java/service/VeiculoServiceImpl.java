package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import objects.Veiculo;

public class VeiculoServiceImpl implements VeiculoService {
	
	private List<Veiculo> veiculos = new ArrayList<>();
	private Integer actalId = 10;
	
	public VeiculoServiceImpl() {
		for(int i = 0; i < 10; i++) {
			Veiculo veiculo = new Veiculo();
			veiculo.setId(i);
			veiculo.setNome("Ferrari " + i);
			veiculo.setAno(2018);
			veiculos.add(veiculo);
		}
	}
	
	@Override
	public List<Veiculo> getAll() {
		return veiculos;
	}
	
	@Override
	public Veiculo findById(Integer id) {
		Optional<Veiculo> veiculoOptional = veiculos.stream()
				.filter(veiculo -> veiculo.getId().equals(id)).findFirst();
		
		return veiculoOptional.orElse(null);
	}
	
	@Override
	public Veiculo saveVeiculo(Veiculo veiculo) {
		if(veiculo.getId() != null) {
			this.deleteById(veiculo.getId());
		} else {
			actalId++;
			veiculo.setId(actalId);
		}
		this.veiculos.add(veiculo);
		return veiculo;
	}

	@Override
	public void deleteById(Integer id) {
		this.veiculos.removeIf(veiculo -> veiculo.getId().equals(id));
	}

}
