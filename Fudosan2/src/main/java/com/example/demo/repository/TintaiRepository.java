package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Buken;
import com.example.demo.entity.Tintai;

/**
 * 審査情報 Repository
 */
@Repository
public interface TintaiRepository extends JpaRepository<Tintai, Long> {
	//List<Tintai> findById(Long id);
	List<Tintai> findByOkyaku(String okyaku);
	//void updateTintaiFields(Long bukenId, String shinsasyaA, String shinsasyaB);
	@Modifying
    @Query("UPDATE Tintai t SET t.shinsasyaA = :shinsasyaA, t.shinsasyaB = :shinsasyaB WHERE t.id = :bukenId")
    void updateTintaiFields(@Param("bukenId") Long bukenId, @Param("shinsasyaA") String shinsasyaA, @Param("shinsasyaB") String shinsasyaB);
}

