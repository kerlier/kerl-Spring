# Spring

### Nacos
#### SpringBoot以nacos为配置中心
```
1. 加入Maven依赖
<!-- nacos 依赖 -->
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>nacos-config-spring-boot-starter</artifactId>
    <version>0.2.1</version>
</dependency>

2. Application类
主要配置dataId
@SpringBootApplication
@RestController
@NacosConfigurationProperties(dataId = "nacos-config",autoRefreshed=true)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}

3. 使用
@NacosValue(value = "${name:1}",autoRefreshed = true)
private String info;

@GetMapping("/info")
public String getInfo(){
    return info;
}

4. 配置application.yml
nacos:
  config:
    server-addr: 127.0.0.1:8848
    namespace: 9945916c-1be4-44ab-827a-42433d0b8ff5
server:
  port: 8080

5. 配置nacos

```