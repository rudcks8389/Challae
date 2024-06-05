package com.ezen.springmvc.domain.field.dto;
import lombok.*;

import java.util.Date;
/**
 * fieldDto
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FieldDto {
    private int fieldNum; /**구장번호**/
    private String fieldName; /**구장이름**/
    private String fieldOldAddress; /**구장구주소**/
    private String fieldNewAddress; /**구장신주소**/
    private String fieldPhone; /**구장전화번호**/
    private Date fieldRegdate; /**구장등록일시**/
    private String fieldSize; /**구장사이즈**/
    private String fieldPhoto; /**구장사진**/
    private int fieldDayprice; /**평일가격**/
    private int fieldEndprice; /**주말가격**/
    private Date fieldPaydate; /**구장정산일자**/
    private String fieldOpentime; /**구장오픈시간**/
    private String fieldClosetime; /**구장마감시간**/
    private String fieldthumbnails; /**썸네일사진**/
    private String fieldDetailaddress; /**상세주소**/
    private String fieldX;/**위도**/
    private String fieldY;/**경도**/
}