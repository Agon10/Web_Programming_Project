package com.web_project.demo.repository;

import com.web_project.demo.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguagesRepository extends JpaRepository<Language,String> {


}
