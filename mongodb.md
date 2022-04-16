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


