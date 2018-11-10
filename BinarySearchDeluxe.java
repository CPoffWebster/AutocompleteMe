import java.util.Arrays;
import java.util.Comparator;


public class BinarySearchDeluxe {

    // Returns the index of the first key in a[] that 
    //equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        //if inputs are null
        if (a == null) throw new java.lang.NullPointerException();
        if (key == null) throw new java.lang.NullPointerException();
        if (comparator == null) throw new java.lang.NullPointerException();
        
        //If the array is empty then there is nothing to compare
        if(a.length == 0) return -1;
        
        // variables to be used for binarySearch
        int start = 0, //start of the array
                end = a.length - 1, //end of the array
                mid; //mid will be updated in binarySearch loop
        
        //binary search - will get the firstIndexOf the key
        while (start+1 < end) { //start+1 being the key before the required key
            mid = start + (end - start)/2; 
            if (comparator.compare(key, a[mid]) <= 0) { //if key is less/equal mid
                end = mid; //update end
            } else {
                start = mid; //otherwise key is greaterThan mid so update start
            }
        }
        
        //either start or end will be the firstIndex
        if (comparator.compare(key, a[start]) == 0) { //start is the first index
            return start; //return start (position) as the first index
        } else
        if (comparator.compare(key, a[end]) == 0) { //end is the first index
            return end; //return end (position) as the first index
        }
        
        return -1; //no match, key is not in the array
    }

    // Returns the index of the last key in a[] that equals the search key, 
    //or -1 if no such key.
    //Only 1 thing is changed between this method and firstIndex
    //Within the while there is an explanation
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        //if inputs are null
        if (a == null) throw new java.lang.NullPointerException();
        if (key == null) throw new java.lang.NullPointerException();
        if (comparator == null) throw new java.lang.NullPointerException();
        
        //If the array is empty then there is nothing to compare
        if(a.length == 0) return -1;
        
        // variables to be used for binarySearch
        int start = 0, //start of the array
                end = a.length - 1, //end of the array
                mid; //mid will be updated in binarySearch loop
        
        //binary search - will get the firstIndexOf the key
        while (start+1 < end) { //start+1 being the key before the required key
            mid = start + (end - start)/2; 
            //instead of being <= 0, which forces end to the left
            //having < 0, will force start to the right
            //hence giving the lastIndex rather than the first
            if (comparator.compare(key, a[mid]) < 0) { //if key is less/equal mid
                end = mid; //update end
            } else {
                start = mid; //otherwise key is greaterThan mid so update start
            }
        }
        
        //either start or end will be the firstIndex
        if (comparator.compare(key, a[start]) == 0) { //start is the first index
            return start; //return start (position) as the first index
        } else
        if (comparator.compare(key, a[end]) == 0) { //end is the first index
            return end; //return end (position) as the first index
        }
        
        return -1; //no match, key is not in the array
    }
    
    
    
    //The following is from the overview video
    private static class IntComp implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    }

    private static Integer[] toInteger(int[] inputInts) {
        int N = inputInts.length;
        Integer[] outputInts = new Integer[N];
        for  (int i = 0; i < N; i++) {
            outputInts[i] = new Integer(inputInts[i]);
        }
        return outputInts;
    }
    
    // unit testing (required)
    public static void main(String[] args) {
        int[] someInts = {10, 10, 20, 30, 30, 30, 40, 50, 50, 70, 70, 70};
        Integer[] intObjects = toInteger(someInts);
        
        Comparator<Integer> intComp = new IntComp();
        
        Arrays.sort(intObjects, intComp);
        int first30 = BinarySearchDeluxe.firstIndexOf(intObjects,  30,  intComp);
        System.out.println(first30);
    }
}
