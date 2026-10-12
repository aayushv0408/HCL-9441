// import java.util.*;

// class Student {
//     String name;
//     int roll;
//     int age;
//     double marks;

//     // Constructor
//     Student(String name, int roll, int age, double marks) {
//         this.name = name;
//         this.roll = roll;
//         this.age = age;
//         this.marks = marks;
//     }

//     // Display student details
//     void display() {
//         System.out.println("Name: " + name);
//         System.out.println("Roll: " + roll);
//         System.out.println("Age: " + age);
//         System.out.println("Marks: " + marks);
//         System.out.println("--------------------");
//     }
// }

// public class Task1 {

//     public static void main(String[] args) {

//         List<Student> ls = new ArrayList<>(18);

//         // Adding student data
//         ls.add(new Student("Aayush", 101, 21, 85.5));
//         ls.add(new Student("Rahul", 102, 20, 78.5));
//         ls.add(new Student("Aman", 103, 21, 90.0));
//         ls.add(new Student("Priya", 104, 20, 88.5));
//         ls.add(new Student("Neha", 105, 22, 76.0));

//         // Display all students
//         for (Student s : ls) {
//             s.display();
//         }


//         ls.sort(Comparator.comparing(s -> s.name));

//         for (Student s : ls) {
//             s.display();
//         }

//         System.out.println("Total Students: " + ls.size());
//     }
// }




import java.util.*;

class Student implements Comparable<Student> {

    String name;
    int roll;
    int age;
    double marks;

    // Constructor
    Student(String name, int roll, int age, double marks) {
        this.name = name;
        this.roll = roll;
        this.age = age;
        this.marks = marks;
    }

    // Comparable implementation: sort by name
    @Override
    public int compareTo(Student s) {
        return this.name.compareTo(s.name);
    }

    // Display student details
    void display() {
        System.out.println("Name: " + name);
        System.out.println("Roll: " + roll);
        System.out.println("Age: " + age);
        System.out.println("Marks: " + marks);
        System.out.println("--------------------");
    }
}

// Comparator implementation: sort by marks
class SortByMarks implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s1.marks, s2.marks);
    }
}

public class Task1 {

    public static void main(String[] args) {

        List<Student> ls = new ArrayList<>(18);

        // Adding student data
        ls.add(new Student("Aayush", 101, 21, 85.5));
        ls.add(new Student("Rahul", 102, 20, 78.5));
        ls.add(new Student("Aman", 103, 21, 90.0));
        ls.add(new Student("Priya", 104, 20, 88.5));
        ls.add(new Student("Neha", 105, 22, 76.0));

        // 1. Comparable: sort by name
        Collections.sort(ls);

        System.out.println("Sorted by Name (Comparable):");

        for (Student s : ls) {
            s.display();
        }

        // 2. Comparator: sort by marks
        Collections.sort(ls, new SortByMarks());

        System.out.println("Sorted by Marks (Comparator):");

        for (Student s : ls) {
            s.display();
        }

        System.out.println("Total Students: " + ls.size());
    }
}
