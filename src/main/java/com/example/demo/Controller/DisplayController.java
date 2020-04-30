package com.example.demo.Controller;

import java.util.List;

import com.example.demo.Bean.CharactersViewBean;
import com.example.demo.Bean.QuestionsBean;
import com.example.demo.Entity.Characters;
import com.example.demo.Entity.Member;
import com.example.demo.Entity.Questions;
import com.example.demo.Repository.CharacterRepository;
import com.example.demo.Repository.MemberRepository;
import com.example.demo.Service.DisplayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Secured("ROLE_USER")
/**
 * ユーザー用コントローラー
 */
public class DisplayController {

    @Autowired
    private DisplayService displayService;

    /**
     * 初期表示
     * 
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(final Model model) {

        return "index";
    }

    /**
     * キャラクターリスト表示処理
     * 
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String display(Model model) {
        List<CharactersViewBean> bean = displayService.getDataList();
        model.addAttribute("characterList", bean);
        return "hello_app/list";
    }

    /**
     * 診断トップページ表示処理
     * 
     * @return
     */

    @RequestMapping("/random")
    public String random() {
        return "hello_app/random";
    }

    /**
     * 診断スタートの合図を受け取り選択肢を表示する
     * 
     * @param model
     * @return
     */

    @RequestMapping("/start")
    public String start(Model model) {

        List<Questions> list1 = displayService.takeRandom();

        List<Questions> list2 = displayService.takeRandom();

        List<Questions> list3 = displayService.takeRandom();
        model.addAttribute("choices", list1);
        model.addAttribute("choices2", list2);
        model.addAttribute("choices3", list3);
        return "hello_app/select";

    }

    /**
     * 選択肢を受け取り診断処理をサービスに送り結果を受け取る
     * 
     * @param questionId
     * @param model
     * @return
     */
    @RequestMapping("/selected")
    public String shuffle(@RequestParam(name = "id", required = true) Integer questionId, Model model) {

        CharactersViewBean result = displayService.takeShuffleResult(questionId);
        model.addAttribute("resultData", result);
        return "hello_app/result";
    }
}
