import java.util.*;

// ================== ENTITY ==================
class SchoolStudent {
    private String id;
    private String name;
    private int age;
    private double gpa;

    public SchoolStudent(String id, String name, int age, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGpa() { return gpa; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    @Override
    public String toString() {
        return "ID:" + id + " | Name:" + name + " | Age:" + age + " | GPA:" + gpa;
    }
}

// ================== REPOSITORY ==================
class StudentRepository {
    private List<SchoolStudent> students = new ArrayList<>();

    public void add(SchoolStudent s) {
        students.add(s);
    }

    public void removeById(String id) {
        students.removeIf(s -> s.getId().equals(id));
    }

    public SchoolStudent findById(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<SchoolStudent> findByName(String name) {
        return students.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .toList();
    }

    public List<SchoolStudent> findAll() {
        return new ArrayList<>(students);
    }

    public List<SchoolStudent> findExcellent() {
        return students.stream()
                .filter(s -> s.getGpa() > 8.0)
                .toList();
    }

    public void sortByName() {
        students.sort(Comparator.comparing(SchoolStudent::getName));
    }

    public void sortByGpaDesc() {
        students.sort((a, b) -> Double.compare(b.getGpa(), a.getGpa()));
    }
}

// ================== MENU ==================
class StudentMenu {
    private Scanner sc;
    private StudentRepository repo;

    public StudentMenu(Scanner sc, StudentRepository repo) {
        this.sc = sc;
        this.repo = repo;
    }

    public void show() {
        int choice = 0;
        while (choice != 9) {
            System.out.println("\n--- QUAN LY SINH VIEN ---");
            System.out.println("1. Them SV");
            System.out.println("2. Xoa SV");
            System.out.println("3. Cap nhat SV");
            System.out.println("4. Hien thi tat ca SV");
            System.out.println("5. Tim SV theo ten");
            System.out.println("6. Tim SV GPA > 8");
            System.out.println("7. Sap xep theo ten");
            System.out.println("8. Sap xep theo GPA");
            System.out.println("9. Quay lai");
            System.out.print("Chon: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> removeStudent();
                case 3 -> updateStudent();
                case 4 -> repo.findAll().forEach(System.out::println);
                case 5 -> searchByName();
                case 6 -> repo.findExcellent().forEach(System.out::println);
                case 7 -> { repo.sortByName(); System.out.println("Đã sắp xếp theo tên."); }
                case 8 -> { repo.sortByGpaDesc(); System.out.println("Đã sắp xếp theo GPA."); }
            }
        }
    }

    private void addStudent() {
        System.out.print("Nhap id: ");
        String id = sc.nextLine();
        System.out.print("Nhap ten: ");
        String name = sc.nextLine();
        System.out.print("Nhap tuoi: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap GPA: ");
        double gpa = Double.parseDouble(sc.nextLine());
        repo.add(new SchoolStudent(id, name, age, gpa));
    }

    private void removeStudent() {
        System.out.print("Nhap id can xoa: ");
        String id = sc.nextLine();
        repo.removeById(id);
    }

    private void updateStudent() {
        System.out.print("Nhap id can cap nhat: ");
        String id = sc.nextLine();
        SchoolStudent s = repo.findById(id);
        if (s == null) {
            System.out.println("Không tìm thấy SV!");
            return;
        }
        System.out.print("Nhap ten moi: ");
        s.setName(sc.nextLine());
        System.out.print("Nhap tuoi moi: ");
        s.setAge(Integer.parseInt(sc.nextLine()));
        System.out.print("Nhap GPA moi: ");
        s.setGpa(Double.parseDouble(sc.nextLine()));
    }

    private void searchByName() {
        System.out.print("Nhap ten: ");
        String name = sc.nextLine();
        repo.findByName(name).forEach(System.out::println);
    }
}

// ================== MAIN ==================
public class SchoolProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentRepository studentRepo = new StudentRepository();
        StudentMenu studentMenu = new StudentMenu(sc, studentRepo);

        int menu = 0;
        while (menu != 99) {
            System.out.println("\n============= MENU CHINH =============");
            System.out.println("1. Quan ly Sinh vien");
            System.out.println("99. Thoat");
            System.out.print("Nhap lua chon: ");
            menu = Integer.parseInt(sc.nextLine());

            if (menu == 1) {
                studentMenu.show();
            }
        }
    }
}
