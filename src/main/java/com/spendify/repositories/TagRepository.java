package com.spendify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spendify.entities.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
