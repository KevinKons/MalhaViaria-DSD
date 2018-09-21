package utils;

/**
 *
 * @author Avell
 */
public class UtilMethods {
    
    public static int[] stringArrayToIntArray(String[] array) {
        int[] intArray = new int[array.length];
        int i = 0;
        for(String s : array) {
            intArray[i] = Integer.parseInt(s);
            i++;
        }
        return intArray;
    }
}
