package com.lhuang.blog.user.api.typehandler;

import com.lhuang.blog.user.api.pojo.GenderEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author lhunag
 * date 2019/10/3
 */
@MappedJdbcTypes({JdbcType.INTEGER})
@MappedTypes({GenderEnum.class})
public class GenderEnumTypeHandler extends BaseTypeHandler<GenderEnum> {

    /**
     * 通过 传入的T类型写你自己的逻辑，
     * 选择调用 PreparedStatement 对象的某个set方法将数据写入数据库。此方法用来写库。
     * @param preparedStatement
     * @param i
     * @param genderEnum
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, GenderEnum genderEnum, JdbcType jdbcType) throws SQLException {
             if (jdbcType == null){
                 preparedStatement.setInt(i,genderEnum.getValue());
             }
             else {
                 preparedStatement.setObject(i,genderEnum.getValue(),jdbcType.TYPE_CODE);
             }
    }

    /**
     * 通过字段名来读库并转换为T类型
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public GenderEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return getGenderType(resultSet.getInt(s));
    }

    /**
     *  通过字段索引来读库并转换为T类型。
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public GenderEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return getGenderType(resultSet.getInt(i)) ;
    }

    /**
     * 调用存储过程来获取结果并转换为T类型。
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public GenderEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return getGenderType(callableStatement.getInt(i));
    }

    /**
     * 找到值对应的枚举类型返回
     */
    private GenderEnum getGenderType(int value){

        Class<GenderEnum>genderEnumClass = GenderEnum.class;

        return Arrays.stream(genderEnumClass.getEnumConstants())
                .filter(genderEnum -> genderEnum.getValue() == value)
                .findFirst().orElse(GenderEnum.UNKNOWN);


    }
}
