package org.thoughts.on.java.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ReviewTextConverter implements AttributeConverter<ReviewText, String>{

    @Override
    public String convertToDatabaseColumn(ReviewText x) {
        if(null == x) {
            return null;
        }
        return x.toString();
    }

    @Override
    public ReviewText convertToEntityAttribute(String y) {
        if(null == y) {
            return null;
        }
        return ReviewText.fromString(y);
    }
    
}
