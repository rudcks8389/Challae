package com.ezen.springmvc.domain.common.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SearchDto {
<<<<<<< HEAD
=======
	private String searchType;
>>>>>>> 1776326671b77b631dcaf6d8dc377d02609568b6
	private String searchValue;
	private int limit;
	private int page;
}
