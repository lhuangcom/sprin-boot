package com.neo;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



/**
 * Created by asus on 2018/8/3.
 */
public class HelloShiroTest {

    private HelloShiro helloShiro;

    @Before
    public void init(){
        helloShiro =new HelloShiro();
    }


    @Test
    public void testloginLogout() throws Exception {

        new HelloShiro().LoginLogout();

    }

    @Test
    public void testMyRealm(){

        new HelloShiro().myRealm();
    }

    @Test
    public void testJdbcRealm() throws Exception {

       new HelloShiro().testJdbcRealm();
    }

    @Test
    public void testAllSuccessfulStrategyWithSuccess() {
        helloShiro.login("classpath:shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();
        //得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
    }

    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }

    public Subject subject() {
        return SecurityUtils.getSubject();
    }

    @Test
    public void  testIsPermitted() {
        helloShiro.login("classpath:shiro-jdbc-authorizer.ini");
        Assert.assertTrue(subject().isPermitted("user1:update"));
        Assert.assertTrue(subject().isPermitted("user2:update"));
        //通过二进制位的方式表示权限
        Assert.assertTrue(subject().isPermitted("+user1+2"));//新增权限
        Assert.assertTrue(subject().isPermitted("+user1+8"));//查看权限
        Assert.assertTrue(subject().isPermitted("+user2+10"));//新增及查看

        Assert.assertFalse(subject().isPermitted("+user1+4"));//没有删除权限

    }

    @Test
    public void testEncodeHash(){
        helloShiro.hashEncode();
    }

    @Test
    public void testGeneratePassword(){
        String password = helloShiro.GeneratePassword("md5","liu","123");
        System.out.println(password);
    }

    @Test
    public void testHashedCredentialsMatcherWithJdbcRealm() {

        //使用testGeneratePassword生成的散列密码
        helloShiro.HashedCredentialsMatcherWithJdbcRealm("classpath:shiro-jdbc-hashedCredentialsMatcher.ini", "liu", "12");
    }

    @Test()
    public void testRetryLimitHashedCredentialsMatcherWithMyRealm() {
        for(int i = 1; i <= 4; i++) {
            try {
                helloShiro.HashedCredentialsMatcherWithJdbcRealm("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "12");
            } catch (Exception e) {}
        }
        helloShiro.HashedCredentialsMatcherWithJdbcRealm("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "123");
        for(int i = 1; i <= 4; i++) {
            try {
                helloShiro.HashedCredentialsMatcherWithJdbcRealm("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "12");

            } catch (Exception e) {}
        }
    }


}