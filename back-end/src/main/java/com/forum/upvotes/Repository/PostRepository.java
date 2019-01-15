package com.forum.upvotes.Repository;

import com.forum.upvotes.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@EnableJpaRepositories(basePackages = {"com.forum.upvotes.Repository"}, entityManagerFactoryRef = "")
public interface PostRepository extends JpaRepository<PostEntity, Integer> {


}
