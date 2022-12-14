package com.example.nac.controller;

import com.example.nac.Mapper.*;
import com.example.nac.model.bonsche;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/calender")
public class CalenderController {
    //달력 정보를 넘겨주는 달력 컨트롤러
    private bonschamapper bonmapper;
    private commschemapper commapper;
    private CommKongMapper commKongMapper;
    private CommMainMapper commMainMapper;
    private CommSmallMapper commSmallMapper;

    public CalenderController(bonschamapper bonmapper, commschemapper commapper, CommKongMapper commKongMapper, CommMainMapper commMainMapper, CommSmallMapper commSmallMapper) {
        this.bonmapper = bonmapper;
        this.commapper = commapper;
        this.commKongMapper = commKongMapper;
        this.commMainMapper = commMainMapper;
        this.commSmallMapper = commSmallMapper;
    }


    //날자를 알려주면 해당 날자의 본회의 일정을 돌려줌
    @GetMapping("/date/{date}")
    public List<bonsche> GetCalenderDate(@PathVariable("date") String date){
        return bonmapper.GetbonscheDate(date);
    }

    //날자를 알려주면 해당 한달간의 본회의 일정을 돌려줌
    @GetMapping("/month/{date}")
    public Map<String,Object> GetCalenderMonth(@PathVariable("date") String date){
        String month = date.substring(0, 7); //년도와 달로 자름 2022-09-28 -> 2022-09

        //DB에서 가져온 Scadule객체 LIST를 MAP에 대입해 JSON형식으로 출력
        Map<String, Object> map = new HashMap<>();
        map.put("bonsche",bonmapper.GetbonscheMonth(month));
        map.put("commKong",commKongMapper.GetCommSche_KongMonth(month)); //일반회의 - 공청회 정보
        map.put("commMain",commMainMapper.GetCommSche_MainMonth(month)); //일반회의 - 위원회
        map.put("commSmall",commSmallMapper.GetCommSche_SmallMonth(month)); //일반회의 - 위원회
        //map.put("commsche",commapper.GetcommscheMonth(month));
        return map;
    }

    /*@PutMapping("commsche/put/{TITLE}")
    public void putcommsche(@PathVariable("TITLE")String TITLE, @RequestParam("meetingsession") String meetingsession, @RequestParam("cha") String cha, @RequestParam("meeting_date") String meeting_time, @RequestParam("meeting_time") String meeting_date,@RequestParam("link_url") String link_url, @RequestParam("unit_cd") String unit_cd, @RequestParam("unit_nm") String unit_nm){
        mapper.Putcommsche(meetingsession, cha, TITLE, meeting_date, meeting_time, link_url, unit_cd, unit_nm);
    }*/

}
