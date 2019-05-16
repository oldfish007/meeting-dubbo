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

首先先从authcontrolers申请我的jwt


DAO层生成代码
EntityGenerator
配置
gc.setOutputDir("E:\\2018\\syxc\\JOB\\guns\\guns-user\\src\\main\\java");
修改数据源
dsc.setUrl("jdbc:mysql://127.0.0.1:3306/guns_rest?autoReconnect=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8");
 strategy.setInclude(new String[]{"mooc_user_t"});
 配置生成位置
 pc.setEntity("com.stylefeng.guns.rest.common.persistence.model");
 pc.setMapper("com.stylefeng.guns.rest.common.persistence.dao");
 pc.setXml("com.stylefeng.guns.rest.common.persistence.dao.mapping");

注意微服务里面 出现service 就是暴露接口的实现 跟我们单体的serive有所区别

Caused by: java.lang.IllegalStateException: Ambiguous mapping. Cannot map 'userController' method
public com.stylefeng.guns.rest.modular.vo.ResponseVO com.stylefeng.guns.rest.modular.user.UserController.logout(com.stylefeng.guns.api.user.UserModel)
to {[/user/check],methods=[POST]}: There is already 'userController' bean method
是因为在controller 注释上面@RequestMapping(name="",)应该用value
                            192.168.1.20
gateway                     192.168.1.21
                            192.168.1.22
让每一台机器都更合理的利用 这是负载均衡
Random          随机 按照权重设置随机概率  配置到99% 和配置到%0没有区别
RoundRobin      轮询 按照公约后的权重设置轮询概率(第一个服务1s 第二个1s 第三个一个小时没有返回)
他不管你好久返回  只要来了第三个服务 就会放到第三胎服务器上面
第三台机器一直返回不了  一直等待等待  这就会造成dubbo雪崩问题
除了雪崩问题 业务会阻塞很久 这都是我们不能接受的
这种情况下 还有一种选择就是最小活跃调用数 他就会计算你的每一个服务的返回时间
他就会去计算 每一次服务返回的时间
计算出当前的节点 在哪一个请求上 只要保持机器数不变
两段式hash 这个在tomcat集群里 在dubbo服务下面一般不会选择这个
一般来说 我们会把负载配置到服务端
业务系统里面我们要控制负载 一定是配置在业务微服务里面
dubbo是在服务提供者和服务消费者之间建立一个管道 这个管道在你整个服务结束之前
他并不会结束 他一直保持在这 减少了3次握手 以及建立连接的时间 传输的速度相对比较快的
dubbo之所以认为比cloud要快的原因就在于他的底层协议他的底层协议是TCP cloud是http
tcp一定比http要快  dubbo比较适合的场景 数据包不大 dubbo的协议最多支撑到100K左右
就是单数据包100K左右 1000M网除以速率 最后计算出来的公式 大概在200多K的样子
数据包不要太大 有多个消费着对应一个服务提供者 user服务不需要提供那么多
gateway部署10台 user服务一台就可以了
RMI 在什么情况下回去替代dubbo 变成我们的默认协议呢
我们的数据包大小在100K左右
1个消费端 1个服务提供段 RMI的性能还比dubbo要好一些

总结：
       搭建框架
       API网关与服务之间的调用方式
       服务消费者 和 服务提供者之间的调用关系
       dubbo特性：负载均衡、启动检查、DUBBO协议
       什么时候用 什么时候不用
       最后就是dubbo协议这块
       重点了解了一下dubbo协议：
       长连接
       TCP协议  适用场景 数据量不大 消费者远大于提供者（就是DUBBO常见的使用协议）
       JWT
掌握API网关服务聚合功能实现
       服务聚合：前端调用一个接口
       更好的安全性  API接口做安全防护就可以了
影片模块
        创建API 公共model
        从gateway出发 先把需要的接口 然后把每个接口里面需要做什么事情想清楚
        紧接着返回在做API 跟着在做实现
