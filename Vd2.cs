// Git giữ hộ lịch sử, lỡ dại thì còn đường về!
// _ví dụ 2_ sửa code bẩn thành code sạch BongBaby hehe!
// Clean School Program

using System;
using System.Collections.Generic;
using System.Linq;


class Student
{
    public string Id { get; set; }
    public string Name { get; set; }
    public int Age { get; set; }
    public double Gpa { get; set; }
    public override string ToString() => $"[Student] ID:{Id} Name:{Name} Age:{Age} GPA:{Gpa}";
}

class Teacher
{
    public string Id { get; set; }
    public string Name { get; set; }
    public string Major { get; set; }
    public override string ToString() => $"[Teacher] ID:{Id} Name:{Name} Major:{Major}";
}

class Course
{
    public string Id { get; set; }
    public string Name { get; set; }
    public int Credits { get; set; }
    public override string ToString() => $"[Course] ID:{Id} Name:{Name} Credits:{Credits}";
}

class Enrollment
{
    public Student Student { get; set; }
    public Course Course { get; set; }
    public override string ToString() => $"[Enroll] {Student.Name} -> {Course.Name}";
}

class Grade
{
    public Student Student { get; set; }
    public Course Course { get; set; }
    public double Score { get; set; }
    public override string ToString() => $"[Grade] {Student.Name} {Course.Name} = {Score}";
}

interface IManager<T>
{
    void Add(T item);
    void Remove(string id);
    void ShowAll();
    T FindById(string id);
}

class StudentManager : IManager<Student>
{
    private List<Student> students = new();

    public void Add(Student s) => students.Add(s);
    public void Remove(string id) => students.RemoveAll(s => s.Id == id);
    public Student FindById(string id) => students.FirstOrDefault(s => s.Id == id);
    public void ShowAll() => students.ForEach(Console.WriteLine);

    public void FindByName(string name) =>
        students.Where(s => s.Name.Contains(name, StringComparison.OrdinalIgnoreCase))
                .ToList().ForEach(Console.WriteLine);

    public void SortByName() { students = students.OrderBy(s => s.Name).ToList(); ShowAll(); }
    public void SortByGpa() { students = students.OrderByDescending(s => s.Gpa).ToList(); ShowAll(); }
}

class TeacherManager : IManager<Teacher>
{
    private List<Teacher> teachers = new();

    public void Add(Teacher t) => teachers.Add(t);
    public void Remove(string id) => teachers.RemoveAll(t => t.Id == id);
    public Teacher FindById(string id) => teachers.FirstOrDefault(t => t.Id == id);
    public void ShowAll() => teachers.ForEach(Console.WriteLine);
}

class CourseManager : IManager<Course>
{
    private List<Course> courses = new();

    public void Add(Course c) => courses.Add(c);
    public void Remove(string id) => courses.RemoveAll(c => c.Id == id);
    public Course FindById(string id) => courses.FirstOrDefault(c => c.Id == id);
    public void ShowAll() => courses.ForEach(Console.WriteLine);
}

class EnrollmentManager : IManager<Enrollment>
{
    private List<Enrollment> enrollments = new();

    public void Add(Enrollment e) => enrollments.Add(e);
    public void Remove(string id) => enrollments.RemoveAll(e => e.Student.Id == id);
    public Enrollment FindById(string id) => enrollments.FirstOrDefault(e => e.Student.Id == id);
    public void ShowAll() => enrollments.ForEach(Console.WriteLine);
}

class GradeManager : IManager<Grade>
{
    private List<Grade> grades = new();

    public void Add(Grade g) => grades.Add(g);
    public void Remove(string id) => grades.RemoveAll(gr => gr.Student.Id == id);
    public Grade FindById(string id) => grades.FirstOrDefault(gr => gr.Student.Id == id);
    public void ShowAll() => grades.ForEach(Console.WriteLine);
}



class Menu
{
    private readonly StudentManager sm = new();
    private readonly TeacherManager tm = new();
    private readonly CourseManager cm = new();
    private readonly EnrollmentManager em = new();
    private readonly GradeManager gm = new();

    public void Run()
    {
        int menu = 0;
        while (menu != 99)
        {
            Console.WriteLine("\n=== MENU ===");
            Console.WriteLine("1. Quan ly Sinh vien");
            Console.WriteLine("2. Quan ly Giao vien");
            Console.WriteLine("3. Quan ly Mon hoc");
            Console.WriteLine("4. Quan ly Dang ky hoc");
            Console.WriteLine("5. Quan ly Diem");
            Console.WriteLine("99. Thoat");
            Console.Write("Chon: ");
            menu = ReadInt();

            switch (menu)
            {
                case 1: StudentMenu(); break;
                case 2: tm.ShowAll(); break;
                case 3: cm.ShowAll(); break;
                case 4: em.ShowAll(); break;
                case 5: gm.ShowAll(); break;
            }
        }
    }

    private void StudentMenu()
    {
        int smenu = 0;
        while (smenu != 9)
        {
            Console.WriteLine("\n--- QUAN LY SINH VIEN ---");
            Console.WriteLine("1. Them SV");
            Console.WriteLine("2. Xoa SV");
            Console.WriteLine("3. Hien thi tat ca");
            Console.WriteLine("4. Tim theo ten");
            Console.WriteLine("5. Sap xep theo ten");
            Console.WriteLine("6. Sap xep theo GPA");
            Console.WriteLine("9. Quay lai");
            Console.Write("Chon: ");
            smenu = ReadInt();

            switch (smenu)
            {
                case 1:
                    Console.Write("Nhap id: "); string id = Console.ReadLine();
                    Console.Write("Nhap ten: "); string name = Console.ReadLine();
                    Console.Write("Nhap tuoi: "); int age = ReadInt();
                    Console.Write("Nhap GPA: "); double gpa = ReadDouble();
                    sm.Add(new Student { Id = id, Name = name, Age = age, Gpa = gpa });
                    break;
                case 2:
                    Console.Write("Nhap id can xoa: ");
                    sm.Remove(Console.ReadLine());
                    break;
                case 3: sm.ShowAll(); break;
                case 4:
                    Console.Write("Nhap ten: ");
                    sm.FindByName(Console.ReadLine());
                    break;
                case 5: sm.SortByName(); break;
                case 6: sm.SortByGpa(); break;
            }
        }
    }

    private int ReadInt() => int.TryParse(Console.ReadLine(), out var x) ? x : 0;
    private double ReadDouble() => double.TryParse(Console.ReadLine(), out var x) ? x : 0.0;
}

public class CleanSchoolProgram
{
    public static void Main(string[] args)
    {
        new Menu().Run();
    }
}
