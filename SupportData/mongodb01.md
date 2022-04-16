## 优化 
https://www.cnblogs.com/duanxz/p/4388546.html

800~900ms -》 100ms
## 
{"op": "command", "ns": "kanban-data.package_define_record", "command": "{N}", "keysExamined": 58882, "docsExamined": 18, "cursorExhausted": true, "numYield": 460, "nreturned": 1, "locks": {"Global": {"acquireCount": {"r": 462}}, "Database": {"acquireCount": {"r": 462}}, "Collection": {"acquireCount": {"r": 462}}}, "responseLength": 543, "protocol": "op_msg", "millis": 109, "planSummary": "IXSCAN { pipelineTime: -1, parentPipelineId: 1, pipelineName: 1 }", "ts": {"$date": 1648310385839}, "client": "192.168.6.147", "allUsers": [{"user": "__system", "db": "local"}], "user": "__system@local"}


### 【hadoop】 
#--------------------------------------------------------------------
mkdir -p /mnt/disk/data/hadoop/hdfs/name/
mkdir -p /mnt/disk/data/hadoop/hdfs/data/


cat /mnt/disk/hadoop-2.8.0/logs/hadoop-root-namenode-master.log

/mnt/disk/hadoop-2.8.0/logs/


cd /mnt/disk/hadoop-2.8.0/etc/hadoop/
vim /mnt/disk/hadoop-2.8.0/etc/hadoop/core-site.yml


/mnt/disk/hadoop-2.8.0/bin/hdfs master –format

/mnt/disk/hadoop-2.8.0/bin/hadoop namenode -recover
/mnt/disk/hadoop-2.8.0/bin/hadoop master -recover
/mnt/disk/hadoop-2.8.0/sbin/start-dfs.sh
/mnt/disk/hadoop-2.8.0/sbin/stop-dfs.sh
/mnt/disk/hadoop-2.8.0/sbin/stop-all.sh


scp -r hadoop-2.8.0/ root@10.247.14.130:`pwd`
scp -r hadoop-2.8.0/ root@10.247.14.167:`pwd`



### 【hadoop: 初始化失败】 
#--------------------------------------------------------------------
格式化 NameNode 提示 SHUTDOWN_MSG: Shutting down NameNode at xxx/xxx.xxx.xxx.xxx

【01】需要让 hostname 和 /etc/hosts 下的 主机名 与 ip 地址对应， 检查/etc/hosts
# 执行 hostname 与  cat /etc/hosts 比对

【02】多次初始化 或 修改 hadoop.tmp.dir；需要先清理NameNode 与 DadaNode下文件，否则报错
https://www.jianshu.com/p/e50307229c68
清理脚本：cleanInit.sh
#!/bin/bash
set -x

if [[ ! -d "/mnt/disk/data/" ]]; then
        rm -r /mnt/disk/data/*
fi

if [[ ! -d "/mnt/disk/tmp/hadoop/" ]]; then
           rm -r /mnt/disk/tmp/hadoop/*
fi

if [[ ! -d "/mnt/disk/hadoop-2.8.0/logs/" ]]; then
   rm -r /mnt/disk/hadoop-2.8.0/logs/*
fi

mkdir -p /mnt/disk/data/hadoop/hdfs/name/
mkdir -p /mnt/disk/data/hadoop/hdfs/data/


【03 HDFS各节点常用端口】
https://blog.csdn.net/old_six_laobadaola/article/details/78482591?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~aggregatepage~first_rank_ecpm_v1~rank_v31_ecpm-3-78482591.pc_agg_new_rank&utm_term=hdfs8032%E7%AB%AF%E5%8F%A3%E6%B2%A1%E6%9C%89%E7%9B%91%E5%90%AC&spm=1000.2123.3001.4430

### 【flink: yarn集群启动报错】 
#--------------------------------------------------------------------

/mnt/disk/flink-1.7.0/bin/yarn-session.sh -s 2 -tm 800 -n 2
./yarn-session.sh -d -s 2 -tm 800 -n 2

# 报错

2022-03-24 03:29:36,010 WARN  org.apache.flink.shaded.zookeeper.org.apache.zookeeper.ClientCnxn  - SASL configuration failed: javax.security.auth.login.LoginException: No JAAS configuration section named 'Client' was found in specified JAAS configuration file: '/tmp/jaas-7796486529627953599.conf'. Will continue connection to Zookeeper server without SASL authentication, if Zookeeper server allows it.
2022-03-24 03:29:36,011 INFO  org.apache.flink.shaded.zookeeper.org.apache.zookeeper.ClientCnxn  - Opening socket connection to server node1/10.247.14.130:2181
2022-03-24 03:29:36,012 ERROR org.apache.flink.shaded.curator.org.apache.curator.ConnectionState  - Authentication failed

【解决】
- 删除主节点 /tmp 目录下 flink相关临时目录
- 修改flink安装包下conf/zoo.cfg 中配置与zookeeper中端口一致
- 修改flink.conf配置，将内存配置修改为2048M 即2G

# 常见问题
https://blog.csdn.net/hzyice/article/details/106966880?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-8.pc_relevant_default&spm=1001.2101.3001.4242.5&utm_relevant_index=11

### 【flink:yarn启动后效果】 
#--------------------------------------------------------------------


/mnt/disk/flink-1.7.0/bin/yarn-session.sh -s 2 -tm 800 -n 2
# 后台运行
/mnt/disk/flink-1.7.0/bin/yarn-session.sh -d -s 2 -tm 800 -n 2
-n : TaskManager的数量，相当于executor的数量
-s : 每个JobManager的core的数量，executor-cores。建议将slot的数量设置每台机器的处理器数量
-tm : 每个TaskManager的内存大小，executor-memory
-jm : JobManager的内存大小，driver-memory
【命令解释】：
同时向Yarn申请3个container，其中 2 个 Container 启动 TaskManager（-n 2），每个 TaskManager 拥有两个 Task Slot（-s 2），
并且向每个 TaskManager 的 Container 申请 800M 的内存，以及一个ApplicationMaster（Job Manager）。


# 查看进程
root@master:/mnt/disk/hadoop-2.8.0/sbin# jps
248609 DataNode
340465 YarnSessionClusterEntrypoint
319938 QuorumPeerMain
338594 NodeManager
248395 NameNode
338190 ResourceManager
340713 Jps
248876 SecondaryNameNode


# 主节点:8088 端口，yarn管理页面
http:10.247.14.105:8088
https://blog.csdn.net/piaoken5588/article/details/88633495

http:10.247.14.105:43325

# 终止yarn运行：<application_id> 从http:10.247.14.105:8088页面查询
yarn application -kill <application_id>
yarn application -kill application_1648107250448_0003
yarn application -kill application_1648111894111_0004


### 【flink: 测试案例】 
#--------------------------------------------------------------------
# 提交一个flink程序到yarn集群
# 参考文章： https://blog.csdn.net/qq_42761569/article/details/107385947
# 官网： https://nightlies.apache.org/flink/flink-docs-release-1.4/ops/cli.html
./bin/flink run -d -m yarn-cluster -yjm 1024 -ytm 1027 -c <入口函数> <jar_name>.jar


【使用WordCount测试用例】
./flink run -m yarn-cluster -yn 2 ../examples/batch/WordCount.jar
./flink run -m yarn-cluster -yn 2 ../examples/batch/WordCount.jar --input /mnt/disk/test/word --output /mnt/disk/test/result


### 【flink: 提交Jar|终止Job|查看日志】
# 查看日志：https://blog.csdn.net/fct2001140269/article/details/88342413
# 终止Job https://www.cnblogs.com/linyuxb-123/articles/11440146.html




### 【canal: 安装】 
#--------------------------------------------------------------------
## 安装教程
#  https://blog.csdn.net/wsdc0521/article/details/108727632?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-0.pc_relevant_paycolumn_v3&spm=1001.2101.3001.4242.1&utm_relevant_index=3
## 安装包下载：
#  https://github.com/alibaba/canal/releases

Web： http://10.247.14.105:3000
登录密码： admin 123456


### 【canal配置】 
#--------------------------------------------------------------------
## 新建Instance https://blog.csdn.net/yabingshi_tech/article/details/109642371

mysql 数据解析关注的表，Perl正则表达式.多个正则之间以逗号(,)分隔，转义符需要双斜杠(\\) 
常见例子：
1. 所有表：.* or .*\\..*
2. canal schema下所有表： canal\\..*
3. canal下的以canal打头的表：canal\\.canal.*
4. canal schema下的一张表：canal.test1
5. 多个规则组合使用：canal\\..*,mysql.test1,mysql.test2 (逗号分隔)
注意：此过滤条件只针对row模式的数据有效(ps. mixed/statement因为不解析sql，所以无法准确提取tableName进行过滤)

# filter 配置
canal.instance.filter.regex=.*\\..*
【监听所有表】.*\\..\*
【监听指定库下所有表】{库名称}\.*
【监听单表】{库名}.{表名} 
【多规则组合使用，使用逗号分隔】test\\..*, test_db.test_table

# 配置黑名单，即不监听内容
canal.instance.filter.black.regex =


### 【kafka配置】 
#--------------------------------------------------------------------

# auto.offset.reset
earliest
当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费

latest
当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据

none
topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常

默认建议用earliest。设置该参数后 kafka出错后重启，找到未消费的offset可以继续消费。

而latest 这个设置容易丢失消息，假如kafka出现问题，还有数据往topic中写，这个时候重启kafka，这个设置会从最新的offset开始消费,中间出问题的哪些就不管了。 
none这个设置没有用过，兼容性太差，经常出问题。



### 【mongo-driver: 操作mongoDB数据库 】 
#--------------------------------------------------------------------
## 3.8版本官网 http://mongodb.github.io/mongo-java-driver/3.8/driver/tutorials/bulk-writes/
## 批量操作： https://www.cnblogs.com/cnwcl/p/15359962.html
## https://www.cnblogs.com/whthomas/p/mongo-java-crud.html
