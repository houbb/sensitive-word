package com.github.houbb.sensitive.word.spring;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.spring.annotation.Autowired;
import com.github.houbb.sensitive.word.spring.annotation.Bean;
import com.github.houbb.sensitive.word.spring.annotation.Configuration;
import com.github.houbb.sensitive.word.spring.database.MyDdWordAllow;
import com.github.houbb.sensitive.word.spring.database.MyDdWordDeny;
import com.github.houbb.sensitive.word.support.allow.WordAllows;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@Configuration
public class SpringSensitiveWordConfig {

    @Autowired
    private MyDdWordAllow myDdWordAllow;

    @Autowired
    private MyDdWordDeny myDdWordDeny;

    /**
     * 初始化引导类
     * @return 初始化引导类
     * @since 1.0.0
     */
    @Bean
    public SensitiveWordBs sensitiveWordBs() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.chains(WordAllows.system(), myDdWordAllow))
                .wordDeny(myDdWordDeny)
                // 各种其他配置
                .init();

        return sensitiveWordBs;
    }

}
