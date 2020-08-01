package com.crio.buildouts.buildoutsqa.exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateAnswersRequests {
    @NotNull
    private List<UserResponse> responses;

}
