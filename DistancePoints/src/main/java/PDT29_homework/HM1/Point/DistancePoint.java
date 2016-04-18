package PDT29_homework.HM1.Point;

public class DistancePoint {

  public static void main(String[] args){

    Point p1 = new Point(-2.3, 4);
    Point p2 = new Point(8.5 ,0.7);
    System.out.println("Расстояние между точкой 1(" + p1.x +", "+ p1.y +") и точкой 2(" + p2.x +", "+ p2.y +") = " +p1.distance(p2));
  }


}