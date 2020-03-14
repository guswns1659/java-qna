package com.codessquad.qna.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @Column(nullable = false, length = 20)
    @JsonProperty
    private String userId;
    @JsonProperty
    private String password;
    @JsonProperty
    private String name;
    @JsonProperty
    private String email;

    @OneToMany(mappedBy = "writer")
    @OrderBy("id asc")
    @JsonIgnore
    private List<Question> questions;
    @OneToMany(mappedBy = "writer")
    @OrderBy("id asc")
    @JsonIgnore
    private List<Answer> answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }


    public void update(String name, String email, String newPassword) {
        this.name = name;
        this.email = email;
        this.password = newPassword;
    }

    public boolean notMatchId(Long id) {
        return !id.equals(this.id);
    }

    public boolean notMatchPassword(String password) {
        return !password.equals(this.password);
    }

    public boolean notMatchWriter(String writer) {
        return !writer.equals(name);
    }
}
