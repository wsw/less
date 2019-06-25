package wsw.github.io.less.common.util;

import lombok.Getter;
import lombok.Setter;

public enum ResultEnum {
    ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    USER_DUPLICATE(1001, "用户名重复了"),
    LACK_OF_USERINFO(1002, "缺少用户名或密码！"),
    LOGIN_WRONG(1003, "账号或密码不正确"),
    USER_INVALID(1004, "账号已被锁定,请联系管理员"),
    CAPTCHA_EXPIRE(1010, "验证码过期了"),
    CAPTCHA_WRONG(1011, "验证码不正确")
    ;

    @Getter
    @Setter
    private Integer code;
    @Getter
    @Setter
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}