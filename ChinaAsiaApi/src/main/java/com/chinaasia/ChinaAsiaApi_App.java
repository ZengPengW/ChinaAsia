package com.chinaasia;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.github.pagehelper.PageHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.Properties;

@SpringBootApplication
public class ChinaAsiaApi_App {
    public static void main(String[] args) {
        SpringApplication.run(ChinaAsiaApi_App.class,args);
        System.out.println("/  __ | |   (_)                       (_)      \n" +
                "| /  \\| |__  _ _ __   __ _    __ _ ___ _  __ _ \n" +
                "| |   | '_ \\| | '_ \\ / _` |  / _` / __| |/ _` |\n" +
                "| \\__/| | | | | | | | (_| | | (_| \\__ | | (_| |\n" +
                " \\____|_| |_|_|_| |_|\\__,_|  \\__,_|___|_|\\__,_|\n" +
                "                                               ");
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 定义一个信息转换对象
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        // 定义一个配置信息 比如要返回的json
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter=fastJsonConverter;
        return new HttpMessageConverters(converter);
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
}
