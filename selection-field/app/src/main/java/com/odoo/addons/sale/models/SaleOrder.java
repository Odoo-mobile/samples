package com.odoo.addons.sale.models;

import android.content.Context;

import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

/**
 * Created by dpr-odoo on 22/12/15.
 */
public class SaleOrder extends OModel {

    OColumn name = new OColumn("Name", OVarchar.class);

    OColumn state = new OColumn("State", OSelection.class)
            .addSelection("draft", "Draft Quotation")
            .addSelection("sent", "Quotation sent")
            .addSelection("cancel", "Cancelled")
            .addSelection("waiting_date", "Waiting schedule")
            .addSelection("progress", "Sales order")
            .addSelection("manual", "Sale to invoice")
            .addSelection("shipping_except", "Shipping Exception")
            .addSelection("invoice_except", "Invoice Exception")
            .addSelection("done", "Done");


    OColumn languages = new OColumn("Languages", OSelection.class)
            .setLocalColumn()
            .addSelection("en", "English")
            .addSelection("hi", "Hindi")
            .addSelection("fr", "Franch");

    public SaleOrder(Context context, OUser user) {
        super(context, "sale.order", user);
    }
}
