package com.ezen.springmvc.domain.field.service;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import org.springframework.stereotype.Service;
import com.ezen.springmvc.domain.field.mapper.FieldMapper;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FieldServiceImpl implements FieldService{
    private final FieldMapper fieldMapper;

    @Override
    public List<FieldDto> findByAll() {
        return fieldMapper.findByAll();
    }

    @Override
    public FieldDto findByFieldNum(int fieldNum){
        return fieldMapper.findByFieldNum(fieldNum);
    }

    @Override
    public FieldDto findByFieldNum2(int fieldNum){
        return fieldMapper.findByFieldNum2(fieldNum);
    }
}
