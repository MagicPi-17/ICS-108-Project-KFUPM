package Rshad1;

import java.util.Scanner;

public class input {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("What is your name:");
        String name = input.nextLine();
        System.out.println("What is your major:");
        String major = input.nextLine();
        System.out.println("What is your GPA (:");
        double gpa = input.nextDouble();
        System.out.println("\nName: " + name + "\nMajor: " + major + "\nGPA: " + gpa);
        input.close();
    }
}
