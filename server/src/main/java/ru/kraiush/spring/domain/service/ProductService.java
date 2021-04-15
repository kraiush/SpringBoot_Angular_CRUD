package ru.kraiush.spring.domain.service;

import java.util.List;
import ru.kraiush.spring.api.model.ProductDto;
import ru.kraiush.spring.domain.model.Product;

public interface ProductService {

	/**
	 * Получение всех продуктов {@link ProductDto}.
	 *
	 * @return список продуктов
	 * @throws {@code ProductException} общая ошибка при работе с сервисом продуктов
	 */
	List<ProductDto> findAll();

	/**
	 * Получение продукта по его id.
	 *
	 * @param id id продукта
	 * @return Продукт {@link ProductDto}
	 * @throws {@code ProductException} общая ошибка при работе с сервисом продуктов
	 */
	ProductDto findById(Long id);

	/**
	 * Добавление нового продукта / Изменение продукта.
	 *
	 * @param productDto модель продукта {@link Product}
	 * @throws {@code ProductException} общая ошибка при работе с сервисом продуктов
	 */
	void createOrUpdate(ProductDto productDto);

	/**
	 * Удаление продукта по его id
	 *
	 * @param id идентификатор продукта
	 * @throws {@code ProductException} общая ошибка при работе с сервисом продуктов
	 */
	void delete(Long id);
}
