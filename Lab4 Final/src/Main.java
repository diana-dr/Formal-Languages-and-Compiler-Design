import model.MyScanner;
import model.ProgramInternalForm;
import model.SymbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> filenames = new ArrayList<>(Arrays.asList("input/p1.txt"/*, "input/p2.txt",
                "input/p3.txt", "input/p1err.txt"*/));
        int fileNo = 0;
        for (String file : filenames) {
            try {
                fileNo += 1;
                File myObj = new File(file);
                Scanner myReader = new Scanner(myObj);

                SymbolTable<Integer, String> st = new SymbolTable();
                ProgramInternalForm<String, Integer> pif = new ProgramInternalForm();
                int lineNo = 0;

                while (myReader.hasNextLine()) {
                    lineNo += 1;
                    String data = myReader.nextLine();
                    List<String> separators = new ArrayList<>(Arrays.asList(" ", "\t"));
                    String[] line = data.trim().split(separators.toString());
                    List<String> al = Arrays.asList(line);
//                    System.out.println(al);
                    MyScanner scanner = new MyScanner();
                    ArrayList<String> tokens = scanner.tokenGenerator(al);
//                    System.out.println(tokens);
                    for (String token : tokens) {
                        if (scanner.isOperator(token) || scanner.containsReservedWord(token) ||
                            scanner.getLanguageSpecification().getSeparators().contains(token)) {
                            pif.add(token, 0);
                        }
                        else if (scanner.isIdentifier(token)) {
                            token = token.replaceAll(scanner.getLanguageSpecification().getSeparators().toString(), "");
                            int id = st.add(token);
                            pif.add(token, id);
                        }
                        else if (scanner.isConstant(token)) {
                            int id = st.add(token);
                            pif.add(token, id);
                        }
                        else
                            System.out.println("Lexically error " + token + " at line " + lineNo);
                    }
                }

//                System.out.println("Program Internal Form: \n");
//                System.out.println(pif);

//                System.out.println("Symbol Table: \n");
//                System.out.println(st);

                FileWriter pifWriter = new FileWriter("output/pif" + fileNo + ".out");
                pifWriter.write(String.valueOf(pif));

                pifWriter.close();

                FileWriter stWriter = new FileWriter("output/st"+ fileNo + ".out");
                stWriter.write(String.valueOf(st));

                stWriter.close();

                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
