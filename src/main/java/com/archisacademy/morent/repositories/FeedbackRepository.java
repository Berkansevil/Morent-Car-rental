package com.archisacademy.morent.repositories;

import com.archisacademy.morent.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
