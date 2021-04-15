package ru.kraiush.spring.component.aspect;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static ru.kraiush.spring.common.Constants.Common.BASE_PACKAGE;
import javax.servlet.http.HttpServletRequest;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import ru.kraiush.spring.common.aspect.GlobalExceptionHandler;

class GlobalExceptionHandlerTest {

	@InjectMocks
	private GlobalExceptionHandler handler;

	@BeforeEach
	void setUp() {
		initMocks(this);
	}

	@Test
	void handleExceptionTest() {
		val request = mock(HttpServletRequest.class);
		val exception = mock(Exception.class);
		val uri = randomAlphabetic(10);
		val message = randomAlphabetic(10);
		val cause = mock(Throwable.class);

		when(exception.getStackTrace()).thenReturn(new StackTraceElement[]{});
		when(request.getRequestURI()).thenReturn(uri);
		when(exception.getMessage()).thenReturn(message);
		when(exception.getCause()).thenReturn(cause);

		val actual = handler.handleException(request, exception);
		val body = actual.getBody();

		assertEquals(INTERNAL_SERVER_ERROR, actual.getStatusCode());
		assertNotNull(body);
		assertFalse(body.isSuccess());
		assertEquals(uri, body.getUri());
		assertEquals(INTERNAL_SERVER_ERROR.value(), body.getCode());
		assertEquals(INTERNAL_SERVER_ERROR.getReasonPhrase(), body.getTitle());
		assertEquals(exception.getMessage(), body.getDescription());
		assertEquals(cause.toString(), body.getMessage());
		assertTrue(body.getStackTrace().isEmpty());
	}

//	@Test
//	void handleExceptionFilterTest() {
//		val request = mock(HttpServletRequest.class);
//		val exception = mock(Exception.class);
//		val uri = randomAlphabetic(10);
//		val message = randomAlphabetic(10);
//		val cause = mock(Throwable.class);
//		val element = mock(StackTraceElement.class);
//
//		when(exception.getStackTrace()).thenReturn(new StackTraceElement[]{element});
//		when(element.getClassName()).thenReturn(BASE_PACKAGE);
//		when(request.getRequestURI()).thenReturn(uri);
//		when(exception.getMessage()).thenReturn(message);
//		when(exception.getCause()).thenReturn(cause);
//
//		val actual = handler.handleException(request, exception);
//		val body = actual.getBody();
//
//		assertNotNull(body);
//		assertFalse(body.getStackTrace().isEmpty());
//	}
}