package com.dyz.skeleton;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dyz.skeleton.infrastructure.db.entity.UserPO;
import com.dyz.skeleton.infrastructure.db.mapper.UserPOMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class SpringbootSkeletonApplicationTests {
	@Autowired
	private UserPOMapper userPOMapper;

	@Test
	void test() {
		LambdaQueryWrapper<UserPO> queryWrapper = new LambdaQueryWrapper<>(UserPO.class);
		queryWrapper.eq(UserPO::getName,"zhangsan@foxmail.com");
		System.out.println(userPOMapper.selectOne(queryWrapper));
	}

}
