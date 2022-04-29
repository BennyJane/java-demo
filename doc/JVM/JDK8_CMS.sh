#!/bin/bash

# 通用的JVM参数配置: 优先缩短间隔时间
# 新生代垃圾回收器是采用ParNew（标记复制算法）
# 老年代的垃圾回收器是采用CMS（标记清除算法）
# 备用老年代垃圾回收器（Concurrent Mode Failure)采用Serial Old（标记整理算法）


# 设置初始内存和最大内存为5120m(具体大小可根据项目自行调节)，设置为统一，避免扩容造成的性能损失
-Xms5120m
-Xmx5120m

# 设置新生代大小为1706m 推荐新生代大小：老年代大小比例为 1:2，新生代占整堆大小的1/3
# 或 修改比例参数
-Xmn1706m

# 设置线程栈为512k，调小该值可以增加线程数量；调大该值可以增加递归深度
-Xss512k

# 设置元空间初始内存和最大内存为512m，类的元信息、运行时常量池
-XX:MaxMetaspaceSize=512m
-XX:MetaspaceSize=512m

# 指定CMS垃圾回收器作为老年代回收器， 默认就会将 ParNew作为新生代垃圾回收器
-XX:+UseConcMarkSweepGC

# 使用Parnew垃圾回收器作为新生代回收器
-XX:+UseParNewGC

# 关闭JVM自动调控的垃圾回收，不开启此参数，后续很多配置参数无意义
# 默认CMS GC触发时间，是由JVM动态调控
-XX:+UseCMSInitiatingOccupancyOnly

# FullGC之后开启压缩，配套使用CMSFullGCsBeforeCompaction的值作为FullGC几次之后 压缩一次，
# 值为0的话就默认每次FullGC压缩一次，
# 注意CMS GC不是Full GC，可自行百度CMS 的 foreground GC和 background GC的区别）

-XX:+UseCMSCompactAtFullCollection
-XX:CMSFullGCsBeforeCompaction=0

# 在老年代内存达到70%的时候，进行CMS垃圾回收
# 老年代并行回收垃圾器，都需要考虑 为并发的用户线程创建新对象预留空间
-XX:CMSInitiatingOccupancyFraction=70

# CMS垃圾回收时卸载无用的class类
-XX:+CMSClassUnloadingEnabled

# 在做System.gc()时会做background模式CMS GC。
# 主要因为用NIO/Netty框架的时候，会直接申请堆外内存，
# 很多框架底层，为了释放mmap分配的空间，会调用System.gc()来回收
-XX:+ExplicitGCInvokesConcurrent

# 以下为打印GC日志和OOM时dump的参数
-XX:+HeapDumpOnOutOfMemoryError
# 定义堆转储日志文件位置
-XX:HeapDumpPath=xxxx/heapdump.hprof

# GC日志输出
-XX:+PrintGC
-XX:+PrintGCDateStamps
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps

# 可选参数
# 在CMS remark之前做一次ygc
-XX:CMSScavengeBeforeRemark

# 并行处理Reference
-XX:+ParallelRefProcEnabled

# 排查GC时间过长的可选参数
-XX:+PrintStringTableStatistics

-XX:+PrintReferenceGC

-XX:+PrintHeapAtGC
# 打印GC暂停时间
-XX:+PrintGCApplicationStoppedTime
# 安全点分析
-XX:+PrintSafepointStatistics

-XX:PrintSafepointStatisticsCount=1