package com.ufukuzun.projectdiff.api.service;

import com.ufukuzun.projectdiff.api.domain.Data;
import com.ufukuzun.projectdiff.api.dto.DataDifference;
import com.ufukuzun.projectdiff.api.dto.DataDifferencesResponse;
import com.ufukuzun.projectdiff.api.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataDifferenceService {

    @Autowired
    private DataRepository dataRepository;

    public DataDifferencesResponse getDataDifferencesFor(String id) {
        Data data = dataRepository.getOne(id);

        if (data.hasNotEqualLengths()) {
            return new DataDifferencesResponse();
        }

        return prepareDataDifferencesResponse(data);
    }

    private DataDifferencesResponse prepareDataDifferencesResponse(Data data) {
        DataDifferencesResponse dataDifferencesResponse = new DataDifferencesResponse();
        dataDifferencesResponse.setEqualLengths(true);
        dataDifferencesResponse.setDifferences(findDifferencesFor(data));
        return dataDifferencesResponse;
    }

    private List<DataDifference> findDifferencesFor(Data data) {
        List<DataDifference> dataDifferences = new ArrayList<>();

        for (int charIndex = 0; data.hasMoreContent(charIndex); charIndex++) {
            if (data.hasDifferenceAt(charIndex)) {
                DataDifference dataDifference = new DataDifference(charIndex);
                dataDifferences.add(dataDifference);
                do {
                    dataDifference.incrementLength();
                    charIndex++;
                } while (data.hasMoreContent(charIndex) && data.hasDifferenceAt(charIndex));
            }
        }

        return dataDifferences;
    }

}
