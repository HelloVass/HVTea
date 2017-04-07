package info.hellovass.hv_tea.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hellovass on 2017/4/7.
 */

@SuppressWarnings("WeakerAccess") public abstract class SpfHelper {

  private final SharedPreferences mSharedPreferences;

  protected SpfHelper(Context context) {

    mSharedPreferences = context.getSharedPreferences(provideSpfName(), Context.MODE_PRIVATE);
  }

  public final boolean commit() {

    return getEditor().commit();
  }

  public final void apply() {

    getEditor().apply();
  }

  public final void clear() {

    mSharedPreferences.edit().clear().apply();
  }

  public SharedPreferences.Editor putString(String key, String value) {

    SharedPreferences.Editor editor = getEditor();
    editor.putString(key, value);
    return editor;
  }

  public String getString(String key, String defaultValue) {

    return mSharedPreferences.getString(key, defaultValue);
  }

  public SharedPreferences.Editor putInt(String key, int value) {

    SharedPreferences.Editor editor = getEditor();
    editor.putInt(key, value);
    return editor;
  }

  public int getInt(String key, int defaultValue) {

    return mSharedPreferences.getInt(key, defaultValue);
  }

  public SharedPreferences.Editor putBoolean(String key, boolean value) {

    SharedPreferences.Editor editor = getEditor();
    editor.putBoolean(key, value);
    return editor;
  }

  public boolean getBoolean(String key, boolean defaultValue) {

    return mSharedPreferences.getBoolean(key, defaultValue);
  }

  public SharedPreferences.Editor putFloat(String key, float value) {

    SharedPreferences.Editor editor = getEditor();
    editor.putFloat(key, value);
    return editor;
  }

  public float getFloat(String key, float defaultValue) {

    return mSharedPreferences.getFloat(key, defaultValue);
  }

  public SharedPreferences.Editor putLong(String key, long value) {

    SharedPreferences.Editor editor = getEditor();
    editor.putLong(key, value);
    return editor;
  }

  public float getLong(String key, long defaultValue) {

    return mSharedPreferences.getLong(key, defaultValue);
  }

  private SharedPreferences.Editor getEditor() {

    return mSharedPreferences.edit();
  }

  protected abstract String provideSpfName();
}
