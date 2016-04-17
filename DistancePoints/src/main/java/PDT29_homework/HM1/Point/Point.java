package PDT29_homework.HM1.Point;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y){
    this.x = x;
    this.y = y;
  }

  public double distance(){
    double dx = this.x - x;
    double dy = this.y - y;
    return Math.sqrt(dx*dx+dy*dy);
  }

}
