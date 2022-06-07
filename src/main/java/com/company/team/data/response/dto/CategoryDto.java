package com.company.team.data.response.dto;

import com.company.team.utils.time.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private long categoryId;
    private String name;
    private String description;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createdDate;
}
