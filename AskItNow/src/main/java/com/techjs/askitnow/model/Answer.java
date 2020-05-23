package com.techjs.askitnow.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "answers")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size
	private String body;
	
	private Instant postedTime;
	
	@ManyToOne
	@JoinColumn(name = "posted_by", nullable = false, referencedColumnName = "id")
	private User postedBy;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "question_id", nullable = false, referencedColumnName = "id")
	private Question question;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "answer", orphanRemoval = true, cascade = CascadeType.ALL)
//	private Set<Comment> comments = new HashSet<Comment>();
//	
}
