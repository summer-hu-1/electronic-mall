<<<<<<< HEAD
# electronic-mall
My undergraduate project~ micro service kinds
=======
# 电子商城

#### 介绍
SpringCloud 制作的电子商城

#### 安装教程

1.  将项目导入idea中
2.  数据库为mysql放在goods-consumer下的静态文件夹下，路径: electronic-mall/goods-consumer/src/main/resources/static/sql
3.  修改对应数据库的配置项，数据库默认账号密码都为root
4.  修改 user-consumer/src/main/java/com/itheima/userconsumer/controller/GoodsController.java 文件中 addGoods() 方法下面的path路径，将其替换成自己的图片存放位置
5.  第4点的文件上传已经被注释，需要将图片预先放在 electronic-mall/goods-consumer/src/main/resources/static/img/goods路径下

#### 启动说明

1.  首先启动 eureka-server 服务注册中心
2.  启动 goods-provider
3.  启动 goods-consumer
4.  启动 user-provider
5.  启动 user-consumer
6.  从 user-consumer 进行系统页面（系统有后端和前端2块，通过角色进行划分）
     - 可以在数据库的用户表中的 category 字段进行区分；
     - 1：后台用户（管理员）；2：前端用户；

#### 项目结构

1.  common 为公共类模块（里面存放的是项目的通用类，通过maven导包进入其他模块内）
2.  eureka-server 是服务注册中心
3.  gateway-service 为服务网关（暂未使用）
4.  goods-consumer 和 goods-provider 为前端商品系统
    - goods-consumer：css、js、html文件存放位置（前端商品界面），controller、service接口文件（前端）；
    - goods-provider：mapper文件存放位置（前端商品界面）；
5.  user-consumer 和 user-provider 为后台管理系统
    - user-consumer：css、js、html文件存放位置（后台管理系统），controller、service接口文件（后台）；
    - user-provider：mapper文件存放位置（后台管理系统）；

#### 页面展示

<table>
    <tr>
        <td><img src="user-consumer/src/main/resources/static/img/admin/%E7%99%BB%E5%BD%95.png"/></td>
        <td><img src="user-consumer/src/main/resources/static/img/admin/%E6%B3%A8%E5%86%8C%E9%A1%B5%E9%9D%A2.png"/></td>
    </tr>
    <tr>
        <td><img src="user-consumer/src/main/resources/static/img/admin/%E5%90%8E%E5%8F%B0%E7%B3%BB%E7%BB%9F.png"/></td>
        <td><img src="user-consumer/src/main/resources/static/img/admin/%E8%AE%A2%E5%8D%95%E9%A1%B5%E9%9D%A2.png"/></td>
    </tr>
    <tr>
        <td><img src="user-consumer/src/main/resources/static/img/admin/%E5%95%86%E5%9F%8E%E9%A1%B5%E9%9D%A2.png"/></td>
        <td><img src="user-consumer/src/main/resources/static/img/admin/%E5%95%86%E5%93%81%E8%AF%A6%E6%83%85%E9%A1%B5%E9%9D%A2.png"/></td>
    </tr>
</table>


>>>>>>> e4a0a8a (first commit)
