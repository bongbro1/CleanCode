import java.util.*;

class Student {
    String id;
    String name;
    int age;
    double gpa;

    Student(String id, String name, int age, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "ID:" + id + " Name:" + name + " Age:" + age + " GPA:" + gpa;
    }
}

class Teacher {
    String id;
    String name;
    String major;

    Teacher(String id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }

    @Override
    public String toString() {
        return "ID:" + id + " Name:" + name + " Major:" + major;
    }
}

class Course {
    String id;
    String name;
    int credit;

    Course(String id, String name, int credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "ID:" + id + " Name:" + name + " Credit:" + credit;
    }
}

class Enrollment {
    String studentId;
    String courseId;

    Enrollment(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Student:" + studentId + " Course:" + courseId;
    }
}

class Grade {
    String studentId;
    String courseId;
    double score;

    Grade(String studentId, String courseId, double score) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student:" + studentId + " Course:" + courseId + " Score:" + score;
    }
}

public class SchoolProgram {
    private static Scanner sc = new Scanner(System.in);

    private static List<Student> students = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static List<Enrollment> enrollments = new ArrayList<>();
    private static List<Grade> grades = new ArrayList<>();

    public static void main(String[] args) {
        int menu;
        do {
            System.out.println("============= MENU CHINH =============");
            System.out.println("1. Quan ly Sinh vien");
            System.out.println("2. Quan ly Giao vien");
            System.out.println("3. Quan ly Mon hoc");
            System.out.println("4. Quan ly Dang ky hoc");
            System.out.println("5. Quan ly Diem");
            System.out.println("6. Bao cao tong hop");
            System.out.println("99. Thoat");
            System.out.print("Nhap lua chon: ");
            menu = Integer.parseInt(sc.nextLine());

            switch (menu) {
                case 1 -> studentMenu();
                case 2 -> teacherMenu();
                case 3 -> courseMenu();
                case 4 -> enrollmentMenu();
                case 5 -> gradeMenu();
                case 6 -> report();
            }
        } while (menu != 99);
    }

    private static void studentMenu() {
        int smenu;
        do {
            System.out.println("--- QUAN LY SINH VIEN ---");
            System.out.println("1. Them SV");
            System.out.println("2. Xoa SV");
            System.out.println("3. Cap nhat SV");
            System.out.println("4. Hien thi tat ca SV");
            System.out.println("5. Tim SV theo ten");
            System.out.println("6. Tim SV GPA > 8");
            System.out.println("7. Sap xep theo ten");
            System.out.println("8. Sap xep theo GPA");
            System.out.println("9. Quay lai");
            smenu = Integer.parseInt(sc.nextLine());

            switch (smenu) {
                case 1 -> {
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Ten: ");
                    String name = sc.nextLine();
                    System.out.print("Tuoi: ");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.print("GPA: ");
                    double gpa = Double.parseDouble(sc.nextLine());
                    students.add(new Student(id, name, age, gpa));
                }
                case 2 -> {
                    System.out.print("Nhap id can xoa: ");
                    String id = sc.nextLine();
                    students.removeIf(s -> s.id.equals(id));
                }
                case 3 -> {
                    System.out.print("Nhap id can cap nhat: ");
                    String id = sc.nextLine();
                    for (Student s : students) {
                        if (s.id.equals(id)) {
                            System.out.print("Ten moi: ");
                            s.name = sc.nextLine();
                            System.out.print("Tuoi moi: ");
                            s.age = Integer.parseInt(sc.nextLine());
                            System.out.print("GPA moi: ");
                            s.gpa = Double.parseDouble(sc.nextLine());
                        }
                    }
                }
                case 4 -> students.forEach(System.out::println);
                case 5 -> {
                    System.out.print("Nhap ten: ");
                    String name = sc.nextLine();
                    students.stream().filter(s -> s.name.equals(name)).forEach(System.out::println);
                }
                case 6 -> students.stream().filter(s -> s.gpa > 8.0).forEach(System.out::println);
                case 7 -> students.sort(Comparator.comparing(s -> s.name));
                case 8 -> students.sort(Comparator.comparingDouble((Student s) -> s.gpa).reversed());
            }
        } while (smenu != 9);
    }

    private static void teacherMenu() {
        int tmenu;
        do {
            System.out.println("--- QUAN LY GIAO VIEN ---");
            System.out.println("1. Them GV");
            System.out.println("2. Xoa GV");
            System.out.println("3. Cap nhat GV");
            System.out.println("4. Hien thi GV");
            System.out.println("9. Quay lai");
            tmenu = Integer.parseInt(sc.nextLine());

            switch (tmenu) {
                case 1 -> {
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Ten: ");
                    String name = sc.nextLine();
                    System.out.print("Chuyen mon: ");
                    String major = sc.nextLine();
                    teachers.add(new Teacher(id, name, major));
                }
                case 2 -> {
                    System.out.print("Nhap id GV can xoa: ");
                    String id = sc.nextLine();
                    teachers.removeIf(t -> t.id.equals(id));
                }
                case 3 -> {
                    System.out.print("Nhap id GV cap nhat: ");
                    String id = sc.nextLine();
                    for (Teacher t : teachers) {
                        if (t.id.equals(id)) {
                            System.out.print("Ten moi: ");
                            t.name = sc.nextLine();
                            System.out.print("Chuyen mon moi: ");
                            t.major = sc.nextLine();
                        }
                    }
                }
                case 4 -> teachers.forEach(System.out::println);
            }
        } while (tmenu != 9);
    }

    private static void courseMenu() {
        int cmenu;
        do {
            System.out.println("--- QUAN LY MON HOC ---");
            System.out.println("1. Them MH");
            System.out.println("2. Xoa MH");
            System.out.println("3. Cap nhat MH");
            System.out.println("4. Hien thi MH");
            System.out.println("9. Quay lai");
            cmenu = Integer.parseInt(sc.nextLine());

            switch (cmenu) {
                case 1 -> {
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Ten: ");
                    String name = sc.nextLine();
                    System.out.print("Tin chi: ");
                    int credit = Integer.parseInt(sc.nextLine());
                    courses.add(new Course(id, name, credit));
                }
                case 2 -> {
                    System.out.print("Nhap id MH can xoa: ");
                    String id = sc.nextLine();
                    courses.removeIf(c -> c.id.equals(id));
                }
                case 3 -> {
                    System.out.print("Nhap id MH cap nhat: ");
                    String id = sc.nextLine();
                    for (Course c : courses) {
                        if (c.id.equals(id)) {
                            System.out.print("Ten moi: ");
                            c.name = sc.nextLine();
                            System.out.print("Tin chi moi: ");
                            c.credit = Integer.parseInt(sc.nextLine());
                        }
                    }
                }
                case 4 -> courses.forEach(System.out::println);
            }
        } while (cmenu != 9);
    }

    private static void enrollmentMenu() {
        int emenu;
        do {
            System.out.println("--- QUAN LY DANG KY ---");
            System.out.println("1. Dang ky mon hoc");
            System.out.println("2. Huy dang ky");
            System.out.println("3. Hien thi dang ky");
            System.out.println("9. Quay lai");
            emenu = Integer.parseInt(sc.nextLine());

            switch (emenu) {
                case 1 -> {
                    System.out.print("ID SV: ");
                    String sid = sc.nextLine();
                    System.out.print("ID MH: ");
                    String cid = sc.nextLine();
                    enrollments.add(new Enrollment(sid, cid));
                }
                case 2 -> {
                    System.out.print("ID SV: ");
                    String sid = sc.nextLine();
                    System.out.print("ID MH: ");
                    String cid = sc.nextLine();
                    enrollments.removeIf(e -> e.studentId.equals(sid) && e.courseId.equals(cid));
                }
                case 3 -> enrollments.forEach(System.out::println);
            }
        } while (emenu != 9);
    }

    private static void gradeMenu() {
        int gmenu;
        do {
            System.out.println("--- QUAN LY DIEM ---");
            System.out.println("1. Nhap diem");
            System.out.println("2. Cap nhat diem");
            System.out.println("3. Hien thi diem");
            System.out.println("9. Quay lai");
            gmenu = Integer.parseInt(sc.nextLine());

            switch (gmenu) {
                case 1 -> {
                    System.out.print("ID SV: ");
                    String sid = sc.nextLine();
                    System.out.print("ID MH: ");
                    String cid = sc.nextLine();
                    System.out.print("Diem: ");
                    double score = Double.parseDouble(sc.nextLine());
                    grades.add(new Grade(sid, cid, score));
                }
                case 2 -> {
                    System.out.print("ID SV: ");
                    String sid = sc.nextLine();
                    System.out.print("ID MH: ");
                    String cid = sc.nextLine();
                    for (Grade g : grades) {
                        if (g.studentId.equals(sid) && g.courseId.equals(cid)) {
                            System.out.print("Nhap diem moi: ");
                            g.score = Double.parseDouble(sc.nextLine());
                        }
                    }
                }
                case 3 -> grades.forEach(System.out::println);
            }
        } while (gmenu != 9);
    }

    private static void report() {
        System.out.println("=== BAO CAO TONG HOP ===");
        for (Student s : students) {
            System.out.println("Sinh vien: " + s.name);
            for (Enrollment e : enrollments) {
                if (e.studentId.equals(s.id)) {
                    for (Course c : courses) {
                        if (c.id.equals(e.courseId)) {
                            System.out.print(" - Mon hoc: " + c.name);
                            for (Grade g : grades) {
                                if (g.studentId.equals(s.id) && g.courseId.equals(c.id)) {
                                    System.out.print(" | Diem: " + g.score);
                                }
                            }
                            System.out.println();
                        }
                    }
                }
            }
        }
    }
}
