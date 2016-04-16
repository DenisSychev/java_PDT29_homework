package PDT29_homework.HM1.Point;

public class DistancePoint {

  public static void main(String[] args){

    Point p1 = new Point(4, 6);
    Point p2 = new Point(5 ,8);
    System.out.println("Расстояние между точкой 1:(" + p1.x +", "+ p1.y +") и точкой 2:(" + p2.x +", "+ p2.y +") = " +distance(p1, p2));
  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt(((p2.x - p1.x)*2)+((p2.y - p1.y)*2));
  }
}