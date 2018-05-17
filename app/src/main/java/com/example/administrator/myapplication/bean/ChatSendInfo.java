package com.example.administrator.myapplication.bean;

/**
 * Created by Administrator on 2018/5/17.
 */

public class ChatSendInfo {


    /**
     * reqType : 0
     * perception : {"inputText":{"text":"附近的酒店"}}
     * userInfo : {"apiKey":"","userId":""}
     */

    private int reqType;
    private PerceptionBean perception;
    private UserInfoBean userInfo;

    public ChatSendInfo(int reqType, PerceptionBean perception, UserInfoBean userInfo) {
        this.reqType = reqType;
        this.perception = perception;
        this.userInfo = userInfo;
    }

    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public PerceptionBean getPerception() {
        return perception;
    }

    public void setPerception(PerceptionBean perception) {
        this.perception = perception;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class PerceptionBean {
        /**
         * inputText : {"text":"附近的酒店"}
         */

        private InputTextBean inputText;

        public InputTextBean getInputText() {
            return inputText;
        }

        public void setInputText(InputTextBean inputText) {
            this.inputText = inputText;
        }

        public static class InputTextBean {
            /**
             * text : 附近的酒店
             */

            private String text;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }

    public static class UserInfoBean {
        /**
         * apiKey :
         * userId :
         */

        private String apiKey;
        private String userId;

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
