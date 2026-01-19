package com.study.lastlayer.member;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import com.study.lastlayer.authUser.AuthUser;
import com.study.lastlayer.file.File;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor // default Constructor
@AllArgsConstructor
@Builder
public class Member {
	@Id
	private Long member_id;

	@OneToOne
	@MapsId // Member.member_id를 AuthUser.id와 매핑 (공유 PK 방식)
	@JoinColumn(name = "member_id")
	private AuthUser authUser;

	@Column(nullable = false)
	@ColumnDefault("0")
	private int notificationCount;

	private String gender; // "M", "W" 문자열로 저장
	private LocalDate birthday;
	private Float height;
	private Float weight;

	@Column(comment = "1개만 선택 : 중 감량, 건강 유지, 근육량 증가, 혈당 관리, 콜레스테롤 관리")
	private String goal;
	private Float goal_weight;

	@Column(comment = "식단에서 피해야 할 음식 리스트. 예)갑각류,콩류")
	private String allergies;

	@Column(comment = "특이사항. 예) 고기 안먹음.")
	private String special_notes;

	// @OneToOne이면 디폴트로 사용 할 file_id를 공유 수 없다.
	@ManyToOne
	@JoinColumn(name = "file_id", nullable = true, unique = false, foreignKey = @ForeignKey(name = "fk_member__file_id"))
	private File profileImage;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	public void onCreated() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = this.createdAt;
	}

	@PreUpdate
	public void onUpdatedd() {
		this.updatedAt = LocalDateTime.now();
	}

}
