package com.ezen.springmvc.domain.field.service;
import com.ezen.springmvc.domain.field.dto.FieldDto;

import java.lang.reflect.Field;
import java.util.List;
/**
 * FieldService
 */
public interface FieldService {
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
