## 上海电力学院OA系统-自定义邮件服务

> *上海电力学院OA系统-自定义邮件服务*由`上海象融信息科技有限公司`开发提供，第三方系统可通过该服务向OA系统指定账号发送邮件。

#### 发送邮件服务说明：
* 服务地址：http://210.35.88.151/services/xr/mail/send?token=TOKEN&timestamp=TIMESTAMP（*测试地址，生成环境下切换为生产环境的IP即可*）
* http请求方式：POST

#####  参数说明：

* 请求参数

| **参数名称**  | **参数类型** | **必需** | **参数说明**    |
| --------- | -------- | ------ | ----------- |
| token     | String   | 是      | 安全认证签名      |
| timestamp | String   | 是      | 时间戳         |
| data      | String   | 是      | 邮件数据，json格式 |

* data结构说明
  ```json
    {
    "mailFrom":"1994000008",
    "mailTo":"admin 1994000008",
    "mailToCc":"1994000008",
    "subject":"this is test mail",
    "content":"hello,nice to meet you,<a href='https://www.baidu.com'>https://www.baidu.com</a>"
    }
  ```
  | **参数名称** | **参数类型** | **必需** | **参数说明**                  |
  | -------- | -------- | ------ | ------------------------- |
  | mailFrom | String   | 否      | 发件人，合法的OA系统账号，为空时默认为admin |
  | mailTo   | String   | 是      | 收件人，合法的OA系统账号，多个用空格分隔     |
  | mailToCc | String   | 否      | 抄送，合法的OA系统账号，多个用空格分隔      |
  | subject  | String   | 是      | 邮件标题                      |
  | content  | String   | 是      | 邮件正文内容                    |

*  响应信息

  ```json
  {
      "message": "56142",
      "ok": true
  }
  ```

  | 参数名称    | 参数类型    | 参数说明                              |
  | ------- | ------- | --------------------------------- |
  | ok      | boolean | 是否成功，成功为true，失败为false             |
  | message | String  | 响应信息，ok为true时邮件任务id，ok为false时失败信息 |

  ​

  ##### 安全认证token说明：

* 考虑到服务的安全性，邮件服务通过参数签名进行校验，校验成功则执行发送邮件服务，若校验失败则抛出异常，服务消费方将会接收到异常信息；

* token生成方法：

  1. 向服务提供方（*上海象融信息科技有限公司*）索取签名所需key值，这里假设key值为KEY；
  2. 获取当前时间戳timestamp，这里假设时间戳为1525240885;
  3. 按照key=KEY&timestamp=TIMESTAMP的格式进行字符串拼接，如：String string="key=KEY&timestamp=1525240885";
  4. 对拼接后的字符串进行md5加密并转为小写，token=MD5(string).toLowerCase()； 

```java
String key = "KEY";
StringBuilder resource = new StringBuilder();
resource.append("key=").append(key).append("&timestamp=1525240885");
System.out.println(DigestUtils.md5Hex(resource.toString()).toLowerCase());
```

