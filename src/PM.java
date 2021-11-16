import java.util.*;

public class PM {
    private int[] pm;
    private Map<Integer, Integer> pageNotResident = new HashMap<>();
    private Map<Integer, Integer> ptNotResident = new HashMap<>();
    private LinkedList<Integer> usedFrame = new LinkedList<>();
    Disk d;

    public PM() {
        this.pm = new int[524288];
    }

    void initialize(String l1, String l2) {
        d = new Disk();
        int[] line1 = Arrays.stream(l1.split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] line2 = Arrays.stream(l2.split(" ")).mapToInt(Integer::parseInt).toArray();
        int line1Length = line1.length;
        int line2Length = line2.length;
        // frame 0, 1 are already used.
        usedFrame.add(0);
        usedFrame.add(1);
        for (int i = 0; i + 3 <= line1Length; i += 3) {
            pm[2 * line1[i]] = line1[i + 1];
            pm[2 * line1[i] + 1] = line1[i + 2];
            if (pm[2 * line1[i] + 1] <= 0) {
                // store specific ST's index of in the disk
                ptNotResident.put(line1[i], Math.abs(line1[i + 2]));
            }else{
                // if the value is not negative, this frame should be set used.
                usedFrame.add(line1[i + 2]);
            }
        }
        for (int j = 0; j + 3 <= line2Length; j += 3) {
            if (pm[2 * line2[j] + 1] <= 0) {
                // according disk index to store value inside the disk block.
                d.setDiskValue(ptNotResident.get(line2[j]), line2[j + 1], line2[j + 2]);
            }else{
                pm[pm[2 * line2[j] + 1] * 512 + line2[j + 1]] = line2[j + 2];
            }
            if (line2[j + 2] <= 0){
                //store the index of specific page of segment of in the disk
                pageNotResident.put(line2[j], Math.abs(line2[j + 2]));
            }else{
                usedFrame.add(line2[j + 2]);
            }
        }
    }

    /**
     *
     * @param index
     * @return the size of the segment
     */
    int getBoundary(int index) {
        return pm[2 * index];
    }

    int returnLeastFreeFrame() {
        usedFrame.sort(Comparator.naturalOrder());// sort
        int start = 1;
        for (Integer integer : usedFrame) {
            if (integer - start > 1) {
                break;
            } else {
                start = integer;
            }
        }
        return start + 1;
    }

    /**
     * get Physical Address
     * @param s
     * @param p
     * @param w
     * @return
     */
    int getValue(int s, int p, int w) {
        if (pm[2 * s + 1] < 0) {
            int f1 = returnLeastFreeFrame();
            usedFrame.add(f1);// the frame new allocated should be set used.
            copyFromDisk2Memory(d.readBlock(Math.abs(pm[2 * s + 1])), f1 * 512);
            pm[2 * s + 1] = f1;
        }
        if (pm[pm[2 * s + 1] * 512 + p] < 0){
            int f2 = returnLeastFreeFrame();
            usedFrame.add(f2);
            copyFromDisk2Memory(d.readBlock(Math.abs(pm[pm[2 * s + 1] * 512 + p])), f2 * 512);
            pm[pm[2 * s + 1] * 512 + p] = f2;
        }
        return pm[pm[2 * s + 1] * 512 + p] * 512 + w;

    }

    /**
     * copy block from disk to physical memory
     * @param block
     * @param index
     */
    void copyFromDisk2Memory(int[] block, int index){
        System.arraycopy(block, 0, pm, index, 512);
    }
}