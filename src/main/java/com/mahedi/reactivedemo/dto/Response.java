package com.mahedi.reactivedemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Response extends Mono<Response> {

  private Long timeStamp;

  private int statusCode;

  private String status;

  private String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Object content;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private int numberOfElement;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Long rowCount;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<ErrorResponseDto> errors;

  @Override
  public void subscribe(CoreSubscriber<? super Response> coreSubscriber) {

  }
}
