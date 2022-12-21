package com.ncoutsource;


import com.ncoutsource.service.IPraybillDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Resource
    private static IPraybillDetailService iPraybillDetailService;
	
	@Test
	public void test() {
     	//testService.insert();
	}

}
