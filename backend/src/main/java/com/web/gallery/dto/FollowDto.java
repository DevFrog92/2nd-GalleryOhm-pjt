package com.web.gallery.dto;

public class FollowDto {
	private String follow_artistId;		// 팔로우한 작가 아이디
	private String follow_userId;		// 팔로잉한 사용자 아이디
	
	public FollowDto() {
	}

	public FollowDto(String follow_artistId, String follow_userId) {
		this.follow_artistId = follow_artistId;
		this.follow_userId = follow_userId;
	}

	public String getFollow_artistId() {
		return follow_artistId;
	}

	public void setFollow_artistId(String follow_artistId) {
		this.follow_artistId = follow_artistId;
	}

	public String getFollow_userId() {
		return follow_userId;
	}

	public void setFollow_userId(String follow_userId) {
		this.follow_userId = follow_userId;
	}

	@Override
	public String toString() {
		return "FollowDto{" +
				"follow_artistId='" + follow_artistId + '\'' +
				", follow_userId='" + follow_userId + '\'' +
				'}';
	}
}
