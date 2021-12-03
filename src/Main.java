import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PM pm = new PM();
        LinkedList<Integer> output = new LinkedList<Integer>();
        String[] init = new String[2];
        String input = null;
        String choice;
        File outputFile = null;
        ReadWrite rw = new ReadWrite();
        Scanner sc = new Scanner(System.in);
        choice = sc.nextLine();
        if ("1".equals(choice)) {
            String fileName = sc.nextLine();
            File cwd = new File("data");
            File initFile = new File(cwd.getAbsolutePath() + "/" + fileName + ".txt");
            try {
                LinkedList<String> initList = rw.readFile(initFile);
                // convert linkList to String array
                init = initList.toArray(new String[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }
            fileName = sc.nextLine();
            File inputFile = new File(cwd.getAbsolutePath() + "/" + fileName + ".txt");
            try {
                LinkedList<String> inputList = rw.readFile(inputFile);
                input = inputList.getFirst();
            } catch (IOException e) {
                e.printStackTrace();
            }
            outputFile = new File(cwd.getAbsolutePath() + "/output-dp.txt");
        }else{
            for (int i = 0; i < 2; i++) {
                init[i] = sc.nextLine();
            }
                input = sc.nextLine();
        }
        pm.initialize(init[0], init[1]);
        int[] va = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int j : va) {
            int s = Translation.getS(j);
            int p = Translation.getP(j);
            int w = Translation.getW(j);
            int pw = Translation.getPw(j);
            if (pw >= pm.getBoundary(s)) {
                output.add(-1);
            } else {
                //store outputs in list then print in one line.
                output.add(pm.getValue(s, p, w));
            }
        }
        for (int pa : output) {
            System.out.print(pa + " ");
        }
        if (outputFile != null) {
            try {
                rw.writeFile(outputFile, output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        sc.close();

    }

}
