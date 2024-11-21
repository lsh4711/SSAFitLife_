package com.ssafy.ssafitlife.activity.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

	private int actNo;
	private int memNo;
	private String actName;
	private String actInten;
	private String actCalorie;
	private boolean isShared;
}
