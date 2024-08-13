package br.com.edu.grupo4.projetofinal.application;

// OBS: Não usar classes //

import br.com.edu.grupo4.projetofinal.constants.Constants;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Boolean programIsRunning = true;

        System.out.println(Constants.AGENDA_DISPLAY);

        do {
            System.out.print(Constants.CONTATOS_DISPLAY);
            System.out.println(Constants.CONTATOS_CAMPOS_DISPLAY);

            System.out.println(Constants.MENU_CONTATO_DISPLAY);
            System.out.println(Constants.MENU);

            System.out.println("Selecione uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();


            switch (option) {
                case 2:
                    System.out.println("Contato detalhado.");
                    break;
                case 5:
                    programIsRunning = false;
                    break;
            }
        } while (programIsRunning);

        scanner.close();
    }

}