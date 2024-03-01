# Compilation
## For Linux
To compile the class, simply run the following:
```bash
mkdir build
javac -sourcepath ./src -d ./build/ ./src/*
```
To run the file, you can run the following:
```bash
java -cp build App <CSV relative file path>
```

Alternatively, you can use the run.sh that combines both steps:
```bash
# grant necessary access 
chmod 700 run.sh
./run.sh <CSV relative file path> 
```

## Task 1
### Subtasks
- [x] App should take in 1 parameter from the command line.
- [x] Read a row/line of text from CSV file using a function called `ReadCSV` in `FileService.java`
- [x] Store each row as a String List array. 
- [x] List of all stacks should be stored as a `Map<Integer, List<String>>`
- [x] Have a `WriteAsCSV` function to turn stacks into a new csv file. 
- [x] `WriteAsCSV` needs to be able to append. 

## Task 2
### Subtasks
- [x] Write `printUniquePokemonInStack` that prints all unique Pokemon in the stack provided by the argument.
- [x] Should throw error if number provided is not 1-8
- [x] Write `printNext5StarsPokemon` that takes a Pokemon String. Look at page 6 for more info lol. 
- [x] Write `printPokemonCardCount` to display 10 Pokemon that has the highest reoccurence. 