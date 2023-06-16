package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Buken;
import com.example.demo.entity.Okiniiri;

/**
 * ユーザー情報 Repository
 */
@Repository
public interface OkiniiriRepository extends JpaRepository<Okiniiri, Long> {


}
