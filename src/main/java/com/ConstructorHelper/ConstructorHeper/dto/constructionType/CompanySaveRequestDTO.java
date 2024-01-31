package com.ConstructorHelper.ConstructorHeper.dto.constructionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanySaveRequestDTO {
    private long companyId;

    private String companyName;

}
