package com.iff.NexusBlog.exception;

public class DeleteNotAllowedException extends RuntimeException {
  public DeleteNotAllowedException(String message) {
      super(message);
  }
}
