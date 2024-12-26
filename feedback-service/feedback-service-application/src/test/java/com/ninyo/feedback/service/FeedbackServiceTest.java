package com.ninyo.feedback.service;

import com.ninyo.feedback.model.FeedbackDto;
import com.ninyo.feedback.model.UserDto;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:application-test.yml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FeedbackServiceTest {

    @Resource
    private FeedbackService feedbackService;

    @Test
    public void shouldCreateAndRead() {
        FeedbackDto feedbackDto = FeedbackDto.builder()
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .user(UserDto.builder().name("name").email("email").build())
                .type("type")
                .title("title")
                .description("description")
                .votes(0)
                .status("status")
                .build();
        String id = feedbackService.create(feedbackDto);

        FeedbackDto feedbackDtoResponse = feedbackService.read(id);
        Assert.assertNotNull(feedbackDtoResponse);
        Assert.assertEquals(feedbackDto.getCreatedAt(), feedbackDtoResponse.getCreatedAt());
        Assert.assertEquals(feedbackDto.getUpdatedAt(), feedbackDtoResponse.getUpdatedAt());
        Assert.assertEquals(feedbackDto.getUser().getName(), feedbackDtoResponse.getUser().getName());
        Assert.assertEquals(feedbackDto.getUser().getEmail(), feedbackDtoResponse.getUser().getEmail());
        Assert.assertEquals(feedbackDto.getType(), feedbackDtoResponse.getType());
        Assert.assertEquals(feedbackDto.getTitle(), feedbackDtoResponse.getTitle());
        Assert.assertEquals(feedbackDto.getDescription(), feedbackDtoResponse.getDescription());
        Assert.assertEquals(feedbackDto.getVotes(), feedbackDtoResponse.getVotes());
        Assert.assertEquals(feedbackDto.getStatus(), feedbackDtoResponse.getStatus());
    }

    @Test
    public void shouldReadAll() {
        FeedbackDto feedbackDto1 = FeedbackDto.builder()
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .user(UserDto.builder().name("name1").email("email1").build())
                .type("type1")
                .title("title1")
                .description("description1")
                .votes(0)
                .status("status1")
                .build();
        feedbackService.create(feedbackDto1);
        FeedbackDto feedbackDto2 = FeedbackDto.builder()
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .user(UserDto.builder().name("name2").email("email2").build())
                .type("type2")
                .title("title2")
                .description("description2")
                .votes(0)
                .status("status2")
                .build();
        feedbackService.create(feedbackDto2);

        List<FeedbackDto> feedbackDtoListResponse = feedbackService.list();
        Assert.assertNotNull(feedbackDtoListResponse);
        Assert.assertEquals(2, feedbackDtoListResponse.size());
    }

    @Test
    public void shouldUpdate() {
        FeedbackDto feedbackDto1 = FeedbackDto.builder()
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .user(UserDto.builder().name("name1").email("email1").build())
                .type("type1")
                .title("title1")
                .description("description1")
                .votes(0)
                .status("status1")
                .build();
        String id = feedbackService.create(feedbackDto1);

        FeedbackDto feedbackDtoResponse = feedbackService.read(id);
        Assert.assertNotNull(feedbackDtoResponse);
        Assert.assertEquals(feedbackDto1.getCreatedAt(), feedbackDtoResponse.getCreatedAt());
        Assert.assertEquals(feedbackDto1.getUpdatedAt(), feedbackDtoResponse.getUpdatedAt());
        Assert.assertEquals(feedbackDto1.getUser().getName(), feedbackDtoResponse.getUser().getName());
        Assert.assertEquals(feedbackDto1.getUser().getEmail(), feedbackDtoResponse.getUser().getEmail());
        Assert.assertEquals(feedbackDto1.getType(), feedbackDtoResponse.getType());
        Assert.assertEquals(feedbackDto1.getTitle(), feedbackDtoResponse.getTitle());
        Assert.assertEquals(feedbackDto1.getDescription(), feedbackDtoResponse.getDescription());
        Assert.assertEquals(feedbackDto1.getVotes(), feedbackDtoResponse.getVotes());
        Assert.assertEquals(feedbackDto1.getStatus(), feedbackDtoResponse.getStatus());

        FeedbackDto feedbackDto2 = FeedbackDto.builder()
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .user(UserDto.builder().name("name2").email("email2").build())
                .type("type2")
                .title("title2")
                .description("description2")
                .votes(0)
                .status("status2")
                .build();
        feedbackService.update(id, feedbackDto2);

        feedbackDtoResponse = feedbackService.read(id);
        Assert.assertNotNull(feedbackDtoResponse);
        Assert.assertEquals(feedbackDto2.getCreatedAt(), feedbackDtoResponse.getCreatedAt());
        Assert.assertEquals(feedbackDto2.getUpdatedAt(), feedbackDtoResponse.getUpdatedAt());
        Assert.assertEquals(feedbackDto2.getUser().getName(), feedbackDtoResponse.getUser().getName());
        Assert.assertEquals(feedbackDto2.getUser().getEmail(), feedbackDtoResponse.getUser().getEmail());
        Assert.assertEquals(feedbackDto2.getType(), feedbackDtoResponse.getType());
        Assert.assertEquals(feedbackDto2.getTitle(), feedbackDtoResponse.getTitle());
        Assert.assertEquals(feedbackDto2.getDescription(), feedbackDtoResponse.getDescription());
        Assert.assertEquals(feedbackDto2.getVotes(), feedbackDtoResponse.getVotes());
        Assert.assertEquals(feedbackDto2.getStatus(), feedbackDtoResponse.getStatus());
    }

    //TODO oren
//    @Test
//    public void shouldNotCreate() {
//        FeedbackDto feedbackDto1 = FeedbackDto.builder()
//                .createdAt(Instant.now())
//                .updatedAt(Instant.now())
//                .type("type")
//                .title("title")
//                .description("description")
//                .votes(0)
//                .status("status")
//                .build();
//        feedbackService.create(feedbackDto1);
//
//        FeedbackDto feedbackDto2 = FeedbackDto.builder()
//                .createdAt(Instant.now())
//                .updatedAt(Instant.now())
//                .user(UserDto.builder().email("email").build())
//                .type("type")
//                .title("title")
//                .description("description")
//                .votes(0)
//                .status("status")
//                .build();
//        feedbackService.create(feedbackDto2);
//
//        FeedbackDto feedbackDto3 = FeedbackDto.builder()
//                .createdAt(Instant.now())
//                .updatedAt(Instant.now())
//                .user(UserDto.builder().name("name").build())
//                .type("type")
//                .title("title")
//                .description("description")
//                .votes(0)
//                .status("status")
//                .build();
//        feedbackService.create(feedbackDto3);
//    }
}