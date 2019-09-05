package com.bootdo;

import com.bootdo.api.pojo.ImageApi;
import com.bootdo.api.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import org.apache.solr.client.solrj.SolrClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.bootdo.*.dao")
@SpringBootApplication
@EnableCaching
@PropertySource(value={"classpath:config/myresources.properties"},ignoreResourceNotFound = true)
@Configuration
public class BootdoApplication {
    public static void main(String[] args) {

        SpringApplication.run(BootdoApplication.class, args);
//        System.out.println("ヾ(◍°∇°◍)ﾉﾞ    bootdo启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" +
//                " ______                    _   ______            \n" +
//                "|_   _ \\                  / |_|_   _ `.          \n" +
//                "  | |_) |   .--.    .--. `| |-' | | `. \\  .--.   \n" +
//                "  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n" +
//                " _| |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n" +
//                "|_______/  '.__.'  '.__.' \\__/|______.'  '.__.'  ");
        System.out.println("/  __ | |   (_)                       (_)      \n" +
                "| /  \\| |__  _ _ __   __ _    __ _ ___ _  __ _ \n" +
                "| |   | '_ \\| | '_ \\ / _` |  / _` / __| |/ _` |\n" +
                "| \\__/| | | | | | | | (_| | | (_| \\__ | | (_| |\n" +
                " \\____|_| |_|_|_| |_|\\__,_|  \\__,_|___|_|\\__,_|\n" +
                "                                               ");
    }

    @Bean
    public PageHelper pageHelper(){

        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        pageHelper.setProperties(properties);
        return pageHelper;

    }

    @Bean
    @ConditionalOnMissingBean(SolrTemplate.class)
    public SolrTemplate solrTemplate(SolrClient solrClient) {
        return new SolrTemplate(solrClient);
    }

}
