package wsw.github.io.less.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysUserController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }
}
