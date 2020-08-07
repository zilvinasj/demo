package com.demo.top.exception;

public class FailedDependencyException extends RuntimeException {

  public FailedDependencyException(String message) {
    super(message);
  }

}
