package freha_tddd13.registration_form;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;


public class TextField extends EditText {

    private boolean isCompulsory = false;

    public TextField(Context context) {
        super(context);
    }

    public TextField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCompulsory(boolean isCompulsory) {
        this.isCompulsory = isCompulsory;
    }

    public boolean isCompulsory() {
        return isCompulsory;
    }
}
