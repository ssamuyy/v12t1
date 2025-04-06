package com.main.v12t1;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.main.v12t1", appContext.getPackageName());
    }

    @Test
    public void testResourceIdAccess() throws NoSuchFieldException {
        // Tarkistaa, että R.id arvo voidaan noutaa dynaamisesti
        Field field = R.id.class.getDeclaredField("ShowMonsterFragmentButton");
        assertNotNull(field);
    }
}