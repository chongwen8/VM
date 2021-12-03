import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

/***
 * This class is extension class
 * which is convenient for users to read input file and write outputs to the file
 */
public class ReadWrite{

    FileWriter writer;
    LinkedList<String> readFile(File inputFile) throws IOException {
        FileReader reader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String temp;
        LinkedList<String> input = new LinkedList<String>();
        while((temp = bufferedReader.readLine()) != null){
            input.add(temp);
        }
        bufferedReader.close();
        return input;
    }
    void writeFile(File outputFile, LinkedList<Integer> outputList) throws IOException {
        FileWriter writer = new FileWriter(outputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        StringBuilder output = new StringBuilder();
        ListIterator<Integer> listIterator = outputList.listIterator();

        while (listIterator.hasNext()) {

            // using append method for appending string
            output.append(listIterator.next())
                    .append(" ");
        }
            bufferedWriter.write(output.toString() + "\n");
            bufferedWriter.close();
        }
    }


