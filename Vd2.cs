
using System;
using System.Collections.Generic;
using System.Linq;
// Student.cs
public class Student
{
    public string Id { get; }
    public string Name { get; private set; }
    public int Age { get; private set; }
    public double GPA { get; private set; }

    public Student(string id, string name, int age, double gpa)
    {
        Id = id;
        Name = name;
        Age = age;
        GPA = gpa;
    }

    public void Update(string name, int age, double gpa)
    {
        Name = name;
        Age = age;
        GPA = gpa;
    }

    public override string ToString()
    {
        return $"ID:{Id} | Name:{Name} | Age:{Age} | GPA:{GPA}";
    }
}
// StudentService.cs

public class StudentService
{
    private readonly List<Student> _students = new();

    public void Add(Student s) => _students.Add(s);
    public void Remove(string id) => _students.RemoveAll(s => s.Id == id);
    public void Update(string id, string newName, int newAge, double newGpa)
    {
        var s = _students.FirstOrDefault(st => st.Id == id);
        if (s != null) s.Update(newName, newAge, newGpa);
    }
    public void ShowAll() => _students.ForEach(Console.WriteLine);
    public void FindByName(string name)
    {
        var result = _students.Where(s => s.Name.Equals(name, StringComparison.OrdinalIgnoreCase));
        foreach (var s in result) Console.WriteLine(s);
    }
    public void ShowExcellent()
    {
        var result = _students.Where(s => s.GPA > 8.0);
        foreach (var s in result) Console.WriteLine("Sinh viên giỏi: " + s);
    }
    public void SortByName() => _students.Sort((a, b) => a.Name.CompareTo(b.Name));
    public void SortByGpa() => _students.Sort((a, b) => b.GPA.CompareTo(a.GPA));
}
// Program.cs

public class BadSchoolProgram
{
    private static StudentService studentService = new();

    public static void Main(string[] args)
    {
        int menu = 0;
        while (menu != 99)
        {
            Console.WriteLine("============= MENU CHÍNH =============");
            Console.WriteLine("1. Quản lý Sinh viên");
            Console.WriteLine("99. Thoát");
            Console.Write("Nhập lựa chọn: ");
            menu = int.Parse(Console.ReadLine() ?? "0");

            switch (menu)
            {
                case 1: StudentMenu(); break;
                case 99: Console.WriteLine("Thoát chương trình."); break;
                default: Console.WriteLine("Lựa chọn không hợp lệ."); break;
            }
        }
    }

    private static void StudentMenu()
    {
        int menu;
        do
        {
            Console.WriteLine("--- QUẢN LÝ SINH VIÊN ---");
            Console.WriteLine("1. Thêm SV");
            Console.WriteLine("2. Xóa SV");
            Console.WriteLine("3. Cập nhật SV");
            Console.WriteLine("4. Hiển thị tất cả SV");
            Console.WriteLine("5. Tìm SV theo tên");
            Console.WriteLine("6. Tìm SV GPA > 8");
            Console.WriteLine("7. Sắp xếp theo tên");
            Console.WriteLine("8. Sắp xếp theo GPA");
            Console.WriteLine("9. Quay lại");
            Console.Write("Nhập lựa chọn: ");
            menu = int.Parse(Console.ReadLine() ?? "0");

            switch (menu)
            {
                case 1: AddStudent(); break;
                case 2: RemoveStudent(); break;
                case 3: UpdateStudent(); break;
                case 4: studentService.ShowAll(); break;
                case 5: FindStudentByName(); break;
                case 6: studentService.ShowExcellent(); break;
                case 7: studentService.SortByName(); Console.WriteLine("Đã sắp xếp theo tên."); break;
                case 8: studentService.SortByGpa(); Console.WriteLine("Đã sắp xếp theo GPA."); break;
                case 9: Console.WriteLine("Quay lại menu chính."); break;
                default: Console.WriteLine("Lựa chọn không hợp lệ."); break;
            }
        } while (menu != 9);
    }

    private static void AddStudent()
    {
        Console.Write("Nhập id: ");
        string id = Console.ReadLine()!;
        Console.Write("Nhập tên: ");
        string name = Console.ReadLine()!;
        Console.Write("Nhập tuổi: ");
        int age = int.Parse(Console.ReadLine() ?? "0");
        Console.Write("Nhập GPA: ");
        double gpa = double.Parse(Console.ReadLine() ?? "0");
        studentService.Add(new Student(id, name, age, gpa));
    }

    private static void RemoveStudent()
    {
        Console.Write("Nhập id cần xóa: ");
        string id = Console.ReadLine()!;
        studentService.Remove(id);
    }

    private static void UpdateStudent()
    {
        Console.Write("Nhập id cần cập nhật: ");
        string id = Console.ReadLine()!;
        Console.Write("Nhập tên mới: ");
        string name = Console.ReadLine()!;
        Console.Write("Nhập tuổi mới: ");
        int age = int.Parse(Console.ReadLine() ?? "0");
        Console.Write("Nhập GPA mới: ");
        double gpa = double.Parse(Console.ReadLine() ?? "0");
        studentService.Update(id, name, age, gpa);
    }

    private static void FindStudentByName()
    {
        Console.Write("Nhập tên: ");
        string name = Console.ReadLine()!;
        studentService.FindByName(name);
    }
}
