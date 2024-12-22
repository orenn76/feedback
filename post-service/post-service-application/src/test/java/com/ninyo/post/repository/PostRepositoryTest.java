package com.ninyo.post.repository;

import com.ninyo.post.model.Post;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:application-test.yml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PostRepositoryTest {

    @Resource
    private PostRepository postRepository;

    @Test
    public void shouldAddAndFindPost() {
        Integer adjacent = ThreadLocalRandom.current().nextInt(1, 1000);
        Integer opposite = ThreadLocalRandom.current().nextInt(1, 1000);
        Post post = new Post();
        post.setName("name1");
        post.setDescription("description1");
        post.setAmount(100);
        post = postRepository.save(post);

        Post postResponse = postRepository.findById(post.getId()).orElse(null);
        Assert.assertNotNull(postResponse);
        Assert.assertEquals(post.getName(), postResponse.getName());
        Assert.assertEquals(post.getDescription(), postResponse.getDescription());
        Assert.assertEquals(post.getAmount(), postResponse.getAmount());
    }

}