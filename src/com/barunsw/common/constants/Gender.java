package com.barunsw.common.constants;

public enum Gender {
	M
	, W;
	
	@Override
	public String toString() {
		if (this.equals(Gender.M)) {
			return "남자";
		}
		else if (this.equals(Gender.W)) {
			return "여자";
		}
		
		return "";
 	}
}
