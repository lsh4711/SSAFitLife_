package com.ssafy.ssafitlife.post.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCondition {
	private String key;
	private String word;
	private String orderBy;
	private String orderByDir;
}
