package com.iff.NexusBlog.exception;

public class InvalidRequestException extends RuntimeException {
  public InvalidRequestException(String message) {
      super(message);
  }
}
