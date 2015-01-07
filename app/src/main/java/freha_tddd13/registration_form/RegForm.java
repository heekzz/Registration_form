package freha_tddd13.registration_form;


import android.content.Context;
import android.text.Layout;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Extending a LinearLayout. Inside this we have a TableLayout where our different fields are in
 * After the TableLayout we add a button for the "submit"
 */
public class RegForm extends LinearLayout {

    Context context;
    public static final int TEXT_FIELD = 0;
    public static final int PASSWORD_FIELD = 1;
    public static final int EMAIL_FIELD = 2;
    public static final int NUMBER_FIELD = 3;

    private List<FieldRow> fields;

    // Map which contains all our data when we submit the form
    private Map<String, String> data;

    // We want to put our fields in a TableLayout
    // Every row is a TableRow we just att to this TableLayout
    private TableLayout tableLayout;

    public RegForm(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public RegForm(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER); // To make the button appear in centered mode
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        /*
         * Here in the TableLayout we put our different fields in the form.
         * The FieldRow class extends a TableRow and are added to this TableLayout
         */
        tableLayout = new TableLayout(context);
        tableLayout.setId(1);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        tableLayout.setColumnStretchable(1, true);

        fields = new ArrayList<>();
        data = new ArrayMap<>();

        TextView info = new TextView(context);
        info.setLayoutParams(params);
        info.setText("Fields marked with a * is compulsory");

        // Adds the components to RegForm which is an LinearLayout
        addView(tableLayout);
        addView(info);
    }

    /**
     * Adds a new field to the form. Specify input type with the constants in the RegForm class
     * @param name String - Name of the form, shows next to the text field
     * @param isCompulsory Boolean - True if a compulsory field. Cannot submit with this field empty
     * @param inputType Int - Input type of the field. Public integers in this class
     */
    public void addTextField(String name, boolean isCompulsory, int inputType) {
        FieldRow row = new FieldRow(context, name, isCompulsory, inputType);
        fields.add(row);
        tableLayout.addView(row);
    }

    /**
     * Saves the data in a HashMap, but only if there are no empty compulsory fields.
     * If there are empty compulsory fields we notify the user by changing the text color
     * of the fields where there are missing input.
     *
     * This function should be called from a button's onClickListener
     */
    public void submit() {
        boolean emptyCompulsoryFields = false;

        // Loop through all our fields in the form to
        // check if there are any empty compulsory fields
        for (int i = 0; i < fields.size(); i++) {
            FieldRow tempRow = fields.get(i);
            if (tempRow.isCompulsory() && tempRow.getInput().length() == 0) {
                tempRow.setRedText();
                emptyCompulsoryFields = true;

                // Also print a toast to notify the user the missing input
                Toast promt = Toast.makeText(context,"The field \"" + tempRow.getName() + "\" is compulsory", Toast.LENGTH_SHORT);
                promt.setGravity(Gravity.CENTER, 0, -100);
                promt.show();
            } else {
                tempRow.restoreTextColor(); // Reset the color to default if it was red before and the field is not empty
                emptyCompulsoryFields = false;
            }
        }

        // If there are no empty compulsory fields we save the inputs from the fields
        if (!emptyCompulsoryFields) {
            // Save data from the text fields into a HashMap
            for (int k = 0; k < fields.size(); k++) {
                String name = fields.get(k).getName();
                String input = fields.get(k).getInput();
                data.put(name, input);
            }
        }
    }

    /**
     * Returns a Map containing all the data from input with name as Key and input as Value.
     * @return Map - Containing data from the input in the form
     */
    public Map<String, String> getData() {
        return data;
    }
}
