package com.example.demo.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.example.demo.Bean.CharactersEnum;
import com.example.demo.Bean.CharactersViewBean;
import com.example.demo.Bean.QuestionsBean;
import com.example.demo.Entity.Characters;
import com.example.demo.Entity.Questions;
import com.example.demo.Repository.CharacterRepository;
import com.example.demo.Repository.QuestionRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@Setter
@Getter
@RequiredArgsConstructor
/**
 * データ表示サービス
 */
public class DisplayService {
  @Autowired
  CharacterRepository characterRepository;
  @Autowired
  QuestionRepository questionRepository;
  @Autowired
  ModelMapper modelMappar;

  /**
   * データをリストで取得
   * 
   * @return
   */
  public List<CharactersViewBean> getDataList() {
    final List<Characters> dataList = characterRepository.findAll();
    final List<CharactersViewBean> beanList = new ArrayList<CharactersViewBean>();

    for (final Characters characters : dataList) {
      final CharactersViewBean bean = new CharactersViewBean();
      bean.setCharacterId(characters.getCharacterId());
      bean.setCharacterName(characters.getCharacterName());
      bean.setBirthday(characters.getBirthday());
      bean.setMemberOf(CharactersEnum.getCharactersEnum(characters.getMemberOf()));
      bean.setGender(characters.getGender());
      bean.setHeight(characters.getHeight());
      bean.setNationality(characters.getNationality());
      bean.setWeapon(characters.getWeapon());
      bean.setFav(characters.getFav());
      bean.setDislike(characters.getDislike());
      beanList.add(bean);
    }
    return beanList;
  }

  /**
   * リストから指定された数の要素をランダムに重複なく取り出す。
   */
  public List<Questions> takeRandom() {

    List<Questions> qList = questionRepository.findAll();

    // randomに選択された要素を持たせる
    List<Questions> takenResult = new ArrayList<Questions>();

    // 残っている要素のリスト
    List<Questions> remaining = new ArrayList<>(qList);
    Random random = new SecureRandom();

    for (int i = 0; i < 3; i++) {
      // 残っている要素を数える
      int remainingCount = remaining.size();
      // randomに選択されたインデックス
      int index = random.nextInt(remainingCount);

      // randomに選択された要素
      Questions element = remaining.get(index);
      // randomに選択された要素を持たせているリストの末尾に、randomに選択された要素を追加する
      takenResult.add(element);

      // 残っている要素のリストの最後のインデックス
      int lastIndex = remainingCount - 1;
      Questions lastElement = remaining.remove(lastIndex);// 末尾削除

      if (index < lastIndex) {
        remaining.set(index, lastElement);
      }
    }
    return takenResult;

  }

  /**
   * 質問をランダムに取得する
   */
  public CharactersViewBean takeShuffleResult(Integer questionId) {

    Questions data = questionRepository.findByQuestionIdEquals(questionId);
    int group = data.getGroups();

    // groupが1=奇数ID, 2=偶数ID, 3=memberOf1+3, 4=memberOf2+4, 5=memberOf1+2
    // 6,memberOf3+4を取得する
    CharactersViewBean result = new CharactersViewBean();

    // 1のとき
    if (group == 1) {
      // 奇数ID取得
      List<Characters> oddList = characterRepository.findByOddCharacterId();

      Random random = new SecureRandom();

      int remainingCount = oddList.size();
      int index = random.nextInt(remainingCount);

      // randomに選択された要素
      Characters element = oddList.get(index);
      result.setMemberOf(CharactersEnum.getCharactersEnum(element.getMemberOf()));
      // randomに選択された要素を持たせているリストの末尾に、randomに選択された要素を追加する
      BeanUtils.copyProperties(element, result);

      return result;
  
      //groupが2のとき
    } else if (group == 2) {
      List<Characters> evenList = characterRepository.findByEvenCharacterId();

      Random random = new SecureRandom();

      int remainingCount = evenList.size();
      int index = random.nextInt(remainingCount);

      Characters evenIdCharacter = evenList.get(index);
      result.setMemberOf(CharactersEnum.getCharactersEnum(evenIdCharacter.getMemberOf()));
      BeanUtils.copyProperties(evenIdCharacter, result);

      return result;

      //groupが3のとき
    } else if (group == 3) {
      List<Characters> mixList = characterRepository.findByExorcistAndNoah();

      Random random = new SecureRandom();

      int remainingCount = mixList.size();
      int index = random.nextInt(remainingCount);

      Characters mixCharacter = mixList.get(index);
      result.setMemberOf(CharactersEnum.getCharactersEnum(mixCharacter.getMemberOf()));
      BeanUtils.copyProperties(mixCharacter, result);
      return result;

      //groupが4のとき
    } else if (group == 4) {
      List<Characters> mixList = characterRepository.findByOthers();

      Random random = new SecureRandom();

      int remainingCount = mixList.size();
      int index = random.nextInt(remainingCount);

      Characters mixCharacter = mixList.get(index);
      result.setMemberOf(CharactersEnum.getCharactersEnum(mixCharacter.getMemberOf()));
      BeanUtils.copyProperties(mixCharacter, result);

      return result;

      //groupが5のとき
    } else if (group == 5) {
      List<Characters> mixList = characterRepository.findByBlackOrder();

      Random random = new SecureRandom();

      int remainingCount = mixList.size();
      int index = random.nextInt(remainingCount);

      Characters mixCharacter = mixList.get(index);
      result.setMemberOf(CharactersEnum.getCharactersEnum(mixCharacter.getMemberOf()));
      BeanUtils.copyProperties(mixCharacter, result);

      return result;

      //groupが6のとき
    } else if (group == 6) {
      List<Characters> mixList = characterRepository.findByNoahAndOthers();

      Random random = new SecureRandom();

      int remainingCount = mixList.size();
      int index = random.nextInt(remainingCount);

      Characters mixCharacter = mixList.get(index);
      result.setMemberOf(CharactersEnum.getCharactersEnum(mixCharacter.getMemberOf()));
      BeanUtils.copyProperties(mixCharacter, result);

      return result;
    }

    return result;
  }
}
