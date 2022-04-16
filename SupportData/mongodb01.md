## 优化 
https://www.cnblogs.com/duanxz/p/4388546.html

800~900ms -》 100ms
## 
{"op": "command", "ns": "kanban-data.package_define_record", "command": "{N}", "keysExamined": 58882, "docsExamined": 18, "cursorExhausted": true, "numYield": 460, "nreturned": 1, "locks": {"Global": {"acquireCount": {"r": 462}}, "Database": {"acquireCount": {"r": 462}}, "Collection": {"acquireCount": {"r": 462}}}, "responseLength": 543, "protocol": "op_msg", "millis": 109, "planSummary": "IXSCAN { pipelineTime: -1, parentPipelineId: 1, pipelineName: 1 }", "ts": {"$date": 1648310385839}, "client": "192.168.6.147", "allUsers": [{"user": "__system", "db": "local"}], "user": "__system@local"}
