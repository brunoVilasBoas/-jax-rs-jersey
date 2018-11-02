package objects;

public class Veiculo {
	
	private Integer id;
	private String nome;
	private Integer ano;
	
	public Veiculo(Integer id, String nome, Integer ano) {
		super();
		this.id = id;
		this.nome = nome;
		this.ano = ano;
	}
	
	public Veiculo() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	
	
}
