package com.ufukuzun.projectdiff.api.controller;

import com.ufukuzun.projectdiff.api.dto.DataCollectRequest;
import com.ufukuzun.projectdiff.api.service.DataCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("diff")
public class DataCollectRestController {

    @Autowired
    private DataCollectService dataCollectService;

    @PostMapping("{id}/left")
    public void saveOlderVersion(@PathVariable String id, @RequestBody DataCollectRequest dataCollectRequest) {
        dataCollectService.saveOlderVersion(id, dataCollectRequest);
    }

    @PostMapping("{id}/right")
    public void saveNewerVersion(@PathVariable String id, @RequestBody DataCollectRequest dataCollectRequest) {
        dataCollectService.saveNewerVersion(id, dataCollectRequest);
    }

}
