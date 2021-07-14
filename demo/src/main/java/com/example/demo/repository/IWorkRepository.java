package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Work;

@Repository
public interface IWorkRepository extends JpaRepository<Work, Integer> {

}
