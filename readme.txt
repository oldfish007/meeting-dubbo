1.搭框架
    spring+guns+dubbo 技术选型
    创建guns-gateway
    修改POM文件
    修改结构
    API网关集成DUBBO
引入两个依赖
<!--在注册中心寻找-->
        <dependency>
            <groupId>com.101tec</groupId>
            <artifactId>zkclient</artifactId>
            <version>0.10</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba.spring.boot</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>2.0.0</version>
        </dependency>
修改配置文件
spring:
  application:
    name: meeting-gateway
  dubbo:
    server: true
    registry: zookeeper://localhost:2181
 API网关变形应用
  抽离业务接口 现在的工程里麻烦的点 一个实现类就有一个接口
  而在微服务架构中 会单独在建一个 单独承载业务接口
  各个业务模块里面需要的实体类 都会放到子工程里面
创建一个接口工程 guns-api
copy guns-core guns-gateway
删除core下面的包
注意在gateway包下面引用api module
注意删除gateway
api必须要放到maven库里面 才能依赖的上
注意安装完以后 在POM工程里面也要引入依赖
重新再guns-gateway 里面重新引入UserAPI
这样API就只需要写一次其它工程就都可以引入进来了

用户模块开发这块
修改guns jwt模块
修改
增加忽略验证URL的配置  有些地方确实不需要登陆验证
用户信息保存 ThreadLocal
jwt在前后端分离的业务中应该怎么办？
思路：会把userId查出来 然后通过jwt进行封装返回
jwt的token给我返回回来之后 做个反验证把userId取出来 把他存在threadlocal里面（变相session）
复制guns-gateway guns-user
用户服务端口 port: 8083 #用户端口不能和gateway冲突
在gateway里面 AuthController 里面添加依赖
@Reference(interfaceClass = UserAPI.class)
private UserAPI userAPI;
 userAPI.login(authRequest.getUserName(),authRequest.getPassword());
然后启动网关服务
查看User服务是否输出
配置忽略列表
jwtProperties 添加成员变量
private String ignoreUrl="";
注意忽略的URL 只是不用验证JWT
在authFilter里面添加

