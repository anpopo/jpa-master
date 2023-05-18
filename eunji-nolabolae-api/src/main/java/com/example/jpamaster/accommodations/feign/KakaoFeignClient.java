package com.example.jpamaster.accommodations.feign;

import com.example.jpamaster.accommodations.feign.configuration.KaKaoFeignConfiguration;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "kakaoLocationClient", url = "${open-api.kakao.request-uri}", configuration = KaKaoFeignConfiguration.class)
public interface KakaoFeignClient {

    @GetMapping
    public Map searchLocation(@RequestParam("query") String query);
}
