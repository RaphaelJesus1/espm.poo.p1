
public class PessoaJuridica extends Cliente{
	private String cnpj;
	
	public PessoaJuridica(String nome, String cnpj) {
		super(nome);
		this.cnpj = cnpj;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PessoaJuridica) {
			String auxCnpj;
			auxCnpj = ((PessoaJuridica) obj).cnpj;
			if(cnpj.equals(auxCnpj)) {
				return true;
			}
		}
		return false;
	}
}
