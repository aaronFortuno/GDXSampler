package com.mygdx.game.common;

import com.mygdx.game.ActionsSample;
import com.mygdx.game.ApplicationListenerSample;
import com.mygdx.game.AshleyEngineSample;
import com.mygdx.game.AshleySystemSample;
import com.mygdx.game.AssetManagerSample;
import com.mygdx.game.BitmapFontSample;
import com.mygdx.game.CustomActorSample;
import com.mygdx.game.GDXModuleInfoSample;
import com.mygdx.game.GDXReflectionSample;
import com.mygdx.game.GDXSamplerGame;
import com.mygdx.game.InputListeningSample;
import com.mygdx.game.InputPollingSample;
import com.mygdx.game.OrthographicCameraSample;
import com.mygdx.game.PoolingSample;
import com.mygdx.game.ShapeRendererSample;
import com.mygdx.game.SkinSample;
import com.mygdx.game.SpriteBatchSample;
import com.mygdx.game.TableSample;
import com.mygdx.game.TextureAtlasSample;
import com.mygdx.game.ViewportSample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SampleInfos {

    // IMPORTANT - TO GET SAMPLES INTO APPLET:
    // add public static final SampleInfo SAMPLE_INFO = new SampleInfo([CLASS_NAME].class);
    // and call here by [CLASS_NAME].SAMPLE_INFO
    public static final List<SampleInfo> ALL = Arrays.asList(
            ApplicationListenerSample.SAMPLE_INFO,
            GDXModuleInfoSample.SAMPLE_INFO,
            GDXReflectionSample.SAMPLE_INFO,
            GDXSamplerGame.SAMPLE_INFO,
            InputListeningSample.SAMPLE_INFO,
            InputPollingSample.SAMPLE_INFO,
            OrthographicCameraSample.SAMPLE_INFO,
            ViewportSample.SAMPLE_INFO,
            SpriteBatchSample.SAMPLE_INFO,
            ShapeRendererSample.SAMPLE_INFO,
            PoolingSample.SAMPLE_INFO,
            ShapeRendererSample.SAMPLE_INFO,
            BitmapFontSample.SAMPLE_INFO,
            AssetManagerSample.SAMPLE_INFO,
            TextureAtlasSample.SAMPLE_INFO,
            CustomActorSample.SAMPLE_INFO,
            ActionsSample.SAMPLE_INFO,
            TableSample.SAMPLE_INFO,
            SkinSample.SAMPLE_INFO,
            AshleyEngineSample.SAMPLE_INFO,
            AshleySystemSample.SAMPLE_INFO
    );

    public static List<String> getSampleNames() {
        List<String> ret = new ArrayList<>();

        for (SampleInfo info : ALL) {
            ret.add(info.getName());
        }

        Collections.sort(ret);
        return ret;
    }

    public static SampleInfo find(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name argument is required");
        }

        SampleInfo ret = null;

        for (SampleInfo info : ALL) {
            if (info.getName().equals(name)) {
                ret = info;
                break;
            }
        }

        if (ret == null) {
            throw new IllegalArgumentException("Could not find sample with name: " + name);
        }

        return ret;
    }

    private SampleInfos() {}
}
