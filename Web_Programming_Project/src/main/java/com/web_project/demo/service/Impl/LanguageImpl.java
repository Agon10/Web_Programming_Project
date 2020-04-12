package com.web_project.demo.service.Impl;

import com.web_project.demo.model.Language;
import com.web_project.demo.repository.LanguagesRepository;
import com.web_project.demo.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LanguageImpl implements LanguageService {

    public final LanguagesRepository languagesRepository;
    public LanguageImpl(LanguagesRepository languagesRepository)
    {
        this.languagesRepository = languagesRepository;
    }

    @Override
    public Language save(Language language) {
       return languagesRepository.save(language);
    }

    @Override
    public void delete(Language language) {
        languagesRepository.delete(language);
    }

    @Override
    public Optional<Language> findLanguage(String name) {
      return   languagesRepository.findById(name);
    }
}
