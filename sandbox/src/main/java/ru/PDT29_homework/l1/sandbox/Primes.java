package ru.PDT29_homework.l1.sandbox;

public class Primes {

  public static boolean isPrime(int n){
    for (int i = 2; i < n; i++){
      if (n % i == 0){
        return false;
      }
    }
    return true;
  }

  public static boolean isPrime(long n){
    for (long i = 2; i < n; i++){
      if (n % i == 0){
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeWhile(int n){
    int i = 2;
    while (i < n && i != 0){
      i++;
    }
    return n == i;
  }
}
