package com.example.demo.Service;

import com.example.demo.pojo.Tsp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Dave
 * @date 2021/10/26 18:13
 */
@Mapper
public interface TspService {
    @Select("select * from formtable_main_428")
    List<Tsp> findlist();

    @Select("select xgjb from formtable_main_430 where id = #{id}")
    String xgjb(int id);

    @Select("select jcxx from formtable_main_429 where id = #{id}")
    String jyxx (int id);

    @Update("update formtable_main_428 set zhyy = #{zhyy} where id = #{id}")
    boolean up(@Param("zhyy") String zhyy, @Param("id") int id);
}
