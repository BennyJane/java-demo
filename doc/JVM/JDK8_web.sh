#!/bin/bash


#### 一个性能较好的web服务器jvm参数配置
# -Xmn和-Xmx之比大概是1:9，如果把新生代内存设置得太大会导致young gc时间较长
# 一个好的Web系统应该是每次http请求申请内存都能在young gc回收掉，full gc永不发生，当然这是最理想的情况
# xmn的值应该是保证够用（够http并发请求之用）的前提下设置得尽量小

#### 游戏服务器的配置
# 游戏服务器的xmn即年轻代设置比较大，和Xmx大概1:3的关系，
# 因为游戏服务器一般是长连接，在保持一定的并发量后需要较大的年轻代堆内存，
# 如果设置得大小了会经常引发young gc

# 服务器模式
-server

-Xmx2g
-Xms2g
# 设置年轻代大小
-Xmn256m
-Xss256k

# 忽略手动调用GC, System.gc()的调用就会变成一个空调用，完全不触发GC
-XX:+DisableExplicitGC
# 使用并发标记清除GC收集器
-XX:+UseConcMarkSweepGC
# 在FULL GC的时候对年老代的压缩
-XX:+UseCMSCompactAtFullCollection
# 内存页的大小
-XX:LargePageSizeInBytes=128m
# 原始类型的快速优化
-XX:+UseFastAccessorMethods
# 使用手动定义初始化定义开始CMS收集
-XX:+UseCMSInitiatingOccupancyOnly
# -XX:CMSInitiatingOccupancyFraction=70
-XX:CMSInitiatingOccupancyFraction=70
