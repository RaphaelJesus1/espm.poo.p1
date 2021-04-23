import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner input;
	
	public static void main(String[] args) {
		input = new Scanner(System.in);
		List<Reserva> lista = new ArrayList<>();
		int opcao = 0;
		boolean running = true; //false para testes
		
		/* TESTES */
		
		while(running) {
			System.out.print(menu());
			System.out.print("> ");
			
			// Validação da opção do menu inicial
			try {
				opcao = input.nextInt();
				if(opcao <= 0 || opcao > 7) {
					throw new Exception();
				}
			} catch(InputMismatchException e) {
				System.out.println("Opção inválida. Apenas valores numéricos são reconhecidos.\n");
				opcao = 0;
			} catch(Exception e) {
				System.out.println("Opção inválida. As operações disponíveis estão entre 1 e 6.\n");
				opcao = 0;
			} finally {
				input.nextLine();
				System.out.println();
			}
			
			try {
				switch(opcao) {
				case 1:
					// reservar
					Reserva reserva = reservar(lista);
					if(reserva == null) {
						System.out.println("O cliente já foi cadastrado.\n");
						break;
					}
					
					lista.add(reserva);
					System.out.println("Reserva efetuada com sucesso.");
					
					if(lista.indexOf(reserva) <= 5) {
						System.out.println("Sua mesa está reservada.");
					} else {
						System.out.println("Você está no "+ (lista.indexOf(reserva)-5)+"° lugar da fila de espera.");
					}
					break;
				case 2:
					// pesquisar
					pesquisar(lista);
					break;
				case 3:
					// imprimir reservas
					printReserva(lista);
					break;
				case 4:
					// imprimir lista de espera
					printEspera(lista);
					break;
				case 5:
					// cancelar reserva
					int index = indexPfOuPj(lista);
					if(index != -1) {
						lista.remove(index);
						System.out.println("Reserva excluída com sucesso.");
					} else {
						System.out.println("Reserva não encontrada.");
					}
					break;
				case 6:
					//finalizar
					running = false;
				}
			}catch (Exception e) {
				System.out.println("Opção inválida. Erro: "+ e.getMessage());
			}
		
		System.out.println();
		}
		
		input.close();
	}
	
	public static void printEspera(List<Reserva> lista) {
		if(lista.size() > 6) {
			int tamanho = lista.size() - 6; // ignorar os primeiros 6 elementos(pois já têm mesa).
			System.out.println("| Lista de espera |");
			for(int i = 0; i < tamanho; i++) {
				System.out.println((i+1) + "° " + lista.get(i+6));
			}
		} else {
			System.out.println("Não existe fila de espera.");
		}
	}
	
	public static void printReserva(List<Reserva> lista) {
		if(lista.size() != 0) {
			int tamanho = lista.size() > 6 ? 6:lista.size(); // para limitar os objetos impressos. imprimirá até, no máximo, o 6°.
			System.out.println("| Lista de clientes com mesa |");
			for(int i = 0; i < tamanho; i++) {
				System.out.println((i+1) + "° " + lista.get(i));
			}
		} else {
			System.out.println("Não existem reservas registradas.");
		}
	}
	
	private static int indexPfOuPj(List<Reserva> lista) throws Exception{
		int opcao;
		int index = -1;
		System.out.println("1. Pessoa física \n2. Pessoa jurídica");
		opcao = input.nextInt();
		input.nextLine();
		
		switch (opcao) {
		case 1:
			String cpf;
			System.out.print("CPF: ");
			cpf = input.nextLine().trim();
			index = pesquisarIndexPorCpf(cpf, lista);
			break;
		case 2:
			String cnpj;
			System.out.print("CNPJ: ");
			cnpj = input.nextLine().trim();
			index = pesquisarIndexPorCnpj(cnpj, lista);
			break;
		default:
			throw new Exception("o valor deve ser 1 para Pessoa Física ou 2 para Pessoa Jurídica.");
		}
		return index;
	}
	
	public static void pesquisar(List<Reserva> lista) throws Exception{
		int index = indexPfOuPj(lista);
		if(index != -1) {
			System.out.println(lista.get(index));
			System.out.println("O cliente " + (index < 6 ? "possui mesa reservada.":String.format("está em %d° lugar na fila de espera.", index + 1)));
		} else {
			System.out.println("O cliente não possui reserva.");
		}
	}
	
	private static int pesquisarIndexPorCpf(String cpf, List<Reserva> lista) {
		PessoaFisica pf = new PessoaFisica("", cpf);
		return lista.indexOf(new Reserva(pf, true));
	}
	
	private static int pesquisarIndexPorCnpj(String cnpj, List<Reserva> lista) {
		PessoaJuridica pj = new PessoaJuridica("", cnpj);
		return lista.indexOf(new Reserva(pj, true));
	}
	
	public static Reserva reservar(List<Reserva> lista) throws Exception{
		boolean avista;
		int aux;
		Cliente novoCliente = cadastrarCliente(lista);
		if(novoCliente == null) {
			return null;
		}
		
		System.out.print("\nForma de pagamento?\n1. à vista\n2. parcelado.\n> ");
		aux = input.nextInt();
		if(aux == 1) {
			System.out.println("> à vista.");
			avista = true;
		} else if(aux == 2){
			System.out.println("> parcelado.");
			avista = false;
		} else {
			throw new Exception("dígito de pagamento inválido.");
		}
		
		return new Reserva(novoCliente, avista);
	}
	
	public static Cliente cadastrarCliente(List<Reserva> lista) {
		int pessoa = 0, auxDoc;
		String nome = "";
		String documento = "";
		
		while(pessoa != 1 && pessoa != 2) {
			System.out.println("- CADASTRO -");
			System.out.println("1. Pessoa física \n2. Pessoa jurídica");
			
			try {
				pessoa = input.nextInt();
				if(pessoa != 1 && pessoa != 2) {
					throw new Exception();
				}
			}catch(Exception e) {System.out.println("Opção inválida.");
			} finally {
				System.out.println();
				input.nextLine();
			}
		}
		
		System.out.print("Nome: ");
		nome = input.nextLine().trim();
		
		if(pessoa == 1) {
			System.out.print("CPF: ");
			documento = input.next();
			auxDoc = pesquisarIndexPorCpf(documento, lista);
			if(auxDoc == -1) { //se o cpf não estiver cadastrado
				PessoaFisica pf = new PessoaFisica(nome, documento.trim());
				return pf;				
			}
		} else {
			System.out.print("CNPJ: ");
			documento = input.next();
			auxDoc = pesquisarIndexPorCnpj(documento, lista);
			if(auxDoc == -1) {
				PessoaJuridica pj = new PessoaJuridica(nome, documento.trim());
				return pj;
			}
		}
		return null; // se já estiver cadastrado retorna nulo
	}
	
	public static String menu() {
		String espacamento = "| %-27s |\n";
		return  String.format(espacamento, "Restaurante SABOR SOFISCADO") +
				String.format(espacamento, "1. Reservar mesa") +
				String.format(espacamento, "2. Pesquisar reserva") +
				String.format(espacamento, "3. Imprimir reservas") +
				String.format(espacamento, "4. Imprimir lista de espera") +
				String.format(espacamento, "5. Cancelar reserva") +
				String.format(espacamento, "6. Finalizar");
	}
	
	
}
