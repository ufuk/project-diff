package com.ufukuzun.projectdiff.api.controller;

import com.ufukuzun.projectdiff.api.dto.DataDifferencesResponse;
import com.ufukuzun.projectdiff.api.service.DataDifferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataDifferenceRestController {

    @Autowired
    private DataDifferenceService dataDifferenceService;

    @GetMapping("{id}")
    public DataDifferencesResponse getDataDifferencesFor(@PathVariable String id) {
        return dataDifferenceService.getDataDifferencesFor(id);
    }

}
