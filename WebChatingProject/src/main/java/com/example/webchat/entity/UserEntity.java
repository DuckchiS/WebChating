package com.example.webchat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userNo")
	private int uNo;
	
	@Column(name = "userNickName", nullable = false, unique = true, length = 100)
	private String uNickName;
	
	@Column(name = "userPassword", nullable = false, length = 255)
	private String uPw;
	
	@Column(name = "userName", nullable = false, length = 50)
	private String uName;
	
	@Column(name = "userPhone", nullable = false, length = 25)
	private String uNumber;
	
	@Column(name = "userEmail", nullable = false, length = 255)
	private String uEmail;
	
	@Column(name = "userRole", nullable = false, length = 50)
	private String uRole;
}
