
public abstract class Cliente {
	private String nome;
	
	public Cliente(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
}
