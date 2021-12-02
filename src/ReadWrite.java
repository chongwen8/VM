import java.io.*;
import java.util.LinkedList;

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
        String output = outputFile.toString();
            bufferedWriter.write(output);
            bufferedWriter.close();
        }
    }


