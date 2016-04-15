package ru.PDT29_homework.l1.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("root");

    Square s = new Square(6);
    System.out.println("Площадь квадрата со стороной " + s.l + " равна " + s.area());

    Rectaingle r = new Rectaingle(4, 5);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и "+ r.b + " = " + r.area());
  }

  public static void  hello(String somebody){
    System.out.println("Hello, " + somebody);
  }

}