// BadSchoolProgram.cs
// Chương trình quản lý trường học bằng C# cực kỳ BAD CODE
// Gồm: Sinh viên, Giáo viên, Môn học, Đăng ký, Điểm
// Tất cả lưu bằng List<string> kiểu "id|field1|field2|..."

using System;
using System.Collections.Generic;
public class student
{
    public string Id {get;}
    public string Name {get; private set;}
    public string Age {get; private set;}
    public double GPA {get; private set;}
    public Student(string id, string name, string age, double gpa)
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
public class StudentService
{
    public void Add(Student s) => _student.Add(s);
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
        foreach (var s in result) Console.WriteLine("Sinh vien gioi: " + s);
    }
    public void SortByName() => _students.Sort((a, b) => a.Name.CompareTo(b.Name));
    public void SortByGpa() => _students.Sort((a, b) => b.GPA.CompareTo(a.GPA));
}
public class BadSchoolProgram
{
    public static void Main(string[] args)
    {   
        int menu = 0;
        while (menu != 99)
        {
            Console.WriteLine("============= MENU CHINH =============");
            Console.WriteLine("1. Quan ly Sinh vien");
            Console.WriteLine("2. Quan ly Giao vien");
            Console.WriteLine("3. Quan ly Mon hoc");
            Console.WriteLine("4. Quan ly Dang ky hoc");
            Console.WriteLine("5. Quan ly Diem");
            Console.WriteLine("6. Bao cao tong hop");
            Console.WriteLine("99. Thoat");
            Console.Write("Nhap lua chon: ");
            menu = int.Parse(Console.ReadLine());
            
            switch (menu)
            {
                case 1:
                    StudentMenu();
                    break;
                case 99:
                    Console.WriteLine("Thoat chuong trinh.");
                    break;
                default:
                    Console.WriteLine("Lua chon khong hop le.");
                    break;
            }
        } while (menu != 99);
    }
    private static void StudentMenu()
    {
        int menu;
        do
        {
            Console.WriteLine("--- QUAN LY SINH VIEN ---");
            Console.WriteLine("1. Them SV");
            Console.WriteLine("2. Xoa SV");
            Console.WriteLine("3. Cap nhat SV");
            Console.WriteLine("4. Hien thi tat ca SV");
            Console.WriteLine("5. Tim SV theo ten");
            Console.WriteLine("6. Tim SV GPA > 8");
            Console.WriteLine("7. Sap xep theo ten");
            Console.WriteLine("8. Sap xep theo GPA");
            Console.WriteLine("9. Quay lai");
            menu = int.Parse(Console.ReadLine() ?? "0");

            switch (menu)
            {
                case 1: AddStudent(); break;
                case 2: RemoveStudent(); break;
                case 3: UpdateStudent(); break;
                case 4: studentService.ShowAll(); break;
                case 5: FindStudentByName(); break;
                case 6: studentService.ShowExcellent(); break;
                case 7: studentService.SortByName(); Console.WriteLine("Da sap xep theo ten."); break;
                case 8: studentService.SortByGpa(); Console.WriteLine("Da sap xep theo GPA."); break;
                case 9: Console.WriteLine("Quay lai menu chinh."); break;
                default: Console.WriteLine("Lua chon khong hop le."); break;
            }
        } while (menu != 9);
    }
    private static void AddStudent()
    {
        Console.Write("Nhap id: ");
        string id = Console.ReadLine()!;
        Console.Write("Nhap ten: ");
        string name = Console.ReadLine()!;
        Console.Write("Nhap tuoi: ");
        int age = int.Parse(Console.ReadLine() ?? "0");
        Console.Write("Nhap GPA: ");
        double gpa = double.Parse(Console.ReadLine() ?? "0");
        studentService.Add(new Student(id, name, age, gpa));
    }

    private static void RemoveStudent()
    {
        Console.Write("Nhap id can xoa: ");
        string id = Console.ReadLine()!;
        studentService.Remove(id);
    }

    private static void UpdateStudent()
    {
        Console.Write("Nhap id can cap nhat: ");
        string id = Console.ReadLine()!;
        Console.Write("Nhap ten moi: ");
        string name = Console.ReadLine()!;
        Console.Write("Nhap tuoi moi: ");
        int age = int.Parse(Console.ReadLine() ?? "0");
        Console.Write("Nhap GPA moi: ");
        double gpa = double.Parse(Console.ReadLine() ?? "0");
        studentService.Update(id, name, age, gpa);
    }

    private static void FindStudentByName()
    {
        Console.Write("Nhap ten: ");
        string name = Console.ReadLine()!;
        studentService.FindByName(name);
    }
}