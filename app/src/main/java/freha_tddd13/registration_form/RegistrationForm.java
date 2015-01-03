package freha_tddd13.registration_form;

import android.content.Context;
import android.text.InputType;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RegistrationForm extends LinearLayout {
    // HashMap with all our data from the form.
    private Map<String, String> data;
    private Map<String, Boolean> compulsoryMap;
    private List<EditText> editTextList;
    private TableLayout tableLayout;
    private Button button;
    Context context;

    public RegistrationForm(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public RegistrationForm(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        data = new ArrayMap<>();
        compulsoryMap = new HashMap<>();
        editTextList = new ArrayList<>();

        tableLayout = new TableLayout(context);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        tableLayout.setColumnStretchable(1, true);

        button = new Button(context);
        button.setText("Submit");
        button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // When we click on the submit button we want to save the data in our forms
        // to a data structure
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        addView(tableLayout);
        addView(button);
    }

    public void addTextField(String fieldName, boolean isCompulsory) {
        TableRow tableRow = new TableRow(context);
        TextView textView = new TextView(context);
        TextField textField = new TextField(context);

        // If the question is compulsory we mark the question with an asterix
        if(isCompulsory)
            textView.setText(fieldName + "*");
        else
            textView.setText(fieldName);

        textField.setInputType(InputType.TYPE_CLASS_TEXT);
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

        tableRow.addView(textView, 0, params);
        tableRow.addView(textField, 1, params);
        tableLayout.addView(tableRow);
    }

    // Adds a textfield for passwords, shows "*" instead of text.
    public void addPasswordField(){
        TableRow tableRow = new TableRow(context);
        TextView textView = new TextView(context);
        EditText editText = new EditText(context);
        textView.setText("Password");
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

        tableRow.addView(textView, 0, params);
        tableRow.addView(editText, 1, params);
        tableLayout.addView(tableRow);
    }

    private void saveData() {
        for(int i = 0; i < editTextList.size(); i++)  {

        }

    }

    public Map<String, String> getData() {
        return data;
    }

}
