package stackoverflow.project.policyretrieval.util;

public class ResponseUtil<E> {

    private int code;
    private String msg;
    private E data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public static <E> ResponseUtil<E> fail() {
        ResponseUtil<E> ret = new ResponseUtil<E>();
        ret.setCode(500);
        ret.setMsg("内部错误");
        return ret;
    }

    public static <E>  ResponseUtil<E> fail(E data) {
        ResponseUtil<E> ret = ResponseUtil.fail();
        ret.setData(data);
        return ret;
    }

    public static <E>  ResponseUtil<E> failMessage(String msg) {
        ResponseUtil<E> ret = ResponseUtil.fail();
        ret.setMsg(msg);
        return ret;
    }
    public static <E> ResponseUtil<E> success() {
        ResponseUtil<E> ret = new ResponseUtil<E>();
        ret.setCode(200);
        ret.setMsg("成功");
        return ret;
    }
    public static <E> ResponseUtil<E> success(E data) {
        ResponseUtil<E> ret = ResponseUtil.success();
        ret.setData(data);
        return ret;
    }
    public static <E>  ResponseUtil<E> successMessage(String msg) {
        ResponseUtil<E> ret = ResponseUtil.success();
        ret.setMsg(msg);
        return ret;
    }
}
