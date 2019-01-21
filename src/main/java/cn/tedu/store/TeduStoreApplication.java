package cn.tedu.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

@SpringBootApplication
@MapperScan("cn.tedu.store.mapper")
public class TeduStoreApplication {
	
	DataSourceProperties dsp;
	public static void main(String[] args) {
		SpringApplication.run(TeduStoreApplication.class, args);
	}
}
//http://localhost:8080/address/create?province=370000&city=370100&area=370126&name=清

