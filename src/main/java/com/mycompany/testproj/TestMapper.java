package com.mycompany.testproj;

import java.util.HashMap;
import java.util.List;

public interface TestMapper {

    List<HashMap> selectTestData();

    int insertPassengerInfo(List<String> data);

    List<HashMap> selectTopTenByDayAvg();

    HashMap selectLowestByMonAvg();

    HashMap selectDiffByMonNum();
}
