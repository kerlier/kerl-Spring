# Spring
### Spring-Cloud-Config
#### SpringCloud使用SpringCloudConfig作为配置中心
```
1. 加入maven依赖
 <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
 </dependency>

2. application配置文件

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/kerlier/fashion-cloud-config.git
          username: kerlier  # 用户名
          password: yang199626  # 密码
      label: dev  # label表示分支
  application:
    name: config   # name必须要有




3. Application类
@EnableConfigServer
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

4. 使用
@Value("${info}")
private String version;


```



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

### Nacos作为注册中心

1. 引入maven依赖
```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-registry-nacos</artifactId>
            <version>2.7.5</version>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>2.7.5</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba.nacos</groupId>
            <artifactId>nacos-client</artifactId>
            <version>1.1.3</version>
        </dependency>
```

2. 修改配置文件
```
spring:
  application:
    name: register-consumer
dubbo:
  application:
    name: register-consumer # 指定dubbo的applicationName
  registry:
    timeout: 6000  # 指定调用的超时时间
    address: nacos://127.0.0.1:8848 # nacos的地址
    check: false # 启动时是否检查有提供者
    parameters:  # 这里可以指定不同的namespace,以便于区分不同的环境
      namespace: f809bd6f-e655-4943-b842-4b1b8e0c5157
server:
  port: 8081
```

3. Application类
```
@SpringBootApplication
@EnableDubbo   # 需要添加@EnableDubbo关键字
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
```
4. 其他调用跟dubbo使用无异



### 使用Sentinel
#####  1. 引入Maven依赖
```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
```
##### 2.  修改application.yml
```
spring:
  cloud:
    sentinel:
      transport:
        dashboard: localhost:8082
        port: 8179
```
##### 3.  代码使用
```
可以定义一个SentinelExceptionHandler
代码如下：
    public static String blockExceptionHandler( BlockException e) {
        // 不同的异常返回不同的提示语
        if (e instanceof FlowException) {
            return "请求过于频繁，请控制频率";
        } else if (e instanceof DegradeException) {
            return "服务熔断降级了";
        } else if (e instanceof ParamFlowException) {
            return "热点参数限流了";
        } else if (e instanceof SystemBlockException) {
            return "触发系统保护规则";
        } else if (e instanceof AuthorityException) {
            return "触发系统保护规则";
        }
        return "sentinel异常";
    }


真正使用地方：
    @GetMapping("/hello")
    @SentinelResource(value = "hotkey",
            blockHandlerClass = SentinelExceptionHandler.class,
            blockHandler="blockExceptionHandler")
    public String hello(){
        return "hello";
    }
```
##### 4. 定义规则
![资源展示](fashion-spring/image/lALPDh0cPQ_uRfnNApbNB2I_1890_662.png)
![设置流控](fashion-spring/image/lALPDgtYw9gRYv7NAmbNBEU_1093_614.png)

当流量超过设置值时，会直接报错，报错信息时SentinelExceptionHandler中的设置的值。



### Mybatis-plus使用

##### 1. 引入Maven依赖
```
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <!-- mybatis plus 代码生成器 -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.2.0</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.2.0</version>
        </dependency>
        <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
            <version>2.3.28</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.47</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.2</version>
        </dependency>
```

##### 2. 自动生成代码
```
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("test");
        gc.setOpen(false);
        //实体属性 Swagger2 注解
        gc.setSwagger2(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.example");
        pc.setEntity("model.auto");
        pc.setMapper("mapper.auto");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.baomidou.mybatisplus.extension.activerecord.Model");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        strategy.setEntityLombokModel(true);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
```
##### 3. 使用
```
    1. 查询
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getById(Integer userId) {
        return userInfoMapper.selectById(userId);
    }

    2. 条件查询
    @Override
    public UserInfo getByUsername(String search){
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.like("username",search);
        List<UserInfo> userInfos = userInfoMapper.selectList(wrapper);
        if(!userInfos.isEmpty()){
            return userInfos.get(0);
        }
        return null;
    }
    注：MybatisPlus中的条件查询一般会使用QueryWrapper
    这个对象会满足我们大部分的需求
```
##### 4. 常用的MybatisPlus拦截器
```
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
```

##### 5. 自定义使用InnerInterceptor
MybatisPlus的拦截器需要继承InnerInterceptor
Mybatis的拦截器需要继承Interceptor
```
1. 实现InnerInterceptor
public class DataPermissionInnerInterceptor implements InnerInterceptor {
    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        try {
            // id为执行的mapper方法的全路径名，如com.cq.UserMapper.insertUser， 便于后续使用反射
            String id = ms.getId();
            // sql语句类型 select、delete、insert、update
            String sqlCommandType = ms.getSqlCommandType().toString();

            // 获取到原始sql语句
            String sql = boundSql.getSql().toLowerCase();
            log.info("SQL：{}", sql);
            // 增强sql
            // 通过反射，拦截方法上带有自定义@DataPermission注解的方法，并增强sql
            String mSql = sqlAnnotationEnhance(id, sqlCommandType, sql);
            PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
            mpBs.sql(mSql);
            log.info("增强后的SQL：{}", mSql);
        } catch (ClassNotFoundException e) {
            log.error("数据权限拦截器异常，{}", Throwables.getStackTraceAsString(e));
        }
    }
}
2. 注册拦截器,需要在Mybatis的拦截器基础上新增
    @Bean
    public MybatisPlusInterceptor dataPermissionInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        DataPermissionInnerInterceptor dataPermissionInterceptor = new DataPermissionInnerInterceptor();
        interceptor.addInnerInterceptor(dataPermissionInterceptor);
        return interceptor;
    }
```

### Redis
##### Redis集群


### Flowable
##### 读懂Flowable配置文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:xsd="http://www.w3.org/2001/XMLSchema"
             xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI"
             xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC"
             xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI"
             xmlns:flowable="http://flowable.org/bpmn"
             typeLanguage="http://www.w3.org/2001/XMLSchema"
             expressionLanguage="http://www.w3.org/1999/XPath"
             targetNamespace="http://www.flowable.org/processdef">

    <process id="holidayRequest" name="Holiday Request" isExecutable="true">

        <startEvent id="startEvent"/>
        <sequenceFlow sourceRef="startEvent" targetRef="approveTask"/>

        <userTask id="approveTask" name="Approve or reject request" flowable:candidateGroups="managers"/>
        <sequenceFlow sourceRef="approveTask" targetRef="decision"/>

        <exclusiveGateway id="decision"/>
        <sequenceFlow sourceRef="decision" targetRef="externalSystemCall">
            <conditionExpression xsi:type="tFormalExpression">
                <![CDATA[
          ${approved}
        ]]>
            </conditionExpression>
        </sequenceFlow>
        <sequenceFlow  sourceRef="decision" targetRef="sendRejectionMail">
            <conditionExpression xsi:type="tFormalExpression">
                <![CDATA[
          ${!approved}
        ]]>
            </conditionExpression>
        </sequenceFlow>

        <serviceTask id="externalSystemCall" name="Enter holidays in external system"
                     flowable:class="org.flowable.CallExternalSystemDelegate"/>
        <sequenceFlow sourceRef="externalSystemCall" targetRef="holidayApprovedTask"/>

        <userTask id="holidayApprovedTask" name="Holiday approved" flowable:assignee="${employee}"/>
        <sequenceFlow sourceRef="holidayApprovedTask" targetRef="approveEnd"/>

        <serviceTask id="sendRejectionMail" name="Send out rejection email"
                     flowable:class="org.flowable.SendRejectionMail"/>
        <sequenceFlow sourceRef="sendRejectionMail" targetRef="rejectEnd"/>

        <endEvent id="approveEnd"/>

        <endEvent id="rejectEnd"/>

    </process>
</definitions>
```
上面的配置文件看起来特别复杂，但是对照着流程图我们才能看到对应的效果
![flowable流程图](./fashion-spring/image/getting.started.bpmn.process.png)

其中sourceRef以及targetRef对应着箭头的两端，
  sourceRef是箭头的出发端 targetRef指的是箭头的目的端

流程图中的圆圈对应着配置文件中的Event标签.

流程图中的带头像方框对应着配置文件中的userTask.

流程图中的带设置方框对应着配置文件中的serviceTask,可以指定class类.

带叉的表示路由网关：关键词对应exclusiveGateway.

流程图中的箭头对应的是sequenceFlow,可以加判断条件.

### AppcationListener
##### 1. 定义Event事件(需继承ApplicationEvent)
```
public class FashionEvent extends ApplicationEvent {
    public FashionEvent(ConfigDTO source) {
        super(source);
    }

    @Override
    public ConfigDTO getSource(){
        return (ConfigDTO) super.getSource();
    }
}
```
##### 2. 先定义发布者publisher(需实现ApplicationEventPublisher)
```
实现两个方法：
@Component
public class MyOwnPublisher implements ApplicationEventPublisher {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }

    @Override
    public void publishEvent(Object o) {
        applicationContext.publishEvent(o);
    }
}
```

##### 3.定义监听者(需实现ApplicationListener)
```
@Component
public class MyOwnListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent fashionEvent) {
        FashionEvent event = (FashionEvent) fashionEvent;
        System.out.println("获取event:"+ JSONObject.toJSONString(event.getSource()));
    }
}
```

### Callable(Java自带的Callable)
简单来说，就是同一个方法,可以执行不同逻辑。
```
 public static String stringBlankThenExecute(String source, Callable<String> callable){
        if(Objects.isNull(source)||source.isEmpty()){
            try{
                return callable.call();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("string empty and then execute cause an exception");
            }
        }
        return source;
    }

    public static void main(String[] args) {
        //这里可以执行不同的逻辑
        String test = stringBlankThenExecute("", () -> {
            return "为空的时候返回";
        });
        System.out.println(test);
    }
```
### 监听文件变化
```
 executor=new ScheduledThreadPoolExecutor(1);
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行1");
                System.out.println("lastModified:" + lastModified);
                synchronized (this){
                    if(stopped){
                        return ;
                    }
                    boolean reload = false;
                    File file = new File(filePath);
                    if(!Objects.equals(lastModified,file.lastModified())){
                        System.out.println("执行2");
                        reload = true;
                        lastModified = file.lastModified();
                    }

                    if(reload){
                        System.out.println("执行3");
                        print(file);
                    }
                }
            }
        },5 * 1000L,5 * 1000L, TimeUnit.MILLISECONDS);

```



