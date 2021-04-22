
public class Reserva implements Pagamento{
	private Cliente cliente;
	private boolean pagamentoAVista;
	
	public Reserva(Cliente cliente, boolean pagamentoAVista) {
		this.cliente = cliente;
		this.pagamentoAVista = pagamentoAVista;
	}
	
	public double calcularPagamento() {
		double valor = 3200;
		
		if(pagamentoAVista) {
			return valor * 0.9;
		} else {
			return valor;
		}
	}
	
	@Override
	public String toString() {
		String sigla = "";
		if(cliente instanceof PessoaFisica) {
			sigla = "PF";
		} else { //então é uma pessoa jurídica
			sigla = "PJ";
		}
		return sigla+" - "+cliente.toString()
			+" -> Pagamento "+ (pagamentoAVista ? "à vista":"parcelado");
	}
}
