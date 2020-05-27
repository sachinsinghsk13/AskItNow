package com.techjs.askitnow.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
	@Size(min = 1, max = 512, message = "answer length must be between 1 to 512 characters")
	private String body;
	
	private Instant postedTime;
	
	@ManyToOne
	@JoinColumn(name = "posted_by", nullable = false, referencedColumnName = "id")
	private User postedBy;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "question_id", nullable = false, referencedColumnName = "id")
	private Question question;


	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "answer_image_attachments")
	@AttributeOverride(name = "contentType", column = @Column(name="content_type"))
	private Set<ImageAttachment> imageAttachments = new HashSet<ImageAttachment>();
	
}
