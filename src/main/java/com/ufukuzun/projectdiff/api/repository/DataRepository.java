package com.ufukuzun.projectdiff.api.repository;

import com.ufukuzun.projectdiff.api.domain.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, String> {
}
