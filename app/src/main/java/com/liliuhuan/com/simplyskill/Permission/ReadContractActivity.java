package com.liliuhuan.com.simplyskill.Permission;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liliuhuan.com.simplyskill.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ReadContractActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_contract);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        ArrayList<HashMap<String, String>> hashMaps = readContact();
        if (hashMaps != null) {
            for (HashMap<String, String> map : hashMaps) {
                TextView tv = new TextView(this);
                String name = map.get("name");
                String phone = map.get("phone");
                tv.setText(name + ":" + phone);
                ll.addView(tv);
            }
        }
    }
    private ArrayList<HashMap<String, String>> readContact() {
        // 首先,从raw_contacts中读取联系人的id("contact_id")
        // 其次, 根据contact_id从data表中查询出相应的电话号码和联系人名称
        // 然后,根据mimetype来区分哪个是联系人,哪个是电话号码
        Uri rawContactsUri = Uri
                .parse("content://com.android.contacts/raw_contacts");
        Uri dataUri = Uri.parse("content://com.android.contacts/data");
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        // 从raw_contacts中读取联系人的id("contact_id")
        Cursor rawContactsCursor = getContentResolver().query(rawContactsUri,
                new String[]{"contact_id"}, null, null, null);
        if (rawContactsCursor != null) {
            while (rawContactsCursor.moveToNext()) {
                String contactId = rawContactsCursor.getString(0);
                // System.out.println(contactId);
                // 根据contact_id从data表中查询出相应的电话号码和联系人名称, 实际上查询的是视图view_data
                Cursor dataCursor = getContentResolver().query(dataUri,
                        new String[]{"data1", "mimetype"}, "contact_id=?",
                        new String[]{contactId}, null);
                if (dataCursor != null) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    while (dataCursor.moveToNext()) {
                        String data1 = dataCursor.getString(0);
                        String mimetype = dataCursor.getString(1);
                        // System.out.println(contactId + ";" + data1 + ";"
                        // + mimetype);
                        if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
                            map.put("phone", data1);
                        } else if ("vnd.android.cursor.item/name"
                                .equals(mimetype)) {
                            map.put("name", data1);
                        }
                    }
                    list.add(map);
                    dataCursor.close();
                }
            }
            rawContactsCursor.close();
        }
        return list;

    }
}
