package com.example.webchat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private long u_no;
	private String u_id;
	private String u_nickname;
	private String u_pw;
	private String u_name;
	private String u_phone;
	private String u_email;
	private String u_role;
}
