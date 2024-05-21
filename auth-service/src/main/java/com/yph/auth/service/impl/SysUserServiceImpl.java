package com.yph.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yph.auth.entity.SysUser;
import com.yph.auth.service.SysUserService;
import com.yph.auth.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author yph
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2024-05-07 09:44:46
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




