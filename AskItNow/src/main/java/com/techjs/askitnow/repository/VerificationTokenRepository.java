package com.techjs.askitnow.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.techjs.askitnow.model.VerificationToken;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {

	Optional<VerificationToken> findByToken(String token);

}
