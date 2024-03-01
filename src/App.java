
import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class App {
    private static List<String> stack01;
    private static List<String> stack02;
    private static List<String> stack03;
    private static List<String> stack04;
    private static List<String> stack05;
    private static List<String> stack06;
    private static List<String> stack07;
    private static List<String> stack08;
    private static Map<Integer, List<String>> mapOfStacks;
    
    public static void main(String[] args) throws Exception {

        // Run Your Code here
        if (args.length > 0) {
            String fullPathFilename = args[0];
            FileService fileService = new FileService();
            List<String> stacksOfPokemon = fileService.ReadCSV(fullPathFilename);
            stack01 = Arrays.asList(stacksOfPokemon.get(0));
            stack02 = Arrays.asList(stacksOfPokemon.get(1));
            stack03 = Arrays.asList(stacksOfPokemon.get(2));
            stack04 = Arrays.asList(stacksOfPokemon.get(3));
            stack05 = Arrays.asList(stacksOfPokemon.get(4));
            stack06 = Arrays.asList(stacksOfPokemon.get(5));
            stack07 = Arrays.asList(stacksOfPokemon.get(6));
            stack08 = Arrays.asList(stacksOfPokemon.get(7));
            mapOfStacks = Map.of(
                1, stack01,
                2, stack02,
                3, stack03,
                4, stack04,
                5, stack05,
                6, stack06,
                7, stack07,
                8, stack08
            );
        }
        
        Boolean isFinished = false; 

        while (!isFinished) {
            Console cons = System.console();
            printHeader();
            String userInput = cons.readLine();

            switch (userInput) {
                case "1":
                    System.out.println("Display the list of unique Pokemon in stack (1 - 8)");
                    try {
                        Integer stack = Integer.valueOf(cons.readLine());
                        if (stack >= 1 && stack <= 8) {
                            printUniquePokemonStack(stack);
                            System.out.println("");
                        } else {
                            System.out.println("Please enter a number between 1 to 8");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input received.");
                    }
                    break;
                case "2":
                    System.out.println("Search for the next occurrence of 5 stars Pokemon in all stacks based on entered Pokemon");
                    break;
                case "3":
                    System.out.println("Create a new Pokemon stack and save to a new file >");
                    String newStack = cons.readLine();
                    String fullPathFilename = "";
                    while (true) {
                        System.out.println("Enter filename to save (e.g. path/filename.csv)");
                        fullPathFilename = cons.readLine();
                        String fileExtension = fullPathFilename.substring(fullPathFilename.length()-4, fullPathFilename.length());
                        System.out.println("File extension is: " + fileExtension);
                        if (fileExtension.equals(".csv")) {
                            savePokemonStack(newStack, fullPathFilename);
                            System.out.println("Stack has been saved.\n");
                            break;
                        } else {
                            System.out.println("Invalid file extension received, please try again");
                        }
                    }
                    break;
                case "4":
                    
                    break;
                case "q":
                    printExitMessage();
                    isFinished = true; 
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }

    }

    public static void clearConsole() throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Task 1
    public static void pressAnyKeyToContinue() {
        // your code here
        System.out.println("Press any key to continue...");
        
    }

    // Task 1
    public static void printHeader() {

        // Task 1 - your code here
        System.out.println("Welcome to Pokemon Gaole Legend 4 Rush 2\n");
        System.out.println("(1) View unique list of Pokemon in the selected stack");
        System.out.println("(2) Find next 5 stars Pokemon occurent");
        System.out.println("(3) Create new Pokemon stack and save (append) to csv file");
        System.out.println("(4) Print distinct Pokemon and cards count");
        System.out.println("(q) to exit the program");
        System.out.println("Enter your selection > ");
    }

    // Task 1
    public static void printExitMessage() {

        // Task 1 - your code here
        System.out.println("Thank you for using the program...");
        System.out.println("Hope to see you soon...");
    }

    // Task 1
    public static void savePokemonStack(String pokemonStack, String filename) {

        // Task 1 - your code here
        FileService fileService = new FileService();
        fileService.writeAsCSV(pokemonStack, filename);
    }

    // Task 2
    public static void printUniquePokemonStack(Integer stack) {
        // Task 2 - your code here
        Set<String> uniqueSet = new LinkedHashSet<String>();
        String[] retrievedStack = mapOfStacks.get(stack).get(0).split(",");
        for (int i = 0; i < retrievedStack.length; i++) {
            uniqueSet.add(retrievedStack[i]);
        }
        Iterator<String> itr = uniqueSet.iterator();
        Integer count = 1; 
        while (itr.hasNext()) {
            System.out.printf("%d ==> %s\n", count, itr.next());
            count++;   
        }
        
    }
    // Task 2
    public static void printNext5StarsPokemon(String enteredPokemon) {
        // Task 2 - your code here
        Integer counter = 0;
        
        for (int i = 1; i < 8; i++) {
            Boolean pokemonExists = false;
            Boolean fiveStarExists = false;
            Integer cardsToGo = 0;
            String fiveStarPokemon = "";
            System.out.printf("Set %d\n", i);
            String[] stack = mapOfStacks.get(i).get(0).split(",");
            for (int j = 0; j < stack.length; j++) {
                if (stack[i].equals(enteredPokemon)) {
                    pokemonExists = true;
                }
                if (stack[i].contains("5*")) {
                    fiveStarExists = true;
                    fiveStarPokemon = stack[i];
                    cardsToGo = counter;
                }
                counter++;
            }
            if (!pokemonExists) {
                System.out.printf("%s not found in this set.\n", enteredPokemon);
            }
            if (pokemonExists && !fiveStarExists) {
                System.out.printf("No 5 stars Pokemon found subsequently in the stack.");
            }
            if (pokemonExists && fiveStarExists) {
                System.out.printf("%s string found.\n%d more to go.", fiveStarPokemon, cardsToGo);
            }
        }

    }

    // Task 2
    public static void printPokemonCardCount() {
        // Task 2 - your code here
    }

}
