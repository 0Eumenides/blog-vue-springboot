package com.shimh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shimh.entity.Tag;
import com.shimh.repository.TagRepository;
import com.shimh.service.TagService;
/**
 * 
 * @author shimh
 *
 * 2018年1月25日
 *
 */
@Service
public class TagServiceImpl implements TagService {
	
	@Autowired
	private TagRepository tagRepository;


	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	@Override
	public Tag getTagById(Integer id) {
		return tagRepository.getOne(id);
	}

	@Override
	@Transactional
	public Integer saveTag(Tag tag) {
		
		return tagRepository.save(tag).getId();
	}

	@Override
	@Transactional
	public Integer updateTag(Tag tag) {
		Tag oldTag = tagRepository.getOne(tag.getId());
		
		oldTag.setTagname(tag.getTagname());
		oldTag.setAvatar(tag.getAvatar());
		
		return oldTag.getId();
	}

	@Override
	@Transactional
	public void deleteTagById(Integer id) {
		tagRepository.delete(id);
	}

}
