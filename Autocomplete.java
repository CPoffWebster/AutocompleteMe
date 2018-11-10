import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class Autocomplete {

        private final Term[] terms;
        
        // Initializes the data structure from the given array of terms.
        public Autocomplete(Term[] terms) {
            if (terms == null) throw new NullPointerException();
            
            this.terms = terms;
            Arrays.sort(terms);
        }

        // Returns all terms that start with the given prefix, 
        //in descending order of weight.
        public Term[] allMatches(String prefix) {
            if (prefix == null) throw new NullPointerException();
            
            Term prefixTerm = new Term(prefix, 0); //save prefix
            
            //get all matching firstIndex/lastIndex terms
            int firstIndex = BinarySearchDeluxe.firstIndexOf(terms,  prefixTerm,  Term.byPrefixOrder(prefix.length()));
            if (firstIndex == -1) return new Term[0]; //if the word doesn't exist, return
            int lastIndex = BinarySearchDeluxe.lastIndexOf(terms,  prefixTerm,  Term.byPrefixOrder(prefix.length()));
            

            //initialize matches array with matching terms and sort it
            Term[] matches = new Term[lastIndex - firstIndex + 1];
            for (int i = 0; i < matches.length; i++) {
                matches[i] = terms[firstIndex++];
            } //then sort byReverseOrder
            Arrays.sort(matches, Term.byReverseWeightOrder());
            
            return matches;
        }

        // Returns the number of terms that start with the given prefix.
        public int numberOfMatches(String prefix) {
            if (prefix == null) throw new NullPointerException();
            
            Term prefixTerm = new Term(prefix, 0); //save prefix
            
            //get all matching firstIndex/lastIndex terms
            int firstIndex = BinarySearchDeluxe.firstIndexOf(terms,  prefixTerm,  Term.byPrefixOrder(prefix.length()));
            if (firstIndex == -1) return 0; //if the word doesn't exist, return
            int lastIndex = BinarySearchDeluxe.lastIndexOf(terms,  prefixTerm,  Term.byPrefixOrder(prefix.length()));
            
            return lastIndex - firstIndex + 1;
        }

        // unit testing (required)
        public static void main(String[] args) {

            // read in the terms from a file
            String filename = args[0];
            In in = new In(filename);
            int N = in.readInt();
            Term[] terms = new Term[N];
            for (int i = 0; i < N; i++) {
                long weight = in.readLong();           // read the next weight
                in.readChar();                         // scan past the tab
                String query = in.readLine();          // read the next query
                terms[i] = new Term(query, weight);    // construct the term
            }

            // read in queries from standard input and print out the top k matching terms
            int k = Integer.parseInt(args[1]);
            Autocomplete autocomplete = new Autocomplete(terms);
            while (StdIn.hasNextLine()) {
                String prefix = StdIn.readLine();
                Term[] results = autocomplete.allMatches(prefix);
                for (int i = 0; i < Math.min(k, results.length); i++)
                    StdOut.println(results[i]);
            }
        }
    }