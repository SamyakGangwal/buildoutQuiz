package com.crio.buildouts.buildoutsqa.exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateAnswersresponse {

    List<ResponseDetails> questions;

    Summary summary;
}
