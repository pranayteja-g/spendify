package com.spendify.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spendify.entities.Tag;
import com.spendify.repositories.TagRepository;

@Service
public class TagService {

	private TagRepository tagRepo;
	
//	@Autowired not required
	public TagService(TagRepository tagRepo){
		this.tagRepo = tagRepo;
	}
	
	public List<Tag> getAllTags(){
		return tagRepo.findAll();
	}
	
	public Tag createTag(Tag tag) {
		return tagRepo.save(tag);
	}
	
	public void deleteTag(long tagId) {
		tagRepo.deleteById(tagId);
	}
}
