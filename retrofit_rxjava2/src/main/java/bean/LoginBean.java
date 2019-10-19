package bean;

import org.greenrobot.greendao.annotation.Entity;

/**
 *@describe(描述)：LoginBean
 *@data（日期）: 2019/10/11
 *@time（时间）: 15:32
 *@author（作者）: fanyanlong
 **/


@Entity
public class LoginBean {

    /**
     * result : {"headPic":"http://172.17.8.100/images/small/default/user.jpg","nickName":"Ks_p7p72","phone":"18611352766","sessionId":"157077903820110218","sex":1,"userId":10218}
     * message : 登录成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * headPic : http://172.17.8.100/images/small/default/user.jpg
         * nickName : Ks_p7p72
         * phone : 18611352766
         * sessionId : 157077903820110218
         * sex : 1
         * userId : 10218
         */

        private String headPic;
        private String nickName;
        private String phone;
        private String sessionId;
        private String sex;
        private String userId;

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
