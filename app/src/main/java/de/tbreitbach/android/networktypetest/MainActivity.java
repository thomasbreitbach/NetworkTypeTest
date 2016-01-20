package de.tbreitbach.android.networktypetest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String UNKNOWN_STATE = "UNKNOWN";

    private TelephonyManager mTeleManager;

    // UI
    private TextView mSubtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSubtype = (TextView) findViewById(R.id.tv_hello);
        mTeleManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
    }

    @Override
    protected void onStart(){
        super.onStart();
        String type = "";

        if(mTeleManager.getSimState() == TelephonyManager.SIM_STATE_ABSENT || mTeleManager.getSimState() == TelephonyManager.SIM_STATE_UNKNOWN){
            type = UNKNOWN_STATE;
        } else {
            type = getMobileNetworkType(mTeleManager);
        }
        mSubtype.setText(type);
    }

    private String getMobileNetworkType(TelephonyManager manager){
        switch(manager.getNetworkType()){
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return "1xRTT";
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return "CDMA"; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "EDGE"; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return "EVDO_0"; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return "EVDO_A"; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return "GPRS"; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return "HSDPA"; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return "HSPA"; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return "HSUPA"; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return "UMTS"; // ~ 400-7000 kbps

            /*
			 * Above API level 7, make sure to set android:targetSdkVersion
			 * to appropriate level to use these
			 */
            case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
                return "EHRPD"; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                return "EVDO_B"; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                return "HSPAP"; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                return "IDEN"; // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                return "LTE"; // ~ 10+ Mbps
            // Unknown
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
            default:
                return UNKNOWN_STATE;
        }
    }
}
