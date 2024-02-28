package com.example.variatycodeExample;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.internal.LoadingCache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class VariableArgumentsTest {

  public enum Status {
    LOADING
  }
  @Test
  public void variableArgumentTest() {
    String sessionId = String.valueOf(UUID.randomUUID());
    String documentId = "100";
  }

  @Test
  public void printConvertArgs() {
    Object myObjs = convertVariableArgsToType("hello", 12, 123, 12.2345 ,false, Status.LOADING);
    ArrayList arr = (ArrayList) List.of(myObjs);
    for (Object o : arr) {
      System.out.println(o);
    }
  }
  /**
   *
   */

  private Object[] convertVariableArgsToType(Object... args) {
    Object[] processedArgs = new Object[args.length];
    for (int i = 0; i < args.length; i++) { //autoBoxing
      Object arg = args[i];
      if (arg instanceof Integer) {
        System.out.println("hi integer");
        processedArgs[i] = arg;

      } else if (arg instanceof String) {
        System.out.println("hi String");
        processedArgs[i] = arg;
      } else if (arg instanceof Boolean) {
        System.out.println("hi Boolean");
        processedArgs[i] = arg;
      } else if (arg instanceof Short) {
        System.out.println("hi Short");
        processedArgs[i] = arg;
      }
    }
    return processedArgs;
  }
}
