package com.techjs.askitnow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.techjs.askitnow.model.Answer;
import com.techjs.askitnow.model.User;
import com.techjs.askitnow.model.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {
	
	
	@Query("SELECT COUNT(*) FROM Vote v WHERE v.answer = ?1 AND v.voteType = 'UPVOTE'")
	Long countUpvotesByAnswer(Answer answer);
	

	@Query("SELECT COUNT(*) FROM Vote v WHERE v.answer = ?1 AND v.voteType = 'DOWNVOTE'")
	Long countDownvotesByAnswer(Answer answer);
	
	Optional<Vote> findByAnswerAndVotedBy(Answer answer, User user);
}
