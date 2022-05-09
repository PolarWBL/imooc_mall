# 本文档用于慕课网就业班 Java 项目- Spring Boot 慕慕生鲜电商项目

# 用户模块

## 注册新用户

|请求地址|/register|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|userName|用户名|xiaomu|    |
|password|密码|12345678|    |

请求示例

```
/register?userName=xiaomu&password=12345678
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": null
}
```
## 登录

|请求地址|/login|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|userName|用户名|xiaomu|    |
|password|密码|12345678|    |

请求示例

```
/login?userName=xiaomu&password=12345678
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": {
        "id": 9,
        "username": "xiaomu2",
        "password": null,
        "personalizedSignature": "祝你今天好心情",
        "role": 2,
        "createTime": "2020-02-09T12:39:47.000+0000",
        "updateTime": "2020-02-10T16:56:02.000+0000"
    }
}
```

## 更新个性签名 

|请求地址|/user/update|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|signature|更新的签名内容|更新了我的签名|    |

请求示例

```
/user/update?signature=更新了我的签名
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": null
}
```
## 退出登录

|请求地址|/user/logout|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|无|    |    |    |

请求示例

```
/user/logout
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": null
}
```
## 管理员登录

|请求地址|/adminLogin|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|userName|用户名|xiaomu|    |
|password|密码|12345678|    |

请求示例

```
/adminLogin?userName=xiaomu&password=12345678
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": {
        "id": 9,
        "username": "xiaomu2",
        "password": null,
        "personalizedSignature": "祝你今天好心情",
        "role": 2,
        "createTime": "2020-02-09T12:39:47.000+0000",
        "updateTime": "2020-02-10T16:56:02.000+0000"
    }
}
```


# 商品分类模块

## 后台管理：

### 增加目录分类

|请求地址|/admin/category/add|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|name|目录名|新鲜水果|    |
|type|目录层级|1|不超过3级|
|parentId|父目录的ID|2|1级目录的parentId为0|
|orderNum|排序|5|同级目录的排序|

请求示例

```
/admin/category/add
```
body：
```
{"name":"食品","type":1,"parentId":0,"orderNum":1}
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": null
}
```
### 更新目录分类

|请求地址|/admin/category/update|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|id|目录的id|4|    |
|name|目录名|新鲜水果|    |
|type|目录层级|1|不超过3级|
|parentId|父目录的ID|2|1级目录的parentId为0|
|orderNum|排序|5|同级目录的排序|

请求示例

```
/admin/category/update
```
body：
```
{"id":"1","name":"食品品品品","type":1,"parentId":0,"orderNum":1}
```
返回示例
```

{
    "status": 10000,
    "msg": "SUCCESS",
    "data": null
}
```
### 删除分类

|请求地址|/admin/category/delete|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|id|目录的id|4|    |

请求示例

```
/admin/category/delete?id=1
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": null
}
```
### 分类列表（平铺） 

|请求地址|/admin/category/list?pageNum=1&pageSize=10|
|:----|:----|
|请求方式|GET|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|pageNum|页数|1|    |
|pageSize|每页条数|10|    |

请求示例

```
/admin/category/list?pageNum=1&pageSize=10
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": {
        "total": 19,
        "list": [
            {
                "id": 3,
                "name": "新鲜水果",
                "type": 1,
                "parentId": 0,
                "orderNum": 1,
                "createTime": "2019-12-17T17:17:00.000+0000",
                "updateTime": "2019-12-28T09:11:26.000+0000"
            },
            {
                "id": 5,
                "name": "海鲜水产",
                "type": 1,
                "parentId": 0,
                "orderNum": 2,
                "createTime": "2019-12-17T17:17:00.000+0000",
                "updateTime": "2019-12-28T08:25:20.000+0000"
            },
            {
                "id": 6,
                "name": "精选肉类",
                "type": 1,
                "parentId": 0,
                "orderNum": 3,
                "createTime": "2019-12-17T17:17:00.000+0000",
                "updateTime": "2019-12-28T08:25:21.000+0000"
            },
            {
                "id": 9,
                "name": "冷饮冻食",
                "type": 1,
                "parentId": 0,
                "orderNum": 4,
                "createTime": "2019-12-20T05:45:28.000+0000",
                "updateTime": "2019-12-28T08:25:22.000+0000"
            },
            {
                "id": 10,
                "name": "蔬菜蛋品",
                "type": 1,
                "parentId": 0,
                "orderNum": 5,
                "createTime": "2019-12-20T05:45:28.000+0000",
                "updateTime": "2019-12-28T08:25:23.000+0000"
            },
            {
                "id": 27,
                "name": "美味菌菇",
                "type": 1,
                "parentId": 0,
                "orderNum": 7,
                "createTime": "2019-12-20T05:45:28.000+0000",
                "updateTime": "2020-02-10T15:20:36.000+0000"
            },
            {
                "id": 4,
                "name": "橘子橙子",
                "type": 2,
                "parentId": 3,
                "orderNum": 1,
                "createTime": "2019-12-17T17:17:00.000+0000",
                "updateTime": "2019-12-28T08:25:10.000+0000"
            },
            {
                "id": 7,
                "name": "螃蟹",
                "type": 2,
                "parentId": 5,
                "orderNum": 1,
                "createTime": "2019-12-17T17:17:00.000+0000",
                "updateTime": "2019-12-28T08:25:15.000+0000"
            },
            {
                "id": 17,
                "name": "冰淇淋",
                "type": 2,
                "parentId": 9,
                "orderNum": 1,
                "createTime": "2019-12-17T17:17:00.000+0000",
                "updateTime": "2019-12-28T08:25:18.000+0000"
            },
            {
                "id": 16,
                "name": "牛羊肉",
                "type": 2,
                "parentId": 6,
                "orderNum": 1,
                "createTime": "2019-12-17T17:17:00.000+0000",
                "updateTime": "2019-12-28T08:25:18.000+0000"
            }
        ],
        "pageNum": 1,
        "pageSize": 10,
        "size": 10,
        "startRow": 1,
        "endRow": 10,
        "pages": 2,
        "prePage": 0,
        "nextPage": 2,
        "isFirstPage": true,
        "isLastPage": false,
        "hasPreviousPage": false,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
            1,
            2
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 2
    }
}
```
## 前台：

### 分类列表（递归） 

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|无|    |    |    |

请求示例

```
/category/list
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": [
        {
            "id": 3,
            "name": "新鲜水果",
            "type": 1,
            "parentId": 0,
            "orderNum": 1,
            "childCategory": [
                {
                    "id": 4,
                    "name": "橘子橙子",
                    "type": 2,
                    "parentId": 3,
                    "orderNum": 1,
                    "childCategory": [
                        {
                            "id": 19,
                            "name": "果冻橙",
                            "type": 3,
                            "parentId": 4,
                            "orderNum": 1,
                            "childCategory": []
                        }
                    ]
                },
                {
                    "id": 11,
                    "name": "草莓",
                    "type": 2,
                    "parentId": 3,
                    "orderNum": 2,
                    "childCategory": []
                },
                {
                    "id": 12,
                    "name": "奇异果",
                    "type": 2,
                    "parentId": 3,
                    "orderNum": 3,
                    "childCategory": []
                },
                {
                    "id": 14,
                    "name": "车厘子",
                    "type": 2,
                    "parentId": 3,
                    "orderNum": 4,
                    "childCategory": []
                },
                {
                    "id": 28,
                    "name": "其他水果",
                    "type": 2,
                    "parentId": 3,
                    "orderNum": 4,
                    "childCategory": []
                }
            ]
        },
        {
            "id": 5,
            "name": "海鲜水产",
            "type": 1,
            "parentId": 0,
            "orderNum": 2,
            "childCategory": [
                {
                    "id": 7,
                    "name": "螃蟹",
                    "type": 2,
                    "parentId": 5,
                    "orderNum": 1,
                    "childCategory": []
                },
                {
                    "id": 8,
                    "name": "鱼类",
                    "type": 2,
                    "parentId": 5,
                    "orderNum": 2,
                    "childCategory": []
                },
                {
                    "id": 13,
                    "name": "海参",
                    "type": 2,
                    "parentId": 5,
                    "orderNum": 3,
                    "childCategory": []
                }
            ]
        },
        {
            "id": 6,
            "name": "精选肉类",
            "type": 1,
            "parentId": 0,
            "orderNum": 3,
            "childCategory": [
                {
                    "id": 16,
                    "name": "牛羊肉",
                    "type": 2,
                    "parentId": 6,
                    "orderNum": 1,
                    "childCategory": []
                }
            ]
        },
        {
            "id": 9,
            "name": "冷饮冻食",
            "type": 1,
            "parentId": 0,
            "orderNum": 4,
            "childCategory": [
                {
                    "id": 17,
                    "name": "冰淇淋",
                    "type": 2,
                    "parentId": 9,
                    "orderNum": 1,
                    "childCategory": []
                }
            ]
        },
        {
            "id": 10,
            "name": "蔬菜蛋品",
            "type": 1,
            "parentId": 0,
            "orderNum": 5,
            "childCategory": [
                {
                    "id": 18,
                    "name": "蔬菜综合",
                    "type": 2,
                    "parentId": 10,
                    "orderNum": 1,
                    "childCategory": []
                }
            ]
        },
        {
            "id": 27,
            "name": "美味菌菇",
            "type": 1,
            "parentId": 0,
            "orderNum": 7,
            "childCategory": [
                {
                    "id": 15,
                    "name": "火锅食材",
                    "type": 2,
                    "parentId": 27,
                    "orderNum": 5,
                    "childCategory": []
                }
            ]
        }
    ]
}
```
# 商品模块

## 后台管理：

### 增加商品

|请求地址|/admin/product/add|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|name|商品名称|猕猴桃|    |
|categoryId|目录ID|5|    |
|price|价格|1000|单位是分|
|stock|库存|10|    |
|detail|商品描述|新西兰黄心，黄金奇异果|    |
|image|商品图片|[http://127.0.0.1:8083/images/6037baf8-5251-4560-be5a-32b8ee3823cf.png](http://127.0.0.1:8083/images/6037baf8-5251-4560-be5a-32b8ee3823cf.png)|    |

请求示例

```
/admin/product/add
```
body：
```
{"name":"猕猴桃","categoryId":5,"price":1000,"stock":10,"status":1,"detail":"新西兰黄心，黄金奇异果","image":"http://127.0.0.1:8083/images/6037baf8-5251-4560-be5a-32b8ee3823cf.png"}
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": null
}
```

### 上传图片

|请求地址|/admin/upload/file|
|:----|:----|
|请求方式|POST|

参数：

body 的类型是 form-data，key 是 file，value 是里传 file 类型的文件：

![图片](data:image/png;base64,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FKAKUAWoHJADckAOyAE5IAc65YACVMJ1SjhHkLMdQYqneMoBOSAH5MAQOaAAVYAqQOWAHJADckAOyAE50CkHFKASrlPCDXGUZpq1LsgBOSAH5IAcmC0HFKAKUAWoHJADckAOyAE5IAc65YACVMJ1SjhHkLMdQYqneMoBOSAH5MAQOaAAVYAqQOWAHJADckAOyAE50CkHFKASrlPCDXGUZpq1LsgBOSAH5IAcmC0HFKAKUAWoHJADckAOyAE5IAc65YACVMJ1SjhHkLMdQYqneMoBOSAH5MAQOaAAVYAqQOWAHOgJB57b6f8WnsPHYIhiwDQrYrvmgAK0Jx1P1wVvfDY2cqB/HFB8Dl98UobWrf7VLcukf2WiAFWA2ljKATnQEw4oQBWgCqX+CSXLZD5logDtSccjwedDcHEV1yFxQAGqAB0SX02r7etaOKAAVYBq/ZIDcqAnHFCAKkDX0qHrV0E4JA4oQHvS8QyJNKbVRk4OzIcDClAFqHVrPnVLXPuH64YtW7YUnmIgB+SAHJADckAOyAE50BUHFKAKcAcgckAOyAE5IAfkgBzolAMbCg8REAEREAEREAEREAER6BABBWiHYBuVCIiACIiACIiACIhAUShAZYEIiIAIiIAIiIAIiECnCChAO4XbyERABERABERABERABBSgckAEREAEREAEREAERKBTBBSgncJtZCIgAiIgAiIgAiIgAgpQOSACIiACIiACIiACItApAgrQTuE2MhEQAREQAREQAREQAQWoHBABERABERABERABEegUAQVop3AbmQiIgAiIgAiIgAiIwAoB+s477xSvv/568eqrr3qKwdJzgLpAnfAQAREQAREQARGYLQKlAP3www8VngrOpRecdQMvhCh1xEMEREAEREAERGA2CJQCVKunFt868eXzf3CDOuIhAiIgAiIgAiIwGwQ2MMWoyFCAyoFmDjgdP5tGx1BEQAREQAREYIPWz2bhoTgTIzigFdQGUwREQAREQARmg8AGxZXiSg6058Bsqp2hiIAIiIAIiMByI6AAdfORSzAm4MByNxfmXgREQAREQARmg4ACdALxoaWwvaVwUbGaTbUzFBEQAREQARFYbgQUoApQLaATcGC5mwtzLwIiIAIiIAKzQUABOoH4WFSrnvlqb9mdTbUzFBEQAREQARFYbgQUoApQLaATcGC5mwtzLwIiIAIiIAKzQUABOoH40FLY3lK4qFjNptoZigiIgAiIgAgsNwIKUAWoFtAJOLDczYW5FwEREAEREIHZIKAAnUB8LKpVz3y1t+zOptoZigiIgAiIgAgsNwIKUAWoFtAJOLDczYW5FwEREAEREIHZIKAAnUB8aClsbylcVKxmU+0MRQREQAREQASWGwEFqAJUC+gEHFju5sLci4AIiIAIiMBsEFCATiA+FtWqZ77aW3ZnU+0MRQREQAREQASWGwEFqAJUC+gEHFju5sLci4AIiIAIiMBsEOhEgD7xxBPFXXfdVTzwwAPFiy++uPSC55FHpV8sJAAAIABJREFUHikefPDBYvPmzUuPxdCsr7OpdoYiAiIgAiIgAsuNwFwF6LPPPlscf/zxxW677TY699xzz+Lll19eauF11FFHlXg8+eSTS43D0MQn6fUQAREQAREQARFYOwJzFaC/+93vSqGF4Lr55puLm266qbjuuuuWXnQpQNuvueybSF17lTMEERABERABERCBuQnQZ555phSfBx10ULF169alF52pkJq1AL3sssuKCy+8UIwnWMuZlsck9zYZIiACIiACIiACa0dgbgL0vvvuKwXoqaeeqjDKhNGsBejBBx9cnHbaaeKc4TyJsGzrdu1VzhBEQAREQAREQATmJkBvvfXWUoCec845rYXRWjbl4LeNpbUpjqb3bYVK6i4PcxIBumXLlrH4PfrooyXOkwjQpjDTtHu/crmATYYIiIAIiIAIiMDaEZi5AH3ooYeK8847r2Dqnc1H++67b3HYYYeV50knnbRKTN17773FiSeeWBxwwAGle664Y8d8lfh5/PHHy7BYT8r722+/vXTP5qbdd9+9eOqpp8rnrDklXnacsxnq9NNPL9OEm8MPP7w4++yzi5deeql0y/uzzjqr+PWvfz1KM2l47LHHKtNQla78Gbv+jzvuuGKfffYp9thjj+LII48sLrnkkgLx95vf/KaMh68D5P7YHX/mmWeWaY88gSXpI53h/pVXXikuuOCCMnxwJp7Ameudd945ckucd9xxR3HyyScXWEvBgLBxd+WVV7obfwLL6dqrnCGIgAiIgAiIgAjMXIBec801xSmnnFIcffTRpcg65JBDyt88Q5iGgOKKgEI87bXXXsWxxx5bnHHGGcUxxxxTiiNEEmsbU/fcIyjxg9+wsu63337lbnssi2EF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j0W37pm/Zgvv2qucIYiACIiACIiACChAFaBaQCfggE2GCIiACIiACIjA2hFQgE4gPrQQNlsIFx2jtVc5QxABERABERABEVCAKkC1gE7AAZsMERABERABERCBtSOgAJ1AfCy6dc/8NVt4117lDEEEREAEREAEREABqgDVAjoBB2wyREAEREAEREAE1o7Ahtdff10BMoEA0UrYbCVcVIyoKx4iIAIiIAIiIAJrR2DDO++8owBVgMqBFhygrniIgAiIgAiIgAisHYENBKEVdHmteotqrZx1vrR+rr2xMQQREAEREAERCARKAfrhhx8qQltYwGYtagxvGMIf8Ukd8RABERABERABEZgNAqUAjaCYYtQaOgxRpHidfzlRF5x2j9bBqwiIgAiIgAjMDoEVAnR2wRqSCIiACIiACIiACIiACFQjoACtxsWnIiACIiACIiACIiACc0JAATonYA1WBERABERABERABESgGgEFaDUuPhUBERABERABERABEZgTAgrQOQFrsCIgAiIgAiIgAiIgAtUIKECrcfGpCIiACIiACIiACIjAnBBQgM4JWIMVAREQAREQAREQARGoRkABWo2LT0VABERABERABERABOaEgAJ0TsAarAiIgAiIgAiIgAiIQDUCCtBqXHwqAiIgAiIgAiIgAiIwJwQ2bNmypfAUAzkgB+SAHJADckAOyIGuOKAAVYA7AJEDckAOyAE5IAfkQKccWLop+FD2c7IoG6wIlAjIM4nQBQLyrAuUjUMERGAeCChA54GqYS49AgqDpadAJwDIs05gNhIREIE5IKAAnQOoBikCCgM50AUC8qwLlI1DBERgHggoQOeBqmEuPQIKg6WnQCcAyLNOYDYSERCBOSCgAJ0DqAYpAgoDOdAFAvKsC5SNQwREYB4IKEDngaphLj0CCoOlp0AnAMizTmA2EhEQgTkgoACdA6gGKQIKAznQBQLyrAuUjUMERGAeCChA54GqYS49AgqDpadAJwDIs05gNhIREIE5IKAAnQOoBikCCgM50AUC8qwLlI1DBERgHggoQOeBqmEuPQIKg6WnQCcAyLNOYDYSERCBOSCgAJ0DqAYpAgoDOdAFAvKsC5SNQwREYB4IKEDngaphLj0CCoOlp0AnAMizTmA2EhEQgTkgoACdA6gGKQIKAznQBQLyrAuUjUMERGAeCChA54GqYS49AgqD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EAER6AsCCtA1lgQd7gsvvFDccccd5a7Vt956a2yIzz//fGnRyj8bNdaTLweHQFth0CQ01iJA+SLEqaeeWmyzzTaliEXIcn7hC18orrjiilpMP/3pT69wH/7ieuihh67y+9577xWnnHJKsd12263y+9Of/rR46qmnVvkZyoM777yzOOigg4prr7124iTjZ5999ikF+SSem3gRYbXlWbj3KgIiIAJ9QUABuoaS+NOf/lT84he/WNHhfvKTnyywOP3xj38szj777FX/1Wbfffct3T/wwANriFmvfUegrTBoEhprEaB77bVXybVvf/vbxfnnn1/ccMMNxRFHHFF89rOfLZ+fc845q2BEtCI0P/e5zxUHHHBA5XnppZeu8Edev/KVr5T+vve975WiF2vrVVddVey5557l88985jPFq6++usLfEH7wSSQE5G677Vae5513Xutk4zb87bfffq394bCJFxFYW56Fe68iIAIi0BcEBiVA/+t//a/F1VdfXTz33HPFN77xjcrNTtEgdzEFH2LyBz/4QWkdIW3ROX/1q18tO16mPNMj/ChAU1QW7z542JSzJqExrQC95ZZbSv7tsssuq/6vOBZ7rJxYRl966aUVSWQDHwIU8TrJ8Zvf/KbkftX3TI866qgyzF//+teTBNkbt9ddd91ISCIoGViOO5gVwU2IT660DZMcTbyIsNryLNx7FQEREIG+IDAYAfrf//t/Lx5//PERblhqvvWtb60SodEgz1uA8m/y6Kix/FR1uscdd1yB5SmfkleAjopwoW+Ch02ZbBIaCNCPfvSjK4JB4HCOO/bYY4+Sn4RfdWANhb+//e1vV7x+5plnZi4WGTASF/VhqMfFF1+8QlAiMKvKgLbgD3/4wwq3+J30aOJFhNeWZ+HeqwiIgAj0BYFBCFDEZ9Uastdee23dBCgWTDrVAw88cKKyVIBOBNdgHbcVBk1CAwHKsg6Oa665plzygfXyU5/6VPHjH/+4uOiiiyox+trXvlYK17oNR3/5y1/K97vvvvsK/8Hrk08+ecXztfygnlJXWHs65INlDKlVE6GZDj65Z81t6mYa8QlGTbwIHNvyLNx7FQEREIG+INB7AVonPgHwiSeeWDcBGhYkph4nOSYRoHXiYZL4dLs+CLQVBk1CAwGK4DzxxBNLwbjzzjsXhx12WIFwjOl5OBX/gjZyi2hFpI47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)


返回:

```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": "http://127.0.0.1:8082/upload/b899f512-3467-4c71-8d2d-2d491b21f429.png"
}
```
### 更新商品

|请求地址|/admin/product/update|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|id|商品ID|1|    |
|name|商品名称|猕猴桃|    |
|categoryId|目录ID|5|    |
|price|价格|1000|单位是分|
|stock|库存|10|    |
|detail|商品描述|新西兰黄心，黄金奇异果|    |
|image|商品图片|[http://127.0.0.1:8083/images/6037baf8-5251-4560-be5a-32b8ee3823cf.png](http://127.0.0.1:8083/images/6037baf8-5251-4560-be5a-32b8ee3823cf.png)|    |

请求示例

```
/admin/product/add
```
body：
```
{"id":3,"name":"早餐小面包 3","categoryId":5,"price":1000,"stock":10,"status":1,"detail":"好吃的小面包","image":"http://127.0.0.1:8083/images/6037baf8-5251-4560-be5a-32b8ee3823cf.png"}
```

返回示例

```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": null
}
```
### 删除商品

|请求地址|/admin/product/delete|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|id|商品的id|1|    |

请求示例

```
/admin/product/delete?id=1
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": null
}
```
### 批量上下架商品

|请求地址|/admin/product/delete|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|ids|要更改的商品的id|1,2,4|数组|
|sellStatus|上下架状态|1|1是上架，0是下架|

请求示例

```
/admin/product/batchUpdateSellStatus?ids=1,2,4&sellStatus=1
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": null
}
```
### 商品列表(后台）

|请求地址|/admin/product/list|
|:----|:----|
|请求方式|GET|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|pageNum|页数|1|默认会按照修改时间倒序|
|pageSize|每页条数|10|    |

请求示例

```
/admin/product/list?pageSize=5&pageNum=1 
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": {
        "total": 20,
        "list": [
            {
                "id": 40,
                "name": "胡萝卜",
                "image": "http://127.0.0.1:8083/images/huluobo.jpg",
                "detail": "商品名称：绿鲜知胡萝卜商品编号：4116192商品毛重：1.07kg商品产地：北京包装：简装分类：萝卜烹饪建议：火锅，炒菜，炖菜",
                "categoryId": 18,
                "price": 222,
                "stock": 222,
                "status": 1,
                "createTime": "2019-12-28T08:06:34.000+0000",
                "updateTime": "2020-02-10T16:53:25.000+0000"
            }
        ],
        "pageNum": 1,
        "pageSize": 1,
        "size": 1,
        "startRow": 1,
        "endRow": 1,
        "pages": 20,
        "prePage": 0,
        "nextPage": 2,
        "isFirstPage": true,
        "isLastPage": false,
        "hasPreviousPage": false,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 8
    }
}
```
## 前台：

### 商品列表

|请求地址|/product/list|
|:----|:----|
|请求方式|GET|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|orderBy|排序方式|price desc|price desc 或者 price asc|
|categoryId|商品分类 Id|1|    |
|keyword|搜索的关键词|桃|    |
|pageNum|页数|1|    |
|pageSize|每页条数|10|    |

请求示例

```
/product/list?orderBy=price desc&categoryId=4
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": {
        "total": 20,
        "list": [
            {
                "id": 2,
                "name": "澳洲进口大黑车厘子大樱桃包甜黑樱桃大果多汁 500g 特大果",
                "image": "http://127.0.0.1:8083/images/chelizi2.jpg",
                "detail": "商品毛重：1.0kg货号：608323093445原产地：智利类别：美早热卖时间：1月，11月，12月国产/进口：进口售卖方式：单品",
                "categoryId": 14,
                "price": 50,
                "stock": 100,
                "status": 1,
                "createTime": "2019-12-18T08:08:15.000+0000",
                "updateTime": "2020-02-10T16:08:25.000+0000"
            },
            {
                "id": 3,
                "name": "茶树菇 美味菌菇 东北山珍 500g",
                "image": "http://127.0.0.1:8083/images/chashugu.jpg",
                "detail": "商品名：茶树菇 商品特点：美味菌菇 东北山珍 500g",
                "categoryId": 15,
                "price": 1000,
                "stock": 6,
                "status": 1,
                "createTime": "2019-12-18T08:10:50.000+0000",
                "updateTime": "2020-02-10T16:42:42.000+0000"
            },
            {
                "id": 14,
                "name": "Zespri佳沛 新西兰阳光金奇异果 6个装",
                "image": "http://127.0.0.1:8083/images/mihoutao2.jpg",
                "detail": "商品编号：4635056商品毛重：0.71kg商品产地：新西兰类别：金果包装：简装国产/进口：进口原产地：新西兰",
                "categoryId": 12,
                "price": 39,
                "stock": 77,
                "status": 1,
                "createTime": "2019-12-18T08:11:13.000+0000",
                "updateTime": "2020-02-10T15:36:48.000+0000"
            },
            {
                "id": 17,
                "name": "红颜奶油草莓 约重500g/20-24颗 新鲜水果",
                "image": "http://127.0.0.1:8083/images/caomei2.jpg",
                "detail": "商品毛重：0.58kg商品产地：丹东/南通/武汉类别：红颜草莓包装：简装国产/进口：国产",
                "categoryId": 11,
                "price": 99,
                "stock": 84,
                "status": 1,
                "createTime": "2019-12-18T08:11:13.000+0000",
                "updateTime": "2020-02-10T15:37:48.000+0000"
            },
            {
                "id": 21,
                "name": "智利原味三文鱼排（大西洋鲑）240g/袋 4片装",
                "image": "http://127.0.0.1:8083/images/sanwenyu2.jpg",
                "detail": "商品毛重：260.00g商品产地：中国大陆保存状态：冷冻国产/进口：进口包装：简装类别：三文鱼海水/淡水：海水烹饪建议：煎炸，蒸菜，烧烤原产地：智利",
                "categoryId": 8,
                "price": 499,
                "stock": 1,
                "status": 1,
                "createTime": "2019-12-28T07:13:07.000+0000",
                "updateTime": "2020-02-10T15:38:46.000+0000"
            },
            {
                "id": 22,
                "name": "即食海参大连野生辽刺参 新鲜速食 特级生鲜海产 60~80G",
                "image": "http://127.0.0.1:8083/images/haishen.jpg",
                "detail": "商品毛重：1.5kg商品产地：中国大陆贮存条件：冷冻重量：50-99g国产/进口：国产适用场景：养生滋补包装：袋装原产地：辽宁年限：9年以上等级：特级食品工艺：冷冻水产热卖时间：9月类别：即食海参固形物含量：70%-90%特产品类：大连海参售卖方式：单品",
                "categoryId": 13,
                "price": 699,
                "stock": 3,
                "status": 1,
                "createTime": "2019-12-28T07:16:29.000+0000",
                "updateTime": "2020-02-10T16:04:29.000+0000"
            },
            {
                "id": 23,
                "name": "澳大利亚直采鲜橙 精品澳橙12粒 单果130-180g",
                "image": "http://127.0.0.1:8083/images/chengzi.jpg",
                "detail": "商品毛重：2.27kg商品产地：澳大利亚类别：脐橙包装：简装国产/进口：进口原产地：澳大利亚",
                "categoryId": 4,
                "price": 12,
                "stock": 12,
                "status": 1,
                "createTime": "2019-12-28T08:02:13.000+0000",
                "updateTime": "2020-02-10T16:40:15.000+0000"
            },
            {
                "id": 24,
                "name": "智利帝王蟹礼盒装4.4-4.0斤/只 生鲜活鲜熟冻大螃蟹",
                "image": "http://127.0.0.1:8083/images/diwangxie.jpg",
                "detail": "商品毛重：3.0kg商品产地：智利大闸蟹售卖方式：公蟹重量：2000-4999g套餐份量：5人份以上国产/进口：进口海水/淡水：海水烹饪建议：火锅，炒菜，烧烤，刺身，加热即食包装：简装原产地：智利保存状态：冷冻公单蟹重：5.5两及以上分类：帝王蟹特产品类：其它售卖方式：单品",
                "categoryId": 7,
                "price": 222,
                "stock": 222,
                "status": 1,
                "createTime": "2019-12-28T08:06:34.000+0000",
                "updateTime": "2020-02-10T16:05:05.000+0000"
            },
            {
                "id": 25,
                "name": "新疆库尔勒克伦生无籽红提 国产新鲜红提葡萄 提子 5斤装",
                "image": "http://127.0.0.1:8083/images/hongti.jpg",
                "detail": "商品毛重：2.5kg商品产地：中国大陆货号：XZL201909002重量：2000-3999g套餐份量：2人份国产/进口：国产是否有机：非有机单箱规格：3个装，4个装，5个装类别：红提包装：简装原产地：中国大陆售卖方式：单品",
                "categoryId": 28,
                "price": 222,
                "stock": 222,
                "status": 1,
                "createTime": "2019-12-28T08:06:34.000+0000",
                "updateTime": "2020-02-10T16:44:05.000+0000"
            },
            {
                "id": 26,
                "name": "越南进口红心火龙果 4个装 红肉中果 单果约330-420g",
                "image": "http://127.0.0.1:8083/images/hongxinhuolongguo.jpg",
                "detail": "商品毛重：1.79kg商品产地：越南重量：1000-1999g类别：红心火龙果包装：简装国产/进口：进口",
                "categoryId": 28,
                "price": 222,
                "stock": 222,
                "status": 1,
                "createTime": "2019-12-28T08:06:34.000+0000",
                "updateTime": "2020-02-10T16:44:11.000+0000"
            }
        ],
        "pageNum": 1,
        "pageSize": 10,
        "size": 10,
        "startRow": 1,
        "endRow": 10,
        "pages": 2,
        "prePage": 0,
        "nextPage": 2,
        "isFirstPage": true,
        "isLastPage": false,
        "hasPreviousPage": false,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
            1,
            2
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 2
    }
}
```
### 商品详情

|请求地址|/product/detail|
|:----|:----|
|请求方式|GET|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|id|商品ID|2|    |

请求示例

```
/product/detail?id=2
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": {
        "id": 2,
        "name": "澳洲进口大黑车厘子大樱桃包甜黑樱桃大果多汁 500g 特大果",
        "image": "http://127.0.0.1:8083/images/chelizi2.jpg",
        "detail": "商品毛重：1.0kg货号：608323093445原产地：智利类别：美早热卖时间：1月，11月，12月国产/进口：进口售卖方式：单品",
        "categoryId": 14,
        "price": 50,
        "stock": 100,
        "status": 1,
        "createTime": "2019-12-18T08:08:15.000+0000",
        "updateTime": "2020-02-10T16:08:25.000+0000"
    }
}
```
# 购物车模块（都是前台的）

## 1    购物车列表

|请求地址|/cart/list|
|:----|:----|
|请求方式|GET|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|无|    |    |    |

请求示例

```
/cart/list
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": [
        {
            "id": 5,
            "productId": 3,
            "userId": 9,
            "quantity": 1,
            "selected": 1,
            "price": 1000,
            "totalPrice": 1000,
            "productName": "茶树菇 美味菌菇 东北山珍 500g",
            "productImage": "http://127.0.0.1:8083/images/chashugu.jpg"
        }
    ]
}
```
## 2    添加商品到购物车

|请求地址|/cart/add|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|productId|商品ID|5|    |
|count|数量|1|    |

请求示例

```
/cart/add?productId=5&count=1
```
返回的是“购物车列表”
## 3    更新购物车某个商品的数量

|请求地址|/cart/update|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|productId|商品ID|5|    |
|count|数量|1|    |

请求示例

```
/cart/update?productId=5&count=1
```

返回的是“购物车列表”

## 4    删除购物车的某个商品

|请求地址|/cart/delete|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|productId|商品ID|5|    |

请求示例

```
/cart/delete?productId=5
```

返回的是“购物车列表”

## 5    选中/不选中购物车的某个商品

|请求地址|/cart/select|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|productId|商品ID|5|    |
|selected|是否选中|1|0 是不选中，1 是选中|

请求示例

```
/cart/select?productId=2&selected=1
```

返回的是“购物车列表”

## 6    全选/全不选购物车的某个商品

|请求地址|/cart/selectAll|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|selected|是否选中|1|0 是不选中，1 是选中|

请求示例

```
/cart/selectAll?selected=1
```

返回的是“购物车列表”

# 订单模块

## 前台显示

### 前台：创建订单

|请求地址|/order/create |
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|receiverName|收件人姓名|小慕|    |
|receiverMobile|收件人电话|18888888888|    |
|receiverAddress|收件人地址|中国慕城|    |

请求示例

```
/order/create
```
body:
```
{"receiverName":"小慕","receiverMobile":"18888888888","receiverAddress":"中国慕城"}
这三个参数都是必传
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": orderNo
}
```
### 前台：订单详情

|请求地址|/order/detail|
|:----|:----|
|请求方式|GET|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|orderNo|订单号|101442166222<br>|    |

请求示例

```
/order/detail?orderNo=101442166222
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": {
        "orderNo": "116214445684",
        "userId": 9,
        "totalPrice": 1000,
        "receiverName": "小慕",
        "receiverMobile": "18888888888",
        "receiverAddress": "中国慕城",
        "orderStatus": 10,
        "postage": 0,
        "paymentType": 1,
        "deliveryTime": null,
        "payTime": null,
        "endTime": null,
        "createTime": "2020-02-11T08:21:44.000+0000",
        "orderItemVOList": [
            {
                "orderNo": "116214445684",
                "productName": "茶树菇 美味菌菇 东北山珍 500g",
                "productImg": "http://127.0.0.1:8083/images/chashugu.jpg",
                "unitPrice": 1000,
                "quantity": 1,
                "totalPrice": 1000
            }
        ],
        "orderStatusName": "未付款（初始状态）"
    }
}
```
### 前台：订单列表

|请求地址|/order/list|
|:----|:----|
|请求方式|GET|

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|pageNum|页数|1|    |
|pageSize|每页条数|10|    |

请求示例

```
/order/list?pageNum=1&pageSize=10
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": {
        "total": 1,
        "list": [
            {
                "orderNo": "116214445684",
                "userId": 9,
                "totalPrice": 1000,
                "receiverName": "小慕",
                "receiverMobile": "18888888888",
                "receiverAddress": "中国慕城",
                "orderStatus": 10,
                "postage": 0,
                "paymentType": 1,
                "deliveryTime": null,
                "payTime": null,
                "endTime": null,
                "createTime": "2020-02-11T08:21:44.000+0000",
                "orderItemList": [
                    {
                        "orderNo": "116214445684",
                        "productName": "茶树菇 美味菌菇 东北山珍 500g",
                        "productImg": "http://127.0.0.1:8083/images/chashugu.jpg",
                        "unitPrice": 1000,
                        "quantity": 1,
                        "totalPrice": 1000
                    }
                ],
                "orderStatusName": "未付款（初始状态）"
            }
        ],
        "pageNum": 1,
        "pageSize": 10,
        "size": 1,
        "startRow": 1,
        "endRow": 1,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }
}
```

### 前台：取消订单

|请求地址|/order/cancel|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|orderNo|订单号|101442166222|    |

请求示例

```
/order/cancel?orderNo=101442166222
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": null
}
```
### 生成支付二维码

在支付的时候显示的二维码

|请求地址|/order/qrcode|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|orderNo|订单号|101442166222|    |

请求示例

```
/order/cancel?orderNo=101442166222
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": "127.0.0.1:8082/images/101442166222.png"
}
```

### 前台：支付订单

|请求地址|/pay|
|:----|:----|
|请求方式|GET|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|orderNo|订单号|101442166222|    |

请求示例

```
/pay?orderNo=101442166222
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": null
}
```
## 后台管理

### 后台：订单列表

|请求地址|/admin/order/list|
|:----|:----|
|请求方式|GET|

### 
|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|pageNum|页数|1|    |
|pageSize|每页条数|10|    |



请求示例

```
/admin/order/list?pageNum=1&pageSize=10
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": {
        "total": 1,
        "list": [
            {
                "orderNo": "116214445684",
                "userId": 9,
                "totalPrice": 1000,
                "receiverName": "小慕",
                "receiverMobile": "18888888888",
                "receiverAddress": "中国慕城",
                "orderStatus": 10,
                "postage": 0,
                "paymentType": 1,
                "deliveryTime": null,
                "payTime": null,
                "endTime": null,
                "createTime": "2020-02-11T08:21:44.000+0000",
                "orderItemList": [
                    {
                        "orderNo": "116214445684",
                        "productName": "茶树菇 美味菌菇 东北山珍 500g",
                        "productImg": "http://127.0.0.1:8083/images/chashugu.jpg",
                        "unitPrice": 1000,
                        "quantity": 1,
                        "totalPrice": 1000
                    }
                ],
                "orderStatusName": "未付款（初始状态）"
            }
        ],
        "pageNum": 1,
        "pageSize": 1,
        "size": 1,
        "startRow": 0,
        "endRow": 0,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }
}
```

### 后台：订单发货

|请求地址|/admin/order/delivered|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|orderNo|订单号|101442166222|    |

请求示例

```
/admin/order/delivered?orderNo=118213761282
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": null
}
```

## 前后台通用

### 订单完结

|请求地址|/order/finish|
|:----|:----|
|请求方式|POST|

参数

|参数|参数含义|示例|备注|
|:----|:----|:----|:----|
|orderNo|订单号|101442166222|    |

请求示例

```
/order/finish?orderNo=118213761282
```
返回示例
```
{
    "status": 10000,
    "msg": "SUCCESS",
    "data": null
}
```

