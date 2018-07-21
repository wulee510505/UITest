package com.example.wulee.uitest;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvPhoneInfo;
    private Button btnSend;
    private String filePath = Environment.getExternalStorageDirectory() .getAbsolutePath() + File.separator;
    private String fileName = "phone_info.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    private void initView() {
        tvPhoneInfo = findViewById(R.id.tv_phone_info);
        btnSend = findViewById(R.id.btn_send);

        btnSend.setOnClickListener(this);
    }

    private void initData() {
        StringBuilder sbPhoneInfo = new StringBuilder();
        sbPhoneInfo.append("手机型号：").append(PhoneUtil.getDeviceBrand()).append(" ").append(PhoneUtil.getSystemModel()).append("\n")
                .append("屏幕尺寸：").append(UIUtils.getScreenInch(this)).append("\n")
                .append("状态栏的高度：").append(UIUtils.getStatusHeight()).append("\n")
                .append("标题栏高度：").append(UIUtils.getTitleHeight(this)).append("\n")
                .append("屏幕原始尺寸高度，包括虚拟功能键高度：").append(UIUtils.getDpi()).append("\n")
                .append("虚拟功能键高度：").append((UIUtils.getDpi() - UIUtils.getScreenHeight())).append("\n")
                .append("屏幕宽度：").append(UIUtils.getScreenWidth()).append("\n")
                .append("屏幕密度：").append(new DensityUtil(this).getDmDensityDpi() + "  " + DensityUtil.getXydpi(this));
        tvPhoneInfo.setText(sbPhoneInfo.toString());
    }

    @Override
    public void onClick(View v) {
        File file = new File(filePath+fileName);
        if(file.exists()){
            file.delete();
        }
        FileUtil.writeTxtToFile(tvPhoneInfo.getText().toString(), filePath, fileName);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "phone_info.txt";
        MailManager.getInstance().sendMailWithFile("屏幕参数", "", path);
    }

}
