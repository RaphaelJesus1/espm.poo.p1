import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int opcao = 0;
		boolean running = true;
		
		while(running) {
			System.out.print(menu());
			System.out.print("> ");
			
			try {
				opcao = input.nextInt();
				if(opcao <= 0 || opcao > 7) {
					throw new IllegalArgumentException();
				}
			} catch(InputMismatchException e) {
				System.out.println("Opção inválida. Apenas valores numéricos são reconhecidos.\n");
				opcao = 0;
			} catch(IllegalArgumentException e) {
				System.out.println("Opção inválida. As operações disponíveis estão entre 1 e 6.\n");
				opcao = 0;
			} finally {
				input.nextLine();
			}
			
			switch(opcao) {
			case 1:
				// reservar
				break;
			case 2:
				// pesquisar
				break;
			case 3:
				// imprimir reservas
				break;
			case 4:
				// imprimir lista de espera
				break;
			case 5:
				// cancelar reserva
				break;
			case 6:
				//finalizar
				running = false;
			}
		}
		
		input.close();
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
