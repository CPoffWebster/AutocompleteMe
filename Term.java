import java.util.Comparator;

public class Term implements Comparable<Term> {

    private final String query; // text or "query" that will be auto completed
    private final long weight; // what "weight" does the query hold comparedto other querys

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null) { // if query does not exist
            throw new java.lang.NullPointerException();
        }
        if (weight < 0) { // if weight is less than zero than it holds no value
            throw new java.lang.IllegalArgumentException();
        }

        // so the private variables can be used
        this.query = query;
        this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new byReverseWeightOrder();
    }

    private static final class byReverseWeightOrder implements Comparator<Term> {
        // nested class compare class for comparator
        public int compare(Term term1, Term term2) {

            // compares the two terms
            if (term1.weight > term2.weight) { // term 2 weight is greater
                return -1;
            } else if (term1.weight == term2.weight) { // weights are equal
                return 0;
            } else
                return 1; // term1.weight > term2.weight - term1.weight greater
        }
    }

    // Compares the two terms in lexicographic order but using
    // only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0)
            throw new IllegalArgumentException("r is negative");
        return new byPrefixOrder(r);
    }

    // you will need to define another private static nested class.
    // Since you will need to create a comparator that depends upon an
    // integer parameter r, the nested class should have a constructor
    // that takes r as an argument and saves it in an instance variable.
    private static final class byPrefixOrder implements Comparator<Term> {
        private int r;

        byPrefixOrder(int r) {
            this.r = r;
        }

        public int compare(Term term1, Term term2) {
            //by viewing the terms as strings, the characters can be compared
            String string1 = term1.query, string2 = term2.query;
            int min;    //hold which string length is smaller
            
            //which string length is smaller: store that in min
            if (string1.length() < string2.length()) {
                min = string1.length();
            } else {          //string2.length < string1.length
                min = string2.length();
            }
            
            if (min >= r) { //if >= then everything up to r can be compared
                //compare the first r characters of string1 and string 2 
                return string1.substring(0, r).compareTo(string2.substring(0, r));
            } else
                //if the string lengths are the same (0-min)
                if (string1.substring(0, min).compareTo(string2.substring(0, min)) == 0) {
                    //if string1.length == min, then it is less than string2.length
                    if (string1.length() == min) { return -1;
                    //opposite of above so string2.length is larger
                    } else return 1;
            } else 
                //else return the compare value
                return string1.substring(0, min).compareTo(string2.substring(0, min));
        }
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        String cmp1 = this.query;
        String cmp2 = that.query;
        return cmp1.compareTo(cmp2);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return Long.toString(weight) + '\t' + query;
    }

    // unit testing (required)
    public static void main(String[] args) {
        
    }
}
