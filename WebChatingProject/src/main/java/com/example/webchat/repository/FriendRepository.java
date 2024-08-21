package com.example.webchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webchat.entity.FriendEntity;
import com.example.webchat.entity.FriendId;

public interface FriendRepository extends JpaRepository<FriendEntity, FriendId> {
	// 친구 관계를 가져오는 메서드 (친구 목록 조회)
	List<FriendEntity> findByUserNo1OrUserNo2(int userNo1, int userNo2);
}
