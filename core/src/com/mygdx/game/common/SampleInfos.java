package com.mygdx.game.common;

import com.mygdx.game.ApplicationListenerSample;
import com.mygdx.game.GDXModuleInfoSample;
import com.mygdx.game.GDXSamplerGame;
import com.mygdx.game.GdxReflectionSample;
import com.mygdx.game.InputListeningSample;
import com.mygdx.game.InputPollingSample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SampleInfos {

    public static final List<SampleInfo> ALL = Arrays.asList(
            ApplicationListenerSample.SAMPLE_INFO,
            GDXModuleInfoSample.SAMPLE_INFO,
            GdxReflectionSample.SAMPLE_INFO,
            GDXSamplerGame.SAMPLE_INFO,
            InputListeningSample.SAMPLE_INFO,
            InputPollingSample.SAMPLE_INFO
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
