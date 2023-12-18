package main.java.zad11;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
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
        final int testSize = Math.min(dataRepository.size(), TEST_SIZE);
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
    private void close() {
        try {
            fileService.saveEntries(dataRepository.getAll());
            System.out.println("Zapisano stan aplikacji");
        } catch (IOException e) {
            System.out.println("Nie udało się zapisać zmian");
        }
        System.out.println("Bye Bye");
    }
    private void printMenu() {
        System.out.println("Wybierz opcję:");
        for (Option option : Option.values()) {
            System.out.println(option);
        }
    }
    private static enum Option {
        ADD_ENTRY(1,"Dodaj tekst z tłumaczeniem"),
        START_TEST(2,"Rozpocznij test"),
        EXIT(3,"Koniec programu");
        private final int optionNumber;
        private final String description;
        Option(int optionNumber, String description) {
            this.optionNumber = optionNumber;
            this.description = description;
        }
        static Option fromInt(int option) {
            if (option < 0 || option > values().length) {
                throw new IllegalArgumentException("Opcja o takim numerze nie istnieje");
            }
            return values()[option - 1];
        }
        @Override
        public String toString() {
            return String.format("%d - %s",optionNumber, description);
        }
    }
}
