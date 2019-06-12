package wsw.github.io.less.common.exception;

import lombok.Data;

@Data
public class LessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public LessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public LessException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public LessException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public LessException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
