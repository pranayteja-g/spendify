package com.spendify.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spendify.entities.Tag;
import com.spendify.services.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {

	private final TagService tagService;
	
	public TagController(TagService tagService) {
	    this.tagService = tagService;
	}


	@GetMapping
	public List<Tag> getAllTags() {
		return tagService.getAllTags();
	}

	@PostMapping
	public Tag createTag(@RequestBody Tag tag) {
		return tagService.createTag(tag);
	}
	
	@DeleteMapping("/{tagId}")
	public void deleteTag(@PathVariable long tagId) {
		tagService.deleteTag(tagId);
	}
}
