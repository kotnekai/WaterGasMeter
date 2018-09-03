package com.app.watermeter.model;



/**
 * @author admin
 * https://www.showdoc.cc/web/#/137924192608060?page_id=834063989700506
 */
public class MeterTypeModel {

//
//    {
//        "data": [
//        {
//            "id": 1,
//                "name": "水表",
//                "unit": "方",
//                "created_at": "2018-09-02 21:45:18"
//        },
//        {
//            "id": 2,
//                "name": "电表",
//                "unit": "度",
//                "created_at": "2018-09-02 21:45:37"
//        },
//        {
//            "id": 3,
//                "name": "燃气表",
//                "unit": "方",
//                "created_at": "2018-09-02 21:45:50"
//        }
//  ]
//    }


    int id;
    String name;
    String unit;
    String created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
