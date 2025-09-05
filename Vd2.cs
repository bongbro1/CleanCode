using System;
using System.Collections.Generic;
using System.Linq;

public class Student
{
    public string Id { get; set; }
    public string Name { get; set; }
    public int Age { get; set; }
    public double GPA { get; set; }

    public override string ToString()
        => $"ID: {Id} | Name: {Name} | Age: {Age} | GPA: {GPA}";
}

public class SchoolManager
{
    private readonly List<Student> students = new();

    public void AddStudent()
    {
        Console.Write("Nhap id: ");
        string id = Console.ReadLine();
        Console.Write("Nhap ten: ");
        string name = Console.ReadLine();
        Console.Write("Nhap tuoi: ");
        int age = int.Parse(Console.ReadLine());
        Console.Write("Nhap GPA: ");
        double gpa = double.Parse(Console.ReadLine());

        students.Add(new Student { Id = id, Name = name, Age = age, GPA = gpa });
        Console.WriteLine("==> Da them sinh vien thanh cong!");
    }

    public void RemoveStudent()
    {
        Console.Write("Nhap id can xoa: ");
        string id = Console.ReadLine();
        var student = students.FirstOrDefault(s => s.Id == id);
        if (student != null)
        {
            students.Remove(student);
            Console.WriteLine("==> Da xoa sinh vien!");
        }
        else Console.WriteLine("Khong tim thay sinh vien.");
    }

    public void UpdateStudent()
    {
        Console.Write("Nhap id can cap nhat: ");
        string id = Console.ReadLine();
        var student = students.FirstOrDefault(s => s.Id == id);
        if (student != null)
        {
            Console.Write("Nhap ten moi: ");
            student.Name = Console.ReadLine();
            Console.Write("Nhap tuoi moi: ");
            student.Age = int.Parse(Console.ReadLine());
            Console.Write("Nhap GPA moi: ");
            student.GPA = double.Parse(Console.ReadLine());
            Console.WriteLine("==> Da cap nhat sinh vien!");
        }
        else Console.WriteLine("Khong tim thay sinh vien.");
    }

    public void DisplayAllStudents()
    {
        if (students.Count == 0) Console.WriteLine("Danh sach rong.");
        else students.ForEach(s => Console.WriteLine(s));
    }

    public void SearchByName()
    {
        Console.Write("Nhap ten: ");
        string name = Console.ReadLine();
        var results = students.Where(s => s.Name.Equals(name, StringComparison.OrdinalIgnoreCase)).ToList();
        if (results.Count == 0) Console.WriteLine("Khong tim thay.");
        else results.ForEach(s => Console.WriteLine(s));
    }

    public void DisplayExcellentStudents()
    {
        var results = students.Where(s => s.GPA > 8).ToList();
        if (results.Count == 0) Console.WriteLine("Khong co sinh vien gioi.");
        else results.ForEach(s => Console.WriteLine("Sinh vien gioi: " + s));
    }

    public void SortByName()
    {
        students.Sort((a, b) => a.Name.CompareTo(b.Name));
        Console.WriteLine("==> Da sap xep theo ten.");
    }

    public void SortByGPA()
    {
        students.Sort((a, b) => b.GPA.CompareTo(a.GPA));
        Console.WriteLine("==> Da sap xep theo GPA.");
    }
}

public class CleanSchoolProgram
{
    public static void Main(string[] args)
    {
        SchoolManager manager = new();
        int menu = 0;
        while (menu != 99)
        {
            Console.WriteLine("\n============= MENU CHINH =============");
            Console.WriteLine("1. Quan ly Sinh vien");
            Console.WriteLine("99. Thoat");
            Console.Write("Nhap lua chon: ");
            int.TryParse(Console.ReadLine(), out menu);

            if (menu == 1)
            {
                int smenu = 0;
                while (smenu != 9)
                {
                    Console.WriteLine("\n--- QUAN LY SINH VIEN ---");
                    Console.WriteLine("1. Them SV");
                    Console.WriteLine("2. Xoa SV");
                    Console.WriteLine("3. Cap nhat SV");
                    Console.WriteLine("4. Hien thi tat ca SV");
                    Console.WriteLine("5. Tim SV theo ten");
                    Console.WriteLine("6. Tim SV GPA > 8");
                    Console.WriteLine("7. Sap xep theo ten");
                    Console.WriteLine("8. Sap xep theo GPA");
                    Console.WriteLine("9. Quay lai");
                    int.TryParse(Console.ReadLine(), out smenu);

                    switch (smenu)
                    {
                        case 1: manager.AddStudent(); break;
                        case 2: manager.RemoveStudent(); break;
                        case 3: manager.UpdateStudent(); break;
                        case 4: manager.DisplayAllStudents(); break;
                        case 5: manager.SearchByName(); break;
                        case 6: manager.DisplayExcellentStudents(); break;
                        case 7: manager.SortByName(); break;
                        case 8: manager.SortByGPA(); break;
                    }
                }
            }
        }
    }
}
