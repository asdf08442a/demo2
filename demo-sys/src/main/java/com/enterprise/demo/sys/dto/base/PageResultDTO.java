package com.enterprise.demo.sys.dto.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResultDTO {
    private List rows;
    private Long total;
}
