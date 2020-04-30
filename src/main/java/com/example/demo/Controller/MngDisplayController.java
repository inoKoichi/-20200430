package com.example.demo.Controller;

import java.util.List;

import com.example.demo.Bean.CharactersViewBean;
import com.example.demo.Form.CharacterRegistForm;
import com.example.demo.Service.MngDisplayService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import antlr.FileLineFormatter;

@Controller
@Secured("ROLE_ADMIN")
/**
 * 管理者用Controller
 */
public class MngDisplayController {

  @Autowired
  MngDisplayService mngDisplayService;

  /**
   * 初期表示
   * 
   * @return
   */
  @RequestMapping("/admin")
  public String index() {
    return "mng_app/admin";
  }

  /***
   * データリスト表示処理
   * 
   * @param model
   * @return
   */
  @RequestMapping("/admin/data")
  public String display(Model model) {
    List<CharactersViewBean> bean = mngDisplayService.getDataList();
    model.addAttribute("dataList", bean);
    return "mng_app/data_list";
  }

  /**
   * 登録フォーム表示
   * 
   * @param model
   * @return
   */
  @RequestMapping("/admin/data_input")
  public String input(Model model) {
    CharacterRegistForm registForm = new CharacterRegistForm();
    registForm.setRegist(true);
    model.addAttribute("form", registForm);
    return "mng_app/input";
  }

  /**
   * 登録実行処理
   * 
   * @param form
   * @param model
   * @return
   */
  @RequestMapping("/data_regist")
  public String regist(CharacterRegistForm form, Model model) {

    mngDisplayService.registData(form);
    return "redirect:/admin";
  }

  /**
   * 
   * 編集用リスト表示
   */
  @RequestMapping("/admin/data_edit")
  public String dataEditList(Model model) {
    List<CharactersViewBean> bean = mngDisplayService.getDataList();
    model.addAttribute("dataList", bean);
    return "mng_app/dedit_list";
  }

  /**
   * 編集ページ表示
   */
  @RequestMapping("/d_edit")
  public String edit(@RequestParam(name = "id", required = true) Integer characterId, Model model) {

    ModelMapper modelMapper = new ModelMapper();
    CharactersViewBean bean = mngDisplayService.getData(characterId);
    CharacterRegistForm registForm = modelMapper.map(bean, CharacterRegistForm.class);

    registForm.setRegist(true);

    model.addAttribute("form", registForm);
    return "mng_app/input";
  }

}