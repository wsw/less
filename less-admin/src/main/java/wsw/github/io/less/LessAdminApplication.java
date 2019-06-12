package wsw.github.io.less;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"wsw.github.io"})
//@MapperScan("wsw.github.io.less.dao.mapper")
public class LessAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(LessAdminApplication.class, args);
	}

}
