package com.codessquad.qna.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    @JsonProperty
    private User writer;

    @Lob
    @JsonProperty
    private String contents;
    @JsonProperty
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_question"))
    @JsonProperty
    private Question question;
    @JsonProperty
    private boolean deleted;

    public Answer() {
    }

    public Answer(Question question, String contents, User writer) {
        this.question = question;
        this.contents = contents;
        this.writer = writer;
        this.createdDate = LocalDateTime.now();
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

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

    public void update(String contents) {
        this.contents = contents;
        this.createdDate = LocalDateTime.now();
    }

    public String getFormattedCreatedDate() {
        if (createdDate == null) {
            return "";
        }
        return createdDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
    }

    public void delete() {
        deleted = true;
    }
}
