package com.ninyo.post.service;

import com.ninyo.post.model.PostDto;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:application-test.yml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PostServiceTest {

    @Resource
    private PostService postService;

    @Test
    public void shouldCreateAndRead() {
        PostDto postDto = PostDto.builder().name("name").description("description").amount(100).build();
        String id = postService.create(postDto);

        PostDto postDtoResponse = postService.read(id);
        Assert.assertNotNull(postDtoResponse);
        Assert.assertEquals(postDto.getName(), postDtoResponse.getName());
        Assert.assertEquals(postDto.getDescription(), postDtoResponse.getDescription());
        Assert.assertEquals(postDto.getAmount(), postDtoResponse.getAmount());
    }

    @Test
    public void shouldReadAll() {
        PostDto postDto1 = PostDto.builder().name("name1").description("description1").amount(100).build();
        postService.create(postDto1);
        PostDto postDto2 = PostDto.builder().name("name2").description("description2").amount(200).build();
        postService.create(postDto2);

        List<PostDto> postDtoListResponse = postService.list();
        Assert.assertNotNull(postDtoListResponse);
        Assert.assertEquals(2, postDtoListResponse.size());
    }

    @Test
    public void shouldUpdate() {
        PostDto postDto1 = PostDto.builder().name("name1").description("description1").amount(100).build();
        String id = postService.create(postDto1);

        PostDto postDtoResponse = postService.read(id);
        Assert.assertNotNull(postDtoResponse);
        Assert.assertEquals(postDto1.getName(), postDtoResponse.getName());
        Assert.assertEquals(postDto1.getDescription(), postDtoResponse.getDescription());
        Assert.assertEquals(postDto1.getAmount(), postDtoResponse.getAmount());

        PostDto postDto2 = PostDto.builder().name("name2").description("description2").amount(100).build();
        postService.update(id, postDto2);

        postDtoResponse = postService.read(id);
        Assert.assertNotNull(postDtoResponse);
        Assert.assertEquals(postDto2.getName(), postDtoResponse.getName());
        Assert.assertEquals(postDto2.getDescription(), postDtoResponse.getDescription());
        Assert.assertEquals(postDto2.getAmount(), postDtoResponse.getAmount());
    }
}