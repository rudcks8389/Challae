package com.ezen.springmvc.domain.field.mapper;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FieldMapper {
    public List<FieldDto> findByAll();

    public FieldDto findByFieldNum(int fieldNum);


}
