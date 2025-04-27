import models.*;
class MyTestingClass {
    public static void main(String[] args) {
        MyHashTable<Student, Integer> table = new MyHashTable<>(10);
        
        for (int i = 0; i < 10000; i++) {
            String name = "Student" + i;
            int id = i;
            Student student = new Student(name, id);
            table.put(student, i);
        }
        
        countElementsInBuckets(table);
    }
    
    private static int hashCode(Student student) {
        String name = student.toString();
        int hash = 37;
        for (int i = 0; i < name.length(); i++) {
            hash = hash * 31 + name.charAt(i);
        }
        return hash;
    }
    
    private static void countElementsInBuckets(MyHashTable<Student, Integer> table) {
        System.out.println("Размер хеш-таблицы: " + table.size());
        System.out.println("Распределение элементов по бакетам:");
        
        table.printBucketStats();
    }
}