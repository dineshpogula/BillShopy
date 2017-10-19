package dinesh.gm.com.billshopy.ui;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import dinesh.gm.com.billingapp.MainActivity;
import dinesh.gm.com.billingapp.data.BillingModel;
import dinesh.gm.com.billingapp.data.DBConstants;
import dinesh.gm.com.billingapp.data.SqliteDB;

/**
 * Created by dpogula on 10/18/2017.
 */

public class BillingUI extends FragmentActivity {

    private EditText mEnterItem;
    private EditText mQuantity;
    private EditText mRate;
    private EditText mAmount;
    private TextView mTotalAmount;
    private TextView mTotalQuantity;

    private EditText mCustomerName;
    private EditText mCustomerPhoneNum;
    private EditText mBillNum;
    private TextView Date;

    private TextView mBuyersDetails;
    private TextView mAddress;
    private TextView mSubTitle;
    private TextView mShopTitle;
    private EditText mGSTIN;
    private TextView mContactInfo;


    private Button mBtnLogin;
    private Button mBtnPrint;
    private Button mBtnChangePsw;
    private Button mBtnCalculate;
    private Button mBtnAddNewRecord;

    SqliteDB mSqliteDB;

    private String rowID = null;


    private ArrayList<HashMap<String, String>> mTableData = new ArrayList<HashMap<String, String>>();



    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
        mSqliteDB = new SqliteDB(BillingUI.this);
        getAllUIElements();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            String mFirstName = data.getStringExtra(DBConstants.FIRST_NAME);
            String mLastName = data.getStringExtra(DBConstants.LAST_NAME);

            BillingModel mBillingModel = new BillingModel();

            mBillingModel.setFirstName(mFirstName);
            mBillingModel.setLastName(mLastName);

            if (requestCode == DBConstants.ADD_RECORD){

            }
        }
    }

    private void getAllUIElements() {
        btnAddNewRecord = (Button) findViewById(R.id.btnAddNewRecord);

        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        layoutDisplayPeople = (LinearLayout) findViewById(R.id.a);

        tvNoRecordsFound = (TextView) findViewById(R.id.tvNoRecordsFound);

    }

    private void bindUIWitheEvent () {
        mBtnAddNewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BillingUI.this,BillingLoginUI.class );
                intent.putExtra(DBConstants.DML_TYPE, DBConstants.INSERT);
                startActivityForResult(intent, DBConstants.UPDATE_RECORD);
            }
        });
    }

    private void onUpdateRecord(String firstname, String lastname) {
        Intent intent = new Intent(BillingUI.this, BillingLoginUI.class);
        intent.putExtra(DBConstants.FIRST_NAME, firstname);
        intent.putExtra(DBConstants.LAST_NAME, lastname);
        intent.putExtra(DBConstants.DML_TYPE, Constants.UPDATE);
        startActivityForResult(intent, DBConstants.UPDATE_RECORD);
    }
}

