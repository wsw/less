package wsw.github.io.less.common.exception;

import wsw.github.io.less.common.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class LessExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error();
    }

    @ExceptionHandler(LessException.class)
    public R handleLessException(LessException e) {
        R r = new R();

        return r.put("code", e.getCode()).put("msg", e.getMsg());
    }

}
