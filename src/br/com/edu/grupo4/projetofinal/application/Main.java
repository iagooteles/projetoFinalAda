package br.com.edu.grupo4.projetofinal.application;

// OBS: Não usar classes //
// OBS2: coloquei o id pra começar do numero 1, alterar pra começar do 0 ?? //

import br.com.edu.grupo4.projetofinal.constants.Constants;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Boolean programIsRunning = true;
        String[][] contatos = new String[100][3];
        int contador_contatos = 0;

        System.out.println(Constants.AGENDA_DISPLAY);
        do {
            System.out.print(Constants.CONTATOS_DISPLAY);
            System.out.println(Constants.CONTATOS_CAMPOS_DISPLAY);

            listar_todos_contatos(contador_contatos, contatos);

            System.out.println(Constants.SEPARADOR_DE_LINHAS);
            System.out.println(Constants.MENU_CONTATO_DISPLAY);
            System.out.println(Constants.MENU);

            System.out.println("Selecione uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Digite o nome do contato: ");
                    String nome = scanner.nextLine();

                    boolean numeroValidado = false;
                    String telefone = "";

                    while (!numeroValidado) {
                        System.out.println("Digite o telefone do contato: ");
                        telefone = scanner.nextLine();

                        boolean numeroJaExiste = numeroJaExiste(telefone, contatos, contador_contatos);
                        if (!numeroJaExiste) {
                            numeroValidado = true;
                        }
                    }

                    System.out.println("Digite o email do contato: ");
                    String email = scanner.nextLine();

                    adicionarContato(nome, telefone, email, contatos, contador_contatos);
                    contador_contatos++;
                    break;

                case 2:
                    System.out.print("Digite o ID do contato que deseja detalhar: ");
                    int id = scanner.nextInt();
                    String contatoDetalhado = detalhar(id, contatos);
                    System.out.println(Constants.SEPARADOR_DE_LINHAS);
                    System.out.println(contatoDetalhado);
                    System.out.println(Constants.SEPARADOR_DE_LINHAS);

                    break;
                case 5:
                    System.out.println("Fechando o programa...");
                    programIsRunning = false;
                    break;
                default:
                    System.out.println("Por favor escolha outra opção!");
                    break;
            }
        } while (programIsRunning);

        scanner.close();
    }


    static void adicionarContato(String nome, String telefone, String email, String[][] contatos, int contador_contatos) {
        for (int j = 0; j < 3; j++) {
            if (j == 0) {
                contatos[contador_contatos][j] = nome;
            } else if (j == 1) {
                contatos[contador_contatos][j] = telefone;
            } else if (j == 2) {
                contatos[contador_contatos][j] = email;
            }
        }
        System.out.println("Contato adicionado com sucesso!");
        System.out.println(Constants.SEPARADOR_DE_LINHAS);
    }

    static void remover(int id) {

    }

    static String detalhar(int id, String[][] contatos) {
        try {
            String contato_info = "ID: " + (id) + " - ";
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    contato_info += "Nome: " + contatos[id-1][j] + " | ";
                } else if (j == 1) {
                    contato_info += "Telefone: " + contatos[id-1][j] + " | ";
                } else if (j == 2) {
                    contato_info += "Email: " + contatos[id-1][j] + " | ";
                }
            }
            return contato_info;
        } catch (ArrayIndexOutOfBoundsException err) {
            return "Contato não encontrado!";
        }

    }

    static void listar_todos_contatos(int contador_contatos, String[][] contatos) {
        for (int i = 0; i < contador_contatos; i++) {
            String contato_info = (i+1) + " - ";
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    contato_info += "Nome: " + contatos[i][j] + " | ";
                } else if (j == 1) {
                    contato_info += "Telefone: " + contatos[i][j] + " | ";
                } else if (j == 2) {
                    contato_info += "Email: " + contatos[i][j] + " | ";
                }
            }
            System.out.println(contato_info);
        }
    }

    static boolean numeroJaExiste(String telefone, String[][] contatos, int contador_contatos) {
        for (int i = 0; i < contador_contatos; i++) {
            if (telefone.equals(contatos[i][1])) {
                System.out.println("Este telefone já existe na database! Por favor digite outro número.");
                return true;
            }
        }
        return false;
    }
}