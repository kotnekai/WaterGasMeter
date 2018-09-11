package com.app.watermeter.model;



/**
 * @author admin
 * https://www.showdoc.cc/web/#/137924192608060?page_id=834063989700506
 */
public class MeterTypeModel {

//    {
//        "data":[
//        {
//            "id":1,
//                "name_zh":"水表",
//                "name_en":"water machine",
//                "name_kh":"xxx",
//                "unit":"m2",
//                "created_at":"2018-09-06 21:49:30",
//                "updated_at":"2018-09-06 21:49:33"
//        },
//        {
//            "id":2,
//                "name_zh":"电表",
//                "name_en":"eletric machine",
//                "name_kh":"xxx",
//                "unit":"kw/h",
//                "created_at":"2018-09-06 21:50:05",
//                "updated_at":"2018-09-06 21:50:07"
//        },
//        {
//            "id":3,
//                "name_zh":"燃气表",
//                "name_en":"ranqi machine",
//                "name_kh":"xxx",
//                "unit":"m2",
//                "created_at":"2018-09-06 21:50:27",
//                "updated_at":"2018-09-06 21:50:30"
//        }
//    ],
//        "err_code":0,
//            "message":"success",
//            "status_code":200


    int id;
    String name_zh;
    String name_en;
    String name_kh;
    String unit;
    String created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_zh() {
        return name_zh;
    }

    public void setName_zh(String name_zh) {
        this.name_zh = name_zh;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName_kh() {
        return name_kh;
    }

    public void setName_kh(String name_kh) {
        this.name_kh = name_kh;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
