package ru.kraiush.spring;

import static lombok.AccessLevel.PRIVATE;
import java.util.Random;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class RandomData {

	public static 	Long generatingRandomLongBounded() {
		long range = 123456789L;
		Random r = new Random();
		return (long)(r.nextDouble()*range);
	}

	public static Integer generatingRandomIntegerBounded() {
		int leftLimit = 1;
		int rightLimit = 10;
		return leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
	}
}
