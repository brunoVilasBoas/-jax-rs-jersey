package service;

import java.util.List;

import objects.Veiculo;

public interface VeiculoService {
	
	List<Veiculo> getAll();
	
	Veiculo findById(Integer id);
	
	Veiculo saveVeiculo(Veiculo veiculo);
	
	void deleteById(Integer id);
}
