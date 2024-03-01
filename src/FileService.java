import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public List<String> ReadCSV(String fullPathFilename) {
        // Task 1 - your code here
        List<String> stacksOfPokemons = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fullPathFilename));
            String stack = reader.readLine();
            while (stack != null) {
                if (stack.length() > 0) {
                    stacksOfPokemons.add(stack);
                }
                stack = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stacksOfPokemons;
    }

    public void writeAsCSV(String pokemons, String fullPathFilename) {
        // Task 1 - your code here
        File csvFile = new File(fullPathFilename);
        try {
            BufferedWriter writer = null;
            if (csvFile.exists()) {
                writer = new BufferedWriter(new FileWriter(csvFile, true));
                writer.write("\n"+pokemons);
            } else {
                writer = new BufferedWriter(new FileWriter(csvFile, false));
                writer.write(pokemons);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
