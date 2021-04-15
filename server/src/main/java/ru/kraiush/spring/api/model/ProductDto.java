package ru.kraiush.spring.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder(builderMethodName = "init", setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {

    private long id;

    private String code;

    private String name;

    private BigDecimal price;

    private String article;

//    @JsonFormat(pattern = DATE_FORMAT_PATTERN)
    private LocalDate productiondate;

    private Integer quantity;
}
