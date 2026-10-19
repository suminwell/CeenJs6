package org.ceenjs.ceenjs.external.shortcut;

import android.app.Activity;
import android.os.Bundle;

import org.ceenjs.ceenjs.external.ScriptIntents;
import org.ceenjs.ceenjs.model.script.PathChecker;
import org.ceenjs.ceenjs.model.script.ScriptFile;
import org.ceenjs.ceenjs.model.script.Scripts;
import org.ceenjs.ceenjs.util.ViewUtils;

/**
 * Created by Stardust on Jan 23, 2017.
 */
public class ShortcutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String path = getIntent().getStringExtra(ScriptIntents.EXTRA_KEY_PATH);
        if (new PathChecker(this).checkAndToastError(path)) {
            runScriptFile(path);
        }
        finish();
    }

    private void runScriptFile(String path) {
        try {
            Scripts.run(this, new ScriptFile(path));
        } catch (Exception e) {
            e.printStackTrace();
            ViewUtils.showToast(this, e.getMessage(), true);
        }
    }

}
