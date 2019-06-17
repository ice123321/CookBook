# 小食代

#### 前言

有人说2018年的冬天是互联网的冬天，无论大小公司都在裁员，软件公司不要再进了，IT行业饱和了。其实我想说的是：互联网冬天来没来，我不知道，我只知道对于废物程序员来说，每一天都是冬天！

#### 介绍

基于Android端的菜谱应用类App，主要功能大致有：用户的登录和注册，菜品的分类，菜谱的列表，以及菜谱详情和用户评论

#### 项目功能

1. 使用[GreenDao3.0](http://greenrobot.org/greendao/)插件操控本地数据库实现用户的登录和注册功能；
2. 在[BaseAdapter](http://www.recyclerview.org/)基础上进行封装属于自己的BaseAdapter；
3. 使用[聚合API-菜谱数据](https://www.juhe.cn/docs/api/id/46)来实现菜谱的分类，详细列表，详情等功能；
4. 利用GreenDao3.0插件操控数据库实现本地收藏功能。

#### 项目架构以及开发中遇到问题

1. 该项目采用MVP架构进行开发，如若基础比较差的同学可以先阅读我之前的项目([Android新闻客户端](https://gitee.com/YiDer/News))，新闻客户端的项目具有开发单元小，容易上手等优点，类似于MVP介绍的文章在该项目中也有提及，有兴趣的小伙伴可以前去一阅；
2. 不得不提及的MVP架构有两个需要注意的：<br>
   *采用RxLifecycle解决RxJava使用过程中的内存泄漏;<br>
   *在Activity关闭之后取消网络请求;<br>
3. 项目中当网络请求失败时，显示错误页面的方案：采用ViewStub在Activity.xml中进行镶嵌，当请求失败报错时，开始ViewStub的初始化和显示;
4. GreenDao3.0的数据库的增删改查以及条件查询，在此就不再提及，如有需要可以联系我，我会详细讲解;
5. 项目中的菜品详情页属于复杂页面，属于菜品介绍和步骤HeadeView+用户评论RecyclerView，之前我是采用封装好的BaseAdapter通过getItemViewType(possition)来添加HeadeView，但是该方式的滑动会出现卡顿和掉帧，所以选用成熟轮子[淘宝框架V-Layout](https://github.com/alibaba/vlayout)来实现该复杂页面，滑动和下拉的效果还算很理想;
6. 如若App中无法获取数据，请在[聚合API-菜谱数据](https://www.juhe.cn/docs/api/id/46)中申请API-KEY，并在Config.class中替换HTTP_NET_KEY即可;

#### 应用试玩

![CookBook](https://www.pgyer.com/app/qrcode/8zU1)

#### 总结

* Android在国内的开发发展至今不过二十几年，但是Android开发者的就业依旧和每个行业一样，就是一个金字塔，全栈式，直播编码解码，人工智能等高端开发人才依然稀缺，还是那句话废物程序员的每一天都是冬天！
* 项目地址：https://gitee.com/YiDer/CookBook
* 码云个人地址： https://gitee.com/YiDer
* 联系方式：1070138445
* 广告：本人承接各个学历的计算机毕业设计，主要方向是Java，Android以及Web，公司信誉和个人资料担保，开发周期固定，保障顾客利益，避免各类骗术！