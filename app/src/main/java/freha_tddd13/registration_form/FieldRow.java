package freha_tddd13.registration_form;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

public class FieldRow extends TableRow {

    Context context;
    private boolean isCompulsory;
    private String name;
    private EditText editText;
    private TextView textView;

    public static final int TEXT_FIELD = 0;
    public static final int PASSWORD_FIELD = 1;
    public static final int EMAIL_FIELD = 2;
    public static final int NUMBER_FIELD = 3;

    public FieldRow(Context context, String name, boolean isCompulsory, int fieldType) {
        super(context);
        this.context = context;
        this.isCompulsory = isCompulsory;
        this.name = name;

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);

        editText = new EditText(context);
        textView = new TextView(context);
        editText.setLayoutParams(params);
        textView.setText(name);

        switch (fieldType) {
            case 0:
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case 1:
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
            case 2:
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case 3:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            default:
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
        }


        addView(textView, 0, params);
        addView(editText, 1, params);
    }

    public boolean isCompulsory() {
        return isCompulsory;
    }
    public String getName() {
        return name;
    }

    public void makePasswordField() {

    }

}
