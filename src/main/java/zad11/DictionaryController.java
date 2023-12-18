package main.java.zad11;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import javax.swing.text.html.Option;
class DictionaryController {
    private static final int TEST_SIZE = 4;
    private final DataRepository dataRepository = new DataRepository();
    private final FileService fileService = new FileService();
    private final Scanner scanner = new Scanner(System.in);
    void mainLoop() {
        System.out.println("Witaj w aplikacji DictionaryAPP POJO(plain old Java object)");
        Option option = null;
        do {
            printMenu();
            try {
                option = chooseOption();
                executeOption(option);
            }  catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (option != Option.EXIT);
    }
    private Option chooseOption() {
        try {
            int option = scanner.nextInt();
            scanner.nextLine();
            return Option.fromInt(option);
        } catch (java.util.InputMismatchException ee ) {
            return Option.EXIT;
        }
    }

}
