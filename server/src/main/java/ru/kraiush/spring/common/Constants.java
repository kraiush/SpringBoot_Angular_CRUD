package ru.kraiush.spring.common;

import static lombok.AccessLevel.PRIVATE;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class Constants {

	@NoArgsConstructor(access = PRIVATE)
	public static final class ProductExceptionMessage {

		public static final String PRODUCT_LIST_EXCEPTION = "Error getting product list";

		public static final String PRODUCT_ITEM_EXCEPTION = "Error getting product with id: ";

		public static final String PRODUCT_CREATE_EXCEPTION = "Error creating product";

		public static final String PRODUCT_UPDATE_EXCEPTION = "Error updating product";

		public static final String PRODUCT_DELETE_EXCEPTION = "Error deleting product";
	}

	@NoArgsConstructor(access = PRIVATE)
	public static final class ErrorMessage {

		public static final String PRODUCT_ALREADY_EXISTS = "product already exists: ";
	}

	@NoArgsConstructor(access = PRIVATE)
	public static final class ApiConstants {

		public static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";

		public static final String DATE_FORMAT_EXAMPLE = "21.12.2021";
	}

	@NoArgsConstructor(access = PRIVATE)
	public static final class Common {

		public static final String BASE_PACKAGE = "ru.kraiush.*";

		public static final String HTTP_PREFIX = "http://";
	}
}
