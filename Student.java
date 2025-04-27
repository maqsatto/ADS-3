package models;

public class Student implements Comparable<Student> {
    private String name;
    private int id;
    
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id == student.id && name.equals(student.name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode() + id;
    }
    
    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", id=" + id + '}';
    }
    
    @Override
    public int compareTo(Student other) {
        // Сначала сравниваем по id
        int idComparison = Integer.compare(this.id, other.id);
        if (idComparison != 0) {
            return idComparison;
        }
        
        // Если id одинаковы, сравниваем по имени
        return this.name.compareTo(other.name);
    }
}

