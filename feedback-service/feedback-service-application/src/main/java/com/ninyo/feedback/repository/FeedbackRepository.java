package com.ninyo.feedback.repository;

import com.ninyo.common.crudcore.repository.BaseRepository;
import com.ninyo.feedback.model.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends BaseRepository<String, Feedback> {
}
