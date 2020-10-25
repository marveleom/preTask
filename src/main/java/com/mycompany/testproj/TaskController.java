package com.mycompany.testproj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TestMapper testMapper;


    /**
     * 테스트 코드잉빈다
     * @param model
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public List<HashMap> test(Model model) {

        List<HashMap> resultMap = testMapper.selectTestData();
        return resultMap;
    }

    /**
     * 1. 데이터파일(.csv)의 각 레코드를 데이터베이스에 저장
     * @return
     */
//    @PostMapping(value = "/test0")
//    public HashMap test0(){
//
//        int affected = testMapper.insertPassengerInfo();
//
//        return null;
//    }

    /**
     * 2. 일평균 인원이 가장 많은 상위 10개의 역 명과 인원수 조회
     * @return
     */
    @GetMapping(value = "/selectTopTenByDayAvg")
    public List<HashMap> selectTopTenByDayAvg() {

        List<HashMap> resultMap = testMapper.selectTopTenByDayAvg();
        return resultMap;
    }

    /**
     * 3. 월 인원수 평균이 가장 낮은 역 명과 인원수 조회
     * @return
     */
    @GetMapping(value = "/selectLowestByMonAvg")
    public HashMap selectLowestByMonAvg() {

        HashMap resultMap = testMapper.selectLowestByMonAvg();

        return resultMap;
    }

    /**
     * 4. 월 인원수 최대 최소의 차이가 가장 큰 역 명을 조회
     * @return
     */
    @GetMapping(value = "/selectDiffByMonNum")
    public HashMap selectDiffByMonNum() {

        HashMap resultMap = testMapper.selectDiffByMonNum();

        return resultMap;
    }

}
