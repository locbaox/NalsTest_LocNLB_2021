package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entity.Work;

public interface IWorkSortAndPaginationRepository extends PagingAndSortingRepository<Work, Integer> {

}
