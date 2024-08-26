package com.example.webchat.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.webchat.entity.FriendEntity;
import com.example.webchat.entity.FriendId;

public interface FriendRepository extends JpaRepository<FriendEntity, FriendId> {
    // 특정 유저가 친구인 경우를 조회 (유저 ID 기준)
    @Query("SELECT f FROM FriendEntity f WHERE f.userNo1.userNo = :userNo OR f.userNo2.userNo = :userNo")
    List<FriendEntity> findByUserNo1OrUserNo2(@Param("userNo") int userNo);
}
