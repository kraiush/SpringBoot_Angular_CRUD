package ru.kraiush.spring.domain.converter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kraiush.spring.api.model.ProductDto;
import ru.kraiush.spring.domain.model.Product;

@Component
@RequiredArgsConstructor
public class ProductConverter implements Converter<ProductDto, Product> {

	@Override
	public Product convert(@NonNull final ProductDto source) {
		return Product .init()
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
