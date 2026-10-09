# kehoulianxi12 —— 课后练习12：Spring 整合 MyBatis 案例扩展（增加班级）

在练习12（Spring 整合 MyBatis 分页案例）基础上增加**添加班级**功能：mapper 层加 `@Insert`，service 层加 `add()`，控制层新增跳转与提交两个 Servlet，页面提供表单，提交后提示"添加成功"并跳回列表。

## 数据流

```
列表页"增加班级" → /toAddClazz（doGet 转发）→ class.jsp 表单
→ POST /addClazz（doPost）→ service.add() → mapper.insert() → MySQL
→ alert("添加成功") → 自动跳回 /queryClazz 列表
```

## 新增代码

- `ClazzMapper#insert`：`@Insert("insert into class(cid, cno, cname, tid) values (...)")`
- `IClazzService#add` / `ClazzServiceImpl#add`
- `ToAddClazzServlet`（/toAddClazz）：doGet 转发到 class.jsp 表单页
- `AddClazzServlet`（/addClazz）：doPost 收表单 → `service.add()` → 提示"添加成功" → 跳回列表
- `webapp/class.jsp`：班级ID / 班级编号 / 班级名称 / 教师编号 四字段表单
- `clazz.jsp`：列表页增加"增加班级"文字超链接

## 踩坑记录

- 本机 class 表的 cid 原本不是自增主键，插入不含 cid 时报 `Field 'cid' doesn't have a default value`，改成自增即可：
  `ALTER TABLE class MODIFY cid INT NOT NULL AUTO_INCREMENT;`
  之后表单不填 cid 会自增，填了就按填的存
- testAdd 加了**防重跑守卫**：库里已有同班号直接跳过插入，避免反复跑测试插出重复数据
- POST 中文乱码：`request.setCharacterEncoding("UTF-8")` 必须在读取参数之前调用
- "添加成功"提示用 `alert` + `location.href` 跳回列表；拼 URL 用变量，不要在 href 属性里手工断行——续行行首的缩进空格会被浏览器编码成 %20 拼进 URL（翻页 404 的教训）

## 运行

- `mvn test`：4 个测试（mapper 的 selectAll / testAdd、service 的 selectByPage / testAdd，重跑自动跳过）
- Tomcat 部署后从 `/queryClazz` 列表页点"增加班级"走完整新增流程

---
刘万宇 202440710122
