package wsw.github.io.less.admin.form;

import lombok.Data;

@Data
public class SysLoginForm {
    private String uuid;
    private String code;
    private String username;
    private String password;
}
