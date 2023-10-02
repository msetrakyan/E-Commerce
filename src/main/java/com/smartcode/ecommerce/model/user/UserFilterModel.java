package com.smartcode.ecommerce.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
public class UserFilterModel {

    private Filter filter;
    private Search search;


    @Getter
    @Setter
    public static class Filter {
        @Positive
        private Integer startAge;
        @Positive
        private Integer endAge;
        private Boolean isVerified;
    }

    @Getter
    @Setter
    public static class Search {
        private String text;
    }


}
