package com.ezen.springmvc.domain.field.service;

import com.ezen.springmvc.domain.field.dto.FieldDto;

import java.lang.reflect.Field;
import java.util.List;

public interface FieldService {
    public List<FieldDto> findByAll();

    public FieldDto findByFieldNum(int fieldNum);

    public FieldDto findByFieldNum2(int fieldNum);


}
