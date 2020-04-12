package com.web_project.demo.service;

import com.web_project.demo.model.Language;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface LanguageService {
    public Language save(Language language);
    public void delete(Language language);
    public Optional<Language> findLanguage(String name);
}
