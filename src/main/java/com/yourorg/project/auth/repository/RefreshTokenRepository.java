package com.yourorg.project.auth.repository;

import com.yourorg.project.auth.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

}
