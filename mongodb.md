## 注意事项

\- 一般，key不需要放在单引号内，value如何为字符串，必须放在单引号内（双引号也可以）

## 常用find操作

```shell
-- 多条件查询（and）, 多个key:value, 
db.collection.find( { key : vlaue, key1, value1})
-- 第二个大括号内，设置返回数据中字段是否显示；_id 默认为1，如有必要，需要显示关闭
db.collection.find( { key : vlaue, key1, value1}， {key: 1, key1: 1, _id: 0})

-- 查询运算符
-- $lt $lte $gt $gte $ne $in $nin $or
-- { key: {operator: value, ...}}

-- $in 限定单个字段的取值范围; 同一字段优先使用$in,而不是$or
db.collection.find( {key: { $in: ['A', 'D']}})
-- $in 正则表达式
db.collection.find( {key: { $in: [/^WX.*/, 'benny']}})

-- $or 任意一个条件满足，就会返回
-- $or 建议：将匹配内容多的条件放在靠前的位置(短路判断)；and：将最严苛的条件放在最前面
db.collection.find($or: [{key: 'value1'}, {key2: 'value2'}])
-- $or 支持嵌套其他运算符号，嵌套$in 使用
db.collection.find($or: [{key: {$in: ['value1', 'value3']}}, 
                         {key2: 'value2'}])

-- $not 元条件语句，即可用在任何其他条件之上：正则、取模运算
db.collection.find({'id_num': {$mod: [5, 1]}})	-- $mod 查询字段的值除以第一个给定值，当余数等于第二值时，返回该条数据
db.collection.find({'id_num': {$not: {$mod: [5, 1]}}})
db.collection.find({'id_num': {$not: {$regex: /[a-z]WX.*/i}}})

-- =============================================================
-- 特定类型的查询： null
-- null: 可匹配 键的值为null的数据； 不存在该键的数据
db.collection.find(key: null)	-- 匹配两类数据
db.collection.find(key: {$in: [null], $exists: true})	-- 只匹配键的值为null的数据
db.collection.find(key: {$exists: false})	-- 只匹配键不存在的数据

-- =============================================================
-- 正则表达式： Mongodb使用Perl兼容的正则表达式(PCRE)库来匹配正则表达式，PCRE支持的正则表达式都可以被MongoDB支持；建议使用JavaScript shell检查语法
-- MongoDB会为前缀型正则表达式(/^joe/)查询创建索引，SO这种查询非常高效
db.collection.find({name: /^joe/i})
db.collection.find({name: /^joey?/i})
db.collection.find({name: /baz/})	-- 可以匹配自身,即数据中 /baz/

-- =============================================================
-- 查询数组：{'fruit': ['apple', 'banana', 'peach']}
-- 数组内元素可以都看作键的值
db.collection.find({'fruit': 'banana'})
db.collection.find('fruit': ['apple', 'banana'])	-- 精确匹配，数组内元素必须一致(不能多，也不能少)，
-- $all 匹配数组内包含指定数据，不考虑顺序
db.collection.find('fruit': {$all: ['apple', 'banana']})
-- key.index 查询数组指定位置的元素，索引从0开始
db.collection.find('fruit.1': 'banana')
-- 查询指定长度的数组；不能和 $lt 等结合使用，不能实现查询长度范围的功能（实现策略：增加size字段，每次更新数据时，单独维护该字段）
db.collection.find('fruit': {$size: 3})	

-- $slice 作为find的第二个参数，指定返回数组的一个子集合
db.collection.findOne(criteria, {'comments': {$slice: 10})
db.collection.findOne(criteria, {'comments': {$slice: -10})
-- fset, size]: 跳过前offset的数据，从 offset+1 开始返回数据
db.collection.findOne(cirteria, {'comments': {$slice: [23, 10]} })


-- =============================================================
-- 查询内嵌文档：
{ 'name': {
 			'first': 'benny',
            'last': 'jane'
        }
}
-- 精准查询：name内嵌的键名称、数量、排序必须与查询语句匹配
db.collection.find({'name': {'first': 'benny', 'last': 'jane'}})
-- 匹配失败：顺序不对
db.collection.find({'name': {'last': 'jane', 'first': 'benny'}})
-- 点表示法： 插入的文档的键中不能包含点号
-- URL作为键时，插入或提取前，将点号全局替换为一个URL中的非法字符
db.collection.find({'name.first': 'benny', 'name.last': 'jane'})
-- 查找Joe发表的5分以上的评论
db.collection.find({'comments': {'author': 'Joe', 'score': {$gte: 5}}}) -- 错误： 精确匹配，限制comments下字段数量、顺序
db.collection.find({'comments.author': 'Joe', 'comments.score': {$gte: 5}})	-- 错误： 符合条件的author score可能不是数组中的同一个元素
-- $elementMatch 部分指定匹配数组中的单个内嵌文档的限定条件
db.collection.find({'comments': {$elementMatch: {
                   'author': 'joe',
                   'score': {$gte: 5}
                   }}})	-- 正确
                   



-- =============================================================
-- $where： 结合JavaScript代码实现筛选
db.collection.find({$where: function () {
                   for (var current in this) {
                   	   for (var other in this) {
                   	   		if (current != other && this[current] == this[other]) {
                   			return true;
                   		}
                   		}
                   }
                   	return false;
                   })
-- 等效
db.collection.find({$where: "this.x + this.y == 10"})
db.collection.find({$where: "function() {return this.x + this.y == 10}"})
-- 尽量避免使用$where: 查询速度慢；不能使用索引；
-- 使用时，尽量先使用其他查询条件过滤，再结合$where使用


-- =============================================================
-- limit(num) skip(num) sort({key: 1})
-- limit 限制返回结果数量，上限，可不足
-- skip 跳过num个数，总数量小于num，则返回空
	-- 避免使用skip跳过太多数据，性能低
-- sort({}) 指定键值对，键指定排序字段，值代表排序方向(1 升序 -1 降序); 可指定多个键，按照顺序生效

db.collection.find({'desc': 'book'}).limit(50).skip(50).sort({'price': 1})

-- 随机选取文档

-- =============================================================
-- 高级查询
$maxscan 查询最多扫描的文档数量
$min 查询的开始条件
$max 查询到结束条件
$hint 指定服务器使用哪个索引进行查询
$explain 获取查询指定的细节(用到的索引、结果数量、耗时等)，并非真正执行查询
$snapshot 确保查询的结果是在查询执行那一刻的一致快照



# 复合查询
# status = A 且 qty 小于 30 
db.collection.find({status: "A", qty: {$lt: 30}})
# $or 满足其中一个条件
db.collection.find({ $or: [{status: "A"}, {qty: {$lt: 30}}]})

# 包含and与or逻辑
db.collection.find({
                   status: "A",
                   $or: [{qty: {$lt:30}}, {item: /^p/}]
                   })

#### 嵌套结构数据查询： {{size: {h: 21, w:25, uom: "cm"}}}
# 匹配要求：字段顺序也要一致
db.collection.find({size: {h: 21 w:25, uom: "com"}})
db.collection.find({size: {w: 25 h:21, uom: "com"}})
# 使用点号
# 查询所有
db.collection.find({"size.uom": "com"}})
db.collection.find({"size.h": {$lt: 15}}})	# 使用查询运算符号
db.collection.find({"size.h": {$lt: 15}, "size.w": {$lt: 20}, status: "A"}})	# 多条件，and

#### 数组查询 { tags: ["red", "black", "blue"]}
# 查询等于(考虑数组内元素顺序、数量、值)指定数组的tags
db.collection.find({tags: ["red", "black", "blue"]}})
# 查询包含指定数组元素tags（不考虑元素顺序、数量）
db.collection.find({tags: {$all: ["red", "black"]}}})
# 查询数组内包含指定值的tags（tags对应的数组中包含red）
db.collection.find({tags: "red"})
# 查询数组内至少一个元素大于25的tags
db.collection.find({tags: {$gt: 25}})
# tags数组内元素任意满足一个条件或两个都满足
db.collection.find({tags: {$gt: 25， $lt: 20}})
# $elemMatch: 查询存在单个元素满足所有条件的数组
db.collection.find({tags: {$elemMatch: {$gt: 25， $lt: 20}}})
# 点号，筛选数组指定索引元素
db.collection.find({"tags.1": {$gt: 25}})
# $size 查询指定长度的数组
db.collection.find({"tags": {$size: 25}})

#### 指定查询返回的字段
# 默认返回每条文档的所有字段
# 返回_id,item, status; 当显示指定返回字段时，_id字段默认也会返回
db.collection.find({status: "A"}, {item: 1, status: 1})
# 显示指定_id 不返回
db.collection.find({status: "A"}, {item: 1, status: 1, _id: 0})
# 指定不返回字段，其余字段返回
db.collection.find({status: "A"}, {item: 0, status: 0})
db.collection.find({status: "A"}, 
                   {item: 0, status: 0, "size.uom": 1})
# $slice:-1 返回最后一个元素； -2 返回倒数两个，2 返回正序前两个
db.collection.find({status: "A"}, 
                   {item: 0, status: 0, instock: {$slice: -1}})
```

## 聚合操作

```sql
-- =============================================================
-- 聚合

-- $count
db.collection.count()	-- 返回集合的文档数量，执行速度块
db.collection.count({key: 'value'})	-- 传入查询条件，速度变慢
db.collection.find({key: 'value'}).count()	-- 先查询，然后统计数量

-- distinct
-- 必须指定集合名称、键
db.runCommand({'distinct' : 'collectionName', 'key': 'value'})
db.collection.distinct('key');

-- gr

# 查询： $match
# 分组聚合计算： $group , $sum 求amount的总和
db.orders.aggregate([
    {$match: {status: "A"}},
    {$group: {_id:"$cust_id", total: {$sum: "$amount"}}}
])

# map-reduce
db.orders.mapReduce(
    function() { emit( this.cust_id, this.amount); },
    function(key, values) { return Array.sum(values)},
    {
     query: {status: "A"}.
     out: "order_totals"
    })

# distinct
db.orders.distinct("cust_id")

# aggregate() 可搭配 $sum $avg $min $max $push $addToSet $first $last
# 分组计算总数
db.collection.aggregate([
	{$group: {_id: "$by_user", num_total: {$sum: 1}}}
])

# 输出显示 _id title author三个字段(_id 默认显示)
db.collection.aggregate({ $project: {title: 1, author: 1}})
db.collection.aggregate({ $project: {title: 1, author: 1, _id: 0}})

db.collection.aggregate([{$match: {score: {$gt: 70, $lte: 90}}},
                        {$group: {_id: null, count: {$sum: 1}}}])
db.collection.aggregate({$skip: 5})
```

$project：修改输入文档的结构。可以用来重命名、增加或删除域，也可以用于创建计算结果以及嵌套文档。

$match：用于过滤数据，只输出符合条件的文档。$match使用MongoDB的标准查询操作。

$limit：用来限制MongoDB聚合管道返回的文档数。

$skip：在聚合管道中跳过指定数量的文档，并返回余下的文档。

$unwind：将文档中的某一个数组类型字段拆分成多条，每条包含数组中的一个值。

将**数组类型**的字段进行拆分； 特殊情况下的unwind(空数组，null,非数组，无指定字段)，`[]`,`null`,以及无指定字段的数据都丢失了，需要使用特殊写法，见下文： 

$group：将集合中的文档分组，可用于统计结果。

$sort：将输入文档排序后输出。

$geoNear：输出接近某一地理位置的有序文档。

```sql
# 参考文章链接
# 不分组，统计集合中数据总量
db.orders.aggregate([{$group: {_id: null, count: {$sum: 1}}}])
# 不分组，统计集合中price的总量
db.orders.aggregate([{$group: {_id: null, total: {$sum: "$price"}}}])
# 根据cust_id分组，统计各个分组中price的总和
db.orders.aggregate([$group: {_id: "$cust_id", total: {$sum: "$price"}}])

# 根据每一对唯一的cust_id 和ord_date分组，计算price总和，不包括日期的时间部分
db.orders.aggregate([
	{
    	$group: {
    		_id: {
    			cust_id: "$cust_id",
    			ord_date: {
    				month: {$month: "$ord_date"},
    				day： {$dayOfMonth: "$ord_date"},
    				year: {$year: "$ord_date"}
    		}
    	},
    	total: {$sum: "$price"}
    }
    }
])

# 先分组统计数量，在筛选count大于1的数据
db.orders.aggregate([
	{	
    $group: {
    	_id: "$cust_id",
    	count: {$sum: 1}    
    },
    {$match: {count: {$gt: 1}}}
    }
]) 

# 对每个唯一的cust_id和ord_date分组，计算价格总和，并只返回price总和大于250的记录，且排除日期的时间部分
db.orders.aggregate( [
   {
     $group: {
        _id: {
           cust_id: "$cust_id",
           ord_date: {
               month: { $month: "$ord_date" },
               day: { $dayOfMonth: "$ord_date" },
               year: { $year: "$ord_date"}
           }
        },
        total: { $sum: "$price" }
     }
   },
   { $match: { total: { $gt: 250 } } }
] )

# 对每个唯一的cust_id且status=A，计算price总和
db.orders.aggregate( [
   { $match: { status: 'A' } },
   {
     $group: {
        _id: "$cust_id",
        total: { $sum: "$price" }
     }
   }
] )

# 对每个唯一的cust_id和ord_date分组，计算价格总和，并只返回price总和大于250的记录，且排除日期的时间部分
db.orders.aggregate( [
   {
     $group: {
        _id: {
           cust_id: "$cust_id",
           ord_date: {
               month: { $month: "$ord_date" },
               day: { $dayOfMonth: "$ord_date" },
               year: { $year: "$ord_date"}
           }
        },
        total: { $sum: "$price" }
     }
   },
   { $match: { total: { $gt: 250 } } }
] )

# 对每个唯一的cust_id且status=A，计算price总和
db.orders.aggregate( [
   { $match: { status: 'A' } },
   {
     $group: {
        _id: "$cust_id",
        total: { $sum: "$price" }
     }
   }
] )

# 对每个唯一的cust_id且status=A，计算price总和并且只返回price总和大于250的记录
db.orders.aggregate( [
   { $match: { status: 'A' } },
   {
     $group: {
        _id: "$cust_id",
        total: { $sum: "$price" }
     }
   },
   { $match: { total: { $gt: 250 } } }
] )


# 对于每个唯一的cust_id，将与orders相关联的相应订单项order_lineitem的qty字段进行总计
db.orders.aggregate( [
   { $unwind: "$items" },
   {
     $group: {
        _id: "$cust_id",
        qty: { $sum: "$items.qty" }
     }
   }
] )

# 类似mysql:
SELECT cust_id,
       SUM(li.qty) as qty
FROM orders o,
     order_lineitem li
WHERE li.order_id = o.id
GROUP BY cust_id

# 统计不同cust_id和ord_date分组的数量，排除日期的时间部分
db.orders.aggregate( [
   {
     $group: {
        _id: {
           cust_id: "$cust_id",
           ord_date: {
               month: { $month: "$ord_date" },
               day: { $dayOfMonth: "$ord_date" },
               year: { $year: "$ord_date"}
           }
        }
     }
   },
   {
     $group: {
        _id: null,
        count: { $sum: 1 }
     }
   }
] )

类似mysql:
SELECT COUNT(*)
FROM (SELECT cust_id, ord_date
      FROM orders
      GROUP BY cust_id, ord_date)
      as DerivedTable
```

## 正则查询

```sql
-- 使用功能斜杠，不需要使用单引号
db.collections.find({name: {$regex: /pattern/, $options: '<option>'}})
-- 使用单引号，不需要使用斜杠
db.collections.find({name: {$regex: 'pattern', $options: '<option>'}})
-- 斜杠后添加匹配模式，末尾不需要添加斜杠
db.collections.find({name: {$regex: /pattern/<option>}})
```

options模式

| 模式 | 含义                                                         | 标注位置                    |
| ---- | ------------------------------------------------------------ | --------------------------- |
| i    | 忽略大小写(大小写不敏感，开启后不能有效使用索引)             |                             |
| m    | 使用锚点，可以匹配^ $ \n                                     | 可使用//                    |
| x    | 忽略所有非转义空字符串、换行符号、#标识的注释内容            | 必须使用$options 来显示标明 |
| s    | 设置s选项后，点号可以匹配所有字符，包括换行符(\n)； 默认为单行匹配模式 | 必须使用$options 来显示标明 |

正则规则：参考JavaScript正则 

```sql
-- 匹配单个英文字符
db.collections.find({'startedBy': {$nin: [/[a-z]WX.*/]}})
db.collections.find({'startedBy': {$nin: [/[a-z]WX.*/]}})

-- 查询不以WX开头的数据量
db.collections.find({'id': {$nin: [/WX.*/]}}).count()

-- 查询以WX开头的数据量
db.collections.find({'id': {$in: [/^WX.*/]}}).count()

-- 查询以小写字符开头且包含WX的数据量
db.collections.find({'id': {$in: [/[a-z]WX.*/]}}).count()
db.collections.find({'id': {$in: [/[a-z]WX.*/, 'benny']}}).count()

-- 各个模式组合使用
db.collections.find({'id': {$regex: /^benny.*/, $options: 'si'} })

-- 常用语句 

-- 匹配单个英文字符
db.collections.find({'startedBy': {$nin: [/[a-z]WX.*/]}})
db.collections.find({'startedBy': {$nin: [/[a-z]WX.*/]}})

-- 查询不以WX开头的数据量
db.collections.find({'id': {$nin: [/WX.*/]}}).count()

-- 查询以WX开头的数据量
db.collections.find({'id': {$in: [/^WX.*/]}}).count()

-- 查询以小写字符开头且包含WX的数据量
db.collections.find({'id': {$in: [/[a-z]WX.*/]}}).count()
db.collections.find({'id': {$in: [/[a-z]WX.*/, 'benny']}}).count()

-- 各个模式组合使用
db.collections.find({'id': {$regex: /^benny.*/, $options: 'si'} })

```

## 表达式操作符

 

| 常用表达式      | 含义                                                         |
| --------------- | ------------------------------------------------------------ |
| $sum            | 计算总和，{$sum: 1}表示返回总和×1的值(即总和的数量),使用{$sum: '$制定字段'}也能直接获取制定字段的值的总和 |
| $avg            | 平均值                                                       |
| $min            | min                                                          |
| $max            | max                                                          |
| $push           | 将结果文档中插入值到一个数组中                               |
| $first          | 根据文档的排序获取第一个文档数据                             |
| $last           | 同理，获取最后一个数据                                       |
| $dateToString   | 将日期格式转为字符串                                         |
| $dateFromString | 根据字符串生成日期格式数据                                   |
| $substr         | 只能截取匹配ASCII码的数据                                    |
| $substrCP       | 可以截取中文和字符串                                         |



```sql
-- 分组后，每个分组内新增数组名称gameName, 包含当前分组的所有name字段的值 
db.collection.aggregate([
       {
           $group: {
               _id: '$time',
               gameName: {$push: '$name'}
           }
       }
   ]) 

-- 拿到所有time>=20的document的name字段
db.collection.aggregate([
    {
        $match: {
            time: {$gte: 20}
        }
    },
    {
        $project: {
            _id: 0, // _id不显示
            name: 1 // name是要显示的
        }
    },
    {
        $group: {
            _id: null,
            name: {$push: '$name'}
        }
    }
])

-- 注意先写skip在写limit
db.collection.aggregate([
    {
        $skip: 1
    },
    {
        $limit: 2
    }
])

db.collection.aggregate([
    {
        $unwind: '$list' // 指定list字段
    }
])

-- 避免 [] null 无字段的数据丢失
db.unwind.aggregate([
    {
        $unwind: {
            path: '$list', // path是指定字段
            preserveNullAndEmptyArrays: true //该属性为true即保留
        }
    }
])
```

## -------------------------------------------------------------------------------------------------------------------
## 分割字符串：$split

在`$project`语句中使用，

格式`{"$project": {"name": {"$split": ["$targetName", "分割符"]}}}`

往往需要搭配`$arrayElemAt`方法提取数组指定索引的字段。

```sql
db.proto_type_entrance_test_info.aggregate ( [ 
{"$project": {"nodeNames": {"$split": ["$nodeName", "__URL__"]}
        }},
{"$project": {
    "name": "$tag", 
    "url": {"$arrayElemAt": ["$nodeNames", 1]}, 
    "url":{ "$toLower": "$url"},
    "status":1,
    "_id": 0}}
])
```

## 字符串转数值： $convert

```sql
db.proto_type_entrance_test_info.aggregate ( [ 
{ "$match" : {"latestBatch" : true}},
{"$limit": 1},
{"$project": {"count": {"$convert": {"input": "$buildNumber", "to": "int"}}, "_id": 0}}
])
```

## 查询数组大小: $size, $where, $key.index

```sql
-- 查询指定长度的数组
-- 无法查询某个范围内的数组; 不能搭配$gt使用
db.collection.find({"key": {"$size": 2}})

-- $where: 速度较慢； key： 数组名称
db.collection.find({"$where": "this.key.length < 3")
-- $exists: 筛选索引存在的数据； 可以用于筛选数组长度范围
db.collection.find({"key.3": {"$exists" : 1})
db.collection.find({"key.2": {"$exists" : 0})
```

##查询每个个分组中的Top N数据: $slice

语法:

```sql
-- 该函数接收数组参数：目标数组，截取前N个参数
{"$slice": ["$sortedArray", limitNum]}
```

查询Top N的逻辑

```sql
-- 先排序，再分组并借助 $push $$ROOT 保留每个分组的原始数据, 
-- 然后再使用$slice截取数组一定长度数据
-- 最后通过$unwind，再次展开数组
{"$sort"：{"field": 1},
{"$group"：{"_id": "$targetField", "sortedArray": {"$push": "$$ROOT"}}},
{"res": {"$slice": ["$array", limitNum]}}
```

实际应用：

```sql
db.apk_build_info.aggregate ([
    {
		"$match": {
			"buildStartTimeText": {
				"$regex": "2021-12-07"
			}
		}
	},
	{
		"$project": {
			"apkName": 1,
			"taskName": 1,
			"buildLogUrl": 1,
			"pipelineUrl": 1,
			"nickname": 1,
			"buildStatus": 1,
			"buildType": 1,
			"spend": {
				"$divide": [{
					"$subtract": ["$buildEndTime", "$buildStartTime"]
				}, 1000]
			}
		}
	},
	{
		"$sort": {
			"spend": -1
		}
	},
        {
                "$group": {"_id": "$taskName", "array":{"$push": "$$ROOT"}}
        },
	{
		"$project": {
			"apkName": 1,
			"name": "$_id",
			"value": "$spend",
			"buildLogUrl": 1,
			"pipelineUrl": 1,
                        "rankArr":{"$slice": ["$array", 10]},
			"_id": 0
		}
	},
       	{
 		"$unwind": "$rankArr"
 	},
	{
		"$project": {
			"apkName": "$rankArr.apkName",
			"name": 1,
			"spend": "$rankArr.spend",
			"buildLogUrl": "$rankArr.buildLogUrl",
			"pipelineUrl": "$rankArr.pipelineUrl",
			"buildType": "$rankArr.buildType"
		}
	},
        {
            "$sort": {"name": 1, "spend": -1}
            }
])
```

## 解决字符串转时间格式后时差问题：$dateFromString

实际问题：时间格式输入插入mongodb后默认减去8小时，使用`$dateFromString`字符串转时间格式后，会默认加上8小时

语法：

```sql

    $dateFromString: {
         dateString: <dateStringExpression>,    // 要转换的时间字符串
         format: <formatStringExpression>,    // 转换的格式，‘%Y-%m-%dT%H:%M:%S.%LZ’
         timezone: <tzExpression>,    // 指定的时区
         onError: <onErrorExpression>,    // 报错时输出
         onNull: <onNullExpression>    // null时输出
     }

$dateFromString: {
     dateString: "15-06-2018",
     format: "%d-%m-%Y"
}
```

实际应用

时区格式可以在grafana的时间筛选框底部查找。

```sql
db.apk_build_info.aggregate ( [
{"$match": {    
        "buildStartTimeText":{"$gte": "$endDay"}
    }
},
{
    "$project": {
        "_id": 0, 
       "time": { "$substr": ["$buildStartTimeText", 0, 16] }
    }
},
{"$group": {"_id": "$time",


            "count": {"$sum": 1}
    }},
{"$project": {"value": "$count","ts": { "$dateFromString": {"dateString": "$_id", "timezone":"Asia/Shanghai"}},"_id" : 0}},
{"$sort":{"ts": 1}}
])
```



## distinct

```shell
db.colection.distinct("key", {"key":{"$lte": "2021"}}).length
```

## -------------------------------------------------------------------------------------------------------------------
## Grafana内置参数，以及查询Prometheus参数
参考文章：https://blog.csdn.net/java_4_ever/article/details/108582949
```sql
Grafana内置参数
$__dashboard
当前dashboard的名称


$__from $__to
时间范围的毫秒值
可自定义格式,比如:{$__from: date :YYYY-MM-DD HH:mm:ss} {$__from: date :seconds}
!!! 实际使用中, 前面应该是配置一个下划线

$__interval
查询的时间间隔,包含单位,比如:30s,2m

$__interval_ms
查询的时间间隔,毫秒值

$__range
查询的时间区间大小,包含单位,比如:2d

$__range_s $__range_ms
查询的时间区间大小,分别是秒数和毫秒数

$__timeFilter
返回当前选择的时间范围表达式,比如:time > now() -7d,常用于数据库作为datasource的时候.

```

## Grafana 的 Mongo 数据源

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

// 如果要按 Metric对时间序列进行分组,请投影一个名为 的字段__metric。
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


## Mongodb 常用语法
http://wenote.huawei.com/wapp/pages/view/share/s/0BjYEw1T1h7H2EobsV290UjQ2IDxM026bN7H2zTjsF1Z0M73
http://wenote.huawei.com/wapp/pages/view/share/s/0BjYEw1T1h7H2EobsV290UjQ1jikdg26Yx7H2zTjsF1Z0M73





日期格式数据处理
日期格式化： $dateToString
# 日期格式化
$dateToString: {
    date: <dateExpression>,
    format: <formatString>,
    timezone: <tzExpression>,
    onNull: <expression>
} 
db.build_history_info.aggregate([
    {
        $project: {
            _id: 1, time: { $dateToString: { format: "%Y-%m-%d", date: "$startTime" } },
        }
    },
    { $group: { _id: "$time", count: { $sum: 1 }} },
    { $sort: { "_id": -1 } }
])
$dateFromString
在aggregate查询中使用
$dateFromString: {
     dateString: <dateStringExpression>,    // 要转换的时间字符串
     format: <formatStringExpression>,    // 转换的格式，‘%Y-%m-%dT%H:%M:%S.%LZ’
     timezone: <tzExpression>,    // 指定的时区
     onError: <onErrorExpression>,    // 报错时输出
     onNull: <onNullExpression>    // null时输出
 }
 
 $dateFromString: {
     dateString: "15-06-2018",
     format: "%d-%m-%Y"
}


字符串操作
$substr: 只能截取匹配ascII码的数据
$substrCP:   可以截取中文和字符串


加减乘除操作
db.dev.aggregate([
    {$match: {size: {$ne:null} } },
    {$project: {_id:0,title:1,New_Size: {$add:["$size",1] } }
    }])
db.dev.aggregate([
    {$match: {size:{$ne:null}}},
    {$project: {_id:0,title:1, New_Size: {$subtract:["$size",1]}}
    }])
db.dev.aggregate([
    {$match: {size:{$ne:null}}},
    {$project: {_id:0,title:1, New_Size: {$multiply:["$size",2]}}
                  }]) 
db.dev.aggregate([
    {$match:{size:{$ne:null}}},
    {$project:{_id:0,title:1, New_Size: {$divide:["$size",2]}}
    }])
    
 db.dev.aggregate([
     {$match: {size:{$ne:null}}},
     {$project: {_id:0,title:1, New_Size: {$mod:["$size",2]}}
     }])
连表查询
https://www.cnblogs.com/xuliuzai/p/10055535.html
db.getCollection('build_plugin_info').aggregate([
                  {   // HotaPackage ColdHotPatch
                      $match: {'pipelineNameEn': 'HotaPackage',
                          'dataSource': 'cde',
                          'comboFlag': {$ne: true},
                          'isOldBuild': {$ne: true},
                          'hotaVersionType': {$in: ['PRELOAD', 'BASE', 'CUST']},
                          }
                  },
                       {
                         $lookup: {
                             from: 'group_user_info',
                             localField: 'groupId',
                             foreignField: '_id',
                             as: 'group_info'
                             }
                         },
                     {
                         $unwind: '$group_info'
                         },
                  {
                      $project: {
                          pipelineNameEn: 1, dataSource: 1, emuiProductName: 1, jobId: 1, groupId: 1,
                          'group_info.name':1,
                          time: { $substr: ["$startTime", 0, 7] }
                      }
                  },
                  {
                      $group: {
                          _id: '$group_info.name',
                          count: {$sum: 1}
                          }
                      },
                  {$sort: {'_id': -1}}
                  ]) 
                  


聚合查询 
https://www.cnblogs.com/nixi8/p/4856746.html
db.collection.aggregate(pipeline, options);
pipeline Array
# 与mysql中的字段对比说明
$project # 返回哪些字段,select,说它像select其实是不太准确的,因为aggregate是一个阶段性管道操作符,$project是取出哪些数据进入下一个阶段管道操作,真正的最终数据返回还是在group等操作中;
$match # 放在group前相当于where使用,放在group后面相当于having使用
$sort # 排序1升-1降 sort一般放在group后,也就是说得到结果后再排序,如果先排序再分组没什么意义;
$limit # 相当于limit m,不能设置偏移量
$skip # 跳过第几个文档
$unwind # 把文档中的数组元素打开,并形成多个文档,参考Example1
$group: { _id: <expression>, <field1>: { <accumulator1> : <expression1> }, ...  # 按什么字段分组,注意所有字段名前面都要加$,否则mongodb就为以为不加$的是普通常量,其中accumulator又包括以下几个操作符
# $sum,$avg,$first,$last,$max,$min,$push,$addToSet
#如果group by null就是 count(*)的效果
$geoNear # 取某一点的最近或最远,在LBS地理位置中有用
$out # 把结果写进新的集合中。注意1,不能写进一个分片集合中。注意2,不能写进
unwind
> db.test.insert({ "_id" : 1, "item" : "ABC1", sizes: [ "S", "M", "L"] });
WriteResult({ "nInserted" : 1 })
> db.test.aggregate( [ { $unwind : "$sizes" } ] )
{ "_id" : 1, "item" : "ABC1", "sizes" : "S" }
{ "_id" : 1, "item" : "ABC1", "sizes" : "M" }
{ "_id" : 1, "item" : "ABC1", "sizes" : "L" }
db.test.insert({ "_id" : 2, "item" : "ABC1", sizes: [ "S", "M", "L",["XXL",'XL']] });
WriteResult({ "nInserted" : 1 })
> db.test.aggregate( [ { $unwind : "$sizes" } ] )
{ "_id" : 1, "item" : "ABC1", "sizes" : "S" }
{ "_id" : 1, "item" : "ABC1", "sizes" : "M" }
{ "_id" : 1, "item" : "ABC1", "sizes" : "L" }
{ "_id" : 2, "item" : "ABC1", "sizes" : "S" }
{ "_id" : 2, "item" : "ABC1", "sizes" : "M" }
{ "_id" : 2, "item" : "ABC1", "sizes" : "L" }
{ "_id" : 2, "item" : "ABC1", "sizes" : [ "XXL", "XL" ] } # 只能打散一维数组
example1
#数据源
{ "_id" : 1, "item" : "abc", "price" : 10, "quantity" : 2, "date" : ISODate("2014-03-01T08:00:00Z") }
{ "_id" : 2, "item" : "jkl", "price" : 20, "quantity" : 1, "date" : ISODate("2014-03-01T09:00:00Z") }
{ "_id" : 3, "item" : "xyz", "price" : 5, "quantity" : 10, "date" : ISODate("2014-03-15T09:00:00Z") }
{ "_id" : 4, "item" : "xyz", "price" : 5, "quantity" : 20, "date" : ISODate("2014-04-04T11:21:39.736Z") }
{ "_id" : 5, "item" : "abc", "price" : 10, "quantity" : 10, "date" : ISODate("2014-04-04T21:23:13.331Z") }
# 综合示例
db.sales.aggregate([
  # 由上到下,分阶段的进行,注意该数组中的顺序是有意义的
  {
    $project:{item:1,price:1,quantity:1} # 1.取出什么元素待操作;
  },
  {
    $group:{ # 2. 对已取出的元素进行聚合运算;
      _id:"$item", # 根据什么来分组
      quantityCount:{$sum:'$quantity'},
      priceTotal:{$sum:'$price'}
    }
  },
  {
    $sort:{
      quantityCount:1 #3.升序
    }
  },
  # 4.基于上面的结果,取倒数第二名
  {
    $skip: 2
  },
  {
    $limit:1
  },
  # 5.然后把结果写到result集合中
  {
    $out:'result'
  }
])
#表达式$month,$dayOfMonth,$year,$sum,$avg
db.sales.aggregate(
   [
      {
        $group : {
           _id : { month: { $month: "$date" }, day: { $dayOfMonth: "$date" }, year: { $year: "$date" } }, #按月日年分组
           totalPrice: { $sum: { $multiply: [ "$price", "$quantity" ] } },
           averageQuantity: { $avg: "$quantity" },
           count: { $sum: 1 }
        }
      }
   ]
)
#结果
{ "_id" : { "month" : 3, "day" : 15, "year" : 2014 }, "totalPrice" : 50, "averageQuantity" : 10, "count" : 1 }
{ "_id" : { "month" : 4, "day" : 4, "year" : 2014 }, "totalPrice" : 200, "averageQuantity" : 15, "count" : 2 }
{ "_id" : { "month" : 3, "day" : 1, "year" : 2014 }, "totalPrice" : 40, "averageQuantity" : 1.5, "count" : 2 }
#
#
# 表达式$push
db.sales.aggregate(
   [
     {
       $group:
         {
           _id: { day: { $dayOfYear: "$date"}, year: { $year: "$date" } },
           itemsSold: { $push:  { item: "$item", quantity: "$quantity" } }
         }
     }
   ]
)
# result
{
   "_id" : { "day" : 46, "year" : 2014 },
   "itemsSold" : [
      { "item" : "abc", "quantity" : 10 },
      { "item" : "xyz", "quantity" : 10 },
      { "item" : "xyz", "quantity" : 5 },
      { "item" : "xyz", "quantity" : 10 }
   ]
}
{
   "_id" : { "day" : 34, "year" : 2014 },
   "itemsSold" : [
      { "item" : "jkl", "quantity" : 1 },
      { "item" : "xyz", "quantity" : 5 }
   ]
}
{
   "_id" : { "day" : 1, "year" : 2014 },
   "itemsSold" : [ { "item" : "abc", "quantity" : 2 } ]
}
#
#
# 表达式$addToSet
db.sales.aggregate(
   [
     {
       $group:
         {
           _id: { day: { $dayOfYear: "$date"}, year: { $year: "$date" } },
           itemsSold: { $addToSet: "$item" }
         }
     }
   ]
)
#result
{ "_id" : { "day" : 46, "year" : 2014 }, "itemsSold" : [ "xyz", "abc" ] }
{ "_id" : { "day" : 34, "year" : 2014 }, "itemsSold" : [ "xyz", "jkl" ] }
{ "_id" : { "day" : 1, "year" : 2014 }, "itemsSold" : [ "abc" ] }
#
#
# 表达式 $first
db.sales.aggregate(
   [
     { $sort: { item: 1, date: 1 } },
     {
       $group:
         {
           _id: "$item",
           firstSalesDate: { $first: "$date" }
         }
     }
   ]
)
# result
{ "_id" : "xyz", "firstSalesDate" : ISODate("2014-02-03T09:05:00Z") }
{ "_id" : "jkl", "firstSalesDate" : ISODate("2014-02-03T09:00:00Z") }
{ "_id" : "abc", "firstSalesDate" : ISODate("2014-01-01T08:00:00Z") }
example2
db.sales.aggregate(
   [
      {
        $group : {
           _id : null, # 如果为null,就统计出全部
           totalPrice: { $sum: { $multiply: [ "$price", "$quantity" ] } },
           averageQuantity: { $avg: "$quantity" },
           count: { $sum: 1 }
        }
      }
   ]
)
# 数据源
{ "_id" : 8751, "title" : "The Banquet", "author" : "Dante", "copies" : 2 }
{ "_id" : 8752, "title" : "Divine Comedy", "author" : "Dante", "copies" : 1 }
{ "_id" : 8645, "title" : "Eclogues", "author" : "Dante", "copies" : 2 }
{ "_id" : 7000, "title" : "The Odyssey", "author" : "Homer", "copies" : 10 }
{ "_id" : 7020, "title" : "Iliad", "author" : "Homer", "copies" : 10 }
# 根据作者分组,获得其著多少书籍
db.books.aggregate(
   [
     { $group : { _id : "$author", books: { $push: "$title" } } }
   ]
)
# result
{ "_id" : "Homer", "books" : [ "The Odyssey", "Iliad" ] }
{ "_id" : "Dante", "books" : [ "The Banquet", "Divine Comedy", "Eclogues" ] }
# 通过系统变量$$ROOT(当前的根文档)来分组
db.books.aggregate(
   [
     { $group : { _id : "$author", books: { $push: "$$ROOT" } } }
   ]
)
# result
{
  "_id" : "Homer",
  "books" :
     [
       { "_id" : 7000, "title" : "The Odyssey", "author" : "Homer", "copies" : 10 },
       { "_id" : 7020, "title" : "Iliad", "author" : "Homer", "copies" : 10 }
     ]
}
{
  "_id" : "Dante",
  "books" :
     [
       { "_id" : 8751, "title" : "The Banquet", "author" : "Dante", "copies" : 2 },
       { "_id" : 8752, "title" : "Divine Comedy", "author" : "Dante", "copies" : 1 },
       { "_id" : 8645, "title" : "Eclogues", "author" : "Dante", "copies" : 2 }
     ]
}


统计数组中元素的个数
> db.images.find()
{ "_id" : 3, "height" : 480, "width" : 640, "tags" : [ "kittens", "travel" ] }
{ "_id" : 1, "height" : 480, "width" : 640, "tags" : [ "cats", "sunrises", "kittens", "travel", "vacation", "work" ] }
{ "_id" : 0, "height" : 480, "width" : 640, "tags" : [ "dogs", "work" ] }
{ "_id" : 6, "height" : 480, "width" : 640, "tags" : [ "work" ] }
{ "_id" : 4, "height" : 480, "width" : 640, "tags" : [ "dogs", "sunrises", "kittens", "travel" ] }
{ "_id" : 5, "height" : 480, "width" : 640, "tags" : [ "dogs", "cats", "sunrises", "kittens", "work" ] }
{ "_id" : 7, "height" : 480, "width" : 640, "tags" : [ "dogs", "sunrises" ] }
{ "_id" : 8, "height" : 480, "width" : 640, "tags" : [ "dogs", "cats", "sunrises", "kittens", "travel" ] }
 db.images.aggregate( [ 
     {$unwind:"$tags"},
     {$group:
            {_id:"$tags",
     num_of_tag:{$sum:1}}},
     {$project:
            {_id:0,tags:"$_id",num_of_tag:1}},{$sort:{num_of_tag:-1}} ])
            
            
  db.oneDoc.aggregate([
    //拆分数组
    {$unwind:"$tag"},
    //过滤条件
    {$match:{creater:'5a4ef79a1c9c2316463a57f4'}},
    //分组统计
    {$group:{_id:"$tag",num:{$sum:1}}},
    //显示字段过滤
    {$project:{_id:0,tag:"$_id",num:1}},
    //排序
    {$sort:{num:-1}}
])
查询时间范围
db.project_entrance_test_info.aggregate ( [ 
    {
        "$project": {
            "_id": 0, 
           "time": { "$substr": ["$updateTime", 0, 10] }
        }
    },
{"$group": {"_id": "$time"}},
{"$project": {"_id": {"$concat": ["$_id", " 23:59:59"]}}},
{"$sort" : {"_id" : -1}}
])







