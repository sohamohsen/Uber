package com.uber.uber.repository;

import com.uber.uber.models.ReleaseYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseYearRepo extends JpaRepository<ReleaseYear, Integer> {
}
