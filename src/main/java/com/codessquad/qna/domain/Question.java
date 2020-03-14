package com.codessquad.qna.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @Column(nullable = false)
    @JsonProperty
    private String title;
    @JsonProperty
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    @JsonProperty
    private User writer;

    @OneToMany(mappedBy = "question")
    @OrderBy("id asc")
    @JsonIgnore
    private List<Answer> answers;

    @Lob
    @JsonProperty
    private String contents;
    @JsonProperty
    private boolean deleted;

    public Question() {
    }

    public Question(User writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        createdDate = LocalDateTime.now();
        deleted = false;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public long getCountOfAnswers() {
        return answers.stream().filter(answer -> !answer.isDeleted()).count();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.createdDate = LocalDateTime.now();
    }

    public String getFormattedCreatedDate() {
        if (createdDate == null) {
            return "";
        }
        return createdDate.format(DateTimeFormatter.ofPattern("YYYY-MM-SS HH:mm:ss"));
    }

    public boolean isNoAnswers() {
        return answers.isEmpty();
    }

    public boolean isSameBetweenWritersOfAnswers() {
        long countOfSameWriter = answers.stream()
                .filter(answer -> answer.getWriter() == writer).count();
        return countOfSameWriter == answers.size();
    }

    public void delete() {
        deleted = true;
        answers.forEach(Answer::delete);
    }
}
