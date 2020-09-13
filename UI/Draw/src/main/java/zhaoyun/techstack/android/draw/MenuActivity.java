package zhaoyun.techstack.android.draw;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import zhaoyun.techstack.android.draw.bezier.BezierActivity;
import zhaoyun.techstack.android.draw.colorfilter.LightingColorFilterActivity;
import zhaoyun.techstack.android.draw.colorfilter.PorterDuffColorFilterActivity;
import zhaoyun.techstack.android.draw.fontmetrics.FontMetricsActivity;
import zhaoyun.techstack.android.draw.maskfilter.MaskFilterActivity;
import zhaoyun.techstack.android.draw.particle.ParticleEffectActivity;
import zhaoyun.techstack.android.draw.patheffect.PathEffectActivity;
import zhaoyun.techstack.android.draw.shader.ShaderActivity;
import zhaoyun.techstack.android.draw.shader.ShaderExampleActivity;
import zhaoyun.techstack.android.draw.style.StyleActivity;
import zhaoyun.techstack.android.draw.transfermode.PorterDuffTransferModeActivity;

import static zhaoyun.techstack.android.draw.shader.ShaderExampleActivity.INTENT_KEY_INDEX;

public class MenuActivity extends AppCompatActivity {

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void launchStyleActivity(View view) {
        startActivity(new Intent(this, StyleActivity.class));
    }

    public void launchPathEffectActivity(View view) {
        startActivity(new Intent(this, PathEffectActivity.class));
    }

    public void launchShaderActivity(View view) {
        startActivity(new Intent(this, ShaderActivity.class));
    }

    public void launchShaderExampleActivity(View view) {
        int index = Integer.parseInt((String) view.getTag());
        startActivity(new Intent(this, ShaderExampleActivity.class).putExtra(INTENT_KEY_INDEX, index));
    }

    public void launchFontMetricsActivity(View view) {
        startActivity(new Intent(this, FontMetricsActivity.class));
    }

    public void launchBezierActivity(View view) {
        startActivity(new Intent(this, BezierActivity.class));
    }

    public void launchPorterDuffTransferModeActivity(View view) {
        startActivity(new Intent(this, PorterDuffTransferModeActivity.class));
    }

    public void launchPorterDuffColorFilterActivity(View view) {
        startActivity(new Intent(this, PorterDuffColorFilterActivity.class));
    }

    public void launchLightingColorFilterActivity(View view) {
        startActivity(new Intent(this, LightingColorFilterActivity.class));
    }

    public void launchMaskFilterActivity(View view) {
        startActivity(new Intent(this, MaskFilterActivity.class));
    }

    public void launchParticleEffectActivity(View view) {
        startActivity(new Intent(this, ParticleEffectActivity.class));
    }
}
