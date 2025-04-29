package com.iff.NexusBlog.exception;

public class AuthenticationException  extends RuntimeException {
  public AuthenticationException (String message) {
      super(message);
  }
}
