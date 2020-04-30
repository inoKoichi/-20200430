package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.demo.Bean.CharactersEnum;
import com.example.demo.Bean.CharactersViewBean;
import com.example.demo.Entity.Characters;
import com.example.demo.Entity.Questions;
import com.example.demo.Form.CharacterRegistForm;
import com.example.demo.Repository.CharacterRepository;

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
 * 管理者ページ表示サービス
 */
public class MngDisplayService {

  @Autowired
  CharacterRepository characterRepository;
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
   * 登録実行されたデータをDBにsaveする
   * 
   * @param characterId
   * @param characterName
   * @param birthday
   * @param weapon
   * @param nationality
   * @param height
   * @param fav
   * @param dislike
   * @param gender
   * @param memberOf
   */
  public void registData(CharacterRegistForm form) {

    if (Objects.isNull(form.getCharacterId())) {
      final Characters characterData = modelMappar.map(form, Characters.class);
      characterRepository.save(characterData);
    } else { // 更新
      Characters updateData = modelMappar.map(form, Characters.class);
      characterRepository.save(updateData);
    }

  }

  /**
   * キャラクターデータの取得
   */
  public CharactersViewBean getData(Integer characterId) {

    Characters data = characterRepository.findByCharacterIdEquals(characterId);
    CharactersViewBean bean = modelMappar.map(data, CharactersViewBean.class);
    return bean;
  }
}
