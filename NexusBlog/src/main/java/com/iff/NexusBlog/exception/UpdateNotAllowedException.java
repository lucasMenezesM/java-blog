package com.iff.NexusBlog.exception;

public class UpdateNotAllowedException extends RuntimeException {
  public UpdateNotAllowedException(String message) {
      super(message);
  }
}
