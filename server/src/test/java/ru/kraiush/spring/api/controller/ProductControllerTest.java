package ru.kraiush.spring.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static ru.kraiush.spring.RandomData.generatingRandomLongBounded;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.kraiush.spring.api.controller.impl.ProductControllerImpl;
import ru.kraiush.spring.api.model.ProductDto;
import ru.kraiush.spring.domain.service.impl.ProductServiceImpl;

class ProductControllerTest {

	@Mock
	private ProductServiceImpl productService;

	@InjectMocks
	private ProductControllerImpl productController;

	@BeforeEach
	public void setUp() {
		initMocks(this);
	}

	@Test
	void createTest() {
		val productDto = mock(ProductDto.class);

		val actual = productController.create(productDto);

		verify(productService).createOrUpdate(productDto);
		verifyNoMoreInteractions(productService);

		assertEquals(CREATED, actual.getStatusCode());
	}

	@Test
	void updateTest() {
		val currencyDto = mock(ProductDto.class);

		val actual = productController.update(currencyDto);

		verify(productService).createOrUpdate(currencyDto);
		verifyNoMoreInteractions(productService);

		assertEquals(NO_CONTENT, actual.getStatusCode());
	}

	@Test
	void deleteTest() {
		val id = generatingRandomLongBounded();

		val actual = productController.delete(id);

		verify(productService).delete(id);
		verifyNoMoreInteractions(productService);

		assertEquals(NO_CONTENT, actual.getStatusCode());
	}

	@Test
	void findAllTest() {
		val retVal = mock(List.class);

		when(productService.findAll()).thenReturn(retVal);

		val actual = productController.findAll();

		verify(productService).findAll();
		verifyNoMoreInteractions(productService);

		assertEquals(OK, actual.getStatusCode());
		assertEquals(retVal, actual.getBody());
	}

	@Test
	void findByIdTest() {
		val id = generatingRandomLongBounded();
		val retVal = mock(ProductDto.class);

		when(productService.findById(id)).thenReturn(retVal);

		val actual = productController.getById(id);

		verify(productService).findById(id);
		verifyNoMoreInteractions(productService);

		assertEquals(OK, actual.getStatusCode());
		assertEquals(retVal, actual.getBody());
	}
}