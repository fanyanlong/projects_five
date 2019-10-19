package bean;

/**
 *@describe(描述)：RegisterBean
 *@data（日期）: 2019/10/11
 *@time（时间）: 15:32
 *@author（作者）: fanyanlong
 **/


public class RegisterBean {
    /**
     * message : 注册成功
     * status : 0000
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
