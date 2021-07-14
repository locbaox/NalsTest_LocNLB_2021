package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Work;
import com.example.demo.repository.IWorkRepository;
import com.example.demo.repository.IWorkSortAndPaginationRepository;

@Service
public class WorkService implements IWorkService {
	@Autowired
	private IWorkRepository workRepository;

	@Autowired
	private IWorkSortAndPaginationRepository sortAndPaginationRepository;

	@Override
	public Optional<Work> findById(int id) {
		return workRepository.findById(id);
	}

	@Override
	public Work save(Work work) {
		return workRepository.save(work);
	}

	@Override
	public void remove(int id) {
		workRepository.deleteById(id);
	}

	public List<Work> getAllWorks(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Work> pagedResult = sortAndPaginationRepository.findAll(paging);

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Work>();
		}

	}
}
