package Pratico_2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class SegundoCliente {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            RemoteInterface remoteObject = (RemoteInterface) registry.lookup("RemoteObject");

            Scanner scanner = new Scanner(System.in);

            int opcao;
            do {
                System.out.println("Menu:");
                System.out.println("1. Adicionar Recurso");
                System.out.println("2. Consultar Recurso");
                System.out.println("3. Requisitar Recurso");
                System.out.println("4. Devolver Recurso");
                System.out.println("0. Sair");

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

        Recurso novoRecurso = new Recurso(id, descricao);
        try {
            remoteObject.inserirRecurso(novoRecurso);
            System.out.println("Recurso inserido com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void consultarRecurso(Scanner scanner, RemoteInterface remoteObject) {
        System.out.print("Digite uma parte da descrição para consultar recursos: ");
        String consultaDescricao = scanner.nextLine();

        try {
            List<Recurso> recursosEncontrados = remoteObject.consultarRecursos(consultaDescricao);
            if (recursosEncontrados.isEmpty()) {
                System.out.println("Nenhum recurso encontrado.");
            } else {
                System.out.println("Recursos encontrados:");
                for (Recurso recurso : recursosEncontrados) {
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
            ClienteCallback callback = new SegundoClienteCallbackImpl();
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
            ClienteCallback callback = new SegundoClienteCallbackImpl();
            remoteObject.devolverRecurso(id, callback);
            System.out.println("Recurso devolvido com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
