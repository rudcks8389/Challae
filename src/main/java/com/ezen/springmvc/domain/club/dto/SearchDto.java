package com.ezen.springmvc.domain.club.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SearchDto {
	private String searchValue;
	private int limit;
	private int page;
}
