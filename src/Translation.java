/***
 * Final Class designing for deriving S, P, W and pw from VA
 * @author chong
 */
public final class Translation {
    public static int getS(int i){
        return i >>> 18;
    }
    public static int getW(int i){
        return i & 0X1FF;
    }
    public static int getP(int i){
        return (i >>> 9) & 0X1FF;
    }
    public static int getPw(int i){
        return i & 0X3FFFF;
    }
}
