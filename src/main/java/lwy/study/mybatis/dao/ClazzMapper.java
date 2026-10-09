package lwy.study.mybatis.dao;

import lwy.study.mybatis.pojo.Clazz;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ClazzMapper {

    @Select("select * from class limit  #{pageStart},#{pageSize}")
    List<Clazz> selectByPage(@Param("pageStart") Integer pageStart,
                             @Param("pageSize") Integer pageSize);

    @Select("select * from class")
    List<Clazz> selectAll();

    @Insert("insert into class(cid, cno, cname, tid) " +
            "values ( #{cid}, #{cno}, #{cname}, #{tid})")
    void insert(Clazz clazz);
}
