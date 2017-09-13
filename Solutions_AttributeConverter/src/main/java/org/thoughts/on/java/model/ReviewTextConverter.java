package org.thoughts.on.java.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ReviewTextConverter implements
		AttributeConverter<ReviewText, String> {

	@Override
	public String convertToDatabaseColumn(ReviewText attribute) {
		if (attribute != null) {
			return attribute.toString();
		} else {
			return null;
		}
	}

	@Override
	public ReviewText convertToEntityAttribute(String dbData) {
		if (dbData != null && !dbData.isEmpty()) {
			return ReviewText.fromString(dbData);
		} else {
			return null;
		}
	}

}
