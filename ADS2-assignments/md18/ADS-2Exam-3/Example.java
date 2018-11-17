
// Java program to demonstrate working of
// comparator based priority queue constructor
import java.util.*;
class WordCount {
    public String word;
    public int frequency;

    // A parameterized student constructor
    public Student(String word, double freq) {

        this.word = word;
        this.frequency = freq;
    }

    public String getWord() {
        return word;
    }
}

public final class Example {

        PriorityQueue<Student> pq = new
             PriorityQueue<Student>(5, new StudentComparator());

                // Invoking a parameterized Student constructor with
                // name and cgpa as the elements of queue
                Student student1 = new Student("Nandini", 3.2);

                // Adding a student object containing fields
                // name and cgpa to priority queue
                pq.add(student1);
                Student student2 = new Student("Anmol", 3.6);
                        pq.add(student2);
                Student student3 = new Student("Palak", 4.0);
                        pq.add(student3);

    }

class StudentComparator implements Comparator<WordCount>{

            // Overriding compare()method of Comparator
                        // for descending order of cgpa
            public int compare(WordCount s1, WordCount s2) {
                if (s1.frequency < s2.frequency)  {
                    return 1;
                }
                else if (s1.frequency > s2.frequency) {
                    return -1;
                }
                return 0;
                }
        }

