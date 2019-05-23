package com.hxx.realm;

import com.hxx.entity.Blogger;
import com.hxx.service.BloggerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName MyRealm
 * @Description //TODO 自定义Realm
 * @Author haoxx
 * @Date 2019/4/3 - 15:31
 * @Version 1.0
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private BloggerService bloggerService;

    /**
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @author haoxx
     * @Description //TODO 为当前的登录的用户角色和权限
     * @Date 2019/4/3 - 15:32
     * @Param [principals]
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @author haoxx
     * @Description //TODO 验证当前登录的用户
     * @Date 2019/4/3 - 15:32
     * @Param [token]
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName=(String) token.getPrincipal();
        //获取数据库里保存的用户信息
        Blogger blogger=bloggerService.getByUserName(userName);
        if(blogger!=null){
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger); // 把当前用户信息存到session中
            //生成认证信息
            AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(blogger.getUserName(), blogger.getPassword(), "hxx");
            return authcInfo;
        }else{
            return null;
        }
    }
}
