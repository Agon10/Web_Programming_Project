package com.web_project.demo.web.rest;

import com.web_project.demo.model.Language;
import com.web_project.demo.service.LanguageService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users/languages", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class LanguagesAPI {
    private final LanguageService languageService;

    public LanguagesAPI(LanguageService languageService)
    {
        this.languageService = languageService;
    }

    @PostMapping
    public Language saveLanguage(@RequestParam(value = "languages") String languages)
    {
        return languageService.save(new Language(languages));
    }
}
