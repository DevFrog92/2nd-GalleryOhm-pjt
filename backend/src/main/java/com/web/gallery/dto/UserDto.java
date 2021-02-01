package com.web.gallery.dto;

public class UserDto {
	private String user_id;			// 사용자 아이디
	private String user_name;		// 사용자 이름
	private String user_password;	// 사용자 비밀번호
	private String user_nickName;	// 사용자 닉네임
	private String user_email;		// 사용자 이메일
	private String user_tel;		// 사용자 전화번호
	private String user_birth;		// 사용자 생일
	private int user_type;			// 사용자 타입 (0:admin, 1:guest, 2:artist)
	private String user_createDate;	// 사용자 가입일
	private byte[] user_profile;	// 사용자 프로필 사진
	private String user_about;		// 사용자 대한 설명 (default:"나를 소개해보세요!")
	
	public UserDto() {
	}

	// login
	public UserDto(String user_id, String user_password) {
		this.user_id = user_id;
		this.user_password = user_password;
	}

	// join
	public UserDto(String user_id, String user_email, String user_name, String user_password, String user_nickName,
			String user_tel, String user_birth, int user_type, String user_createDate, byte[] user_profile,
			String user_about) {
		this.user_id = user_id;
		this.user_email = user_email;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_nickName = user_nickName;
		this.user_tel = user_tel;
		this.user_birth = user_birth;
		this.user_type = user_type;
		this.user_createDate = user_createDate;
		this.user_profile = user_profile;
		this.user_about = user_about;
	}
	
	// findId
	public UserDto(String user_email, String user_name, String user_birth) {
		super();
		this.user_email = user_email;
		this.user_name = user_name;
		this.user_birth = user_birth;
	}


	public String getUser_id() {
		return user_id;
	}

	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_nickName() {
		return user_nickName;
	}

	public void setUser_nickName(String user_nickName) {
		this.user_nickName = user_nickName;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}

	public int getUser_type() {
		return user_type;
	}
	
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	public String getUser_createDate() {
		return user_createDate;
	}

	public void setUser_createDate(String user_createDate) {
		this.user_createDate = user_createDate;
	}

	public byte[] getUser_profile() {
		return user_profile;
	}

	public void setUser_profile(byte[] user_profile) {
		this.user_profile = user_profile;
	}

	public String getUser_about() {
		return user_about;
	}

	public void setUser_about(String user_about) {
		this.user_about = user_about;
	}
	
	// guest : 1, artist : 2, admin : 0
	public void setType(int user_type) {
		this.user_type = user_type;
	}
	
	@Override
	public String toString() {
		return "UserDto [user_id=" + user_id + ", user_email=" + user_email + ", user_name=" + user_name
				+ ", user_password=" + user_password + ", user_nickName=" + user_nickName + ", user_tel=" + user_tel
				+ ", user_birth=" + user_birth + ", user_type=" + user_type + ", user_createDate=" + user_createDate
				+ "]";
	}
}
