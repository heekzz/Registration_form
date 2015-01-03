package freha_tddd13.registration_form;


import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class RegForm extends LinearLayout{

    Context context;
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
        setGravity(Gravity.CENTER);

        tableLayout = new TableLayout(context);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        tableLayout.setColumnStretchable(1, true);

        Button button = new Button(context);
        button.setText("Submit");
        button.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        addView(tableLayout);
        addView(button);
    }

    public void addTextField(String name, boolean isCompulsory, int fieldType) {
        FieldRow row = new FieldRow(context, name, isCompulsory, fieldType);
        tableLayout.addView(row);
    }
}
