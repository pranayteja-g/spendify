package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.spendify.entities.Tag;
import com.spendify.repositories.TagRepository;
import com.spendify.services.TagService;

@ExtendWith(MockitoExtension.class)
class EntityTest {

	@Mock
	private TagRepository tagRepo;
	
	@InjectMocks
	private TagService tagService;
	
	@Test
	void testSaveTag() {
		//create a sample tag
		Tag tag = new Tag();
		tag.setName("🍉 Food");
		
		//mock behavior of tagrepository.save()
		when(tagRepo.save(tag)).thenReturn(tag);
		
		//save the tag using the service
		Tag savedTag = tagService.createTag(tag);
		
		//verify that the tag was saved successfully
		assertNotNull(savedTag);
		assertEquals(tag.getName(), savedTag.getName());
	}
	
	@Test
	void testGetAllTags() {
		//create a list of sample tags
		List<Tag> tags = new ArrayList<>();
		tags.add(new Tag("Food"));
		tags.add(new Tag("transport"));
		tags.add(new Tag("movies"));
		
		//mock behavior of tagRepository.findAll()
		when(tagRepo.findAll()).thenReturn(tags);
		
		//retrieve all tags using the service
		List<Tag> retrievedtags = tagService.getAllTags();
		
		//verify that correct number of tags were retrieved
		assertEquals(tags.size(), retrievedtags.size());
	}
}
