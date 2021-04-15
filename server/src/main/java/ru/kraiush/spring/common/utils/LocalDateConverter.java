package ru.kraiush.spring.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import java.sql.Timestamp;

public final class LocalDateConverter implements AttributeConverter <LocalDate, Timestamp > {

    @Override
    public final Timestamp convertToDatabaseColumn(final LocalDate attribute) {
        return attribute != null ? Timestamp.valueOf(attribute.atStartOfDay()) : null;
    }

    @Override
    public final LocalDate convertToEntityAttribute(final Timestamp dbData) {
        return dbData != null ? dbData.toLocalDateTime().toLocalDate() : null;
    }

    public final LocalDateTime toDatePostgres(final LocalDateTime dt) {
        LocalDate localDate = dt.toLocalDate().plusDays(1);
        return localDate.atTime(00, 00, 00);
    }
}
