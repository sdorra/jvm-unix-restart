package com.cloudogu.restart;

import java.io.IOException;

public class App {

  public static void main(String[] args) throws IOException {
    System.out.println("restart test ...");
    if (Boolean.getBoolean("restarted")) {
      System.out.println("successfully restarted ...");
    } else {
      System.out.println("issue restart ...");
      Restart.restart();
    }
  }

}
