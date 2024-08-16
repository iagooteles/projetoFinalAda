package br.com.edu.grupo4.projetofinal.application;

// faltam só validações!

import br.com.edu.grupo4.projetofinal.constants.Constants;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean programIsRunning = true;
        int tamanhoArray = 2;
        String[][] contatos = new String[tamanhoArray][3];
        int contadorContatos = 0;
        int id;
        String nome, telefone, email;

        System.out.println(Constants.AGENDA_DISPLAY);
        do {
            System.out.print(Constants.CONTATOS_DISPLAY);
            System.out.println(Constants.CONTATOS_CAMPOS_DISPLAY);

            listar_todos_contatos(contadorContatos, contatos);

            System.out.println(Constants.SEPARADOR_DE_LINHAS);
            System.out.println(Constants.MENU_CONTATO_DISPLAY);
            System.out.println(Constants.MENU);

            System.out.println("Selecione uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Digite o nome do contato: ");
                    nome = pegarNome();

                    telefone = pegarTelefone(contatos, contadorContatos);

                    System.out.println("Digite o email do contato: ");
                    email = pegarEmail();

                    try {
                        adicionarContato(nome, telefone, email, contatos, contadorContatos);
                        contadorContatos++;
                    } catch (ArrayIndexOutOfBoundsException err) {
                        System.out.println("Agenda atingiu o limite, Aumentando o tamanho da sua agenda...");
                        contatos = aumentarArray(contatos, tamanhoArray);
                        tamanhoArray*=2;
                        adicionarContato(nome, telefone, email, contatos, contadorContatos);
                        contadorContatos++;
                    }
                    break;
                case 2:
                    System.out.print("Digite o ID do contato que deseja detalhar: ");
                    id = scanner.nextInt();
                    String contatoDetalhado = detalhar(id, contatos);

                    System.out.println(Constants.SEPARADOR_DE_LINHAS);
                    System.out.println(contatoDetalhado);
                    System.out.println(Constants.SEPARADOR_DE_LINHAS);
                    break;
                case 3:
                    System.out.println("Digite o ID do contato que deseja editar: ");
                    id = scanner.nextInt();
                    scanner.nextLine();

                    if (id <= 0 || id > contadorContatos) {
                        System.out.println(Constants.ID_INVALIDO);
                    } else {

                        System.out.println("Digite o nome do contato: ");
                        nome = pegarNome();

                        telefone = pegarTelefone(contatos, contadorContatos, id);

                        System.out.println("Digite o email do contato: ");
                        email = pegarEmail();

                        for (int j = 0; j < 3; j++) {
                            if (j == 0) {
                                contatos[id-1][j] = nome;
                            } else if (j == 1) {
                                contatos[id-1][j] = telefone;
                            } else if (j == 2) {
                                contatos[id-1][j] = email;
                            }
                        }
                        System.out.println("Contato editado com sucesso!");
                    }

                    break;
                case 4:
                    System.out.println("Digite o ID do contato que deseja remover: ");
                    id = scanner.nextInt();
                    scanner.nextLine();

                    if (id <= 0 || id > contadorContatos) {
                        System.out.println(Constants.ID_INVALIDO);
                    } else {
                        remover(id, contatos, contadorContatos);
                        contadorContatos--;
                    }

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

    public static String pegarNome() {
        Scanner scanner = new Scanner(System.in);
        String nome = "";
        nome = scanner.nextLine();

        return nome;
    }

    public static String pegarTelefone(String[][] contatos, int contadorContatos) {
        Scanner scanner = new Scanner(System.in);
        boolean numeroValidado = false;
        String telefone = "";

        while (!numeroValidado) {
            System.out.println("Digite o telefone do contato: ");
            telefone = scanner.nextLine();

            boolean numeroJaExiste = numeroJaExiste(telefone, contatos, contadorContatos);
            if (!numeroJaExiste) {
                numeroValidado = true;
            }
        }

        return telefone;
    }

    public static String pegarTelefone(String[][] contatos, int contadorContatos, int id) {
        Scanner scanner = new Scanner(System.in);
        boolean numeroValidadoEditar = false;
        String telefone = "";

        while (!numeroValidadoEditar) {
            System.out.println("Digite o telefone do contato: ");
            telefone = scanner.nextLine();

            boolean numeroJaExiste = numeroJaExiste(telefone, contatos[id-1][1], contatos, contadorContatos);
            if (!numeroJaExiste) {
                numeroValidadoEditar = true;
            }
        }

        return telefone;
    }

    public static String pegarEmail() {
        Scanner scanner = new Scanner(System.in);
        String email = "";
        email = scanner.nextLine();

        return email;
    }

    public static String[][] aumentarArray(String[][] contatos, int novoTamanho) {
        String[][] novoArray = new String[novoTamanho*2][3];

        for (int i = 0; i < contatos.length; i++) {
            for (int j = 0; j < contatos[i].length; j++) {
                novoArray[i][j] = contatos[i][j];
            }
        }

        return novoArray;
    }

    static void adicionarContato(String nome, String telefone, String email, String[][] contatos, int contadorContatos) {
        if (contatos[contadorContatos] == null) {
            contatos[contadorContatos] = new String[3];
        }

        for (int j = 0; j < 3; j++) {
            if (j == 0) {
                contatos[contadorContatos][j] = nome;
            } else if (j == 1) {
                contatos[contadorContatos][j] = telefone;
            } else if (j == 2) {
                contatos[contadorContatos][j] = email;
            }
        }
        System.out.println("Contato adicionado com sucesso!");
        System.out.println(Constants.SEPARADOR_DE_LINHAS);
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

    static void remover(int id, String[][] contatos, int contadorContatos) {
        id = id - 1;

        for (int i = id; i < contadorContatos - 1; i++) {
            contatos[i] = contatos[i + 1];
        }

        contatos[contadorContatos - 1] = null;
        System.out.println("Contato removido com sucesso!");
        System.out.println(Constants.SEPARADOR_DE_LINHAS);
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

    static boolean numeroJaExiste(String telefone, String telefoneAtual, String[][] contatos, int contador_contatos) {
        for (int i = 0; i < contador_contatos; i++) {
            if (telefone.equals(contatos[i][1]) && !telefone.equals(telefoneAtual)) {
                System.out.println("Este telefone já existe na database! Por favor digite outro número.");
                return true;
            }
        }
        return false;
    }
}
