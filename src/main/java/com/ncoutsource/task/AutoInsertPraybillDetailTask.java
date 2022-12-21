package com.ncoutsource.task;


import com.ncoutsource.service.InsertPraybillDetailUpdatePraybillBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class AutoInsertPraybillDetailTask {

//	@Autowired
//	private ITestService testService;

    @Autowired
    private InsertPraybillDetailUpdatePraybillBService insertPraybillDetailUpdatePraybillBService;

//	@Scheduled(cron = "0/5 * * * * ? ")
//	public void selectAll() {
//		List<TableTest> items = testService.selectList(null);
//		log.info("select items size: {}", items);
//	}


    /**
     * 第一次5秒后执行，5分钟执行一次
     */
    @Scheduled(initialDelay = 5*1000, fixedRate = 5*60*1000)
    public void insertPraybillDetail(){

        insertPraybillDetailUpdatePraybillBService.insertPraybillDetails();

    }


}
