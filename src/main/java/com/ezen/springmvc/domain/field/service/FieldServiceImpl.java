package com.ezen.springmvc.domain.field.service;
import com.ezen.springmvc.domain.field.dto.FieldDto;
import org.springframework.stereotype.Service;
import com.ezen.springmvc.domain.field.mapper.FieldMapper;
import lombok.RequiredArgsConstructor;
import java.util.List;
/**
 * FieldServiceImpl
 */
@RequiredArgsConstructor
@Service
public class FieldServiceImpl implements FieldService{
    private final FieldMapper fieldMapper;

    /**
     * 구장리스트출력
     * @return
     */
    @Override
    public List<FieldDto> findByAll() {
        return fieldMapper.findByAll();
    }

    /**
     * 구장 상세보기 필드번호를 이용해서 동적 출력
     * @param fieldNum
     * @return
     */
    @Override
    public FieldDto findByFieldNum(int fieldNum){
        return fieldMapper.findByFieldNum(fieldNum);
    }

    /**
     * 구장 예약 필드번호를 이용해서 동적 출력
     * @param fieldNum
     * @return
     */
    @Override
    public FieldDto findByFieldNum2(int fieldNum){
        return fieldMapper.findByFieldNum2(fieldNum);
    }
}
