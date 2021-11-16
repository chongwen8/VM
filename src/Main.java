import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PM pm = new PM();
        LinkedList<Integer> output = new LinkedList<Integer>();
        String[] init = new String[2];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            init[i] = sc.nextLine();
        }
        pm.initialize(init[0], init[1]);
        String temp = sc.nextLine();
        int[] va = Arrays.stream(temp.split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < va.length; i ++) {
            int s = Translation.getS(va[i]);
            int p = Translation.getP(va[i]);
            int w = Translation.getW(va[i]);
            int pw = Translation.getPw(va[i]);
            if (pw >= pm.getBoundary(s)){
                output.add(-1);
            }else {
                //store outputs in list then print in one line.
                output.add(pm.getValue(s, p, w));
            }
        }
        for (int pa:output) {
            System.out.print(pa + " ");
        }
        sc.close();
    }
}
