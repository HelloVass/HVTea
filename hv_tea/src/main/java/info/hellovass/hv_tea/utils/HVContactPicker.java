package info.hellovass.hv_tea.utils;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hellovass on 16/10/29.
 *
 * 用于获取某个联系人所有手机号码的工具类
 */

public class HVContactPicker implements LoaderManager.LoaderCallbacks<List<String>> {

  private static final String TAG = HVContactPicker.class.getSimpleName();

  private static final int CONTACT_PICKER_LOADER_ID = 23;

  private static final int REQUEST_OPEN_CONTACTS = 11;

  private Context mContext;

  private Uri mUri;

  private Callback mCallback;

  public HVContactPicker(Context context, Callback callback) {
    mContext = context;
    mCallback = callback;
  }

  public void openContacts() {
    ((Activity) mContext).startActivityForResult(createIntent(), REQUEST_OPEN_CONTACTS);
  }

  public void handleActivityResult(int requestCode, int resultCode, Intent data) {

    switch (requestCode) {

      case REQUEST_OPEN_CONTACTS:

        if (resultCode == Activity.RESULT_OK && data != null) {
          parseArgs(data);
          restartLoader();
        } else if (resultCode == Activity.RESULT_CANCELED && mCallback != null) {
          mCallback.onCanceled();
        }
        break;

      default:

        break;
    }
  }

  @Override public Loader<List<String>> onCreateLoader(int loaderId, Bundle bundle) {
    return new PhoneNumLoader(mContext, mUri);
  }

  @Override public void onLoadFinished(Loader<List<String>> loader, List<String> phoneNumList) {

    if (mCallback != null) {
      mCallback.onSucceed(phoneNumList);
    }

    ((Activity) mContext).getLoaderManager().destroyLoader(CONTACT_PICKER_LOADER_ID);
  }

  @Override public void onLoaderReset(Loader<List<String>> loader) {

  }

  public interface Callback {

    void onSucceed(List<String> phoneNumList);

    void onCanceled();
  }

  private Intent createIntent() {
    return new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
  }

  private void parseArgs(Intent data) {
    mUri = data.getData();
  }

  private void restartLoader() {
    ((Activity) mContext).getLoaderManager().restartLoader(CONTACT_PICKER_LOADER_ID, null, this);
  }

  /**
   * 自定义 Loader 类，用于获取某个联系人的所有手机号码
   */
  private static class PhoneNumLoader extends AsyncTaskLoader<List<String>> {

    private Uri mUri;

    private List<String> mPhoneNumList;

    private PhoneNumLoader(Context context, Uri uri) {
      super(context);
      mUri = uri;
    }

    @Override public List<String> loadInBackground() {

      List<String> phoneNumList = new ArrayList<>();

      Cursor contactCursor = getContext().getContentResolver().query(mUri, null, null, null, null);

      if (contactCursor != null) {

        contactCursor.moveToFirst();

        // 得到 contactId
        String contactId =
            contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts._ID));

        // 手机号码的数量
        int phoneCount = contactCursor.getInt(
            contactCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

        if (phoneCount > 0) {
          Cursor phoneCursor = getContext().getContentResolver()
              .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                  ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                  new String[] { contactId }, null);

          if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
              String phoneNum = phoneCursor.getString(
                  phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
              phoneNumList.add(phoneNum);
            }
            phoneCursor.close();
          }
        }

        contactCursor.close();
      }

      return phoneNumList;
    }

    @Override public void deliverResult(List<String> phoneNumList) {

      if (isReset()) {
        releaseResources(phoneNumList);
        return;
      }

      List<String> oldPhoneNumList = mPhoneNumList;
      mPhoneNumList = phoneNumList;

      if (isStarted()) {
        super.deliverResult(phoneNumList);
      }

      if (oldPhoneNumList != null) {
        releaseResources(oldPhoneNumList);
      }
    }

    @Override protected void onStartLoading() {

      if (mPhoneNumList != null) {
        deliverResult(mPhoneNumList);
      }

      if (takeContentChanged() || mPhoneNumList == null) {
        forceLoad();
      }
    }

    @Override public void onCanceled(List<String> phoneNumList) {
      super.onCanceled(phoneNumList);

      releaseResources(phoneNumList);
    }

    @Override protected void onReset() {
      super.onReset();

      onStopLoading();

      if (mPhoneNumList != null) {
        releaseResources(mPhoneNumList);
        mPhoneNumList = null;
      }
    }

    @Override protected void onStopLoading() {
      cancelLoad();
    }

    private void releaseResources(List<String> phoneNumList) {
      // do nothing
    }
  }
}
