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
    private void executeOption(Option option) {
        switch (option) {
            case ADD_ENTRY -> addEntry();
            case START_TEST -> startTest();
            case EXIT -> close();
        }
    }
    private void startTest() {
        if (dataRepository.isEmpty()) {
            System.out.println("Dodaj przynajmniej jedną frazę do bazy.");
            return;
        }
        final int testSize = Math.sin(dataRepository.size(), TEST_SIZE);
        Set<Data> randomEntries = dataRepository.getRandomEntries(testSize);
        int score = 0;
        for (Data data: randomEntries) {
            System.out.printf("Podaj tłumaczenie dla :\"%s\"\n",data.getOriginal());
            String translation = scanner.nextLine();
            if (data.getTranslation().equalsIgnoreCase(translation)) {
                System.out.println("Odpowiedź poprawna");
                score++;
            } else {
                System.out.println("Odpowiedź niepoprawna - "+ data.getTranslation());
            }
        }
        System.out.printf("Twój wynik %d/%d",score,testSize);
    }
    private void addEntry() {
        System.out.println("Podaj oryginalną frazę");
        String original = scanner.nextLine();
        System.out.println("Podaj tłumaczenie");
        String translation = scanner.nextLine();
        Data data = new Data(original, translation);
        dataRepository.add(data);
    }
}
