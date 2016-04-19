package ru.PDT29_homework.l1.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Family on 19.04.2016.
 */
public class SquareTest {

  @Test
  public void testArea(){
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 25);
  }
}
