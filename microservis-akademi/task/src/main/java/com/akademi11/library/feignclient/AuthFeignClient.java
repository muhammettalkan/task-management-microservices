package com.akademi11.library.feignclient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "auth" )
public interface AuthFeignClient  {

}
