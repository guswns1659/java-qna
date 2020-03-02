package com.codessquad.qna;

import com.codessquad.qna.domain.Question;
import com.codessquad.qna.domain.QuestionRepository;
import com.codessquad.qna.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/form")
    public String form(HttpSession httpSession) {
        User user = HttpSessionUtils.getUserFromSession(httpSession);

        if (!HttpSessionUtils.isLoginUser(user)) {
            return "redirect:/users/loginForm";
        }
        return "question/questionForm";
    }

    @PostMapping("")
    public String question(String contents, String title, HttpSession httpSession) {
        User user = HttpSessionUtils.getUserFromSession(httpSession);

        if (!HttpSessionUtils.isLoginUser(user)) {
            return "redirect:/users/loginForm";
        }
        Question question = new Question(user.getName(), title, contents);
        questionRepository.save(question);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detailPage(@PathVariable Long id, Model model) {
        model.addAttribute("question", findQuestion(questionRepository, id));
        return "question/show";
    }

    @GetMapping("/{id}/{question.writer}/updateForm")
    public String updateForm(@PathVariable Long id,
                             @PathVariable("question.writer") String writer,
                             HttpSession httpSession,
                             Model model) {
        User user = HttpSessionUtils.getUserFromSession(httpSession);

        if (!HttpSessionUtils.isLoginUser(user)) {
            return "redirect:/users/loginForm";
        }

        if (user.notMatchWriter(writer)) {
            return "redirect:/users/loginForm";
        }
        model.addAttribute("question", findQuestion(questionRepository, id));
        return "question/updateForm";
    }

    @PutMapping("{id}/{question.writer}/update")
    public String update(@PathVariable Long id,
                         @PathVariable("question.writer") String writer,
                         String title, String contents,
                         HttpSession httpSession) {
        User user = HttpSessionUtils.getUserFromSession(httpSession);

        if (!HttpSessionUtils.isLoginUser(user)) {
            return "redirect:/users/loginForm";
        }

        if (user.notMatchWriter(writer)) {
            return "redirect:/users/loginForm";
        }

        Question question = findQuestion(questionRepository, id);
        question.update(title, contents);
        return "redirect:/questions/{id}";
    }


    private Question findQuestion(QuestionRepository questionRepository, Long id) {
        return questionRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("There is no question."));
    }
}
