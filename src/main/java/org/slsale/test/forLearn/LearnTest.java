package org.slsale.test.forLearn;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slsale.test.forLearn.AddSecurityPermissionModel;
import org.slsale.test.forLearn.Result;
import org.slsale.test.forLearn.SecurityPermission;
import org.slsale.test.forLearn.SecurityPermissionI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/*import com.cgodo.mvc.service.Result;
import com.security.entity.SecurityPermission;
import com.security.model.AddSecurityPermissionModel;
import com.security.model.UpdateSecurityPermissionModel;
import com.security.service.SecurityPermissionI;*/

/**
 * 权限服务层测试类
 *
 * @author 郁永 yuyongwork@126.com
 * @version 1.0
 * @since 2011-11-9 下午06:45:09
 */
/*测试时需要的*/
@RunWith(value = SpringJUnit4ClassRunner.class)
/*在使用所有注释前必须使用@RunWith(SpringJUnit4ClassRunner.class),
让测试运行于Spring测试环境*/
@ContextConfiguration(locations = {"classpath:applicationContext-mybatis.xml"}, loader = GenericXmlContextLoader.class)
    /*@ContextConfiguration 用来指定加载的Spring配置文件的位置,会加载默认配置文件,
    @ContextConfiguration注解有以下两个常用的属性：locations：可以通过该属性手工指定
    Spring 配置文件所在的位置，可以指定一个或多个 Spring 配置文件用，分开。如下所示：
    @ContextConfiguration(locations={“aa/aa.xml”,” aa/bb.xml”})
    inheritLocations：是否要继承父测试用例类中的 Spring 配置文件，默认为 true。*/
//@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
    /*@DirtiesContext 在测试方法上出现这个注解时，表明底层Spring容器在该方法的执行中被“污染”，
     从而必须在方法执行结束后重新创建（无论该测试是否通过）。*/
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
    /*【@TransactionConfiguration 为配置事务性测试定义了类级别的元数据。
     PlatformTransactionManager默认的实例叫transactionManager，
     如果需要的PlatformTransactionManager不是“transactionManager”的话，
     那么可以显式配置驱动事务的PlatformTransactionManager的bean的名字。此外，
     可以将defaultRollback标志改为false，表示不回滚。通常，
     @TransactionConfiguration与@ContextConfiguration搭配使用。*/
@Transactional
/*开启类级别的事物*/
public class LearnTest {

    //注解生成SecurityPermissionI 的代理
    @Autowired
    SecurityPermissionI securityPermissionI;

    /**
     * 描述：初始化测试数据
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Before
    public void before() throws IllegalAccessException, InvocationTargetException {
        //添加删除测试数据
        SecurityPermission securityPermissionDelete = new SecurityPermission();
        securityPermissionDelete.setPermissionId("TEST_permissionId-Delete");
        securityPermissionDelete.setPermissionDescription("TEST_权限描述信息-Delete");
        securityPermissionDelete.setPermissionType("TEST_权限类型-Delete");
        Result<List<SecurityPermission>> resultDelete = securityPermissionI.getPermissionsByType(securityPermissionDelete);

        AddSecurityPermissionModel addSecurityPermissionModelDelete = new AddSecurityPermissionModel();
        BeanUtils.copyProperties(addSecurityPermissionModelDelete, securityPermissionDelete);
        if (resultDelete.getData() == null) {
            securityPermissionI.addPermission(addSecurityPermissionModelDelete);
        }

        //添加删除测试数据
        SecurityPermission securityPermissionDeleteNot = new SecurityPermission();
        securityPermissionDeleteNot.setPermissionId("TEST_permissionId-DeleteNot");
        securityPermissionDeleteNot.setPermissionDescription("TEST_权限描述信息-DeleteNot");
        securityPermissionDeleteNot.setPermissionType("TEST_权限类型-DeleteNot");
        Result<List<SecurityPermission>> resultDeleteNot = securityPermissionI.getPermissionsByType(securityPermissionDeleteNot);

        AddSecurityPermissionModel addSecurityPermissionModelDeleteNot = new AddSecurityPermissionModel();
        BeanUtils.copyProperties(addSecurityPermissionModelDeleteNot, securityPermissionDeleteNot);
        if (resultDeleteNot.getData() == null) {
            securityPermissionI.addPermission(addSecurityPermissionModelDeleteNot);
        }


        //添加更新测试数据
        SecurityPermission securityPermissionUpdate = new SecurityPermission();
        securityPermissionUpdate.setPermissionId("TEST_permissionId-Update");
        securityPermissionUpdate.setPermissionDescription("TEST_权限描述信息-Update");
        securityPermissionUpdate.setPermissionType("TEST_权限类型-Update");
        Result<List<SecurityPermission>> resultUpdate = securityPermissionI.getPermissionsByType(securityPermissionUpdate);

        AddSecurityPermissionModel addSecurityPermissionModelUpdate = new AddSecurityPermissionModel();
        BeanUtils.copyProperties(addSecurityPermissionModelUpdate, securityPermissionUpdate);
        if (resultUpdate.getData() == null) {
            securityPermissionI.addPermission(addSecurityPermissionModelUpdate);
        }

        //添加查询测试数据
        SecurityPermission securityPermissionSelect = new SecurityPermission();
        securityPermissionSelect.setPermissionId("TEST_permissionId-Select");
        securityPermissionSelect.setPermissionDescription("TEST_权限描述信息-Select");
        securityPermissionSelect.setPermissionType("TEST_权限类型-Select");
        Result<List<SecurityPermission>> resultSelect = securityPermissionI.getPermissionsByType(securityPermissionSelect);

        AddSecurityPermissionModel addSecurityPermissionModelSelect = new AddSecurityPermissionModel();
        BeanUtils.copyProperties(addSecurityPermissionModelSelect, securityPermissionSelect);
        if (resultSelect.getData() == null) {
            securityPermissionI.addPermission(addSecurityPermissionModelSelect);
        }

    }

    //@Test 这个将不会被测试
    public void Test() {
        boolean bb = false;
        Assert.assertFalse(bb);
    }


    @Rollback(true)//设置测试后回滚
    @Transactional//为方法开启事物
    @Test //标注要测试的方法，不添加@Test将不会进行测试
    public void TestAddPermission() {
        SecurityPermission securityPermission = new SecurityPermission();
        securityPermission.setPermissionId("TEST_permissionId-Add");
        securityPermission.setPermissionDescription("TEST_权限描述信息-Add");
        securityPermission.setPermissionType("TEST_权限类型-Add");

        AddSecurityPermissionModel model = new AddSecurityPermissionModel();
        try {
            BeanUtils.copyProperties(model, securityPermission);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //测试权限不存在的情况
        Result isNotExists;
        try {
            isNotExists = securityPermissionI.addPermission(model);
            Assert.assertTrue(isNotExists.isSuccess());
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //测试权限已存在的情况
        Result isExists;
        try {
            isExists = securityPermissionI.addPermission(model);
            Assert.assertFalse(isExists.isSuccess());
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

