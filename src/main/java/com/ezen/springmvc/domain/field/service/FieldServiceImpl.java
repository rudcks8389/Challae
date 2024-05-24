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
    public List<FieldDto> findByDetail(){
        return fieldMapper.findByDetail();
    }
    @Override
    public FieldDto showFieldDetail(int fieldNum){
        return fieldMapper.showFieldDetail(fieldNum);
    }
}
