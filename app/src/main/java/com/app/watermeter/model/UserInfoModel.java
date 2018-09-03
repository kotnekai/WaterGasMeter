package com.app.watermeter.model;

/**
 * Create by Admin on 2018/9/3
 */
public class UserInfoModel {
    private int id;
    private String nickname;// 昵称
    private String contact;// 联系方式
    private String email;// 邮箱
    private String province;// 省
    private String city;// 市
    private String area;// 区
    private String street;// 具体位置
    private String real_name;// 真实姓名
    private String created_at;// 创建时间
    private String updated_at;// 最近发生操作的时间
    private String final_recharged_at;// 最后充值时间
    private int status;//状态(1正常-0禁用)
    private CertificationModel certification;

    class CertificationModel {
        private String real_name;//真实姓名
        private String id_number;//身份证号
        private String created_at;//认证时间

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getFinal_recharged_at() {
        return final_recharged_at;
    }

    public void setFinal_recharged_at(String final_recharged_at) {
        this.final_recharged_at = final_recharged_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CertificationModel getCertification() {
        return certification;
    }

    public void setCertification(CertificationModel certification) {
        this.certification = certification;
    }
}
