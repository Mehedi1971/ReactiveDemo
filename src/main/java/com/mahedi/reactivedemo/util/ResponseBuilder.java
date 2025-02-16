package com.mahedi.reactivedemo.util;


import com.mahedi.reactivedemo.dto.ErrorResponseDto;
import com.mahedi.reactivedemo.dto.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import reactor.core.publisher.Mono;

public final class ResponseBuilder {

  private ResponseBuilder() {
  }

  private static List<ErrorResponseDto> getCustomError(BindingResult result) {
    List<ErrorResponseDto> dtoList = new ArrayList<>();
    result.getFieldErrors().forEach(fieldError -> {
      ErrorResponseDto errorResponseDto = ErrorResponseDto.builder().field(fieldError.getField())
          .message(fieldError.getDefaultMessage()).build();
      dtoList.add(errorResponseDto);
    });
    return dtoList;
  }

  public static Mono<Response> getFailureResponse(BindingResult result, String message) {
    return Mono.just(Response.builder().message(message).errors(getCustomError(result))
        .status(HttpStatus.BAD_REQUEST.getReasonPhrase()).statusCode(HttpStatus.BAD_REQUEST.value())
        .timeStamp(new Date().getTime()).build());
  }

  public static Mono<Response> getFailureResponse(HttpStatus status, String message) {
    return Mono.just(Response.builder().message(message).status(status.getReasonPhrase())
        .status(status.getReasonPhrase()).statusCode(status.value()).timeStamp(new Date().getTime())
        .build());
  }

  public static Mono<Response> getSuccessResponse(HttpStatus status, String message, Object content) {
    return Mono.just(Response.builder().message(message).status(status.getReasonPhrase())
        .statusCode(status.value()).content(content).timeStamp(new Date().getTime()).build());
  }

  public static Mono<Response> getDto(HttpStatus status, String message, Object content) {
    return Mono.just(Response.builder().message(message).status(status.getReasonPhrase())
        .statusCode(status.value()).content(content).timeStamp(new Date().getTime()).build());
  }

  public static Mono<Response> getSuccessResponse(HttpStatus status, String message, Object content,
      int numberOfElement) {
    return Mono.just(Response.builder().message(message).status(status.getReasonPhrase())
        .statusCode(status.value()).content(content).timeStamp(new Date().getTime())
        .numberOfElement(numberOfElement).build());
  }

  public static Mono<Response> getSuccessResponse(HttpStatus status, String message, Object content,
      int numberOfElement, Long rowCount) {
    return Mono.just(Response.builder().message(message).status(status.getReasonPhrase())
        .statusCode(status.value()).content(content).timeStamp(new Date().getTime())
        .numberOfElement(numberOfElement).rowCount(rowCount).build());
  }

  public static Mono<Response> getSuccessResponse(HttpStatus status, String message) {
    return Mono.just(Response.builder().message(message).status(status.getReasonPhrase())
        .statusCode(status.value()).timeStamp(new Date().getTime()).build());
  }
}
