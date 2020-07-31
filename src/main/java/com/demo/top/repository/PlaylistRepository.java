package com.demo.top.repository;

import com.demo.top.model.dto.PlaylistDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistDTO, Long> {
}
