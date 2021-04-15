package ru.kraiush.spring.domain.component.converter;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static ru.kraiush.spring.RandomData.generatingRandomIntegerBounded;
import static ru.kraiush.spring.RandomData.generatingRandomLongBounded;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import ru.kraiush.spring.domain.converter.ProductDtoConverter;
import ru.kraiush.spring.domain.model.Product;

class ProductDtoConverterTest {

	@InjectMocks
	private ProductDtoConverter converter;

	@BeforeEach
	void setUp() {
		initMocks(this);
	}

	@Test
	void convertTest() {
		val id = generatingRandomLongBounded();
		val code = randomAlphabetic(10);
		val name = randomAlphabetic(10);
		val price = new BigDecimal(generatingRandomLongBounded());
		val article = randomAlphabetic(10);
		val productiondate = LocalDate.of(2021, Month.JUNE, 8);;
		val quantity = generatingRandomIntegerBounded();
		val source = mock(Product.class);

		when(source.getId()).thenReturn(id);
		when(source.getCode()).thenReturn(code);
		when(source.getName()).thenReturn(name);
		when(source.getPrice()).thenReturn(price);
		when(source.getArticle()).thenReturn(article);
		when(source.getProductiondate()).thenReturn(productiondate);
		when(source.getQuantity()).thenReturn(quantity);

		val actual = converter.convert(source);

		assertNotNull(actual);
		assertEquals(id, actual.getId());
		assertSame(code, actual.getCode());
		assertSame(name, actual.getName());
		assertSame(price, actual.getPrice());
		assertSame(article, actual.getArticle());
		assertSame(productiondate, actual.getProductiondate());
		assertSame(quantity, actual.getQuantity());
	}

	@Test
	void notNullTest() {
		assertThrows(NullPointerException.class, () -> converter.convert(null));
	}
}