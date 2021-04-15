package ru.kraiush.spring.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name="product")
@Table(name="product")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="code", length = 32, nullable = false)
    private String code;

    @Column(name="name", nullable = false, length = 254)
    private String name;

    @Column(name="price", nullable = false, precision=11, scale=2 )
    private BigDecimal price;

    @Column(name="article", nullable = false,  length = 254)
    private String article;

    @Column(name="productiondate")
    private LocalDate productiondate;

    @Column(name="quantity")
    private Integer quantity;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Product)) {
            return false;
        }
        Product that = (Product) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
