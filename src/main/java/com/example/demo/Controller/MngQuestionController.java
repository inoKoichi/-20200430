package com.example.demo.Controller;

import java.util.List;

import com.example.demo.Bean.QuestionsBean;
import com.example.demo.Entity.Questions;
import com.example.demo.Form.QuestionRegistForm;
import com.example.demo.Service.MngQuestionService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Secured("ROLE_ADMIN")
public class MngQuestionController {

  @Autowired
  MngQuestionService mngQuestionService;

  /**
   * 質問リスト表示
   */
  @RequestMapping("/admin/questions")
  public String index(Model model) {
    List<QuestionsBean> bean = mngQuestionService.getQuestionList();
    model.addAttribute("qList", bean);
    return "mng_app/question_list";
  }

  /**
   * 質問登録フォーム表示処理
   * 
   * @param model
   * @return
   */
  @RequestMapping("admin/q_input")
  public String input(Model model) {
    QuestionRegistForm registForm = new QuestionRegistForm();
    registForm.setRegist(true);
    model.addAttribute("qForm", registForm);
    return "mng_app/q_input";
  }

  /**
   * 登録処理
   */
  @RequestMapping("/q_regist")
  public String regist(QuestionRegistForm form, Model model) {

    mngQuestionService.regist(form.getQuestionId(), form.getChoices(), form.getGroups());
    return "redirect:/admin/questions";
  }

  /**
   * 編集用リスト表示
   */
  @RequestMapping("/admin/for_edit_list")
  public String editList(Model model) {
    List<QuestionsBean> bean = mngQuestionService.getQuestionList();
    model.addAttribute("qList", bean);
    return "mng_app/edit_list";
  }

  /**
   * 編集フォーム遷移
   * 
   * @param questionId
   * @param model
   * @return
   */
  @RequestMapping("/q_edit")
  public String edit(@RequestParam(name = "id", required = true) Integer questionId, Model model) {

    ModelMapper modelMapper = new ModelMapper();
    QuestionsBean bean = mngQuestionService.getQData(questionId);
    QuestionRegistForm registForm = modelMapper.map(bean, QuestionRegistForm.class);

    registForm.setRegist(true);

    model.addAttribute("qForm", registForm);
    return "mng_app/q_input";
  }
}