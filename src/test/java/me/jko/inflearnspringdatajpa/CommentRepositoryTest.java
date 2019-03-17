package me.jko.inflearnspringdatajpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void test(){
        this.createComment(100, "spring data jpa");
        this.createComment(55, "HIBERNATE SPRING");

        PageRequest pageRequest = PageRequest.of(0, 10 , Sort.by(Sort.Direction.DESC, "LikeCount"));

        Page<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("Spring", pageRequest);
        assertThat(comments.getNumberOfElements()).isEqualTo(2);
        assertThat(comments).first().hasFieldOrPropertyWithValue("likeCount", 55 );
    }

    private void createComment(int likeCount, String comment) {
        Comment newComment = new Comment();
        newComment.setComment(comment);
        newComment.setLike(100);
        commentRepository.save(newComment);
    }

}