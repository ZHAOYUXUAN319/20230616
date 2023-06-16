package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Shinsa;

/**
 * 審査情報 Repository
 */
@Repository
public interface ShinsaRepository extends JpaRepository<Shinsa, Long> {
	List<Shinsa> findAllByBukenId(Long bukenId);
//	List<Shinsa> findsByBukenId(Long bukenId);
//	Shinsa findByBukenId(Long bukenId);

	@Modifying
	@Query("UPDATE Shinsa s SET s.status = :status WHERE s.bukenId = :bukenId")
	void updateStatusByBukenId(@Param("bukenId") Long bukenId, @Param("status") String status);

}
