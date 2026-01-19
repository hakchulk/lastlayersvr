package com.study.lastlayer.authUser;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "auth_user", uniqueConstraints = {
		@UniqueConstraint(name = "UK_auth_user__email", columnNames = { "email" } // DB 컬럼 이름
		) })
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String email;

	@ToString.Exclude // Lombok, 사용한다면 toString() 호출 시 비밀번호가 로그에 찍히지 않도록 반드시 제외
	@JsonIgnore
	@Column(nullable = false, comment = "BCrypt 암호화 비밀번호")
	private String password;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@PrePersist
	public void onCreated() {
		this.createdAt = LocalDateTime.now();
	}
}
