package ru.kraiush.spring.reposotory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kraiush.spring.domain.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ProductRepository extends JpaRepository<Product, Long>  {

    @Modifying
    @Query(
            value= "insert into product(id, code, name, price, article, productiondate, quantity) " +
                    "values (:id, :code, :name, :price, :article, :productiondate, :quantity)",
            nativeQuery = true)
    void insertProduct(@Param("id") Long id, @Param("code") String code,
                       @Param("name") String name, @Param("price") BigDecimal price,
                       @Param("article") String article, @Param("productiondate") LocalDate productiondate,
                       @Param("quantity") Integer quantity);
}
