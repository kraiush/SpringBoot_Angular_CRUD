package ru.kraiush.spring.domain.converter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kraiush.spring.api.model.ProductDto;
import ru.kraiush.spring.domain.model.Product;

@Component
@RequiredArgsConstructor
public class ProductDtoConverter implements Converter<Product, ProductDto> {

	@Override
	public ProductDto convert(@NonNull final Product source) {
		return ProductDto .init()
				.setId(source.getId())
				.setCode(source.getCode())
				.setName(source.getName())
				.setPrice(source.getPrice())
				.setArticle(source.getArticle())
				.setProductiondate(source.getProductiondate())
				.setQuantity(source.getQuantity())
				.build();
	}
}
