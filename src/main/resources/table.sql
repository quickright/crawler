--帖子数据源表
CREATE TABLE `x520_diandian_library_topic_main` (
  `topic_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `title` varchar(150) NOT NULL DEFAULT '',
  `content` text NOT NULL,
  `count_share` int(11) NOT NULL DEFAULT '0',
  `count_good` int(11) NOT NULL DEFAULT '0',
  `count_review` int(11) NOT NULL DEFAULT '0',
  `count_view` int(11) NOT NULL DEFAULT '0',
  `address` varchar(200) NOT NULL DEFAULT '',
  `publish_type` int(2) NOT NULL DEFAULT '1' COMMENT '发表类型 1 默认  2匿名',
  `industry_id` int(11) NOT NULL DEFAULT '0',
  `anonymous` varchar(150) NOT NULL DEFAULT '' COMMENT '匿名 名称',
  `from_type` varchar(100) NOT NULL DEFAULT '暂无' COMMENT '数据来源',
  `type` int(3) NOT NULL DEFAULT '0' COMMENT '0图文，1gif 2视频',
  `is_show` int(2) NOT NULL DEFAULT '1',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态  0 未处理  ，1通过，2未通过',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;

--#帖子数据源 图片  GIF 视频表
CREATE TABLE `x520_diandian_library_topic_photo_main` (
  `photo_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `topic_id` int(11) NOT NULL DEFAULT '0',
  `url` varchar(100) NOT NULL DEFAULT '',
  `type` int(2) NOT NULL DEFAULT '0' COMMENT '0 图片，1gif，2视频',
  `is_show` int(1) NOT NULL DEFAULT '1',
  `add_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0 普通 1是头图',
  `weight` varchar(20) NOT NULL DEFAULT '0' COMMENT '高',
  `height` varchar(20) NOT NULL DEFAULT '0' COMMENT '高',
  PRIMARY KEY (`photo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8


