
public class PessoaFisica extends Cliente{
	private String cpf;
	
	public PessoaFisica(String nome, String cpf) {
		super(nome);
		this.cpf = cpf;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PessoaFisica) {
			String auxCpf;
			auxCpf = ((PessoaFisica) obj).cpf;
			if(cpf.equals(auxCpf)) {
				return true;
			}
		}
		return false;
	}
}
