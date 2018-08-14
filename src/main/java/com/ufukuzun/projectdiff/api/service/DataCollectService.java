package com.ufukuzun.projectdiff.api.service;

import com.ufukuzun.projectdiff.api.domain.Data;
import com.ufukuzun.projectdiff.api.dto.DataCollectRequest;
import com.ufukuzun.projectdiff.api.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataCollectService {

    @Autowired
    private DataRepository dataRepository;

    public void saveOlderVersion(String id, DataCollectRequest dataCollectRequest) {
        Data data = getOrCreate(id);
        data.setOlderContent(dataCollectRequest.getData());
        dataRepository.save(data);
    }

    public void saveNewerVersion(String id, DataCollectRequest dataCollectRequest) {
        Data data = getOrCreate(id);
        data.setNewerContent(dataCollectRequest.getData());
        dataRepository.save(data);
    }

    private Data getOrCreate(String id) {
        Optional<Data> dataById = dataRepository.findById(id);
        if (dataById.isPresent()) {
            return dataById.get();
        } else {
            Data data = new Data(id);
            return dataRepository.save(data);
        }
    }

}
