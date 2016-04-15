package ru.PDT29_homework.l1.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("root");

    double l = 445;
    System.out.println("Площадь квадрата со стороной " + l + " равна " + area(l));

    int a = 43;
    int b = 34;
    System.out.println("Площадь прямоугольника со сторонами " + a + " и "+ b + " = " + area(a, b));
  }

  public static void  hello(String somebody){
    System.out.println("Hello, " + somebody);
  }

  public static double area(Double dig){
    return dig * dig;
  }

  public static int area (int a, int b){
    return a * b;
  }
}