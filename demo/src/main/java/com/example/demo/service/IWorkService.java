package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Work;

public interface IWorkService {
	
	List<Work> getAllWorks(Integer pageNo, Integer pageSize, String sortBy);
	
	Optional<Work> findById(int id);

	Work save(Work work);

    void remove(int id);
}
