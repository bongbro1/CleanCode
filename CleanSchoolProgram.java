// Git giữ hộ lịch sử, lỡ dại thì còn đường về!
// _ví dụ 1_ sửa code bẩn thành code sạch BongBaby hehe!
// Clean OOP version với Menu tổng hợp
<<<<<<< HEAD
// test demo git
//duyphanbg1

>>>>>>> ee20f8830a880dd89a797bb3b3c9f16af0f7364f
import java.util.*;

interface IManager<T> {
    void add(T item);
    void removeById(String id);
    T findById(String id);
    void showAll();
}

// ===== Entities =====
class Student {
    String id, name;
    int age;
    double gpa;

    Student(String id, String name, int age, double gpa) {
        this.id = id; this.name = name; this.age = age; this.gpa = gpa;
    }
    @Override public String toString() {
        return "Student[ID=" + id + ", Name=" + name + ", Age=" + age + ", GPA=" + gpa + "]";
    }
}

class Teacher {
    String id, name, major;
    Teacher(String id, String name, String major) {
        this.id = id; this.name = name; this.major = major;
    }
    @Override public String toString() {
        return "Teacher[ID=" + id + ", Name=" + name + ", Major=" + major + "]";
    }
}

class Course {
    String id, name; int credits;
    Course(String id, String name, int credits) {
        this.id = id; this.name = name; this.credits = credits;
    }
    @Override public String toString() {
        return "Course[ID=" + id + ", Name=" + name + ", Credits=" + credits + "]";
    }
}

class Enrollment {
    Student student; Course course;
    Enrollment(Student s, Course c) { this.student = s; this.course = c; }
    @Override public String toString() {
        return "Enrollment[" + student.name + " -> " + course.name + "]";
    }
}

class Grade {
    Student student; Course course; double score;
    Grade(Student s, Course c, double sc) { this.student = s; this.course = c; this.score = sc; }
    @Override public String toString() {
        return "Grade[" + student.name + " - " + course.name + " = " + score + "]";
    }
}

// ===== Managers =====
class StudentManager implements IManager<Student> {
    private List<Student> list = new ArrayList<>();
    public void add(Student s) { list.add(s); }
    public void removeById(String id) { list.removeIf(s -> s.id.equals(id)); }
    public Student findById(String id) { return list.stream().filter(s -> s.id.equals(id)).findFirst().orElse(null); }
    public void showAll() { list.forEach(System.out::println); }

    public void sortByName() { list.sort(Comparator.comparing(s -> s.name)); }
    public void sortByGpa() { list.sort((a,b)->Double.compare(b.gpa,a.gpa)); }
}

class TeacherManager implements IManager<Teacher> {
    private List<Teacher> list = new ArrayList<>();
    public void add(Teacher t) { list.add(t); }
    public void removeById(String id) { list.removeIf(t -> t.id.equals(id)); }
    public Teacher findById(String id) { return list.stream().filter(t -> t.id.equals(id)).findFirst().orElse(null); }
    public void showAll() { list.forEach(System.out::println); }
}
//duy//
class CourseManager implements IManager<Course> {
    private List<Course> list = new ArrayList<>();
    public void add(Course c) { list.add(c); }
    public void removeById(String id) { list.removeIf(c -> c.id.equals(id)); }
    public Course findById(String id) { return list.stream().filter(c -> c.id.equals(id)).findFirst().orElse(null); }
    public void showAll() { list.forEach(System.out::println); }
}

class EnrollmentManager implements IManager<Enrollment> {
    private List<Enrollment> list = new ArrayList<>();
    public void add(Enrollment e) { list.add(e); }
    public void removeById(String sid) { list.removeIf(e -> e.student.id.equals(sid)); }
    public Enrollment findById(String sid) { return list.stream().filter(e -> e.student.id.equals(sid)).findFirst().orElse(null); }
    public void showAll() { list.forEach(System.out::println); }
}

class GradeManager implements IManager<Grade> {
    private List<Grade> list = new ArrayList<>();
    public void add(Grade g) { list.add(g); }
    public void removeById(String sid) { list.removeIf(gr -> gr.student.id.equals(sid)); }
    public Grade findById(String sid) { return list.stream().filter(gr -> gr.student.id.equals(sid)).findFirst().orElse(null); }
    public void showAll() { list.forEach(System.out::println); }
}

// ===== Menu =====
class Menu {
    private Scanner sc = new Scanner(System.in);
    private StudentManager sm = new StudentManager();
    private TeacherManager tm = new TeacherManager();
    private CourseManager cm = new CourseManager();
    private EnrollmentManager em = new EnrollmentManager();
    private GradeManager gm = new GradeManager();

    public void run() {
        int choice = 0;
        while (choice != 99) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Quan ly Sinh vien");
            System.out.println("2. Quan ly Giao vien");
            System.out.println("3. Quan ly Mon hoc");
            System.out.println("4. Quan ly Dang ky hoc");
            System.out.println("5. Quan ly Diem");
            System.out.println("6. Bao cao tong hop");
            System.out.println("99. Thoat");
            System.out.print("Chon: ");
            choice = readInt();

            switch (choice) {
                case 1 -> studentMenu();
                case 2 -> teacherMenu();
                case 3 -> courseMenu();
                case 4 -> enrollmentMenu();
                case 5 -> gradeMenu();
                case 6 -> report();
            }
        }
    }

    private void studentMenu() {
        int c=0;
        while(c!=9) {
            System.out.println("\n--- STUDENT MENU ---");
            System.out.println("1. Them SV"); System.out.println("2. Xoa SV");
            System.out.println("3. Hien thi tat ca"); System.out.println("4. Sap xep theo ten");
            System.out.println("5. Sap xep theo GPA"); System.out.println("9. Back");
            c=readInt();
            switch(c){
                case 1 -> {
                    System.out.print("ID: "); String id=sc.nextLine();
                    System.out.print("Name: "); String name=sc.nextLine();
                    System.out.print("Age: "); int age=readInt();
                    System.out.print("GPA: "); double gpa=readDouble();
                    sm.add(new Student(id,name,age,gpa));
                }
                case 2 -> { System.out.print("Nhap id: "); sm.removeById(sc.nextLine()); }
                case 3 -> sm.showAll();
                case 4 -> { sm.sortByName(); sm.showAll(); }
                case 5 -> { sm.sortByGpa(); sm.showAll(); }
            }
        }
    }

    private void teacherMenu() {
        int c=0;
        while(c!=9) {
            System.out.println("\n--- TEACHER MENU ---");
            System.out.println("1. Them GV"); System.out.println("2. Xoa GV");
            System.out.println("3. Hien thi tat ca"); System.out.println("9. Back");
            c=readInt();
            switch(c){
                case 1 -> {
                    System.out.print("ID: "); String id=sc.nextLine();
                    System.out.print("Name: "); String name=sc.nextLine();
                    System.out.print("Major: "); String major=sc.nextLine();
                    tm.add(new Teacher(id,name,major));
                }
                case 2 -> { System.out.print("Nhap id: "); tm.removeById(sc.nextLine()); }
                case 3 -> tm.showAll();
            }
        }
    }

    private void courseMenu() {
        int c=0;
        while(c!=9) {
            System.out.println("\n--- COURSE MENU ---");
            System.out.println("1. Them MH"); System.out.println("2. Xoa MH");
            System.out.println("3. Hien thi tat ca"); System.out.println("9. Back");
            c=readInt();
            switch(c){
                case 1 -> {
                    System.out.print("ID: "); String id=sc.nextLine();
                    System.out.print("Name: "); String name=sc.nextLine();
                    System.out.print("Credits: "); int cr=readInt();
                    cm.add(new Course(id,name,cr));
                }
                case 2 -> { System.out.print("Nhap id: "); cm.removeById(sc.nextLine()); }
                case 3 -> cm.showAll();
            }
        }
    }

    private void enrollmentMenu() {
        int c=0;
        while(c!=9) {
            System.out.println("\n--- ENROLLMENT MENU ---");
            System.out.println("1. Dang ky"); System.out.println("2. Huy dang ky");
            System.out.println("3. Hien thi tat ca"); System.out.println("9. Back");
            c=readInt();
            switch(c){
                case 1 -> {
                    System.out.print("Student ID: "); Student s=sm.findById(sc.nextLine());
                    System.out.print("Course ID: "); Course co=cm.findById(sc.nextLine());
                    if(s!=null && co!=null) em.add(new Enrollment(s,co));
                }
                case 2 -> { System.out.print("Nhap student id: "); em.removeById(sc.nextLine()); }
                case 3 -> em.showAll();
            }
        }
    }

    private void gradeMenu() {
        int c=0;
        while(c!=9) {
            System.out.println("\n--- GRADE MENU ---");
            System.out.println("1. Nhap diem"); System.out.println("2. Xoa diem theo SV");
            System.out.println("3. Hien thi tat ca"); System.out.println("9. Back");
            c=readInt();
            switch(c){
                case 1 -> {
                    System.out.print("Student ID: "); Student s=sm.findById(sc.nextLine());
                    System.out.print("Course ID: "); Course co=cm.findById(sc.nextLine());
                    System.out.print("Score: "); double scs=readDouble();
                    if(s!=null && co!=null) gm.add(new Grade(s,co,scs));
                }
                case 2 -> { System.out.print("Nhap student id: "); gm.removeById(sc.nextLine()); }
                case 3 -> gm.showAll();
            }
        }
    }

    private void report() {
        System.out.println("\n=== BAO CAO TONG HOP ===");
        sm.showAll();
        em.showAll();
        gm.showAll();
    }

    private int readInt() {
        try { return Integer.parseInt(sc.nextLine()); } catch(Exception e){ return 0; }
    }
    private double readDouble() {
        try { return Double.parseDouble(sc.nextLine()); } catch(Exception e){ return 0; }
    }
}

// ===== Main =====
public class CleanSchoolProgram {
    public static void main(String[] args) {
        new Menu().run();
    }
}
