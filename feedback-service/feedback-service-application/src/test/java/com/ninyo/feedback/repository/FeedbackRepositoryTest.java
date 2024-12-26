package com.ninyo.feedback.repository;

import com.ninyo.feedback.model.Feedback;
import com.ninyo.feedback.model.Status;
import com.ninyo.feedback.model.Type;
import com.ninyo.feedback.model.User;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:application-test.yml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FeedbackRepositoryTest {

    @Resource
    private FeedbackRepository feedbackRepository;

    @Test
    public void shouldAddAndFindFeedback() {
        Feedback feedback = new Feedback();

        feedback.setCreatedAt(Instant.now());
        feedback.setUpdatedAt(Instant.now());
        feedback.setUser(User.builder().name("name").email("email").build());
        feedback.setType(Type.FEATURE_REQUEST);
        feedback.setTitle("title1");
        feedback.setDescription("description1");
        feedback.setVotes(ThreadLocalRandom.current().nextInt(0, 100));
        feedback.setStatus(Status.OPEN);
        feedback = feedbackRepository.save(feedback);

        Feedback feedbackResponse = feedbackRepository.findById(feedback.getId()).orElse(null);
        Assert.assertNotNull(feedbackResponse);
        Assert.assertEquals(feedback.getCreatedAt(), feedbackResponse.getCreatedAt());
        Assert.assertEquals(feedback.getUpdatedAt(), feedbackResponse.getUpdatedAt());
        Assert.assertEquals(feedback.getUser().getName(), feedbackResponse.getUser().getName());
        Assert.assertEquals(feedback.getUser().getEmail(), feedbackResponse.getUser().getEmail());
        Assert.assertEquals(feedback.getType(), feedbackResponse.getType());
        Assert.assertEquals(feedback.getTitle(), feedbackResponse.getTitle());
        Assert.assertEquals(feedback.getDescription(), feedbackResponse.getDescription());
        Assert.assertEquals(feedback.getVotes(), feedbackResponse.getVotes());
        Assert.assertEquals(feedback.getStatus(), feedbackResponse.getStatus());
    }

}