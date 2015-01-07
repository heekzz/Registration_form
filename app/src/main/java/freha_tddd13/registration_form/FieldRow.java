package freha_tddd13.registration_form;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * The component FieldRow is a extended TableRow with two columns.
 * The FieldRow component contains a TextView in the first column(to the left).
 * In the right column we have a EditText where the input data is matched with the name
 * showed in the TextView which also is preferred as the name of the field.
 */
public class FieldRow extends TableRow {

    Context context;
    private boolean isCompulsory;
    private String name;
    private EditText editText;
    private TextView textView;
    private ColorStateList defaultColor;


    /**
     *
     * @param context Context - of application
     * @param name String - name of the field. Printed in a TextView
     * @param isCompulsory boolean - true if the field is compulsory
     * @param fieldType int - type of input for the EditText
     */
    public FieldRow(Context context, String name, final boolean isCompulsory, int fieldType) {
        super(context);
        this.context = context;
        this.isCompulsory = isCompulsory;
        this.name = name;

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);

        editText = new EditText(context);
        textView = new TextView(context);
        editText.setLayoutParams(params);
        defaultColor = textView.getTextColors();

        // If the field is compulsory we add a star in the end of the name
        if(isCompulsory) {
            textView.setText(name + "*");
        }
        else {
            textView.setText(name);
        }

        // Sets the input type of the text field depending on user's choice
        // The integers come from constants in RegForm.java
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


        addView(textView, 0, params); // Add TextView to the first column
        addView(editText, 1, params); // Add EditText to the second column
    }

    /**
     * Returns a boolean that is true if the field is compulsory
     * @return boolean - true if the field is compulsory
     */
    public boolean isCompulsory() {
        return isCompulsory;
    }

    /**
     * Returns the name of the field which is located on the left of the text field
     * @return String - name of the field
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the input typed in the text field.
     * @return String - input from text field.
     */
    public String getInput() {
        return editText.getText().toString();
    }

    /**
     * Marks the text on the left of the text field in the color red.
     * This method should be called when a compulsory field is not
     * filled in before submitting.
     */
    public void setRedText() {
        textView.setTextColor(Color.RED);
    }

    /**
     * Restores the color of the TextView to it's default
     */
    public void restoreTextColor() {
        textView.setTextColor(defaultColor);
    }
}
