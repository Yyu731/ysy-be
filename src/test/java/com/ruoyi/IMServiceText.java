package com.ruoyi;

import com.ruoyi.system.service.impl.UserIMServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * ClassName: IMServiceText
 * Package: com.yy.text
 * Description:
 *
 * @Author yy
 * @Create 2024-04-24 10:47
 * @Version 1.0
 */
@SpringBootTest(classes = Main.class)
@ExtendWith(SpringExtension.class)
public class IMServiceText {

    @Autowired
    private UserIMServiceImpl imService;
    
    @Test
    public void test1() {
        imService.sendWelcomeMessage("user1");
    }


}
