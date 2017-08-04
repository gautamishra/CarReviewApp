package com.mindfire.carreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.mindfire.carreview.model.User;
/**
 * Interface For User Repository
 * @author mindfire
 *
 */


@Repository
@RepositoryRestResource(exported = true)
public interface UserJpaRepository extends JpaRepository<User, Integer> {

	 public List<User> findByUserId(Integer id);

	 public User findByEmail(String userName);
//	@Query("Select c from users where c.email = :string")
//	User queryByEmail(@Param(":string")String userName );
	 
	@Query("select m From User m where m.email =:Cname")
	User queryByname(@Param("Cname")String email);
}
