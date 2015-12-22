package com.odoo.addons.sale;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.odoo.R;
import com.odoo.addons.sale.models.SaleOrder;
import com.odoo.core.orm.ODataRow;
import com.odoo.core.orm.OValues;
import com.odoo.core.support.addons.fragment.BaseFragment;
import com.odoo.core.support.drawer.ODrawerItem;

import java.util.Arrays;
import java.util.List;

import odoo.controls.OField;
import odoo.controls.OForm;

/**
 * Created by dpr-odoo on 22/12/15.
 */
public class SaleOrderDetail extends BaseFragment implements OField.IOnFieldValueChangeListener, View.OnClickListener {
    public static final String TAG = SaleOrderDetail.class.getSimpleName();
    private OForm form;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sale_order_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        form = (OForm) getView().findViewById(R.id.saleOrderForm);
        form.initForm(null);
        form.setEditable(true);

        ODataRow row = new ODataRow();
        row.put("name", "Runtime name");
        row.put("state", "done");
        row.put("languages", "2"); // Here we are using string array from resource
        form.setData(row);

        OField languages = (OField) form.findViewById(R.id.fieldLanguages);
        languages.setOnValueChangeListener(this);

        getView().findViewById(R.id.showValue).setOnClickListener(this);
    }

    @Override
    public List<ODrawerItem> drawerMenus(Context context) {
        return Arrays.asList(new ODrawerItem(TAG).setTitle("OSelection Demo")
                .setInstance(new SaleOrderDetail()));
    }

    @Override
    public Class<SaleOrder> database() {
        return SaleOrder.class;
    }

    @Override
    public void onFieldValueChange(OField field, Object value) {
        if (field.getFieldName().equals("languages")) {
            ODataRow item = (ODataRow) value;
            Toast.makeText(getContext(), "Language Selected: " + item.getString("name"),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        OValues values = form.getValues();
        Toast.makeText(getContext(), values + "", Toast.LENGTH_LONG).show();
    }
}
