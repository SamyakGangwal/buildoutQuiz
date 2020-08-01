package com.crio.buildouts.buildoutsqa.exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Summary {
    private int score;
    private int total;
}

