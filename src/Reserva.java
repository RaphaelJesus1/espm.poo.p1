
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
	
	public Cliente getCliente() {
		return cliente;
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Reserva) {
			Cliente auxCliente = ((Reserva) obj).getCliente();
			if(cliente.equals(auxCliente)) {
				return true;
			}
		}
		return false;
	}
}
