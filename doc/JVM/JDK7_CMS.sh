#!/bin/bash

# 每天几百万pv，减少网站停顿
-Dresin.home=$SERVER_ROOT
-server
-Xms6000M
-Xmx6000M
# 设置新生代对象
-Xmn500M
-XX:PermSize=500M
-XX:MaxPermSize=500M
# 无线增大Eden区大小，缩减Survivor区
-XX:SurvivorRatio=65536
# 新生代对象直接进入老年代
-XX:MaxTenuringThreshold=0
# 禁用类垃圾回收，性能会高一点
-Xnoclassgc
# 免得程序员误调用gc方法影响性能
-XX:+DisableExplicitGC
-XX:+UseConcMarkSweepGC
-XX:+UseParNewGC
-XX:+UseCMSCompactAtFullCollection
-XX:CMSFullGCsBeforeCompaction=0
-XX:+CMSClassUnloadingEnabled
-XX:-CMSParallelRemarkEnabled
# 开启关键配置
-XX:+UseCMSInitiatingOccupancyOnly
-XX:CMSInitiatingOccupancyFraction=90
-XX:SoftRefLRUPolicyMSPerMB=0
-XX:+PrintClassHistogram
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps
-XX:+PrintHeapAtGC
-Xloggc:log/gc.log
