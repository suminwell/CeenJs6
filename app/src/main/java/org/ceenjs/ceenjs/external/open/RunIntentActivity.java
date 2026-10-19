package org.ceenjs.ceenjs.external.open;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import org.ceenjs.ceenjs.external.ScriptIntents;
import org.ceenjs.ceenjs.model.script.Scripts;
import org.ceenjs.ceenjs.pio.PFiles;
import org.ceenjs.ceenjs.script.StringScriptSource;
import org.ceenjs.ceenjs.util.ViewUtils;
import org.ceenjs.ceenjs6.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Stardust on Feb 22, 2017.
 */
public class RunIntentActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            handleIntent(getIntent());
        } catch (Exception e) {
            e.printStackTrace();
            ViewUtils.showToast(this, R.string.edit_and_run_handle_intent_error, true);
        }
        finish();
    }

    private void handleIntent(Intent intent) throws FileNotFoundException {
        Uri uri = intent.getData();
        if (uri != null && "content".equals(uri.getScheme())) {
            InputStream stream = getContentResolver().openInputStream(uri);
            Scripts.run(this, new StringScriptSource(PFiles.read(stream)));
        } else {
            ScriptIntents.handleIntent(this, intent);
        }
    }

}
