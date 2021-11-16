/***
 * This class is stimulated Disk storing
 */
public class Disk {
    int[][] disk = new int[1024][512];
    void setDiskValue(int i, int j, int value){
            disk[i][j] = value;
    }
    int[] readBlock(int i){
        return disk[i];
    }
    int getDiskValue(int i, int j){
        return disk[i][j];
    }
}
