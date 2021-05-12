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


5. 当配置一个配置文件的时候，我们可以使用

   @NacosConfigurationProperties(dataId = "nacos-config",autoRefreshed=true)
   指定唯一一个dataId


   当需要指定多个dataId的时候，我们需要使用
   @NacosPropertySource(dataId = "nacos-config-2", autoRefreshed = true)
   如下：


    @Component
    @NacosPropertySource(dataId = "nacos-config",autoRefreshed = true)
    public class NacosConfig {
    
        @NacosValue(value = "${name}", autoRefreshed = true)
        private String name;
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    }

   // 这里的groupId可能没什么用,dataId一样的话，
   // nacos会读取最新修改的配置文件
   // 所以在线上使用的时候，dataId尽可能不一样

   @Component
   @NacosPropertySource(dataId = "nacos-config-2", autoRefreshed = true)
   public class NacosConfig2 {
   
       @NacosValue(value = "${password}", autoRefreshed = true)
       private String password;
   
       public String getPassword() {
           return password;
       }
   
       public void setPassword(String password) {
           this.password = password;
       }
   }
6. 配置nacos

```

### Util
#### ReflectionUtils反射工具类
```
Input input = new Input("test-aaaa");
//查找方法
Method method = ReflectionUtils.findMethod(Input.class, "getName");
//执行方法
Object invoke = ReflectionUtils.invokeMethod(method,input);
```


### 配置Druid
```
Druid页面字段解释:
执行时间分布:
这8个从左到右依次是：
0-1ms次数、
1-10ms次数、
10-100ms次数、
100-1000ms次数、
1-10秒次数、
10-100秒次数、
100-1000秒次数、
大于1000秒次数。


执行+RS时分布是将执行时间+ResultSet持有时间合并监控，
这个能方便诊断返回行数过多的查询。

```