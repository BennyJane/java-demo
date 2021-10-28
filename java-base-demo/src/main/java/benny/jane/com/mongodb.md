# Grafana 的 Mongo 数据源

https://grafana.com/grafana/plugins/grafana-mongodb-datasource/

#### 已知限制

- 仅支持`find`和`aggregate`读取命令
- 目前`ISODate`是唯一支持的对象构造函数

#### 查询数据源

查询编辑器支持与 MongoDB Shell 相同的语法，但有一些限制：

- 只能在每个查询中运行一个命令或查询。
- 仅支持`find`和`aggregate`读取命令。
- `ISODate` 是唯一受支持的对象构造函数。

#### 附加语法

编辑器通过*数据库选择*扩展了 MongoDB Shell 语法，您可以在其中使用数据库名称而不是`db`. 例如，`sample_mflix.movies.find()`。您仍然可以使用`db`来引用连接字符串中的默认数据库。

它还通过*聚合排序*对其进行了扩展。排序通常发生在聚合管道内。扩展语法上允许`aggregate`类似`find`。例如，`sample_mflix.movies.aggregate({}).sort({"time": 1})`。

> **注意：** MongoDB 不会使用此语法执行排序。排序发生在从集合中查询结果之后。

#### 查询为时间序列

通过将日期字段别名 命名为 `time` 来进行时间序列查询。

**注意**： 可以将非日期字段 强制转换为 日期字段，并将其命名为 `time`来进行时间序列查询。

```sql
collection.aggregate([
    {"$match": {"year": {"gt": 2000}}},
    {"$group": {"_id": "$year", "count": {"$sum": 1}}},
    {"$project":
    	{"_id": 0, "count": 1, 
    	"time": {"$dateFromParts": {"year": "$_id", "month": 2}} }},
    {"$sort": {"time": 1}}
])

// 如果要按 Metric对时间序列进行分组，请投影一个名为 的字段__metric。
// 以下示例使用 显示按电影评级随时间变化的电影计数__metric
sample_mflix.movies.aggregate([
{"$match": { "year": {"$gt" : 2000}}},
{"$group": { 
    "_id": {
    	"year":"$year", 
    	"rated":"$rated"}, 
    "count": { "$sum": 1 } 
    }
},
{"$project": { 
    "_id": 0, 
    "time": { "$dateFromParts": {"year": "$_id.year", "month": 2}},       "__metric": "$_id.rated",
    "count": 1}}
]).sort({"time": 1})
```



#### 模板和变量

MongoDB 支持复合变量，其中一个变量用作多个变量来执行复杂的多键过滤。

- 通过以下划线 ( `_`)开头每个单独的名称来命名复合变量，例如`_var1_var2`. 不要使用空格。
- 通过使别名使用由连字符 ( `-`)分隔的相同的单个名称来查询复合变量，例如`val1-val2`.
- 使用普通变量语法调用您的变量。例如，`$_var1`或`$_var2`。

```sql
sample_mflix.movies.aggregate([
    {"$match": {year: {"$gt": 1980}}},
    {"$project": {"_id": 0, "movie_title": "$title"}} 
])

sample_mflix.movies.aggregate([
    {"$match": {year: {"$gt": 1980}}},
    {"$project": {"_id": 0, "movie_year": {"$concat": ["$title", " - ", {"$toString":"$year"}]}}} 
])
// [{"movie-year": "Ted - 2016"}, {"movie-year": "The Terminator - 1985"}]
```





