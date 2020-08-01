package com.crio.buildouts.buildoutsqa.exchanges;

import java.util.List;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateAnswersresponse {

  List<ResponseDetails> questions;

  Summary summary;
}
