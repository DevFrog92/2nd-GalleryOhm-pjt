package com.web.gallery.dto;

public class ArtistDto {
	String artist_id;			// 작가 아이디
	String artist_bank;			// 은행이름
	String artist_account;		// 계좌번호
	String artist_nickName;		// 필명(별명)
	int artist_exp;				// 레벨

	public ArtistDto() {
		super();
	}

	public ArtistDto(String artist_id, String artist_bank,
					 String artist_account, String artist_nickName, int artist_exp) {
		this.artist_id = artist_id;
		this.artist_bank = artist_bank;
		this.artist_account = artist_account;
		this.artist_nickName = artist_nickName;
		this.artist_exp = artist_exp;
	}

	public String getArtist_id() {
		return artist_id;
	}

	public void setArtist_id(String artist_id) {
		this.artist_id = artist_id;
	}

	public String getArtist_bank() {
		return artist_bank;
	}

	public void setArtist_bank(String artist_bank) {
		this.artist_bank = artist_bank;
	}

	public String getArtist_account() {
		return artist_account;
	}

	public void setArtist_account(String artist_account) {
		this.artist_account = artist_account;
	}

	public String getArtist_nickName() {
		return artist_nickName;
	}

	public void setArtist_nickName(String artist_nickName) {
		this.artist_nickName = artist_nickName;
	}

	public int getArtist_exp() {
		return artist_exp;
	}

	public void setArtist_exp(int artist_exp) {
		this.artist_exp = artist_exp;
	}

	@Override
	public String toString() {
		return "ArtistDto{" +
				"artist_id='" + artist_id + '\'' +
				", artist_bank='" + artist_bank + '\'' +
				", artist_account='" + artist_account + '\'' +
				", artist_nickName='" + artist_nickName + '\'' +
				", artist_exp=" + artist_exp +
				'}';
	}
}
