package com.hyh;

import com.hyh.factory.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 描述:
 *
 * @Author shilei
 * @Date 2018/11/2
 */
public class PoolTest {
    public static void main(String[] args) throws Exception{
        Properties pro = new Properties();
        pro.load(PoolTest.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        final DataSource dataSource = new BasicDataSourceFactory().getDataSource(pro);

        for(int i=0; i<300; ++i){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection conn = dataSource.getConnection();
                        Thread.sleep(100);
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Test
    public void testjdbc() throws Exception{

    }
}
