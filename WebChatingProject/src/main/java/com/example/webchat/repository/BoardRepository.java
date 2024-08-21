package com.example.webchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webchat.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
	// 게시글을 제목으로 검색하는 메서드
	List<BoardEntity> findByBoardTitleContaining(String keyword);
}
