package com.ezen.springmvc.domain.field.mapper;
import com.ezen.springmvc.domain.field.dto.FieldDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * FieldMapper
 */
@Mapper
public interface FieldMapper {

    /**
     * 구장리스트출력
     * @return
     */
    public List<FieldDto> findByAll();


    /**
     * 구장 상세보기 필드번호를 이용해서 동적 출력
     * @param fieldNum
     * @return
     */
    public FieldDto findByFieldNum(int fieldNum);


    /**
     * 구장 예약 필드번호를 이용해서 동적 출력
     * @param fieldNum
     * @return
     */
    public FieldDto findByFieldNum2(int fieldNum);
}
