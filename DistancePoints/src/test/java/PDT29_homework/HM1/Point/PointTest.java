package PDT29_homework.HM1.Point;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Family on 19.04.2016.
 */
public class PointTest {

  @Test
  public void pointTest1(){
    Point p1 = new Point(5, 20);
    Point p2 = new Point(6, 12);
    Assert.assertEquals(p1.distance(p2), 8.06225774829855);
  }

  @Test
  public void pointTest2(){
    Point p1 = new Point(-8, 12);
    Point p2 = new Point(0.6, -12);
    Assert.assertEquals(p1.distance(p2), 25.494313091354314);
  }
}
