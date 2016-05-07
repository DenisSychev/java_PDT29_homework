package ru.PDT29_homework.l1.sandbox;

import java.util.ArrayList;
import java.util.List;

public class Collections {

  public static void main(String[] args){

    String[] langs = {"Java", "C#", "Phyton", "PHP"};

    List<String> languages = new ArrayList<>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Phyton");
    languages.add("PHP");

    for (String l : languages){
      System.out.println("Нужно выучить " + l);
    }
  }
}
