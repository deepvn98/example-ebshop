package com.example.ebshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OderDetail {
    private Long id;
    private Book bookId;
    private Oder oderId;
    private int quantity;

}
