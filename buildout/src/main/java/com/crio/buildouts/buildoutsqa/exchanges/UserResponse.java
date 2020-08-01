package com.crio.buildouts.buildoutsqa.exchanges;

import java.util.List;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
  @NotNull
  private String questionId;
  @NotNull
  private List<String> userResponse;
}
