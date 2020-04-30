package com.example.demo.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import com.example.demo.Bean.QuestionsBean;
import com.example.demo.Bean.QuestionsGroupsEnum;
import com.example.demo.Entity.Questions;
import com.example.demo.Repository.QuestionRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@RequiredArgsConstructor

/**
 * 管理者用質問情報サービス
 */
public class MngQuestionService {

  @Autowired
  QuestionRepository questionRepository;

  @Autowired
  ModelMapper modelMapper;

  /**
   * リスト表示 データ取得
   */
  public List<QuestionsBean> getQuestionList() {

    List<Questions> qList = questionRepository.findAll();
    List<QuestionsBean> beanList = new ArrayList<QuestionsBean>();

    for (final Questions q : qList) {
      QuestionsBean bean = new QuestionsBean();
      bean.setQuestionId(q.getQuestionId());
      bean.setChoices(q.getChoices());
      bean.setGroups(QuestionsGroupsEnum.getQuestionsGroupsEnum(q.getGroups()));

      beanList.add(bean);
    }
    return beanList;
  }

  /**
   * 登録処理
   * 
   * @param questionId
   * @param choices
   * @param groups
   */
  public void regist(Integer questionId, String choices, Integer groups) {

    if (Objects.isNull(questionId) && Objects.isNull(groups)) {
      Questions questionData = new Questions();
      questionData.setChoices(choices);

      List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

      Collections.shuffle(list);
      Integer index = new SecureRandom().nextInt(list.size());
      Integer result = list.get(index);
      questionData.setGroups(result);

      questionRepository.save(questionData);
    }
  }

  /**
   * IDにもとづく データ取得
   */
  public QuestionsBean getQData(Integer questionId) {

    ModelMapper modelMappar = new ModelMapper();

    Questions qData = questionRepository.findByQuestionIdEquals(questionId);
    QuestionsBean bean = modelMapper.map(qData, QuestionsBean.class);
    return bean;
  }

}
