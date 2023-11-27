package Trabalho_Pratico;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            RemoteInterface remoteObject = (RemoteInterface) registry.lookup("RemoteObject");
            Scanner scanner = new Scanner(System.in);

            int opcao;
            do {
                System.out.println("===================================Menu===============================");
                System.out.println("1 => Inserir Equipamento.");
                System.out.println("2 => Consultar Equipamento.");
                System.out.println("3 => Reservar Equipamento.");
                System.out.println("4 => Devolver Equipamento.");
                System.out.println("5 => Listar todos os Equipamentos.");
                System.out.println("0 => Quit.");

                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();  // Limpa o buffer do teclado

                switch (opcao) {
                    case 1:
                        adicionarRecurso(scanner, remoteObject);
                        break;
                    case 2:
                        consultarRecurso(scanner, remoteObject);
                        break;
                    case 3:
                        requisitarRecurso(scanner, remoteObject);
                        break;
                    case 4:
                        devolverRecurso(scanner, remoteObject);
                        break;
                    case 5:
                        listarRecursos(remoteObject);
                        break;
                    case 0:
                        System.out.println("Saindo do programa. Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }

            } while (opcao != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void adicionarRecurso(Scanner scanner, RemoteInterface remoteObject) {
        System.out.print("Digite o ID do recurso: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpa o buffer do teclado
        System.out.print("Digite a descrição do recurso: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite o tipo de equipamento: ");
        String tipoEquipamento = scanner.nextLine();
        System.out.print("Digite detalhes específicos do equipamento: ");
        String detalhesEquipamento = scanner.nextLine();

        EquipamentoApoio novoRecurso = new EquipamentoApoio(id, descricao, tipoEquipamento, detalhesEquipamento);
        try {
            remoteObject.inserirRecurso(novoRecurso);
            System.out.println(novoRecurso);
            System.out.println("Recurso inserido com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void consultarRecurso(Scanner scanner, RemoteInterface remoteObject) {
        System.out.print("Digite uma parte da descrição para consultar recursos: ");
        String consultaDescricao = scanner.nextLine();

        try {
            List<EquipamentoApoio> recursosEncontrados = remoteObject.consultarRecursos(consultaDescricao);
            if (recursosEncontrados.isEmpty()) {
                System.out.println("Nenhum recurso encontrado.");
            } else {
                System.out.println("Recursos encontrados:");
                for (EquipamentoApoio recurso : recursosEncontrados) {
                    System.out.println(recurso);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void requisitarRecurso(Scanner scanner, RemoteInterface remoteObject) {
        System.out.print("Digite o ID do recurso que deseja requisitar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpa o buffer do teclado

        try {

            ClienteCallback callback = new ClienteCallback() {
                @Override
                public void notificarRecursoDisponivel() throws RemoteException {
                    // Adicione aqui a lógica para lidar com o recurso disponível
                    System.out.println("Recurso disponível novamente. Faça algo!");
                }
            };

            String resultado = remoteObject.requisitarRecurso(id, callback);
            System.out.println(resultado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void devolverRecurso(Scanner scanner, RemoteInterface remoteObject) {
        System.out.print("Digite o ID do recurso que deseja devolver: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpa o buffer do teclado

        try {
            // ClienteCallback não é mais necessário para devolver o recurso,
            // pois você está apenas notificando o servidor sobre a devolução.

            // Chama o método no servidor para devolver o recurso
            String resultado = remoteObject.devolverRecurso(id);
            System.out.println(resultado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void listarRecursos(RemoteInterface remoteObject) {
        try {
            List<EquipamentoApoio> recursosDisponiveis = remoteObject.listarRecursos();
            if (recursosDisponiveis.isEmpty()) {
                System.out.println("Nenhum recurso disponível.");
            } else {
                System.out.println("Recursos disponíveis:");
                for (EquipamentoApoio recurso : recursosDisponiveis) {
                    System.out.println(recurso);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
