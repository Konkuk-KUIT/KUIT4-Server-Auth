package com.kuit.kuit4serverauth.enums;

import lombok.Getter;

@Getter
public enum Category {

    KOREAN("Korean Food"),
    CHINESE("Chinese Food"),
    JAPANESE("Japanese Food"),
    WESTERN("Western Food"),
    FAST_FOOD("Fast Food"),
    CAFE("Cafe and Dessert"),
    PIZZA("Pizza"),
    CHICKEN("Chicken");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Category fromDbValue(String dbValue) {
        for (Category category : values()) {
            if (category.name().equalsIgnoreCase(dbValue)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown category value: " + dbValue);
    }

    public String toDbValue() {
        return this.name();
    }

}
