package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Work;
import com.example.demo.service.IWorkService;

@RestController
public class WorkController {
	@Autowired
	private IWorkService workService;
	
	@RequestMapping("/get")
    public ResponseEntity<List<Work>> getAllWorks(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy) 
    {
        List<Work> list = workService.getAllWorks(pageNo, pageSize, sortBy);
 
        return new ResponseEntity<List<Work>>(list, HttpStatus.OK); 
    }

	@PostMapping("/create")
	public ResponseEntity<Work> createNewWork(@RequestBody Work work) {
		return new ResponseEntity<>(workService.save(work), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Work> updateCategory(@PathVariable int id, @RequestBody Work work) {
		Optional<Work> workOptional = workService.findById(id);

		return workOptional.map(work1 -> {
			work.setId(work1.getId());
			return new ResponseEntity<>(workService.save(work), HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Work> deleteCategory(@PathVariable int id) {
		Optional<Work> workOptional = workService.findById(id);
		return workOptional.map(work -> {
			workService.remove(id);
			return new ResponseEntity<>(work, HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
