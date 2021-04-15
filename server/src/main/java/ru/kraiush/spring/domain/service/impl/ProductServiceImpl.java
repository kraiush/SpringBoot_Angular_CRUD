package ru.kraiush.spring.domain.service.impl;

import static ru.kraiush.spring.common.Constants.ErrorMessage.PRODUCT_ALREADY_EXISTS;
import static ru.kraiush.spring.common.Constants.ProductExceptionMessage.PRODUCT_CREATE_EXCEPTION;
import static ru.kraiush.spring.common.Constants.ProductExceptionMessage.PRODUCT_DELETE_EXCEPTION;
import static ru.kraiush.spring.common.Constants.ProductExceptionMessage.PRODUCT_ITEM_EXCEPTION;
import static ru.kraiush.spring.common.Constants.ProductExceptionMessage.PRODUCT_LIST_EXCEPTION;
import static ru.kraiush.spring.common.Constants.ProductExceptionMessage.PRODUCT_UPDATE_EXCEPTION;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kraiush.spring.api.model.ProductDto;
import ru.kraiush.spring.domain.model.Product;
import ru.kraiush.spring.domain.service.ProductService;
import ru.kraiush.spring.exception.ProductException;
import ru.kraiush.spring.reposotory.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public List<ProductDto> findAll() {
        try {
            List<Product> productList = repository.findAll();
            return productList.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (final Exception e) {
            throw new ProductException(PRODUCT_LIST_EXCEPTION, e);
        }
    }
    @Override
    public ProductDto findById(final Long id) {
        try {
            return repository.findById(id).stream()
                    .findAny()
                    .map(this::convertToDto)
                    .orElseThrow(RuntimeException::new);
        } catch (final RuntimeException e) {
            throw new ProductException(PRODUCT_ITEM_EXCEPTION + id, e);
        }
    }

    @Transactional
    public void createOrUpdate(ProductDto productDto)
    {
        final Product entity = convertToEntity(productDto);
        Optional<Product> product = Optional.empty();

        try {
            product = repository.findById(entity.getId());
        } catch(Exception ex) {
            throw new ProductException(
                    PRODUCT_ALREADY_EXISTS + entity.getId() + " " + entity.getName());
        }

        if(!product.equals(Optional.empty()))
        {
            try {
                Product newEntity = product.get();
                newEntity.setCode(entity.getCode());
                newEntity.setName(entity.getName());
                newEntity.setPrice(entity.getPrice());
                newEntity.setArticle(entity.getArticle());
                newEntity.setProductiondate(entity.getProductiondate().plusDays(1));
                newEntity.setQuantity(entity.getQuantity());

                repository.save(newEntity);
            } catch (final Exception e) {
            throw new ProductException(PRODUCT_UPDATE_EXCEPTION, e);
        }
        } else {
            try {
                // repository.save(entity); // <=> can be used for serial Id in postgreSQL database
                repository.insertProduct(entity.getId(), entity.getCode(),
                                          entity.getName(), entity.getPrice(),
                                          entity.getArticle(),
                                          entity.getProductiondate().plusDays(1),
                                          entity.getQuantity()
                );
            } catch (final RuntimeException e) {
                throw new ProductException(PRODUCT_CREATE_EXCEPTION, e);
            }
        }
    }

    @Override
    public void delete(final Long id) {
        try {
            repository.deleteById(id);
        } catch (final Exception e) {
            throw new ProductException(PRODUCT_DELETE_EXCEPTION, e);
        }
    }

    private ProductDto convertToDto(final Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    private Product convertToEntity(final ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
