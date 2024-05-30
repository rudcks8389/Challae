package com.ezen.springmvc.domain.record_fc.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RecordFcDto {
    private String recNum;
    private String recFid;
    private String recSco;
    private String recDate;
    private String clubNum;
}
